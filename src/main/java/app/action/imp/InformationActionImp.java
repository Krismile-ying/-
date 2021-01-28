package app.action.imp;

import app.action.InformationAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import web.dao.InformationDao;
import web.model.Information;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class InformationActionImp extends ActionSupport implements InformationAction {
    private Integer informationID;

    public Integer getInformationID() {
        return informationID;
    }

    public void setInformationID(Integer informationID) {
        this.informationID = informationID;
    }

    //咨询标题
    private String informationTitle;
    //咨询标题图片名称
    private String informationImageFileName;
    //咨询标题图片
    private File informationImage;
    //咨询简介
    private String informationBrief;
    //咨询富文本内容
    private String informationMainBody;

    public String getInformationTitle() {
        return informationTitle;
    }

    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle;
    }

    public String getInformationImageFileName() {
        return informationImageFileName;
    }

    public void setInformationImageFileName(String informationImageFileName) {
        this.informationImageFileName = informationImageFileName;
    }

    public File getInformationImage() {
        return informationImage;
    }

    public void setInformationImage(File informationImage) {
        this.informationImage = informationImage;
    }

    public String getInformationBrief() {
        return informationBrief;
    }

    public void setInformationBrief(String informationBrief) {
        this.informationBrief = informationBrief;
    }

    public String getInformationMainBody() {
        return informationMainBody;
    }

    public void setInformationMainBody(String informationMainBody) {
        this.informationMainBody = informationMainBody;
    }
    InformationDao informationDao;

    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
    }

    @Override
    public String addInformation() throws IOException {
        Information information=new Information();
        String informationContent="<x>"+informationTitle+"</x>"+"<x>"+informationBrief+"</x>"+"<x>"+informationMainBody+"</x>";
        information.setInformationContent(informationContent);
        SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        information.setInformationCreateTime(dFormat.format(new Date()));
        if (informationImageFileName!=null){
            //realPath是Tomcat的webapps\DeviceManage\image目录
            String realPath= ServletActionContext.getRequest().getRealPath("/image");
            String hz=informationImageFileName.substring(informationImageFileName.lastIndexOf("."));
            String newFileName= UUID.randomUUID().toString()+hz;
            OutputStream os=new FileOutputStream(new File(realPath,newFileName));
            String titleImageFullName="/DeviceManage/image/"+newFileName;
            information.setInformationImage(titleImageFullName);
            InputStream is=new FileInputStream(informationImage);
            byte flush[]=new byte[1024];
            int len=0;
            while (0<=(len=is.read(flush))){
                os.write(flush,0,len);
            }
            is.close();
            os.close();
        }else {
            System.out.println("未上传图片");
        }
        informationDao.addInformation(information);
        return "success";
    }

    @Override
    public String updateInformation() throws IOException {
        Information dev=informationDao.findInformationById(informationID);
        String informationContent="<x>"+informationTitle+"</x>"+"<x>"+informationBrief+"</x>"+"<x>"+informationMainBody+"</x>";
        dev.setInformationContent(informationContent);
        SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dev.setInformationCreateTime(dFormat.format(new Date()));
        if (informationImageFileName!=null){
            //realPath是Tomcat的webapps\DeviceManage\image目录
            String realPath= ServletActionContext.getRequest().getRealPath("/image");
            String hz=informationImageFileName.substring(informationImageFileName.lastIndexOf("."));
            String newFileName= UUID.randomUUID().toString()+hz;
            OutputStream os=new FileOutputStream(new File(realPath,newFileName));
            String titleImageFullName="/DeviceManage/image/"+newFileName;
            dev.setInformationImage(titleImageFullName);
            InputStream is=new FileInputStream(informationImage);
            byte flush[]=new byte[1024];
            int len=0;
            while (0<=(len=is.read(flush))){
                os.write(flush,0,len);
            }
            is.close();
            os.close();
        }else {
            System.out.println("未上传图片");
        }
        informationDao.editInformation(dev);
        return "success";
    }
}
