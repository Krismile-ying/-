package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import web.action.DeviceAction;
import web.dao.DeviceClassDao;
import web.dao.DeviceDao;
import web.model.Device;
import web.model.Deviceclass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeviceActionImp extends ActionSupport implements DeviceAction,ServletRequestAware,ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private DeviceDao deviceDao;
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
    private DeviceClassDao deviceClassDao;

    public void setDeviceClassDao(DeviceClassDao deviceClassDao) {
        this.deviceClassDao = deviceClassDao;
    }

    //回调函数，当移动端发送响应给tomcat服务器端时，自动调用
    @Override
    public void setServletRequest(HttpServletRequest req) {
            this.request=req;
    }
    //回调函数，当tomcat服务器端发送响应给移动端时，自动调用
    @Override
    public void setServletResponse(HttpServletResponse res) {
             this.response=res;
    }
    public void makeJson(List<Device> list)throws IOException {
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //构造json输出字符串
        JSONArray jsonArray=new JSONArray();
        for(Device dev:list){
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("DeviceID",dev.getDeviceId());
            jsonObj.put("DeviceClassId",dev.getDeviceclassByDeviceClassId().getDeviceClassId());
            jsonObj.put("DeviceName",dev.getDeviceName());
            jsonObj.put("DevicePrice",dev.getDevicePrice());
            jsonArray.add(jsonObj);
        }
        System.out.println(jsonArray.toString());
        JSONObject root=new JSONObject();
        root.put("data",jsonArray);
        out.write(root.toString());
        out.flush();
        out.close();
    }
    public void findAllDevice() throws IOException {
        List<Device> list=deviceDao.findAllDevice();
        makeJson(list);
    }
    public void findDeviceByDeviceClassId() throws IOException {
        String deviceClassId=request.getParameter("deviceClassId");
        List<Device>list=deviceDao.findDeviceByDeviceClassId(new Integer(deviceClassId));
        makeJson(list);
    }
    public String findDeviceById() throws IOException {
        String deviceId=request.getParameter("deviceId");
        Device dev=deviceDao.findDeviceById(new Integer(deviceId));
        /*List<Device>list=new ArrayList<Device>();
        list.add(dev);
        makeJson(list);*/
        return "success";
    }
    public void findDeviceByName() throws IOException {
        String deviceName=request.getParameter("deviceName");
        List<Device>list=deviceDao.findDeviceByName(deviceName);
        makeJson(list);
    }
    public void findDeviceByPrice() throws IOException {
        String low=request.getParameter("low");
        String high=request.getParameter("high");
        List<Device>list=deviceDao.findDeviceByPrice(low,high);
        makeJson(list);
    }
    public void findDeviceByFuzzy() throws IOException{
        String deviceClassName=request.getParameter("deviceClassName");
        String deviceName=request.getParameter("deviceName");
        String low=request.getParameter("low");
        String high=request.getParameter("high");
        List<Device> list=deviceDao.findDeviceByFuzzy(deviceClassName,deviceName,low,high);
        makeJson(list);
    }
    public String updateDeviceById() throws IOException{
        String deviceId=request.getParameter("deviceId");
        String deviceName=request.getParameter("deviceName");
        String devicePrice=request.getParameter("devicePrice");
        deviceDao.updateDeviceById(new Integer(deviceId),deviceName,devicePrice);
        Device dev=deviceDao.findDeviceById(new Integer(deviceId));
        /*List<Device> list=new ArrayList<Device>();
        list.add(dev);
        makeJson(list);*/
        return "success";
    }


    @Override
    public String addDevice() throws IOException {
        String deviceClassId=request.getParameter("deviceClassId");
        String deviceName=request.getParameter("deviceName");
        String devicePrice=request.getParameter("devicePrice");
        Device dc=new Device();
        Deviceclass dcv=deviceClassDao.findDeviceClass(new Integer(deviceClassId));
        dc.setDeviceclassByDeviceClassId(dcv);
        dc.setDeviceName(deviceName);
        dc.setDevicePrice(devicePrice);
        deviceDao.addDevice(dc);
        return "success";
    }

    @Override
    public String deleteDevice() throws IOException {
        String deviceId=request.getParameter("deleteDeviceId");
        Device dc=deviceDao.findDeviceById(new Integer(deviceId));
        deviceDao.deleteDevice(dc);
        return "success";
    }
}
