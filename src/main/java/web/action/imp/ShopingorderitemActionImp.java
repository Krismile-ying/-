package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import web.action.ShopingorderitemAction;
import web.dao.DeviceDao;
import web.dao.ShopingorderDao;
import web.dao.ShopingorderitemDao;
import web.model.Device;
import web.model.Shopingorder;
import web.model.Shopingorderitem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShopingorderitemActionImp extends ActionSupport implements ShopingorderitemAction, ServletRequestAware, ServletResponseAware {
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
    private ShopingorderitemDao shopingorderitemDao;
    public void setShopingorderitemDao(ShopingorderitemDao shopingorderitemDao) {
        this.shopingorderitemDao = shopingorderitemDao;
    }
    private DeviceDao deviceDao;
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
    public void makeJson(List<Shopingorderitem> list)throws IOException {
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //构造json输出字符串
        JSONArray jsonArray=new JSONArray();
        for(Shopingorderitem soi:list){
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("ShopingOrderItemID",soi.getShopingOrderItemId());
            jsonObj.put("ShopingOrderID",soi.getShopingorderByShopingOrderId().getShopingOrderId());
            Integer deviceId=soi.getDeviceByDeviceId().getDeviceId();
            Device dev=deviceDao.findDeviceById(new Integer(deviceId));
            //JSONObject jsonObjdev=new JSONObject();
            jsonObj.put("DeviceID",dev.getDeviceId());
            jsonObj.put("DeviceClassId",dev.getDeviceclassByDeviceClassId().getDeviceClassId());
            jsonObj.put("DeviceName",dev.getDeviceName());
            jsonObj.put("DevicePrice",dev.getDevicePrice());
            //jsonObj.put("",jsonObjdev);
            jsonObj.put("BuyNum",soi.getBuyNum());
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
    public void findAllShopingorderitem() throws IOException {
        List<Shopingorderitem>list=shopingorderitemDao.findAllShopingorderitem();
        makeJson(list);
    }

    @Override
    public String findShopingorderitemById() throws IOException {
        String id=request.getParameter("shopingorderitemId");
        Shopingorderitem shopingorderitem=shopingorderitemDao.findShopingorderitemById(new Integer(id));
       /* List<Shopingorderitem>list=new ArrayList<Shopingorderitem>();
        list.add(shopingorderitem);
        makeJson(list);*/
       return "success";
    }

    @Override
    public void findShopingorderitemListByShopingorderId() throws IOException {
       String id=request.getParameter("shopingorderId");
       List<Shopingorderitem> list=shopingorderitemDao.findShopingorderitemListByShopingorderId(new Integer(id));
       makeJson(list);
    }
    private ShopingorderDao shopingorderDao;
    public void setShopingorderDao(ShopingorderDao shopingorderDao) {
        this.shopingorderDao = shopingorderDao;
    }
    @Override
    public String addShopingorderitem() throws IOException {
        String shopingOrderID=request.getParameter("shopingOrderID");
        String deviceID=request.getParameter("deviceID");
        String buyNum=request.getParameter("buyNum");
        Shopingorderitem soi=new Shopingorderitem();
        Shopingorder so=shopingorderDao.findShopingorderById(new Integer(shopingOrderID));
        soi.setShopingorderByShopingOrderId(so);
        Device dev=deviceDao.findDeviceById(new Integer(deviceID));
        soi.setDeviceByDeviceId(dev);
        soi.setBuyNum(new Integer(buyNum));
        shopingorderitemDao.addShopingorderitem(soi);
        return "success";
    }

    @Override
    public String deleteShopingorderitem() throws IOException {
        String id=request.getParameter("deleteShopingorderitemID");
        Shopingorderitem soi=shopingorderitemDao.findShopingorderitemById(new Integer(id));
        shopingorderitemDao.deleteShopingorderitem(soi);
        return "success";
    }

    @Override
    public String updateShopingorderitem() throws IOException {
        String id=request.getParameter("shopingorderitemId");
        String buyNum=request.getParameter("buyNum");
        shopingorderitemDao.updateShopingorderitem(new Integer(id),new Integer(buyNum));
        Shopingorderitem soi=shopingorderitemDao.findShopingorderitemById(new Integer(id));
        return "success";
    }
}
