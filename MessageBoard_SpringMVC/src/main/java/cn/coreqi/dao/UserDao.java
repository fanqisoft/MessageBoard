package cn.coreqi.dao;

import cn.coreqi.entities.User;

public interface UserDao {
    User login(String userName,String passWord);
    int modifyPassword(User user);
    int addUser(User user);
    boolean existUserWithUserName(String userName);
}
