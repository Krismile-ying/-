package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import web.dao.InformationDao;
import web.model.Information;

import java.util.List;

public class InformationDaoImp extends HibernateDaoSupport implements InformationDao {
    @Override
    public void addInformation(Information info) {
        getHibernateTemplate().save(info);
    }

    @Override
    public List<Information> findAllInformation() {
        List<Information> list=getHibernateTemplate().find("from web.model.Information");
        return list;
    }

    @Override
    public Information findInformationById(int infoId) {
        List<Information> list=getHibernateTemplate().find("from web.model.Information where InformationID=?",infoId);
        Information info=list.get(0);
        return info;
    }

    @Override
    public void deleteInformation(Information information) {
        getHibernateTemplate().delete(information);
    }

    @Override
    public void updateInformation(int informationId, String informationContent, String informationImage, String informationCreateTime) {
        Information dev=findInformationById(informationId);
        dev.setInformationContent(informationContent);
        dev.setInformationImage(informationImage);
        dev.setInformationCreateTime(informationCreateTime);
        getHibernateTemplate().update(dev);
    }

    @Override
    public void editInformation(Information information) {
        getHibernateTemplate().update(information);
    }
}
