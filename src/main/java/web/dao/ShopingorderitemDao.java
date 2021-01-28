package web.dao;

import web.model.Shopingorderitem;

import java.util.List;

public interface ShopingorderitemDao {
    //获得所有订单项对象的列表
    public List<Shopingorderitem>findAllShopingorderitem();
    //获得指定订单项编号的订单项对象
    public Shopingorderitem findShopingorderitemById(int shopingorderitemId);
    //获得指定订单编号的订单项列表
    public List<Shopingorderitem>findShopingorderitemListByShopingorderId(int shopingorderId);
    //新增一个订单项对象
    public void addShopingorderitem(Shopingorderitem shopingorderitem);
    //删除一个订单项对象
    public void deleteShopingorderitem(Shopingorderitem shopingorderitem);
    void updateShopingorderitem(int shopingOrderItemId,int buyNum);
}
