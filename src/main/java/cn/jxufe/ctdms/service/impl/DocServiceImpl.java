package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.UploadTask;
import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.dao.UploadTaskDao;
import cn.jxufe.ctdms.enums.DocStateEnum;
import cn.jxufe.ctdms.enums.DocTypeEnum;
import cn.jxufe.ctdms.service.CourseService;
import cn.jxufe.ctdms.service.DocService;
import cn.jxufe.ctdms.service.UserService;
import cn.jxufe.ctdms.util.excel.ExcelParse;
import cn.jxufe.ctdms.util.excel.MyExcelCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocServiceImpl implements DocService{

    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    UploadTaskDao uploadTaskDao;
    @Override
    public void cp(MultipartFile file) throws IOException {
        //解析excels
        List<MyExcelCourse> excels = ExcelParse.parse(file.getBytes(),file.getOriginalFilename(), MyExcelCourse.class);
        //检查当前学期是否已上传，已上传则清空之前课程并覆盖更新。
        if(checkTermHasCp()){
            deleteNowCp();
        }
        //List<Course> saveCourses = new ArrayList<>(); // 准备保存的课程
        List<User> dbUsers = userService.findAll();//检查是否已存在该教师用户
        List<User> saveUsers = new ArrayList<>(20); // 准备保存的用户

        List<UploadTask>tasks = new ArrayList<>();
        for (MyExcelCourse e : excels) {
            //新建老师
            long uId = createTeacher(e,dbUsers,saveUsers);
            //导入课程
            long cId = importCourse(e);
            //设置上传任务
            setUploadTask(e,uId,cId,tasks);
        }
        //保存任务至数据库
        saveUploadTask(tasks);
        //引用置空 ，让虚拟机gc回收。
        excels = null;
    }

    private void saveUploadTask(List<UploadTask> tasks) {
        uploadTaskDao.saveTasks(tasks);
    }


    private long createTeacher(MyExcelCourse e, List<User> dbUsers, List<User> saveUsers) {
        String teacherName = e.getCourse().getTeacherName();
        User user = getEobj(teacherName,User.class,saveUsers,dbUsers);
        if(user == null){
            User n = new User(teacherName,"123");
            saveUsers.add(n);
            userService.register(n);
            user = n;
        }
        return user.getUId();
    }

    private long importCourse(MyExcelCourse e) {
        return courseService.save(e.getCourse());
    }
    private void setUploadTask(MyExcelCourse e, long uId, long cId, List<UploadTask> tasks) {
        UploadTask task = new UploadTask(uId,cId);
        task.setClassCode(e.getCourse().getClassCode());
        task.setState(DocStateEnum.WAIT.getState());
        task.setTimes(0);
        boolean typeChose = false;
        for(UploadTask u : tasks){
            if(task.getClassCode().equals(u.getClassCode()))
                typeChose = true;
        }
        if(typeChose)
            task.setType(DocTypeEnum.TEACH.getTypeId());
        else
            task.setType(DocTypeEnum.SYLLABUS.getTypeId());
    }
    private boolean checkTermHasCp() {
        return true;
    }

    private void deleteNowCp() {
    }

    @SuppressWarnings("unchecked")
    private <T> T getEobj(String s,Class<T>c, List<T>... objs) {
        for (List<T> list : objs) {
            for (T o : list) {
                if(o.equals(s)){
                    return o ;
                }
            }
        }
        return null;
    }



}
