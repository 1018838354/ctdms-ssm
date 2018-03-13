package cn.jxufe.ctdms.service;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseInfo;
import cn.jxufe.ctdms.bean.CourseTime;
import cn.jxufe.ctdms.dto.CourseDto;
import cn.jxufe.ctdms.dto.UploadTaskDto;

import java.util.List;

public interface CourseService {
    Long save(Course course);

    void saveCourseTimes(List<CourseTime> courseTime);

    void saveCourses(List<Course> saveCourses);

    void saveCourseInfos(List<CourseInfo> courseInfos);

    List<CourseDto> getCourseDto(long uId);

    List<UploadTaskDto> getTasks(long uId);
}
