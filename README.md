# Fabric
这是客户端的代码，需要和后端的业务逻辑结合，主要包含调用链码，IPFS传输文件，注册用户



# Backend（2023.4.15）
与Spring框架整合完成，更新了后端业务逻辑，doc文件夹下有类图和最新的接口，sql文件下有最新的数据库代码
调用的几个点如下：

上链`cn.edu.xmu.blockchainandmultimedia.service.CertificationService#cochain`
![](.\READMEpictures\1.jpg)

注册用户`cn.edu.xmu.blockchainandmultimedia.service.UserService#register`

![](.\READMEpictures\2.jpg)

抽取哈希码//TODO