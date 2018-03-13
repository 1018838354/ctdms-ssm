package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseTime;
import cn.jxufe.ctdms.dto.CourseDto;
import cn.jxufe.ctdms.dto.UploadTaskDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Select("select c.classCode,c.cName,u.teacherName,c.district,c.cCode,c.weekly,ct.day,ct.classIndex,ct.lastTime,ct.classRoom"+
            " from course c " +
            "left join user u on c.uId = #{uId}" +
            "left join course_time ct on c.cId = ct.cId")
    List<CourseDto> findCourseTimeByUId(@Param("uId") long uId);


    @Results(id = "tasks",value = {
            @Result(property = "cId", column = "cId"),
            @Result(property = "state", column = "state"),
            @Result(property = "classCode", column = "classCode"),
            @Result(property = "cName", column = "cName")
    })

    @Select("select c.cId,c.state,c.classCode,ci.cName " +
            "from course c" +
            "INNER join course_info ci on c.cId = ci.cId " +
            "where c.uId#{uId}")
    @ResultMap("tasks")
    List<UploadTaskDto> getTeachingScheduleByUId(long uId);


    @Select("select c.cId,c.state,c.classCode,ci.cName from " +
            "course_info ci,course c where  c.cId = ci.cId and ci.cId in" +
            "(select c.cId from course c,user u where c.uId = u.#{uId}) ")
    @ResultMap("tasks")
    List<UploadTaskDto> getTeachingSYLLABUSByUId(long uId);

}
