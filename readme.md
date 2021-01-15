springboot单体应用滚动发布工具
====
最新版本：v2021.1.15

目录结构
----
```
.
├── SystemInitListener.java //在应用初始化后自动杀掉旧版服务
├── kill-old-server.sh //杀掉旧版服务的脚本文件
├── nginx.conf  //示例nginx.conf
├── readme.md
└── roll-update.sh  //更新服务的脚本文件
```



使用步骤
----

- 修改想要滚动发布的工程，在应用初始化后自动杀掉旧版服务，参考SystemInitListener.java


- 在jar包所在目录下放入kill-old-server.sh和roll-update.sh

- 修改nginx配置文件，参考nginx.conf

- 杀掉已经运行的服务
- 在jar包所在目录执行
```
sh ./roll-update.sh
```



