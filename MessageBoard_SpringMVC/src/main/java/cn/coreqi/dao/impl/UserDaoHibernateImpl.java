package cn.coreqi.dao.impl;

import cn.coreqi.dao.UserDao;
import cn.coreqi.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    //查找用户是否存在
    @Override
    public User login(String userName,String passWord) {
        String sql = "select * from t_user where userName = ? and password = ? limit 1";
        NativeQuery<User> query = currentSession().createNativeQuery(sql,User.class).setParameter(1,userName).setParameter(2,passWord);
        if(query.getResultList().size() == 0){
            return null;
        }
        return query.getSingleResult();
    }

    @Override
    public int modifyPassword(User user) {
        return 1;
    }

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public boolean existUserWithUserName(String userName) {
        return false;
    }
}
