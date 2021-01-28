package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import web.dao.ShopingorderDao;
import web.model.Shopingorder;

import java.util.List;

public class ShopingorderDaoImp extends HibernateDaoSupport implements ShopingorderDao {
    @Override
    public List<Shopingorder> findAllShopingorder() {
        List<Shopingorder> list=getHibernateTemplate().find("from web.model.Shopingorder");
        return list;
    }

    @Override
    public Shopingorder findShopingorderById(int shopingorderId) {
        List<Shopingorder> list=getHibernateTemplate().find("from web.model.Shopingorder where ShopingOrderID=?",shopingorderId);
        Shopingorder shopingorder=list.get(0);
        return shopingorder ;
    }

    @Override
    public List<Shopingorder> findShopingorderByUserId(int userId) {
        List<Shopingorder> list=getHibernateTemplate().find("from web.model.Shopingorder where UserId=?",userId);
        return list;
    }

    @Override
    public void addShopingorder(Shopingorder shopingorder) {
        getHibernateTemplate().save(shopingorder);
    }

    @Override
    public void deleteShopingorder(Shopingorder shopingorder) {
        getHibernateTemplate().delete(shopingorder);
    }

    @Override
    public void updateShopingorder(int shopingOrderId, String receiver, String receiveAddress, String createtime, String moneyAmount) {
        Shopingorder dev=findShopingorderById(shopingOrderId);
        dev.setReceiver(receiver);
        dev.setReceiveAddress(receiveAddress);
        dev.setCreatetime(createtime);
        dev.setMoneyAmount(moneyAmount);
        getHibernateTemplate().update(dev);
    }
}
