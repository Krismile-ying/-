package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import web.dao.ShopingorderitemDao;
import web.model.Shopingorderitem;

import java.util.List;

public class ShopingorderitemDaoImp extends HibernateDaoSupport implements ShopingorderitemDao {
    @Override
    public List<Shopingorderitem> findAllShopingorderitem() {
        List<Shopingorderitem> list=getHibernateTemplate().find("from web.model.Shopingorderitem");
        return list;
    }

    @Override
    public Shopingorderitem findShopingorderitemById(int shopingorderitemId) {
        List<Shopingorderitem> list=getHibernateTemplate().find("from web.model.Shopingorderitem where ShopingOrderItemID=?",shopingorderitemId);
        Shopingorderitem shopingorderitem=list.get(0);
        return shopingorderitem;
    }

    @Override
    public List<Shopingorderitem> findShopingorderitemListByShopingorderId(int shopingorderId) {
        List<Shopingorderitem> list=getHibernateTemplate().find("from web.model.Shopingorderitem where ShopingOrderID=?",shopingorderId);
        return list;
    }

    @Override
    public void addShopingorderitem(Shopingorderitem shopingorderitem) {
        getHibernateTemplate().save(shopingorderitem);
    }

    @Override
    public void deleteShopingorderitem(Shopingorderitem shopingorderitem) {
        getHibernateTemplate().delete(shopingorderitem);
    }

    @Override
    public void updateShopingorderitem(int shopingOrderItemId, int buyNum) {
        Shopingorderitem dev=findShopingorderitemById(shopingOrderItemId);
        dev.setBuyNum(buyNum);
        getHibernateTemplate().update(dev);
    }
}
