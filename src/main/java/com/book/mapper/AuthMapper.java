package com.book.mapper;

import com.book.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface AuthMapper {

    //查询Username是否重复
    @Select("Select username from user where username = #{username}")
    String existUsername(String username);

    //根据username查询密码
    @Select("Select password from user where username = #{username}")
    String getPasswordByUsername(String username);

    //根据username查询role
    @Select("Select role from user where username = #{username}")
    String getRoleByUsername(String username);

    //根据username查询uid
    @Select("Select uid from user where username = #{username}")
    String getUidByUsername(String username);

    //根据name查询sex
    @Select("Select sex from student where name = #{name}")
    String getStudentSexByName(String name);

    //根据name查询grade
    @Select("Select grade from student where name = #{name}")
    String getGradeByName(String name);

    //根据username查询email
    @Select("Select email from user where username = #{username}")
    String getEmailByUsername(String name);

    //注册用户，插入一条用户信息
    @Insert("insert into user(uid, username, role, password) values(#{uid}, #{username}, #{role}, #{password})")
    int registerUser(@Param("uid") String uid, @Param("username")String username, @Param("role")String role, @Param("password")String password);

    //注册用户，插入一条带有邮箱的用户信息
    @Insert("insert into user(uid, username, role, password,email) values(#{uid}, #{username}, #{role}, #{password}, #{email})")
    int registerEmailedUser(@Param("uid") String uid, @Param("username")String username, @Param("role")String role, @Param("password")String password, @Param("email")String email);

    //注册用户，插入一条学生信息
    @Insert("insert into student(uid, name, grade, sex) values(#{uid}, #{name}, #{grade}, #{sex})")
    int addStudentInfo(@Param("uid") String uid, @Param("name")String name, @Param("grade")String grade, @Param("sex")String sex);

    //修改用户信息
    @Update("update student set username = #{name} ,email = #{email} where uid = #{uid}")
    int modifyUserInfo( @Param("uid")String uid,@Param("username") String username,@Param("email")String email);

    @Update("update student set name = #{name}, sex = #{sex}, grade = #{grade} where uid = #{uid}")
    int modifyStudentInfo(@P("uid")String uid,@Param("name")String name, @Param("grade")String grade, @Param("sex")String sex);

    //@Update("update student set (uid,name,grade,sex) = ('${uid}, ${name}, ${grade}, ${sex}) where uid = '${uid}'")
    //int modifyStudentInfo(@Param("uid")String uid,@Param("name")String name,@Param("sex")String sex, @Param("grade")String grade);

    //获得所有学生列表
    @Select("Select * from student")
    List<Student> getStudentList();

    //根据学生列表
    @Select("Select * from student where name like '%${name}%'")
    List<Student> getStudentListByName(String name);

    //获取学生总数量
    @Select("Select count(*) from student")
    int getStudentCounts();

    //删除学生信息
    @Delete("Delete from user where uid = #{uid}")
    void DeleteUserTableInfo(String uid);
}
