# FootstepParticles
适用于Spigot1.12核心服务器集群的玩家脚印特效插件

# 一、使用说明 

- 多子服同时安装该插件，连接至同一MySQL数据库即可实现玩家设置跨服同步
- 插件支持热重载（数据库配置除外）
- 前置插件（强制依赖）: PlaceholderAPI, LuckPerms, ProtocolLib


# 二、权限与命令

- 权限
  默认所有命令仅终端和OP可用，权限节点: `FootstepParticles.Operator`

- 命令
  
  <img width="595" height="102" alt="image" src="https://github.com/user-attachments/assets/85dc1406-51bc-42ff-975c-b75fc2fc647e" />



# 三、配置文件
```yml
# 脚印粒子设置
footprint:
  interval-ticks: 3     # 每隔多少tick生成粒子
  count: 1              # 每次生成几个粒子
  offset-x: 0.2
  offset-y: 0.1
  offset-z: 0.2
  # 预览时间持续时长(ticks)
  preview_duration: 60
  # 预览期间粒子生成的时间间隔(ticks)
  preview_interval: 5

#数据库设置(仅支持MySQL)- 必填
DataBase:
  MySQL:
    Host: localhost
    Username: root
    Password:
    Port: 3306
    # 数据库名称
    Name: footprint
```

# 四、未来

本人时间精力有限，首发版本旨在攻克服务器关键技术，对于细节打磨可能不够到位。

- 未来更新方向

  - 使用协议库或者NMS对粒子发包与跨版本兼容进行进一步大伯
  - 完善命令补全提示
 
# 五、占位符扩展（非Dxzzz.Net开发人员可无视该部分）

该部分扩展用于为服务器自研GUI动态排序提供支持

##  1、权限：菜单的权限赋予请严格遵守如下格式
    ```
    例：
    /lp user <player> permission set footprint.have.<粒子名称>  -  使玩家拥有该粒子特效使用权限
    /lp user <player> permission set footprint.using.<粒子名称> - 标记玩家当前正在使用的粒子特效（用于标记，如不赋予，将会使占位符变量出现异常）
    ```

## 2、占位符
    ```
    %footpint_amount_<粒子名称>%  -  根据权限检查结果动态返回64、2、1
    %footpint_enchant_<粒子名称>%  -  根据权限检查结果动态返回0、1 
    ```
## 3、特殊说明

- 以上 ``<粒子名称>`` 对照表请执行命令 ``/footprint list`` 自行查阅
- 无粒子特效状态下的 ``<粒子名称>`` 为 ``empty``


