package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    //获得所有用户对象的列表
    public List<User> findAllUser();
    //获得指定用户编号的用户对象
    public User findUserById(int userId);
    //登录验证
    public User login(String username, String password);
}
