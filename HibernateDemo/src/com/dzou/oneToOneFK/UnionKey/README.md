## 一对一双向外键联合主键

* 创建主键类
* 主键类必须实现serializable接口, 重写hashCode()和equals()方法

1) 主键类 @Embeddable
2) 实体类 @EmbeddedId
