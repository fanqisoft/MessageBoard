package cn.coreqi.services.impl;

import cn.coreqi.dao.UserDao;
import cn.coreqi.entities.User;
import cn.coreqi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String userName, String passWord) {
        return userDao.login(userName,passWord);
    }
}
