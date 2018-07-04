package com.dzou.manyToOneFK.singleWay;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Students {

    private int sid;
    private String sname;
    private String gender;
    private Date birthday;
    private String major;
    private ClassRoom classRoom;

    public Students() {
    }

    public Students(String sname, String gender, Date birthday, String major, ClassRoom classRoom) {
        this.sname = sname;
        this.gender = gender;
        this.birthday = birthday;
        this.major = major;
        this.classRoom = classRoom;
    }

    @Id
    @GeneratedValue
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
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

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cid", referencedColumnName = "CID")
    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return "Students{" +
                "sid=" + sid +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", major='" + major + '\'' +
                ", classRoom=" + classRoom +
                '}';
    }
}
