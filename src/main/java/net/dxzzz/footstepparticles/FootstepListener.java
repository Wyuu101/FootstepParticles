package net.dxzzz.footstepparticles;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FootstepListener implements Listener {

    private final FootstepParticles plugin;
    private final Map<UUID, Long> lastEmitTime = new HashMap<>();
    private final Map<UUID, String> particleCache = new HashMap<>();
    private final DatabaseManager databaseManager;
    public FootstepListener(FootstepParticles plugin) {
        this.plugin = plugin;
        this.databaseManager = plugin.databaseManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String particle = databaseManager.getParticle(uuid);
            if (particle == null || particle.equals("null")) {
                particleCache.put(UUID.fromString(uuid), "null");
            }
            else{
                particleCache.put(UUID.fromString(uuid), particle);
            }
        });
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String player_name = player.getName();
        String uuid = player.getUniqueId().toString();
        // 先判定是否已经被载入缓存，避免引发报错
        if (particleCache.containsKey(UUID.fromString(uuid))) {
            String particleName = particleCache.get(UUID.fromString(uuid));
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                plugin.databaseManager.updatePersonalSettings(uuid, player_name, particleName);
            });
        }
        // 移除当前子服中的缓存数据
        particleCache.remove(UUID.fromString(uuid));
        lastEmitTime.remove(UUID.fromString(uuid));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        long now = System.currentTimeMillis();
        long interval = plugin.getConfigManager().interval * 50L; // ticks to ms
        // 如果粒子效果未被加载到缓存，则不生成粒子效果
        if (particleCache.get(uuid) == null ){
            return;
        }

        // 1、判定玩家被加载到缓存的脚印特效设置，并包装成Particle对象，同时判定脚印是否合法
        String particleName = particleCache.get(uuid);
        if (!ParticleType.isValid(particleName)){
             particleCache.put(uuid,"null");
             return;
        }
        boolean needBlock = false;
        Particle particle = null;
        Material material = null;

        // 2、字符串分割处理
        if (particleName.contains("+")){
            //如果包含加号，说明是方块类型相关的粒子效果
            String[] parts = particleName.split("\\+");
            needBlock = true;
            particle = Particle.valueOf(parts[0].toUpperCase());
            material = Material.valueOf(parts[1].toUpperCase());
        }
        else {
            needBlock = false;
            particle = Particle.valueOf(particleName.toUpperCase());
            material = null;
        }
        // 3、读取上一次生成粒子的时间,并判定时间间隔
        if (lastEmitTime.containsKey(uuid) && now - lastEmitTime.get(uuid) < interval) {
            return;
        }
        // 4、根据玩家缓存中的设置 生成对应的粒子效果（采用缓存机制）
        lastEmitTime.put(uuid, now);
        if (!needBlock) {
            Particle finalParticle = particle;
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getWorld().spawnParticle(
                            finalParticle,
                            player.getLocation(),
                            plugin.getConfigManager().count,
                            plugin.getConfigManager().offsetX,
                            plugin.getConfigManager().offsetY,
                            plugin.getConfigManager().offsetZ,
                            0
                    );
                }
            }.runTask(plugin);
        }
        else {
            Particle finalParticle = particle;
            Material finalmaterial = material;
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getWorld().spawnParticle(
                            finalParticle,
                            player.getLocation(),
                            plugin.getConfigManager().count,
                            plugin.getConfigManager().offsetX,
                            plugin.getConfigManager().offsetY,
                            plugin.getConfigManager().offsetZ,
                            new MaterialData(finalmaterial)
                    );
                }
            }.runTask(plugin);
        }
    }

    public Map<UUID, String> getParticleCache() {
        return particleCache;
    }
}