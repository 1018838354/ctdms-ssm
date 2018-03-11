package cn.jxufe.ctdms.dao;

import org.apache.ibatis.annotations.*;

import javax.print.Doc;

@Mapper
public interface DocDao {

    @Select("SELECT * FROM doc where cId = #{cId}")
    @Results({
            @Result(property = "cId", column = "cId"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    })
    Doc findByCId(@Param("cid") long cId);
}
