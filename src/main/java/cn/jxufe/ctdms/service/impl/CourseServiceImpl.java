package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseTime;
import cn.jxufe.ctdms.dao.CourseDao;
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
        return courseDao.save(course);
    }

    @Override
    public void saveCourseTimes(List<CourseTime> courseTimes) {
        courseDao.saveCourseTimes(courseTimes);
    }

    @Override
    public void saveCourses(List<Course> saveCourses) {
        courseDao.saveCourses(saveCourses);
    }
}
