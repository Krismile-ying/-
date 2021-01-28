package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import web.action.DeviceClassAction;
import web.dao.DeviceClassDao;
import web.model.Deviceclass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DeviceClassActionImp extends ActionSupport implements DeviceClassAction, ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private DeviceClassDao deviceClassDao;

    public void setDeviceClassDao(DeviceClassDao deviceClassDao) {
        this.deviceClassDao = deviceClassDao;
    }

    @Override
    public void findAllDeviceClass() throws IOException {
      List<Deviceclass> list=deviceClassDao.findAllDeviceClass();
      makeJson(list);
    }

    @Override
    public void findDeviceClass() throws IOException {
     String id=request.getParameter("deviceClassId");
     Deviceclass dc=deviceClassDao.findDeviceClass(new Integer(id));
     List<Deviceclass>list=new ArrayList<Deviceclass>();
     list.add(dc);
     makeJson(list);
    }

    @Override
    public String addDeviceClass() throws IOException {
        String deviceClassName=request.getParameter("deviceClassName");
        Deviceclass dc=new Deviceclass();
        dc.setDeviceClassName(deviceClassName);
        deviceClassDao.addDeviceClass(dc);
        return "success";
    }

    @Override
    public String deleteDeviceClass() throws IOException {
        String deviceClassId=request.getParameter("deleteDeviceClassId");
        Deviceclass dc=deviceClassDao.findDeviceClass(new Integer(deviceClassId));
        deviceClassDao.deleteDeviceClass(dc);
        return "success";
    }

    //回调函数，当Tomcat服务器端发送响应给移动端时，自动调用
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request=httpServletRequest;
    }
    //回调函数，当移动端向tomcat服务器端发送请求时，自动调用
    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
       response=httpServletResponse;
    }
    public void makeJson(List<Deviceclass>list)throws IOException{
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //构造json输出字符串
        JSONArray jsonArray=new JSONArray();
        for(Deviceclass dc:list){
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("DeviceClassID",dc.getDeviceClassId());
            jsonObj.put("DeviceClassName",dc.getDeviceClassName());
            jsonArray.add(jsonObj);
        }
        System.out.println(jsonArray.toString());
        JSONObject root=new JSONObject();
        root.put("data",jsonArray);
        out.write(root.toString());
        out.flush();
        out.close();
    }
}
