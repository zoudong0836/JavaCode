## 多对多单向外键

* @ManyToMany
* @JoinTable(name="teachers_students", joinColumns={@JoinColumn(name="sid")}, inverseJoinColumns={@JoinColumn(name="tid")})