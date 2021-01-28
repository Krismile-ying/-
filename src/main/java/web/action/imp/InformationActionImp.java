package web.action.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import web.action.InformationAction;
import web.dao.InformationDao;
import web.model.Information;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class InformationActionImp extends ActionSupport implements InformationAction, ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private InformationDao informationDao;
    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
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
    public void makeJson(List<Information> list)throws IOException {
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //构造json输出字符串
        JSONArray jsonArray=new JSONArray();
        for(Information info:list){
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("InformationID",info.getInformationId());
            jsonObj.put("InformationContent",info.getInformationContent());
            jsonObj.put("InformationImage",info.getInformationImage());
            jsonObj.put("InformationCreateTime",info.getInformationCreateTime());
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
    public void findAllInformation() throws IOException {
        List<Information>list=informationDao.findAllInformation();
        makeJson(list);
    }

    @Override
    public void findInformationById() throws IOException {
        String id=request.getParameter("infoId");
        Information info=informationDao.findInformationById(new Integer(id));
        List<Information>list=new ArrayList<Information>();
        list.add(info);
        makeJson(list);
    }

    @Override
    public void showInformationByIdFromWebPortol() throws IOException {
         String infoId=request.getParameter("infoId");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        Information information=informationDao.findInformationById(new Integer(infoId));
        if(information!=null){
            String[] array=information.getInformationContent().split("<x>");
            String richtext="<html><body><head>"
                    +"<meta name='viewport' content='width=device-width,initial-scale=1.0,inimum-scale=0.5," +
                    "maximum-scale=2.0,user-scalable=yes'/>"
                    +"<style>img{max-width:100%;height:auto;}</style></head>"
                    +"<div class='text' style='text-align:center;font-size:35px'><strong>"
                    +array[1]
                    +"</strong></div>"
                    +"<div class='text' style='text-align:center'>"
                    +"<img src=\"http://localhost:8080"+information.getInformationImage()+"\"/>"
                    +"</div>"
                    +"<div class='text' style='text-align:right'>"
                    +information.getInformationCreateTime()
                    +"</div>"
                    +"<div class='text' style='text-align:center;font-size:20px;font-style:italic;'><p><strong>"
                    +array[2]+"</strong></p></div>"+"<p>"
                    +array[3]+"</p></body></html>";
            out.write(richtext);
            out.flush();
            out.close();
        }
    }

    @Override
    public void showInformationByIdFromAppPortol() throws IOException {
        String infoId=request.getParameter("infoId");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        Information information=informationDao.findInformationById(new Integer(infoId));
        if(information!=null){
            String[] array=information.getInformationContent().split("<x>");
            String str=array[3];
            array[3]=str.replace("http://localhost:8080","");
            String richtext="<html><body><head>"
                    +"<meta name='viewport' content='width=device-width," +
                    "initial-scale=1.0,inimum-scale=0.5,maximum-scale=2.0,user-scalable=yes'/>"
                    +"<style>img{max-width:100%;height:auto;}</style></head>"
                    +"<div class='text' style='text-align:center;font-size:35px'><strong>"
                    +array[1]
                    +"</strong></div>"
                    +"<div class='text' style='text-align:center'>"
                    +"<img src=\""+information.getInformationImage()+"\"/>"
                    +"</div>"
                    +"<div class='text' style='text-align:right'>"
                    +information.getInformationCreateTime()
                    +"</div>"
                    +"<div class='text' style='text-align:center;font-size:20px;font-style:italic;'><p><strong>"
                    +array[2]+"</strong></p></div>"+"<p>"
                    +array[3]+"</p></body></html>";
            out.write(richtext);
            out.flush();
            out.close();
        }
    }

    @Override
    public String deleteInformation() throws IOException {
        String id=request.getParameter("deleteInformationID");
        Information info=informationDao.findInformationById(new Integer(id));
        informationDao.deleteInformation(info);
        return "success";
    }

    @Override
    public String updateInformation() throws IOException {
        String id=request.getParameter("informationId");
        String informationContent=request.getParameter("informationContent");
        String informationImage=request.getParameter("informationImage");
        String informationCreateTime=request.getParameter("informationCreateTime");
        informationDao.updateInformation(new Integer(id),informationContent,informationImage,informationCreateTime);
        Information ifo=informationDao.findInformationById(new Integer(id));
        return "success";
    }
}
