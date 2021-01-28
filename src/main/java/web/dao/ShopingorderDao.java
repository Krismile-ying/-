package web.dao;

import web.model.Shopingorder;

import java.util.List;

public interface ShopingorderDao {
    //获得所有订单对象的列表
    public List<Shopingorder>findAllShopingorder();
    //获得指定订单编号的订单对象
    public Shopingorder findShopingorderById(int shopingorderId);
    //获得所有指定用户编号的订单对象
    public List<Shopingorder>findShopingorderByUserId(int userId);
    //新增一个订单对象
    public void addShopingorder(Shopingorder shopingorder);
    //删除一个订单对象
     public void deleteShopingorder(Shopingorder shopingorder);
    void updateShopingorder(int shopingOrderId,String receiver,String receiveAddress,String createtime,String moneyAmount);
 }
