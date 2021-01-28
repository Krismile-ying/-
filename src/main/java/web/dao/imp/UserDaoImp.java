package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

public class UserDaoImp extends HibernateDaoSupport implements UserDao {
    @Override
    public List<User> findAllUser() {
        List<User>list=getHibernateTemplate().find("from web.model.User");
        return list;
    }

    @Override
    public User findUserById(int userId) {
        List<User>list=getHibernateTemplate().find("from web.model.User where UserID=?",userId);
        User user=list.get(0);
        return user;
    }
    @Override
    public User login(String username, String password) {
        String strs[]={username,password};
        List<User> list=getHibernateTemplate().find("from web.model.User where UserName=? and UserPassword=?",strs);
        if(list.size()>0){
            return list.get(0);
        }else
            return null;
    }
}
