package com.xlzhou.mapper;

import com.xlzhou.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Auther: zxl
 * @Date: 2020/9/9 - 09 - 09 - 21:58
 */

//@Mapper  @MapperScan("com.xlzhou.mapper")二选一
//@Repository //@Repository注解是Spring的注解，使用该注解和@Autowired注解，就不会出现爆红的情况了，原因很简单，因为@Repository注解是Sring的注解，把当前类注册成一个bean了
public interface UserMapper {

    @Select("select * from sys_user where  username = #{username}")
    public User queryUserByName(String username);
}
