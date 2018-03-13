package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.UploadTask;
import cn.jxufe.ctdms.dto.CourseDto;
import cn.jxufe.ctdms.dto.UploadTaskDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Deprecated
@Mapper
public interface UploadTaskDao {
    void saves(List<UploadTask>tasks);

    @Insert("insert into upload_task(taskId,cId,uId,type,classCode,recordId,times,state) values(#{task.taskId},#{task.cId},#{task.uId},#{task.type},#{task.classCode},#{task.recordId},#{task.times},#{task.state})")
    @Options(useGeneratedKeys = true , keyProperty = "taskId")
    Long save(@Param("task") UploadTask task);

    /**
     * @apiNote
     * pxf
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




}
