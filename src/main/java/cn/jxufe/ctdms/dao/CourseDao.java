package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseInfo;
import cn.jxufe.ctdms.bean.CourseTime;
import cn.jxufe.ctdms.dto.CourseDto;
import cn.jxufe.ctdms.dto.UploadTaskDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

@Mapper
public interface CourseDao {
    /*
    @Insert("insert into course(cId,cName,cCode,classCode,uId,district,weekly) " +
            "values(#{course.cId},#{course.cName},#{course.cCode},#{course.classCode},#{course.uId}" +
            ",#{course.district},#{course.weekly})")
    @Options(useGeneratedKeys = true, keyProperty = "cId")
    Long save(@Param("course") Course course);
    */
    void saveCourses(List<Course>courses);
    void saveCourseInfos(List<CourseInfo> courseInfos);
    void saveCourseTimes(List<CourseTime> courseTimes);
/*

    @Select("select cId,cName,cCode,classCode,uId,district,weekly FORM course c,course_info ci where c.cId=#{cId} and c.cId=ci.cId ")
    Course findById(@Param("cId") Long cId);

*/

    @Results(value = {
            @Result(property = "classCode", column = "classCode"),
            @Result(property = "cName", column = "cName"),
            @Result(property = "teacherName", column = "teacherName"),
            @Result(property = "district", column = "district"),
            @Result(property = "cCode", column = "cCode"),
            @Result(property = "weekly", column = "weekly"),
            @Result(property = "day", column = "day"),
            @Result(property = "classIndex", column = "classIndex"),
            @Result(property = "lastTime", column = "lastTime"),
            @Result(property = "classRoom", column = "classRoom"),
    })
    @Select("select c.classCode,ci.cName,c.district,ci.cCode,c.weekly,ct.day,ct.classIndex,ct.lastTime,ct.classRoom " +
            "from course c " +
            "inner join course_info ci on ci.ciId = c.ciId " +
            "inner join course_time ct on c.cId = ct.cId " +
            "where c.uId = #{uId} ")
    List<CourseDto> findCourseTimeByUId(@Param("uId") long uId);


    @Select("select c.cId,c.state,c.classCode,ci.cName " +
            "from course c " +
            "INNER join course_info ci on c.ciId = ci.ciId " +
            "where c.uId = #{uId}")
    @ResultMap("cn.jxufe.ctdms.dao.CourseDao.tasks")
    List<UploadTaskDto> getTeachingScheduleByUId(long uId);


    @Select("select ci.ciId,c.state,c.classCode,ci.cName from " +
            "course_info ci inner join course c on c.ciId = ci.ciId " +
            "where c.uId = #{uId} ")
    @ResultMap("cn.jxufe.ctdms.dao.CourseDao.tasks")
    List<UploadTaskDto> getTeachingSYLLABUSByUId(long uId);



    @Select("select COUNT(DISTINCT uId) from course where state != #{state}")
    int getUndoneNumsByState(int state);

    @Select("select COUNT(*) from course where state >= #{state}")
    int getCourseNumsGEThenState(int state);

    @Select("select COUNT(*) from course")
    int getCourseNums();
}
