package net.dxzzz.footstepparticles;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PermissionManager {
    public static LuckPerms luckPermsApi= LuckPermsProvider.get();
    private User user;
    private OfflinePlayer player;
    private Logger logger;

    public PermissionManager(OfflinePlayer player, JavaPlugin javaPlugin) {
        this.player = player;
        this.logger = javaPlugin.getLogger();
        this.user = luckPermsApi.getUserManager().getUser(player.getName());
    }

    private List<String> getAllPermissions() {
        List<String> permissionsList = new ArrayList<>();
        // 检查用户对象是否为空
        if (user != null) {
            // 获取权限映射（权限名称 -> 是否启用）
            Map<String, Boolean> permissionMap = user.getCachedData().getPermissionData().getPermissionMap();

            // 遍历权限映射，将权限名称添加到列表中
            permissionMap.forEach((permission, value) -> {
                if (value) {  // 只添加启用的权限
                    permissionsList.add(permission);
                }
            });
        } else {
            // 如果用户对象为空，输出日志提示
            logger.warning("无法找到玩家 " + player.getName() + " 的权限信息！");
        }
        return permissionsList;
    }
    //获取正在使用聊天字体的权限节点: liaotianziti.using.xxx
    public List<String> getUsingPermissions(String head) {
        List<String> originPermissions = getAllPermissions();
        List<String> filteredPermissions = new ArrayList<>();
        // 遍历原始权限列表
        for (String permission : originPermissions) {
            // 如果权限以指定的前缀开头，则添加到新的列表中
            if (permission.startsWith(head)) {
                filteredPermissions.add(permission);
            }
        }
        return filteredPermissions;
    }

    public  void removePermission(OfflinePlayer player, String permission) {
        // 获取 LuckPerms API 实例
        LuckPerms api = LuckPermsProvider.get();

        // 获取玩家的 LuckPerms 用户对象
        User user = api.getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            // 开始编辑用户数据
            user.data().remove(Node.builder(permission).build());

            // 保存更改
            api.getUserManager().saveUser(user);
        }
    }

    public  void addPermission(Player player, String permission) {
        // 获取 LuckPerms API 实例
        LuckPerms api = LuckPermsProvider.get();

        // 获取玩家的 LuckPerms 用户对象
        User user = api.getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            // 创建权限节点并设置为true（启用）
            Node node = Node.builder(permission)
                    .value(true)  // 设置权限为启用状态
                    .build();

            // 添加权限节点
            user.data().add(node);

            // 保存更改
            api.getUserManager().saveUser(user);
        }
    }



}

