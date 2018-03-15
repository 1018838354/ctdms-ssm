package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.*;
import cn.jxufe.ctdms.dao.DocDao;
import cn.jxufe.ctdms.dao.UploadTaskDao;
import cn.jxufe.ctdms.enums.DocStateEnum;
import cn.jxufe.ctdms.enums.DocTypeEnum;
import cn.jxufe.ctdms.service.CourseService;
import cn.jxufe.ctdms.service.DocService;
import cn.jxufe.ctdms.service.UserService;
import cn.jxufe.ctdms.util.RunTimeHelp;
import cn.jxufe.ctdms.util.IDGenerator;
import cn.jxufe.ctdms.util.excel.ExcelParse;
import cn.jxufe.ctdms.util.excel.MyExcelCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class DocServiceImpl implements DocService{

    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    UploadTaskDao uploadTaskDao;
    @Autowired
    DocDao docDao;


    @Override
    public int getFileCount() {
        return docDao.getFileCount();
    }




    @Override
    public void cp(MultipartFile file) throws IOException {
        RunTimeHelp rt = new RunTimeHelp();//检查函数运行时间
        rt.start();
        //解析excels
        List<MyExcelCourse> excels = ExcelParse.parse(file.getBytes(),file.getOriginalFilename(), MyExcelCourse.class);
        //检查当前学期是否已上传，已上传则清空之前课程并覆盖更新。
        if(checkTermHasCp()){
            deleteNowCp();
        }
        List<Course> saveCourses = new ArrayList<>(30); // 准备保存的课程
        List<CourseInfo> saveCourseInfos = new ArrayList<>(20);
        List<User> dbUsers = userService.findAll();//检查是否已存在该教师用户
        List<User> saveUsers = new ArrayList<>(20); // 准备保存的用户

        List<UploadTask> tasks = new ArrayList<>();

        List<CourseTime> cts = new ArrayList<>();
        rt.end("解析excel ");
        rt.start();
        for (MyExcelCourse e : excels) {
            //新建老师
            long uId = createTeacher( e.getCourse().getTeacherName(),dbUsers,saveUsers);
            //导入课程
            long cId = importCourse(e.getCourse(),uId,saveCourses,saveCourseInfos);
            //设置课程表信息
            importCourseTime(cId,e.getCourseTimes(),cts);
            //设置上传任务
           // setUploadTask(e,uId,cId,tasks);
        }
        rt.end("解析excel bean");
        rt.start();
        if(!saveUsers.isEmpty())
            userService.registerTeachers(saveUsers);
        rt.end("新建用户-"+saveUsers.size());
        rt.start();
        if(!saveCourses.isEmpty())
            courseService.saveCourses(saveCourses);
        rt.end("课程导入-"+saveCourses.size());

        rt.start();
        if(!cts.isEmpty())
            courseService.saveCourseTimes(cts);
        rt.end("course_time 导入-"+cts.size());

        rt.start();
        if(!cts.isEmpty())
            courseService.saveCourseInfos(saveCourseInfos);
        rt.end("course_Info 导入-"+saveCourseInfos.size());

        rt.start();
        if(!tasks.isEmpty())
            uploadTaskDao.saves(tasks);
        rt.end("task 导入-"+tasks.size());

        //保存任务至数据库
        //引用置空 ，让虚拟机gc回收。
        excels = null;
    }

    private void importCourseTime(long cId, List<CourseTime> courseTimes, List<CourseTime> cts) {
        courseTimes.forEach(ct->{ct.setcId(cId);});
        cts.addAll(courseTimes);
    }


    private long createTeacher(String teacherName, List<User> dbUsers, List<User> saveUsers) {

        User user = getEobj(teacherName,User.class,saveUsers,dbUsers);
        if(user == null){
            User n = new User(teacherName,"123");
            long uId = IDGenerator.nextId();
            n.setUId(uId);
            saveUsers.add(n);
            user = n;
        }
        return user.getUId();
    }



    private long importCourse(Course c, long uId, List<Course> saveCourses, List<CourseInfo> saveCourseInfos) {
        CourseInfo ci = null;
        boolean notFind = true;
        for (CourseInfo courseInfo: saveCourseInfos){
            if(courseInfo.getcCode().equals(c.getcCode())){
                notFind = false;
                ci = courseInfo;
            }
        }
        if(notFind){
            ci = new CourseInfo(c);
            ci.setCiId(IDGenerator.nextId());
            saveCourseInfos.add(ci);
        }

        long cId = IDGenerator.nextId();
        c.setCiId(ci.getCiId());
        c.setuId(uId);
        c.setcId(cId);
        c.setRecordId(0);
        c.setState(DocStateEnum.WAIT.getState());

        saveCourses.add(c);
        return cId;
    }
    @Deprecated
    private void setUploadTask(MyExcelCourse e, long uId,long cId, List<UploadTask> tasks) {
        UploadTask task = initTask(uId,cId,e.getCourse().getClassCode(),DocTypeEnum.TEACH.getTypeId());

        //UploadTask s = initTask(uId,cId,e.getCourse().getClassCode(),DocTypeEnum.SYLLABUS.getTypeId());

        //uploadTaskDao.save(task);
        long taskId = task.getTaskId();
        //e.getCourseTimes().forEach(ct->{ct.setTaskId(taskId);});
        tasks.add(task);
        courseService.saveCourseTimes(e.getCourseTimes());
    }
    private UploadTask initTask(long uId, long cId,String classCode,int type){
        UploadTask task = new UploadTask(uId,cId);
        task.setClassCode(classCode);
        task.setState(DocStateEnum.WAIT.getState());
        task.setTaskId( type );
        Long taskId = IDGenerator.nextId();
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
