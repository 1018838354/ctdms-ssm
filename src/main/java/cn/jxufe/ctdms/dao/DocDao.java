package cn.jxufe.ctdms.dao;

import org.apache.ibatis.annotations.*;

import javax.print.Doc;

@Mapper
public interface DocDao {

    @Select("select count(*) from upload_record")
    int getFileCount();
}
