package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.UploadTask;
import cn.jxufe.ctdms.dto.CourseDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UploadTaskDao {
    void saves(List<UploadTask>tasks);

    @Insert("insert into upload_task(taskId,cId,uId,type,classCode,recordId,times,state) values(#{task.taskId},#{task.cId},#{task.uId},#{task.type},#{task.classCode},#{task.recordId},#{task.times},#{task.state})")
    @Options(useGeneratedKeys = true , keyProperty = "taskId")
    Long save(@Param("task") UploadTask task);

    /**
     * @author:pxf
     */
    @Select("select taskId,cId,uId,type,classCode,recordId,state FROM upload_task WHERE taskId=#{taskId}")
    @Results({
            @Result(property = "taskId", column = "taskId"),
            @Result(property = "cId", column = "cId"),
            @Result(property = "uId", column = "uId"),
            @Result(property = "type", column = "type"),
            @Result(property = "classCode", column = "classCode"),
            @Result(property = "recordId", column = "recordId"),
            @Result(property = "state", column = "state")
    })
    UploadTask findByTaskId(@Param("taskId") Long taskId);


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
    @Select("select course.classCode,course.cName,course.teacherName,course.district,course.cCode,course.weekly,course_time.day,course_time.classIndex,course_time.lastTime,course_time.classRoom"+
            " from upload_task,course,course_time" +
            " where upload_task.taskId = course_time.taskId and upload_task.cId = course.cId and " +
            "upload_task.uId = #{uId}")
    List<CourseDto> findByUId(@Param("uId") long uId);
}
