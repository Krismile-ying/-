package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import web.action.ShopingorderAction;
import web.dao.ShopingorderDao;
import web.dao.UserDao;
import web.model.Shopingorder;
import web.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShopingorderActionImp extends ActionSupport implements ShopingorderAction, ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    @Override
    public void setServletRequest(HttpServletRequest req) {
        this.request=req;
    }

    @Override
    public void setServletResponse(HttpServletResponse res) {
        this.response=res;
    }
    private ShopingorderDao shopingorderDao;
    public void setShopingorderDao(ShopingorderDao shopingorderDao) {
        this.shopingorderDao = shopingorderDao;
    }
    public void makeJson(List<Shopingorder> list)throws IOException {
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //构造json输出字符串
        JSONArray jsonArray=new JSONArray();
        for(Shopingorder shopingorder:list){
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("ShopingOrderID",new Integer(shopingorder.getShopingOrderId().toString()));
            jsonObj.put("UserId",shopingorder.getUserByUserId().getUserId());
            jsonObj.put("Receiver",shopingorder.getReceiver());
            jsonObj.put("Receive Address",shopingorder.getReceiveAddress());
            jsonObj.put("Createtime",shopingorder.getCreatetime());
            jsonObj.put("MoneyAmount",shopingorder.getMoneyAmount());
            jsonArray.add(jsonObj);
        }
        System.out.println(jsonArray.toString());
        JSONObject root=new JSONObject();
        root.put("data",jsonArray);
        out.write(root.toString());
        out.flush();
        out.close();
    }
    @Override
    public void findAllShopingorder() throws IOException {
        List<Shopingorder>list=shopingorderDao.findAllShopingorder();
        makeJson(list);
    }

    @Override
    public String findShopingorderById() throws IOException {
        String id=request.getParameter("shopingorderId");
        Shopingorder shopingorder=shopingorderDao.findShopingorderById(new Integer(id));
        /*List<Shopingorder>list=new ArrayList<Shopingorder>();
        list.add(shopingorder);
        makeJson(list);*/
        return "success";
    }

    @Override
    public void findShopingorderByUserId() throws IOException {
        String id=request.getParameter("userId");
        List<Shopingorder>list=shopingorderDao.findShopingorderByUserId(new Integer(id));
        makeJson(list);
        /*return "success";*/
    }
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String addShopingorder() throws IOException {
        String userId=request.getParameter("userId");
        String receiver=request.getParameter("receiver");
        String receiveAddress=request.getParameter("receiveAddress");
        String createtime=request.getParameter("createtime");
        String moneyAmount=request.getParameter("moneyAmount");
        Shopingorder so=new Shopingorder();
        User user=userDao.findUserById(new Integer(userId));
        so.setUserByUserId(user);
        so.setReceiver(receiver);
        so.setReceiveAddress(receiveAddress);
        so.setCreatetime(createtime);
        so.setMoneyAmount(moneyAmount);
        shopingorderDao.addShopingorder(so);
        return "success";
    }

    @Override
    public String deleteShopingorder() throws IOException {
        String id=request.getParameter("deleteShopingorderID");
        Shopingorder so=shopingorderDao.findShopingorderById(new Integer(id));
        shopingorderDao.deleteShopingorder(so);
        return "success";
    }

    @Override
    public String updateShopingorder() throws IOException {
        String id=request.getParameter("shopingorderId");
        String receiver=request.getParameter("receiver");
        String receiveAddress=request.getParameter("receiveAddress");
        String createtime=request.getParameter("createtime");
        String moneyAmount=request.getParameter("moneyAmount");
        shopingorderDao.updateShopingorder(new Integer(id),receiver,receiveAddress,createtime,moneyAmount);
        Shopingorder soi=shopingorderDao.findShopingorderById(new Integer(id));
        return "success";
    }
}
