package com.dzou.manyToOneFK.doubleWay;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ClassRoom {


    private String cid;
    private String cname;
    private Set<Students> students;


    public ClassRoom() {
    }

    public ClassRoom(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Id
    @GeneratedValue(generator = "cid")
    @GenericGenerator(name = "cid", strategy = "assigned")
    @Column(length = 4)
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinColumn(name="cid")
    public Set<Students> getStudents() {
        return students;
    }

    public void setStudents(Set<Students> students) {
        this.students = students;
    }
}
