package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import web.dao.DeviceClassDao;
import web.model.Deviceclass;

import java.util.List;

public class DeviceClassDaoImp extends HibernateDaoSupport implements DeviceClassDao {
    public List<Deviceclass>findAllDeviceClass(){
        List<Deviceclass>list=getHibernateTemplate().find("from web.model.Deviceclass");
        return list;
    }
    public Deviceclass findDeviceClass(int deviceClassId){
        List<Deviceclass>list=getHibernateTemplate().find("from Deviceclass where deviceClassId=?",deviceClassId);
        Deviceclass dc=list.get(0);
        return dc;
    }

    @Override
    public void addDeviceClass(Deviceclass deviceclass) {
        getHibernateTemplate().save(deviceclass);
    }

    @Override
    public void deleteDeviceClass(Deviceclass deviceclass) {
        getHibernateTemplate().delete(deviceclass);
    }
}

