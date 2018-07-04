package com.dzou.oneToOneFK.singleWay;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Students {

    private int sid;
    private String gender;
    private Date birthday;
    private String major;
    private IdCard card;

    public Students() {
    }

    public Students(String gender, Date birthday, String major, IdCard card) {
        this.gender = gender;
        this.birthday = birthday;
        this.major = major;
        this.card = card;
    }

    @Id
    @GeneratedValue
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="pid", unique=true)
    public IdCard getCard() {
        return card;
    }

    public void setCard(IdCard card) {
        this.card = card;
    }
}
