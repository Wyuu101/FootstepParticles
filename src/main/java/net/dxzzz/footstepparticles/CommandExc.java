package net.dxzzz.footstepparticles;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public class CommandExc implements CommandExecutor {
    private final FootstepParticles plugin;

    public CommandExc(FootstepParticles plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            return false;
        }
  
        // 重载配置文件
        if (args[0].equals("reload")) {
            plugin.reloadConfig();
            plugin.getConfigManager().loadConfig();
            sender.sendMessage("§b脚印特效 >> §aFootstepParticles 配置重载成功！");
            return true;
        }
        // 为某个玩家启用某个脚印特效
        else if (args[0].equals( "enable")){
            if (args.length < 3) {
                sender.sendMessage("§b脚印特效 >> §c缺少参数");
                return false;
            }
            // 判定玩家是否在线
            String targetName = args[1];
            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                sender.sendMessage("§b脚印特效 >> §c该玩家不在线");
                return true;
            }
            // 注意判定启用的脚印是否存在
            String particleName = args[2];
            if (!ParticleType.isValid(particleName)) {
                sender.sendMessage("§b脚印特效 >> §c无效的粒子特效名称");
                return true;
            }
            // 如果脚印合法，将其写入缓存
            sender.sendMessage("§b脚印特效 >> §a已为玩家§e" + targetName + "§a启用脚印粒子§e" + particleName);
            plugin.footstepListener.getParticleCache().put(target.getUniqueId(), particleName);
            // 更新LuckPerms权限节点
            changeUsingFootprint(target,particleName,plugin);
            return true;
        }
        // 为某个玩家清除脚印特效
        else if (args[0].equals( "clear")){
            if (args.length < 2) {
                sender.sendMessage("§b脚印特效 >> §c缺少参数");
                return false;
            }
            // 判定玩家是否在线
            String targetName = args[1];
            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                sender.sendMessage("§b脚印特效 >> §c该玩家不在线");
                return true;
            }
            // 更新数据库
            sender.sendMessage("§b脚印特效 >> §a已清除玩家§e"+targetName+"§a的脚印特效");
            plugin.footstepListener.getParticleCache().put(target.getUniqueId(), "null");
            return true;
        }
        else if (args[0].equals( "list")){
            ParticleType.sendParticleHelp(sender);
            return true;
        }
        else if (args[0].equals("preview")){
            if (args.length < 3) {
                sender.sendMessage("§b脚印特效 >> §c缺少参数");
                return false;
            }
            // 判定玩家是否在线
            String targetName = args[1];
            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                sender.sendMessage("§b脚印特效 >> §c该玩家不在线");
                return true;
            }
            // 注意判定启用的脚印是否存在
            String particleName = args[2];
            if (!ParticleType.isValid(particleName)) {
                sender.sendMessage("§b脚印特效 >> §c无效的粒子特效名称");
                return true;
            }
            // 如果脚印名称合法，开始向玩家提供效果预览
            ParticlePreviewer previewer = new ParticlePreviewer(plugin);
            previewer.sendPreviewParticle(target, particleName);
            return true;
        }
        else{
            return false;
        }
    }


    // 调用该函数之前需检查脚印名称合法性
    // 更新Lp权限节点
    public static void changeUsingFootprint(Player player, String particleName, JavaPlugin plugin) {
        if(particleName.contains(particleName)){
            PermissionManager permissionManager = new PermissionManager(player,plugin);
            List<String> usingPermissions =permissionManager.getUsingPermissions("footprint.using");
            if(usingPermissions.size()>0){
                if(usingPermissions.get(0).equalsIgnoreCase("footprint.using."+particleName)){
                    return;
                }
                else {
                    for(String usingPermission : usingPermissions){
                        permissionManager.removePermission(player, usingPermission);
                    }
                    permissionManager.addPermission(player, "footprint.using."+particleName);
                }
            }
            else {
                permissionManager.addPermission(player, "footprint.using."+particleName);
            }
        }
    }
}
