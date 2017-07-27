package com.hmz.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 韩明泽 on 2017/7/7.
 */
@Entity
public class User implements Serializable {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int userId;
    @Column(name="userName")
   private String userName;
    @Column(name="userMoney")
   private Integer userMoney;
    @Column(name="gender")
   private String gender;
    @Column(name="email")
   private String email;
    @Column(name="password")
   private String password;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int  userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Integer userMoney) {
        this.userMoney = userMoney;
    }

}
