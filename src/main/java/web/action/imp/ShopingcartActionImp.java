package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import web.action.ShopingcartAction;
import web.dao.DeviceDao;
import web.dao.ShopingcartDao;
import web.dao.UserDao;
import web.model.Device;
import web.model.Shopingcart;
import web.model.Shopingorder;
import web.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShopingcartActionImp extends ActionSupport implements ShopingcartAction, ServletRequestAware, ServletResponseAware {
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
    private ShopingcartDao shopingcartDao;
    public void setShopingcartDao(ShopingcartDao shopingcartDao) {
        this.shopingcartDao = shopingcartDao;
    }
    private DeviceDao deviceDao;
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
    public void makeJson(List<Shopingcart> list)throws IOException {
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //构造json输出字符串
        JSONArray jsonArray=new JSONArray();
        for(Shopingcart shopingcart:list){
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("ShopingcartID",shopingcart.getShopingcartId());
            Integer deviceId=shopingcart.getDevice().getDeviceId();
            Device dev=deviceDao.findDeviceById(new Integer(deviceId));
            JSONObject jsonObjdev=new JSONObject();
            jsonObj.put("DeviceID",dev.getDeviceId());
            jsonObj.put("DeviceClassId",dev.getDeviceclassByDeviceClassId().getDeviceClassId());
            jsonObj.put("DeviceName",dev.getDeviceName());
            jsonObj.put("DevicePrice",dev.getDevicePrice());
            //jsonObj.put("Device",jsonObjdev);
            jsonObj.put("BuyNum",shopingcart.getBuyNum());
            jsonObj.put("UserID",shopingcart.getUser().getUserId());
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
    public void findAllShopingcart() throws IOException {
        List<Shopingcart>list=shopingcartDao.findAllShopingcart();
        makeJson(list);
    }

    @Override
    public String findShopingcartById() throws IOException {
        String id=request.getParameter("shopingcartId");
        Shopingcart shopingcart=shopingcartDao.findShopingcartById(new Integer(id));
        /*List<Shopingcart>list=new ArrayList<Shopingcart>();
        list.add(shopingcart);
        makeJson(list);*/
        return "success";
    }
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override

    public String addShopingcart() throws IOException {
        String deviceId=request.getParameter("addDeviceID");
        String buyNum=request.getParameter("addBuyNum");
        String userId=request.getParameter("addUserID");
        Shopingcart sc=new Shopingcart();
        Device dev=deviceDao.findDeviceById(new Integer(deviceId));
        sc.setDevice(dev);
        sc.setBuyNum(new Integer(buyNum));
        User user=userDao.findUserById(new Integer(userId));
        sc.setUser(user);
        shopingcartDao.addShopingcart(sc);
        return "success";
    }
    @Override
    public String deleteShopingcart() throws IOException {
        String shopingcartId=request.getParameter("deleteShopingcartID");
        Shopingcart sc=shopingcartDao.findShopingcartById(new Integer(shopingcartId));
        shopingcartDao.deleteShopingcart(sc);
        return "success";
    }

    @Override
    public String updateShopingcart() throws IOException {
        String id=request.getParameter("shopingcartId");
        String buyNum=request.getParameter("buyNum");
        shopingcartDao.updateShopingcart(new Integer(id),new Integer(buyNum));
        Shopingcart soi=shopingcartDao.findShopingcartById(new Integer(id));
        return "success";
    }

    @Override
    public void findAllShopingcartByUserId() throws IOException {
        String id=request.getParameter("userId");
        List<Shopingcart>list=new ArrayList<Shopingcart>();
        list=shopingcartDao.findAllShopingcartByUserId(new Integer(id));
        makeJson(list);
    }

    @Override
    public String jiesuanShopingcart() throws IOException {
        Shopingorder so=new Shopingorder();
        String userId=request.getParameter("userId");
        User user=userDao.findUserById(new Integer(userId));
        so.setUserByUserId(user);
        String receiver=request.getParameter("receiver");
        so.setReceiver(receiver);
        String receiveAddress=request.getParameter("receiveAddress");
        so.setReceiveAddress(receiveAddress);
        String createtime=request.getParameter("createtime");
        so.setCreatetime(createtime);
        String moneyAmount=request.getParameter("moneyAmount");
        so.setMoneyAmount(moneyAmount);
        List<Shopingcart>shopingcartList=new ArrayList<Shopingcart>();
        String shopingcartIds=request.getParameter("shopingcartIds");
        String[] shopingcartIdList=shopingcartIds.split(",");
        for (String shopingcartId:shopingcartIdList){
            Shopingcart sc=shopingcartDao.findShopingcartById(new Integer(shopingcartId));
            shopingcartList.add(sc);
        }
        shopingcartDao.jiesuanShopingcart(so,shopingcartList);
        return "success";
    }
}
