package web.action;

import java.io.IOException;

public interface ShopingorderAction {
    public void findAllShopingorder()throws IOException;
    //获得指定订单编号的订单对象
    public String findShopingorderById()throws IOException;
    //获得所有指定用户编号的订单对象
    public void findShopingorderByUserId()throws IOException;
    //新增一个订单对象
    public String addShopingorder()throws IOException;
    //删除一个订单对象
    public String deleteShopingorder()throws IOException;
    String updateShopingorder() throws IOException;
}
