## 一对一单向外加

* @OneToOne(cascade=CascadeType.ALL)
* @JoinColumn(name="pid", unique=true)


## 一对一双向外键

* 主控方的配置同一对一单向外加关联相同
* @OneToOne(mappedBy="card")    // 被控方
* 双向关联, 必须设置mappedBy属性; 因为双向关联只能交给一方去控制, 不可能在双方都设置外键保存关联关系, 否则双方都无法保存