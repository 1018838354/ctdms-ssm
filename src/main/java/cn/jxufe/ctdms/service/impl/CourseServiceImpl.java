package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseInfo;
import cn.jxufe.ctdms.bean.CourseTime;
import cn.jxufe.ctdms.dao.CourseDao;
import cn.jxufe.ctdms.dto.CourseDto;
import cn.jxufe.ctdms.dto.UploadTaskDto;
import cn.jxufe.ctdms.enums.DocTypeEnum;
import cn.jxufe.ctdms.exception.PermissionDeniedException;
import cn.jxufe.ctdms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseDao courseDao;

    @Override
    public Long save(Course course) {
        return null;
    }

    @Override
    public void saveCourseTimes(List<CourseTime> courseTimes) {
        courseDao.saveCourseTimes(courseTimes);
    }

    @Override
    public void saveCourses(List<Course> saveCourses) {
        courseDao.saveCourses(saveCourses);
    }

    @Override
    public void saveCourseInfos(List<CourseInfo> courseInfos) {
        courseDao.saveCourseInfos(courseInfos);
    }

    @Override
    public List<CourseDto> getCourseDto(long uId) {
        return courseDao.findCourseTimeByUId(uId);
    }

    @Override
    public List<UploadTaskDto> getTasks(long uId) {
        List<UploadTaskDto> tasks = courseDao.getTeachingScheduleByUId(uId);
        tasks.forEach(s->{
            s.setType(DocTypeEnum.TEACH.getTypeId());
        });

        List<UploadTaskDto> syllabus = courseDao.getTeachingSYLLABUSByUId(uId);
        syllabus.forEach(s->{
            s.setType(DocTypeEnum.SYLLABUS.getTypeId());
        });
        tasks.addAll(syllabus);
        return tasks;
    }

    @Override
    public void review(long uId, long id, int type, boolean b) throws PermissionDeniedException {
        //1.根据type 和 id 查找被审核的文档
        //2.判断该文档用户是否可以审核
        int state = 0;
        if(type == DocTypeEnum.TEACH.getTypeId()){

        }else{

        }
        if(!hasPermission(uId,state)){
            throw new PermissionDeniedException();
        }


    }

    @Override
    public int getUnDoneNumsByState(int state) {
        return courseDao.getUndoneNumsByState(state);
    }


    private boolean hasPermission(long uId, int state) {
        return true;
    }
}
