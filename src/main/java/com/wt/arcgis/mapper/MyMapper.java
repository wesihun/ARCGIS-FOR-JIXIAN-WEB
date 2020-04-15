package com.wt.arcgis.mapper;

import com.wt.arcgis.pojo.Banner;
import com.wt.arcgis.pojo.Department;
import com.wt.arcgis.pojo.Menue;
import com.wt.arcgis.pojo.Post;
import com.wt.arcgis.pojo.Resourcetype;
import com.wt.arcgis.pojo.Role;
import com.wt.arcgis.pojo.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface MyMapper
{
    @Select("select * from tb_user where username=#{username} and password=#{password} and state=1")
    public User getUserByAccount(User user);//根据用户名密码取得简单用户信息

    @Select("select * from tb_user where username=#{username} and password=#{password} ")
    public User getUserByAccountNoState(User user);//根据用户名密码取得简单用户信息，不论state状态如何

    @Select("select D.userid,D.departmentid as 'department.departmentid',D.postid as 'post.postid',D.username,D.password,D.realname,D.gender,D.telephone,D.createtime,D.state,D.id,D.roleid as 'role.roleid',D.rolename as 'role.rolename',D.detail as 'role.detail',D.privilegeid as 'privilege.privilegeid',D.privilegecode as 'privilege.privilegecode',D.privilegename as 'privilege.privilegename', tb_department.departmentname as 'department.departmentname', tb_department.parentid as 'department.parentid', tb_post.postname as 'post.postname', tb_post.postdetail as 'post.postdetail' from (select C.userid,C.departmentid,C.postid,C.username,C.password,C.realname,C.gender,C.telephone,C.createtime,C.state,C.id,C.roleid,C.rolename,C.detail,C.privilegeid,tb_privilege.privilegecode,tb_privilege.privilegename from  ( select B.userid,B.departmentid,B.postid,B.username,B.password,B.realname,B.gender,B.telephone,B.createtime,B.state,B.id,B.roleid,B.rolename,B.detail,tb_role_privilege.privilegeid from  ( select A.userid,A.departmentid,A.postid,A.username,A.password,A.realname,A.gender,A.telephone,A.createtime,A.state,A.id,A.roleid, tb_role.rolename,tb_role.detail from  ( select tb_user.userid,departmentid,postid,username,password,realname,gender,telephone,createtime,state,id,roleid from tb_user left join tb_user_role on tb_user.userid = tb_user_role.userid where tb_user.userid=#{userid} ) as A left join tb_role on A.roleid=tb_role.roleid ) as B  left join tb_role_privilege on B.roleid= tb_role_privilege.roleid ) C left join tb_privilege on C.privilegeid = tb_privilege.privilegeid ) as D ,tb_department, tb_post  where tb_department.departmentid = D.departmentid and tb_post.postid = D.postid ")
    public List<User> getUserInfo(User user);//取得完整用户信息

    @Select("select * from tb_department where parentid is null")
    public List<Department> getRootDepartment();//根部门

    @Select("select * from tb_department where parentid=#{1}")
    public List<Department> getSubDepartment(int pid);//根据PID取得子部门

    @Select("select * from tb_menue where parentmenueid is null;")
    public List<Menue> getRootMenue();//根菜单

    @Select("select * from tb_menue where parentmenueid=#{1}")
    public List<Menue> getSubMenue(int pid);//子菜单

    @Select("select * from tb_role ")
    public List<Role> getRole();

    @Select("select * from tb_post ")
    public List<Post> getPost();

    
    @Insert("insert into tb_user(departmentid,postid,username,password,realname,gender,telephone,state,createtime) values(#{department.departmentid},#{post.postid},#{username},#{password},#{realname},#{gender},#{telephone},#{state},#{createtime}) ")
    public int insertUser(User user);//添加用户

    @Insert("insert into tb_user_role(roleid,userid) values(#{role.roleid},#{userid}) ")
    public int insertUserRole(User user);//用户角色中间表





}
