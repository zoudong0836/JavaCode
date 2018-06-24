package com.dzou.dao;

import com.dzou.pojo.User;

public interface UserMapper {

    User selectById(int id);

    void addUser(User user);
}
