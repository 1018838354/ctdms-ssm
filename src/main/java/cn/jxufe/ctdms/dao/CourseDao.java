package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseDao {
    @Insert("insert into course(username,password) values(#{user.username},#{user.password})")
    @Options(useGeneratedKeys = true)
    Long save(@Param("course") Course course);

}
