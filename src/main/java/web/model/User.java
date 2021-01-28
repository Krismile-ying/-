package web.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private Set<Shopingcart> shopingcarts;
    private Set<Shopingorder> shopingorders;

    @Id
    @Column(name = "UserID", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UserName", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "UserPassword", nullable = true, length = 255)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(user.userPassword) : user.userPassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Set<Shopingcart> getShopingcarts() {
        return shopingcarts;
    }

    public void setShopingcarts(Set<Shopingcart> shopingcarts) {
        this.shopingcarts = shopingcarts;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Set<Shopingorder> getShopingorders() {
        return shopingorders;
    }

    public void setShopingorders(Set<Shopingorder> shopingorders) {
        this.shopingorders = shopingorders;
    }
}
