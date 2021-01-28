package web.model;

import javax.persistence.*;

@Entity
public class Shopingorderitem {
    private Integer shopingOrderItemId;
    private Integer buyNum;
    private Shopingorder shopingorderByShopingOrderId;
    private Device deviceByDeviceId;

    @Id
    @Column(name = "ShopingOrderItemID", nullable = false)
    public Integer getShopingOrderItemId() {
        return shopingOrderItemId;
    }

    public void setShopingOrderItemId(Integer shopingOrderItemId) {
        this.shopingOrderItemId = shopingOrderItemId;
    }

    @Basic
    @Column(name = "BuyNum", nullable = true)
    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shopingorderitem that = (Shopingorderitem) o;

        if (shopingOrderItemId != null ? !shopingOrderItemId.equals(that.shopingOrderItemId) : that.shopingOrderItemId != null)
            return false;
        if (buyNum != null ? !buyNum.equals(that.buyNum) : that.buyNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopingOrderItemId != null ? shopingOrderItemId.hashCode() : 0;
        result = 31 * result + (buyNum != null ? buyNum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ShopingOrderID", referencedColumnName = "ShopingOrderID", nullable = false)
    public Shopingorder getShopingorderByShopingOrderId() {
        return shopingorderByShopingOrderId;
    }

    public void setShopingorderByShopingOrderId(Shopingorder shopingorderByShopingOrderId) {
        this.shopingorderByShopingOrderId = shopingorderByShopingOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "DeviceID", referencedColumnName = "DeviceID", nullable = false)
    public Device getDeviceByDeviceId() {
        return deviceByDeviceId;
    }

    public void setDeviceByDeviceId(Device deviceByDeviceId) {
        this.deviceByDeviceId = deviceByDeviceId;
    }
}
