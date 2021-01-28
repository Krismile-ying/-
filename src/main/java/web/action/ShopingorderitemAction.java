package web.action;

import java.io.IOException;

public interface ShopingorderitemAction {
    public void findAllShopingorderitem() throws IOException;
    //获得指定订单项编号的订单项对象
    public String findShopingorderitemById() throws IOException;
    //获得指定订单编号的订单项列表
    public void findShopingorderitemListByShopingorderId()throws IOException;
    //新增一个订单项对象
    public String addShopingorderitem()throws IOException;
    //删除一个订单项对象
    public String deleteShopingorderitem()throws IOException;
    String updateShopingorderitem() throws IOException;
}
