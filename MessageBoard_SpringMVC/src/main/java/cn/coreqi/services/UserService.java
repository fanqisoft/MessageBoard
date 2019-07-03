package cn.coreqi.services;

import cn.coreqi.entities.User;

public interface UserService {
    User login(String userName, String passWord);
}
