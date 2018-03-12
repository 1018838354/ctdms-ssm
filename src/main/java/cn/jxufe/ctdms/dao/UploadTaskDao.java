package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.UploadTask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UploadTaskDao {
    void saves(List<UploadTask>tasks);

    @Insert("insert into upload_task(taskId,cId,uId,type,classCode,recordId,times,state) values(#{task.taskId},#{task.cId},#{task.uId},#{task.type},#{task.classCode},#{task.recordId},#{task.times},#{task.state})")
    @Options(useGeneratedKeys = true , keyProperty = "taskId")
    Long save(@Param("task") UploadTask task);
}
