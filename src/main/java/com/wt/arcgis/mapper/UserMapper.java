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
    public User getUserByAccount(User user);//根据用户名密码取得简单用户信息

    @Select("select D.userid,D.departmentid as 'department.departmentid',D.postid as 'post.postid',D.username,D.password,D.realname,D.gender,D.telephone,D.createtime,D.state,D.id,D.roleid as 'role.roleid',D.rolename as 'role.rolename',D.detail as 'role.detail',D.privilegeid as 'privilege.privilegeid',D.privilegecode as 'privilege.privilegecode',D.privilegename as 'privilege.privilegename', tb_department.departmentname as 'department.departmentname', tb_department.parentid as 'department.parentid', tb_post.postname as 'post.postname', tb_post.postdetail as 'post.postdetail' from (select C.userid,C.departmentid,C.postid,C.username,C.password,C.realname,C.gender,C.telephone,C.createtime,C.state,C.id,C.roleid,C.rolename,C.detail,C.privilegeid,tb_privilege.privilegecode,tb_privilege.privilegename from  ( select B.userid,B.departmentid,B.postid,B.username,B.password,B.realname,B.gender,B.telephone,B.createtime,B.state,B.id,B.roleid,B.rolename,B.detail,tb_role_privilege.privilegeid from  ( select A.userid,A.departmentid,A.postid,A.username,A.password,A.realname,A.gender,A.telephone,A.createtime,A.state,A.id,A.roleid, tb_role.rolename,tb_role.detail from  ( select tb_user.userid,departmentid,postid,username,password,realname,gender,telephone,createtime,state,id,roleid from tb_user left join tb_user_role on tb_user.userid = tb_user_role.userid where tb_user.userid=1 ) as A left join tb_role on A.roleid=tb_role.roleid ) as B  left join tb_role_privilege on B.roleid= tb_role_privilege.roleid ) C left join tb_privilege on C.privilegeid = tb_privilege.privilegeid ) as D ,tb_department, tb_post  where tb_department.departmentid = D.departmentid and tb_post.postid = D.postid ")
    public List<User> getUserInfo(User user);//取得完整用户信息








    @Select("select * from tb_user where id= #{1}")
    public List<User> queryAll(int id, int real_name);

    @Insert("INSERT INTO student(id,name) VALUES(#{id}, #{name})")
    public void insertStudent(User user);



}
