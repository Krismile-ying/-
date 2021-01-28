package web.model;

import javax.persistence.*;

@Entity
public class Shopingcart {
    private Integer shopingcartId;
    private Integer buyNum;
    private Device device;
    private User user;

    @Id
    @Column(name = "ShopingcartID", nullable = false)
    public Integer getShopingcartId() {
        return shopingcartId;
    }

    public void setShopingcartId(Integer shopingcartId) {
        this.shopingcartId = shopingcartId;
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

        Shopingcart that = (Shopingcart) o;

        if (shopingcartId != null ? !shopingcartId.equals(that.shopingcartId) : that.shopingcartId != null)
            return false;
        if (buyNum != null ? !buyNum.equals(that.buyNum) : that.buyNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopingcartId != null ? shopingcartId.hashCode() : 0;
        result = 31 * result + (buyNum != null ? buyNum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "DeviceID", referencedColumnName = "DeviceID")
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
