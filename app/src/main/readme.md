```
@Entity 用于标识这是一个需要Greendao帮我们生成代码的bean

@Id 标明主键，括号里可以指定是否自增

@Property 用于设置属性在数据库中的列名（默认不写就是保持一致）

@NotNull 非空

@Transient 标识这个字段是自定义的不会创建到数据库表里

@Unique 添加唯一约束

@ToOne 是将自己的一个属性与另一个表建立关联（外键） 
@ToMany的属性referencedJoinProperty，类似于外键约束。

@JoinProperty 对于更复杂的关系，可以使用这个注解标明目标属性的源属性。

```


4.）Gradle 插件配置

比如上面想指定生成DaoMaster 、DaoSession、Dao位置，可以在model的build.gradle根目录下加入以下配置

```
greendao {
    schemaVersion 1
    daoPackage 'soft.zut.edu.cn.mygreendaotest.entity'
    targetGenDir 'src/main/java'
}
schemaVersion： 数据库schema版本，也可以理解为数据库版本号
daoPackage：设置DaoMaster 、DaoSession、Dao包名
targetGenDir：设置DaoMaster 、DaoSession、Dao目录
targetGenDirTest：设置生成单元测试目录
generateTests：设置自动生成单元测试用例
```
5.）实体@Entity注解


```
schema：告知GreenDao当前实体属于哪个schema
active：标记一个实体处于活动状态，活动实体有更新、删除和刷新方法
nameInDb：在数据中使用的别名，默认使用的是实体的类名
indexes：定义索引，可以跨越多个列
createInDb：标记创建数据库表
```
6.）基础属性注解
```
@Id :主键 Long型，可以通过@Id(autoincrement = true)设置自增长
@Property：设置一个非默认关系映射所对应的列名，默认是的使用字段名 举例：@Property (nameInDb="name")
@NotNul：设置数据库表当前列不能为空
@Transient ：添加次标记之后不会生成数据库表的列
```
7.)索引注解
```
@Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
@Unique：向数据库列添加了一个唯一的约束
```
8.）关系注解
```
@ToOne：定义与另一个实体（一个实体对象）的关系
@ToMany：定义与多个实体对象的关系
```