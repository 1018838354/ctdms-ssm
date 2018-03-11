package cn.jxufe.ctdms.service;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseTime;

import java.util.List;

public interface CourseService {
    long save(Course course);

    void saveCourseTimes(List<CourseTime> courseTime);
}
