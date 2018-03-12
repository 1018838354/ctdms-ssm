package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.Test;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TestDao {
    @Insert("insert into test(username) values(#{username})")
    @Options(useGeneratedKeys = true,keyProperty = "cId")
    Long save(Test t);
}
