## 一对多单向外键

* @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
* JoinColumn(name="cid")

总结: 多对一时候, 多方设置EAGER, 一方设置LAZY