package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM user where username = #{username}")
    @Results({
            @Result(property = "uId" ,column ="uId"),
            @Result(property = "username" ,column ="username"),
            @Result(property = "password" ,column ="password")
    })
    User findByUsername(@Param("username") String username);
    /*
    其中@Options注解常用属性:
    - flushCache:刷新缓存策略，有DEFAULT,TRUE,FALSE三种值，默认DEFAULT表示刷新查询语句的缓存
    - useCache：默认true，表示使用缓存
    - fetchSize：查询时的获取数量
    - useGeneratedKeys：默认false，是否返回插入的id
    - keyProperty：实体类id属性
    - keyColumn：实体类属性对应数据库的字段
     */
    @Insert("insert into user(uId,username,password) values(#{user.uId},#{user.username},#{user.password})")
    @Options(useGeneratedKeys = true , keyProperty = "uId")
    Long save(@Param("user") User user);


    @Select("SELECT * FROM user where uId = #{uId}")
    @Results({
            @Result(property = "uId" ,column ="uId"),
            @Result(property = "username" ,column ="username"),
            @Result(property = "password" ,column ="password")
    })
    User findByUId(@Param("uId") Long uId);

    void saves(List<User> users);


    List<User> findAll();
}
