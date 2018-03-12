package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseTime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseDao {
    @Insert("insert into course(cId,cName,cCode,classCode,teacherName,district,weekly) " +
            "values(#{course.cId},#{course.cName},#{course.cCode},#{course.classCode},#{course.teacherName}" +
            ",#{course.district},#{course.weekly})")
    @Options(useGeneratedKeys = true, keyProperty = "cId")
    Long save(@Param("course") Course course);

    void saveCourses(List<Course>courses);

    void saveCourseTimes(List<CourseTime> courseTimes);
}
