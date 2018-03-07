package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM user where username = #{username}")
    @Results({
            @Result(property = "uId" ,column ="uid"),

            @Result(property = "username" ,column ="username"),
            @Result(property = "password" ,column ="password")
    })
    User findByUsername(@Param("username") String username);

}
