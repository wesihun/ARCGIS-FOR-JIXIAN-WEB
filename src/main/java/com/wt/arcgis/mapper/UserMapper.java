package com.wt.arcgis.mapper;
import com.wt.arcgis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface UserMapper
{
    @Select("select * from tb_user where username=#{username} and password=#{password} and state=1")
    public User getUserByAccount(User user);

    @Select("")
    public User getUserInfo(User user);








    @Select("select * from tb_user where id= #{1}")
    public List<User> queryAll(int id, int real_name);

    @Select("select id as idd, real_name from tb_user where id= #{idd}")
    public List<User> queryAll_2(User user);

    @Insert("INSERT INTO student(id,name) VALUES(#{id}, #{name})")
    public void insertStudent(User user);



}
