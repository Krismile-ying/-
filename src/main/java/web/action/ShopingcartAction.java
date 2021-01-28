package web.action;

import java.io.IOException;

public interface ShopingcartAction {
    public void findAllShopingcart()throws IOException;
    //获得指定购物车编号的购物车对象
    public String findShopingcartById()throws IOException;
    //新增一个购物车对象
    public String addShopingcart()throws IOException;
    //删除一个购物车对象
    public String deleteShopingcart()throws IOException;
    String updateShopingcart() throws IOException;
    //获得指定用户编号的所有购物车对象的列表
    public void findAllShopingcartByUserId()throws IOException;
    //结算购物车
    public String jiesuanShopingcart()throws IOException;
}
