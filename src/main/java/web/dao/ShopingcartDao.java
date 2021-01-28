package web.dao;

import web.model.Shopingcart;
import web.model.Shopingorder;

import java.util.List;

public interface ShopingcartDao {
    //获得所有购物车对象的列表
    public List<Shopingcart>findAllShopingcart();
    //获得指定购物车编号的购物车对象
    public Shopingcart findShopingcartById(int ShopingcartId);
    //新增一个购物车对象
    public void addShopingcart(Shopingcart shopingcart);
    //删除一个购物车对象
    public void deleteShopingcart(Shopingcart shopingcart);
    void updateShopingcart(int shopingcartId,int buyNum);
    //获得指定用户编号的所有购物车对象的列表
    public List<Shopingcart>findAllShopingcartByUserId(int userId);
    //结算购物车
    public void jiesuanShopingcart(Shopingorder so, List<Shopingcart> shopingcartList);
}
