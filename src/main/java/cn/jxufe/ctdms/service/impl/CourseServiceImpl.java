package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.dao.CourseDao;
import cn.jxufe.ctdms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseDao courseDao;
    @Override
    public long save(Course course) {
        return courseDao.save(course);
    }
}