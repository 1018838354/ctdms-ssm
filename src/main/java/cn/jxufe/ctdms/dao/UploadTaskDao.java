package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.UploadTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadTaskDao {
    void saves(List<UploadTask>tasks);
    Long save(UploadTask task);
}
