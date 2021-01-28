package web.action;

import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.SessionAware;
import web.dao.UserDao;
import web.model.User;
import web.util.JSONTools;

import java.util.Map;

public class UserAction extends ActionSupport implements SessionAware {
    //添加session变量，Tomcat将调用setSession自动注入
    private Map session;

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    //添加json返回变量jsonRoot
    private JSONObject jsonRoot;

    public JSONObject getJsonRoot() {
        return jsonRoot;
    }

    public void setJsonRoot(JSONObject jsonRoot) {
        this.jsonRoot = jsonRoot;
    }

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        System.out.println("开始登陆");
        User u = userDao.login(name, password);
        int result = -1;
        if (u == null) {
            result = 1;//登录失败，返回客户端浏览器resResult值为1
        } else {
            result = 0;//登录成功，返回客户端浏览器resResult值为0
            System.out.println("登陆用户名:" + name + ",密码:" + password);
            session.put("user", u);
            session.put("loginname", u.getUserName());
            //登录教师用户(teacher表中的t_name列)的用户名，如博雅读书管理员的用户名：博雅读书
            session.put("uid", u.getUserId());
            // 登录教师用户(teacher表中的t_id列)的编号，如博雅读书管理员的编号：1
            System.out.println("登陆用户uid:" + session.get("uid"));
            session.put("actpag", 1);//用于活动列表的当前页号为1
            session.put("itempag", 1);// 用于活动类别列表的当前页号为1
            session.put("addresspag", 1);// 用于地址列表的当前页号为1
            session.put("announcepag", 1);// 用于公告列表的当前页号为1
        }
        jsonRoot = JSONTools.createJsonObject("resResult", result);
        return "success";
    }
    public String unLogin() {
        System.out.println("退出");
        return "success";
    }
}
