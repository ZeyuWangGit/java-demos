package com.exp.mapper;

import com.exp.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user (username, password, create_time, update_time) VALUES (#{username}, #{password}, NOW(), NOW())")
    void add(String username, String password);

    @Select("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    void update(User user);

    @Update("UPDATE user SET user_pic = #{avatarUrl}, update_time = NOW() WHERE id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("UPDATE user SET password = #{newPassword}, update_time = NOW() WHERE id = #{id}")
    void updatePassword(String newPassword, Integer id);
}
