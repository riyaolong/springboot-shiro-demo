package com.xlzhou.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zxl
 * @Date: 2020/9/9 - 09 - 09 - 21:51
 */
@Data   //使用这个注解，就不用再去手写Getter,Setter,equals,canEqual,hasCode,toString等方法了，注解后在编译时会自动加进去。
@AllArgsConstructor  //使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor   //使用后创建一个无参构造函数
public class User {

    private  int id ;
    private  String username ;
    private  String password ;
    private  String auth;
}
