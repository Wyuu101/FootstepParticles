package net.dxzzz.footstepparticles;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public enum ParticleType {


    EXPLOSION_NORMAL("explosion_normal", Particle.EXPLOSION_NORMAL,"小型爆炸"), // 小型爆炸
    EXPLOSION_LARGE("explosion_large", Particle.EXPLOSION_LARGE,"中型爆炸"), // 中型爆炸
    EXPLOSION_HUGE("explosion_huge", Particle.EXPLOSION_HUGE,"大型爆炸"), // 大型爆炸
    FIREWORKS_SPARK("fireworks_spark", Particle.FIREWORKS_SPARK,"烟花火星"), // 烟花火星
    WATER_BUBBLE("water_bubble", Particle.WATER_BUBBLE,"水中气泡"), // 水中气泡（潜水时出现）
    WATER_SPLASH("water_splash", Particle.WATER_SPLASH,"水花飞溅"), // 水花飞溅
    WATER_WAKE("water_wake", Particle.WATER_WAKE,"划水时的波纹"), // 划水时的波纹
    SUSPENDED("suspended", Particle.SUSPENDED,"水中漂浮粒子"), // 水中漂浮粒子
    SUSPENDED_DEPTH("suspended_depth", Particle.SUSPENDED_DEPTH,"水底漂浮粒子"), // 水底漂浮粒子
    CRIT("crit", Particle.CRIT,"暴击粒子"), // 暴击粒子
    CRIT_MAGIC("crit_magic", Particle.CRIT_MAGIC,"魔法暴击粒子"), // 魔法暴击粒子（附魔剑攻击）
    SMOKE_NORMAL("smoke_normal", Particle.SMOKE_NORMAL,"小烟雾"), // 小烟雾
    SMOKE_LARGE("smoke_large", Particle.SMOKE_LARGE,"大烟雾"), // 大烟雾
    SPELL("spell", Particle.SPELL,"药水魔法"), // 药水魔法（基础）
    SPELL_INSTANT("spell_instant", Particle.SPELL_INSTANT,"即时药水（喷发感）"), // 即时药水（喷发感）
    SPELL_MOB("spell_mob", Particle.SPELL_MOB,"生物药水（带颜色）"), // 生物药水（带颜色）
    SPELL_MOB_AMBIENT("spell_mob_ambient", Particle.SPELL_MOB_AMBIENT,"生物药水环境效果"), // 生物药水环境效果
    SPELL_WITCH("spell_witch", Particle.SPELL_WITCH,"女巫药水轨迹"), // 女巫药水轨迹
    DRIP_WATER("drip_water", Particle.DRIP_WATER,"水滴（屋檐滴水）"), // 水滴（屋檐滴水）
    DRIP_LAVA("drip_lava", Particle.DRIP_LAVA,"岩浆滴水"), // 岩浆滴水
    VILLAGER_ANGRY("villager_angry", Particle.VILLAGER_ANGRY," 村民生气（红叉）"), // 村民生气（红叉）
    VILLAGER_HAPPY("villager_happy", Particle.VILLAGER_HAPPY,"村民开心（绿色爱心）"), // 村民开心（绿色爱心）
    TOWN_AURA("town_aura", Particle.TOWN_AURA,"村庄气氛（绿色烟雾）"), // 村庄气氛（绿色烟雾）
    NOTE("note", Particle.NOTE,"音符"), // 音符（音符盒）
    PORTAL("portal", Particle.PORTAL,"末地传送门粒子"), // 末地传送门粒子
    ENCHANTMENT_TABLE("enchantment_table", Particle.ENCHANTMENT_TABLE,"附魔桌粒子"), // 附魔桌粒子（书吸收效果）
    FLAME("flame", Particle.FLAME,"火焰粒子"), // 火焰粒子
    LAVA("lava", Particle.LAVA,"岩浆泡泡"), // 岩浆泡泡
    FOOTSTEP("footstep", Particle.FOOTSTEP,"脚步痕迹"), // 脚步痕迹（旧版使用）
    CLOUD("cloud", Particle.CLOUD,"白云"), // 白云（虚影）
    REDSTONE("redstone", Particle.REDSTONE,"红石粒子"), // 红石粒子（可变色）
    SNOWBALL("snowball", Particle.SNOWBALL,"雪球破裂"), // 雪球破裂
    SNOW_SHOVEL("snow_shovel", Particle.SNOW_SHOVEL,"铲雪粒子"), // 铲雪粒子
    SLIME("slime", Particle.SLIME,"史莱姆粒子"), // 史莱姆粒子（黏液）
    HEART("heart", Particle.HEART,"爱心（如喂食动物）"), // 爱心（如喂食动物）
    BARRIER("barrier", Particle.BARRIER,"屏障方块提示（红圈）"), // 屏障方块提示（红圈）
    WATER_DROP("water_drop", Particle.WATER_DROP,"水珠（类似水滴）"), // 水珠（类似水滴）
    ITEM_TAKE("item_take", Particle.ITEM_TAKE,"拾取物品（NPC交易）"), // 拾取物品（NPC交易）
    MOB_APPEARANCE("mob_appearance", Particle.MOB_APPEARANCE,"生物生成时的黑烟（例如唤魔者召唤）"), // 生物生成时的黑烟（例如唤魔者召唤）
    DRAGON_BREATH("dragon_breath", Particle.DRAGON_BREATH,"龙息粒子"), // 龙息粒子
    END_ROD("end_rod", Particle.END_ROD,"末地烛光粒子"), // 末地烛光粒子
    DAMAGE_INDICATOR("damage_indicator", Particle.DAMAGE_INDICATOR,"伤害飘血粒子"), // 伤害飘血粒子
    SWEEP_ATTACK("sweep_attack", Particle.SWEEP_ATTACK,"横扫攻击（剑横挥）"), // 横扫攻击（剑横挥）
    TOTEM("totem", Particle.TOTEM,"不死图腾粒子"), // 不死图腾粒子
    SPIT("spit", Particle.SPIT,"羊驼吐口水粒子"), // 羊驼吐口水粒子
    BLOCK_CRACK_TNT("block_crack+tnt",Particle.BLOCK_CRACK,"TNT破坏粒子",Material.TNT),
    BLOCK_CRACK_LEAVES("block_crack+leaves", Particle.BLOCK_CRACK, "橡木树叶破坏粒子", Material.LEAVES),
    BLOCK_CRACK_REDSTONE_BLOCK("block_crack+redstone_block", Particle.BLOCK_CRACK, "红石块破坏粒子", Material.REDSTONE_BLOCK),
    BLOCK_CRACK_STONE("block_crack+stone", Particle.BLOCK_CRACK, "石头破坏粒子", Material.STONE),
    BLOCK_CRACK_SLIME_BLOCK("block_crack+slime_block", Particle.BLOCK_CRACK, "粘液块破坏粒子", Material.SLIME_BLOCK),
    BLOCK_CRACK_DIRT("block_crack+dirt", Particle.BLOCK_CRACK, "泥土破坏粒子", Material.DIRT),
    BLOCK_CRACK_GRASS("block_crack+grass", Particle.BLOCK_CRACK, "草方块破坏粒子", Material.GRASS),
    BLOCK_CRACK_DIAMOND_BLOCK("block_crack+diamond_block", Particle.BLOCK_CRACK, "钻石块破坏粒子", Material.DIAMOND_BLOCK),
    BLOCK_CRACK_GLASS("block_crack+glass", Particle.BLOCK_CRACK, "玻璃破坏粒子", Material.GLASS),
    BLOCK_CRACK_SAND("block_crack+sand", Particle.BLOCK_CRACK, "沙子破坏粒子", Material.SAND),
    BLOCK_CRACK_GRAVEL("block_crack+gravel", Particle.BLOCK_CRACK, "沙砾破坏粒子", Material.GRAVEL),
    BLOCK_CRACK_NETHERRACK("block_crack+netherrack", Particle.BLOCK_CRACK, "下界岩破坏粒子", Material.NETHERRACK),
    BLOCK_CRACK_OBSIDIAN("block_crack+obsidian", Particle.BLOCK_CRACK, "黑曜石破坏粒子", Material.OBSIDIAN),
    BLOCK_CRACK_EMERALD_BLOCK("block_crack+emerald_block", Particle.BLOCK_CRACK, "绿宝石块破坏粒子", Material.EMERALD_BLOCK),
    BLOCK_CRACK_GOLD_BLOCK("block_crack+gold_block", Particle.BLOCK_CRACK, "金块破坏粒子", Material.GOLD_BLOCK),
    BLOCK_CRACK_IRON_BLOCK("block_crack+iron_block", Particle.BLOCK_CRACK, "铁块破坏粒子", Material.IRON_BLOCK),
    BLOCK_CRACK_COAL_BLOCK("block_crack+coal_block", Particle.BLOCK_CRACK, "煤块破坏粒子", Material.COAL_BLOCK),
    BLOCK_CRACK_SNOW_BLOCK("block_crack+snow_block", Particle.BLOCK_CRACK, "雪块破坏粒子", Material.SNOW_BLOCK),
    BLOCK_CRACK_ICE("block_crack+ice", Particle.BLOCK_CRACK, "冰块破坏粒子", Material.ICE),
    ;

    private final String name;
    private final Particle particle;
    private final String description;
    private final Material material;

    private static final Map<String, ParticleType> nameMap = new HashMap<>();

    static {
        for (ParticleType type : values()) {
            nameMap.put(type.name.toLowerCase(), type);
        }
    }

    ParticleType(String name, Particle particle,String description) {
        this.name = name;
        this.particle = particle;
        this.description = description;
        this.material = null;
    }

    ParticleType(String name, Particle particle,String description,Material material) {
        if (!isBlockParticle(particle)){
            throw new IllegalArgumentException("粒子 " + particle.name() + " 不能用于方块粒子效果，必须是 BLOCK_CRACK/BLOCK_DUST/FALLING_DUST 类型");
        }
        if (!material.isBlock()){
            throw new IllegalArgumentException("材料 " + material.name() + " 非方块材料");
        }
        this.name = name;
        this.particle = particle;
        this.description = description;
        this.material = material;
    }

    private static boolean isBlockParticle(Particle particle) {
        return particle == Particle.BLOCK_CRACK ||
                particle == Particle.BLOCK_DUST ||
                particle == Particle.FALLING_DUST;
    }


    public String getName() {
        return name;
    }

    public Particle getParticle() {
        return particle;
    }

    public String getDescription() {
        return description;
    }


    public static ParticleType fromName(String name) {
        if (name == null) return null;
        return nameMap.get(name.toLowerCase());
    }

    public static boolean isValid(String name) {
        return fromName(name) != null;
    }

    public static void sendParticleHelp(CommandSender sender) {
        sender.sendMessage("§b脚印特效 >> §e可用的粒子特效如下：");

        for (ParticleType type : ParticleType.values()) {
            sender.sendMessage("§a- " + type.getName() + " §7: " + type.getDescription());
        }
    }
}