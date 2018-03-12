package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.UploadTask;
import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.dao.UploadTaskDao;
import cn.jxufe.ctdms.enums.DocStateEnum;
import cn.jxufe.ctdms.enums.DocTypeEnum;
import cn.jxufe.ctdms.service.CourseService;
import cn.jxufe.ctdms.service.DocService;
import cn.jxufe.ctdms.service.UserService;
import cn.jxufe.ctdms.util.RunTimeHelp;
import cn.jxufe.ctdms.util.SnowflakeIdWorkerSingleton;
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
        List<Course> saveCourses = new ArrayList<>(); // 准备保存的课程
        List<User> dbUsers = userService.findAll();//检查是否已存在该教师用户
        List<User> saveUsers = new ArrayList<>(20); // 准备保存的用户

        List<UploadTask>tasks = new ArrayList<>();
        RunTimeHelp rt = new RunTimeHelp();
        rt.start();
        for (MyExcelCourse e : excels) {
            //新建老师
            long uId = createTeacher( e.getCourse().getTeacherName(),dbUsers,saveUsers);
            //导入课程
            long cId = importCourse(e,saveCourses);
            //设置上传任务
            setUploadTask(e,uId,cId,tasks);
        }
        rt.end("解析excelBean");
        rt.start();
        if(!saveUsers.isEmpty())
            userService.registerTeachers(saveUsers);
        rt.end("insert user");
        rt.start();
        if(!saveCourses.isEmpty())
            courseService.saveCourses(saveCourses);
        rt.end("课程导入");
        rt.start();
        if(!tasks.isEmpty())
            uploadTaskDao.saves(tasks);
        rt.end("task 导入");
        //保存任务至数据库
        //引用置空 ，让虚拟机gc回收。
        excels = null;
    }



    private long createTeacher(String teacherName, List<User> dbUsers, List<User> saveUsers) {

        User user = getEobj(teacherName,User.class,saveUsers,dbUsers);
        if(user == null){
            User n = new User(teacherName,"123");
            long uId = SnowflakeIdWorkerSingleton.getInstance().nextId();
            n.setUId(uId);
            saveUsers.add(n);
            user = n;
        }
        return user.getUId();
    }



    private long importCourse(MyExcelCourse e, List<Course> saveCourses) {
        //courseService.save(e.getCourse());
        long cId = SnowflakeIdWorkerSingleton.getInstance().nextId();
        e.getCourse().setcId(cId);
        saveCourses.add(e.getCourse());
        return cId;
    }

    private void setUploadTask(MyExcelCourse e, long uId,long cId, List<UploadTask> tasks) {
        UploadTask task = initTask(uId,cId,e.getCourse().getClassCode(),DocTypeEnum.TEACH.getTypeId());
        boolean typeChose = false;
        for(UploadTask u : tasks){
            if(task.getClassCode().equals(u.getClassCode()))
                typeChose = true;
        }
        if(!typeChose) {
            UploadTask s = initTask(uId,cId,e.getCourse().getClassCode(),DocTypeEnum.SYLLABUS.getTypeId());
            tasks.add(s);
        }
        //uploadTaskDao.save(task);
        long taskId = task.getTaskId();
        e.getCourseTimes().forEach(ct->{ct.setTaskId(taskId);});
        tasks.add(task);
        courseService.saveCourseTimes(e.getCourseTimes());
    }
    private UploadTask initTask(long uId, long cId,String classCode,int type){
        UploadTask task = new UploadTask(uId,cId);
        task.setClassCode(classCode);
        task.setState(DocStateEnum.WAIT.getState());
        task.setTimes(0);
        task.setTaskId( type );
        Long taskId = SnowflakeIdWorkerSingleton.getInstance().nextId();
        task.setTaskId(taskId);
        return task;
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
