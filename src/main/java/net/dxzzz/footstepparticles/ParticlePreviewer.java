package net.dxzzz.footstepparticles;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

public class ParticlePreviewer {
    private final FootstepParticles plugin;
    private final Logger logger;

    public ParticlePreviewer(FootstepParticles plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public void sendPreviewParticle(Player player, String particleName) {
        // 解析粒子与方块
        Particle particle;
        Material material = null;

        try {
            if (particleName.contains("+")) {
                String[] parts = particleName.split("\\+");
                particle = Particle.valueOf(parts[0].toUpperCase());
                material = Material.getMaterial(parts[1].toUpperCase());
            } else {
                particle = Particle.valueOf(particleName.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            logger.warning("无效的粒子类型或方块名：" + particleName);
            return;
        }

        int durationTicks = plugin.getConfigManager().preview_duration;  // e.g. 60
        int intervalTicks = plugin.getConfigManager().preview_interval;  // e.g. 5

        Material finalMaterial = material;
        new BukkitRunnable() {
            int ticksPassed = 0;

            @Override
            public void run() {
                if (ticksPassed >= durationTicks || !player.isOnline()) {
                    this.cancel();
                    return;
                }

                Location loc = player.getEyeLocation().add(player.getLocation().getDirection().normalize().multiply(1.0));

                // 粒子数据（用于特殊粒子）
                Object data = null;
                if (particle == Particle.BLOCK_CRACK || particle == Particle.BLOCK_DUST || particle == Particle.FALLING_DUST) {
                    if (finalMaterial == null || !finalMaterial.isBlock()) {
                        logger.warning("BLOCK 类型粒子要求有效方块材质：" + particleName);
                        this.cancel();
                        return;
                    }

                    // 1.12 及以下使用 MaterialData，1.13+ 用 BlockData
                    data =new MaterialData( finalMaterial);
                }

                // 发射粒子
                try {
                    if (data != null) {
                        player.getWorld().spawnParticle(particle, loc, 10, 0, 0, 0, 0, data);
                    } else {
                        player.getWorld().spawnParticle(particle, loc, 10, 0, 0, 0, 0);
                    }
                } catch (Exception e) {
                    logger.warning("播放粒子失败：" + e.getMessage());
                    this.cancel();
                }

                ticksPassed += intervalTicks;
            }
        }.runTaskTimer(plugin, 0L, intervalTicks);
    }
}
