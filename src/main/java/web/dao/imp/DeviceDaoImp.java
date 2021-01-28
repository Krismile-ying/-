package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import web.dao.DeviceDao;
import web.model.Device;
import web.model.Deviceclass;

import java.util.ArrayList;
import java.util.List;

public class DeviceDaoImp extends HibernateDaoSupport implements DeviceDao{
    @Override
    public List<Device> findAllDevice() {
        List<Device>list=getHibernateTemplate().find("from web.model.Device");
        return list;
    }

    @Override
    public List<Device> findDeviceByDeviceClassId(int deviceClassId) {
        List<Device>list=getHibernateTemplate().find("from web.model.Device where deviceClassId=?",deviceClassId);
        return list;
    }

    @Override
    public Device findDeviceById(int deviceId) {
        List<Device>list=getHibernateTemplate().find("from web.model.Device where deviceId=?",deviceId);
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
    @Override
    public List<Device> findDeviceByName(String deviceName) {
        List<Device>list=getHibernateTemplate().find("from web.model.Device where deviceName like '%"+ deviceName + "%'");
        return list;
    }

    @Override
    public List<Device> findDeviceByPrice(String low, String high) {
        String[] params={low,high};
        List<Device>list=getHibernateTemplate().find("from web.model.Device where devicePrice between "+ low + " and "+high);
        return list;
    }

    @Override
    public List<Device> findDeviceByFuzzy(String deviceClassName, String deviceName, String low, String high) {
        String hql="from Device where deviceName like '%"+ deviceName +"%' and devicePrice between "+ low +" and "+high;
        List<Device>preList=getHibernateTemplate().find(hql);//预查询满足条件的设备
        List<Device>finalList=new ArrayList<Device>();
        for (Device d:preList){//从数组里获取满足两个条件的设备
            String hqll="from Deviceclass where deviceClassId="+d.getDeviceclassByDeviceClassId().getDeviceClassId()+"and deviceClassName like '%"+deviceClassName+"%'";
            List<Deviceclass>dclist=getHibernateTemplate().find(hqll);
            d.setDeviceclassByDeviceClassId(dclist.get(0));
            if(dclist!=null){
                finalList.add(d);
            }
        }
        return finalList;
    }
    @Override
    public void updateDeviceById(int deviceId, String deviceName, String devicePrice) {
        Device dev=findDeviceById(deviceId);
        dev.setDeviceName(deviceName);
        dev.setDevicePrice(devicePrice);
        getHibernateTemplate().update(dev);
    }

    @Override
    public void addDevice(Device device) {
        getHibernateTemplate().save(device);
    }

    @Override
    public void deleteDevice(Device device) {
        getHibernateTemplate().delete(device);
    }
}
