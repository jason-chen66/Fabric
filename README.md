# Backend
这是项目的后端，使用Spring整合Fabric和IPFS开发。doc文件夹下有UML图与接口文档，sql文件下有数据库脚本。后端中主要数据结构描述如下：

用户部分数据库设计（为直观表述，不使用ER图；类名为表名，属性为数据库字段）：

![](.\pictures\userDatabase.png)

用户信息部分事务对象关系类图：

![](.\pictures\userDatabase.png)

作品部分数据库设计：

![](.\pictures\workDatabase.png)

作品部分事务对象关系类图：

![](.\pictures\workBo.png)

# Fabric & IPFS

本部分的设计详情见详细设计文档。

本部分主要包含调用链码，IPFS传输文件，注册用户，由后端进行调用。

Hyperledger Fabric是在某种程度上允许创建授权和非授权的区块链。Hyperledger通过提供一个针对身份识别，可审计、隐私安全和健壮的模型，使得缩短计算周期、提高规模效率和响应各个行业的应用需求成为可能。本产品主要使用Fabric存储作品id。

IPFS将数字作品的实际内容保存在分布式文件系统中，从而确保数字作品内容的安全性和不可篡改性。



