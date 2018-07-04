## 一对多(多对一)双向外键

1. 多方 (多方持有一方的引用)
* @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
* JoinColumn(name="cid", referencedColumnName="CID")

2. 一方 (一方持有多方的集合)
* OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
* JoinColumn(name="cid")