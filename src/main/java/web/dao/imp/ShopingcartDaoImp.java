package web.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import web.dao.ShopingcartDao;
import web.dao.ShopingorderDao;
import web.dao.ShopingorderitemDao;
import web.model.Device;
import web.model.Shopingcart;
import web.model.Shopingorder;
import web.model.Shopingorderitem;

import java.util.List;

public class ShopingcartDaoImp extends HibernateDaoSupport implements ShopingcartDao {
    @Override
    public List<Shopingcart> findAllShopingcart() {
        List<Shopingcart>list=getHibernateTemplate().find("from web.model.Shopingcart");
        return list;
    }

    @Override
    public Shopingcart findShopingcartById(int ShopingcartId) {
        List<Shopingcart>list=getHibernateTemplate().find("from web.model.Shopingcart where ShopingcartID=?",ShopingcartId);
        Shopingcart shopingcart=list.get(0);
        return shopingcart;
    }

    @Override
    public void addShopingcart(Shopingcart Shopingcart) {
        getHibernateTemplate().save(Shopingcart);
    }

    @Override
    public void deleteShopingcart(Shopingcart Shopingcart) {
        getHibernateTemplate().delete(Shopingcart);
    }

    @Override
    public void updateShopingcart(int shopingcartId, int buyNum) {
        Shopingcart dev=findShopingcartById(shopingcartId);
        dev.setBuyNum(buyNum);
        getHibernateTemplate().update(dev);
    }

    @Override
    public List<Shopingcart> findAllShopingcartByUserId(int userId) {
        List<Shopingcart>list=getHibernateTemplate().find("from web.model.Shopingcart where UserID=?",userId);
        return list;
    }

    private ShopingorderDao shopingorderDao;
    public ShopingorderDao getShopingorderDao() {
        return shopingorderDao;
    }

    public void setShopingorderDao(ShopingorderDao shopingorderDao) {
        this.shopingorderDao = shopingorderDao;
    }

    private ShopingorderitemDao shopingorderitemDao;
    public ShopingorderitemDao getShopingorderitemDao() {
        return shopingorderitemDao;
    }
    public void setShopingorderitemDao(ShopingorderitemDao shopingorderitemDao) {
        this.shopingorderitemDao = shopingorderitemDao;
    }

    @Override
    public void jiesuanShopingcart(Shopingorder so, List<Shopingcart> shopingcartList) {
        shopingorderDao.addShopingorder(so);
        for (Shopingcart sc:shopingcartList){
            Shopingorderitem soi=new Shopingorderitem();
            soi.setShopingorderByShopingOrderId(so);
            Device dev=sc.getDevice();
            soi.setDeviceByDeviceId(dev);
            int buyNum=sc.getBuyNum();
            soi.setBuyNum(buyNum);
            shopingorderitemDao.addShopingorderitem(soi);
        }
        for(Shopingcart sc:shopingcartList){
            deleteShopingcart(sc);
        }
    }
}
