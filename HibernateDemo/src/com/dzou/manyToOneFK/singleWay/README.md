## 多对一单向外键

* @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
* JoinColumn(name="cid", referencedColumnName="CID")