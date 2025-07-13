package net.dxzzz.footstepparticles;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class FootstepParticles extends JavaPlugin {
    private static FootstepParticles instance;
    private ConfigManager configManager;
    public DatabaseManager databaseManager;
    public FootstepListener footstepListener;
    public Logger logger;
    public static FootstepParticles getInstance() {
        return instance;
    }



    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info("§6===========[FootstepParticles开始加载]===========");
        logger.info("§6 _____ ____  ____  _____  ____  ____  _  _      _____ ");
        logger.info("§6/    //  _ \\/  _ \\/__ __\\/  __\\/  __\\/ \\/ \\  /|/__ __\\");
        logger.info("§6|  __\\| / \\|| / \\|  / \\  |  \\/||  \\/|| || |\\ ||  / \\  ");
        logger.info("§6| |   | \\_/|| \\_/|  | |  |  __/|    /| || | \\||  | |  ");
        logger.info("§6\\_/   \\____/\\____/  \\_/  \\_/   \\_/\\_\\\\_/\\_/  \\|  \\_/  ");
        logger.info("§6                                                      ");
        logger.info("§bAuthor: X_32mx");
        logger.info("§aQQ: 2644489337");
        instance = this;
        // 载入配置文件
        saveDefaultConfig();
        configManager = new ConfigManager(this);
        configManager.loadConfig();
        // 连接数据库
        databaseManager = new DatabaseManager(configManager.db_user,configManager.db_pass,configManager.db_name,configManager.db_host,configManager.db_port);
        if (databaseManager.getUserDb() == null){
            getLogger().severe("未连接到数据库");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        // 创建默认数据表
        databaseManager.createForm_PersonalSettings();
        // 注册监听事件
        footstepListener = new FootstepListener(this);
        getServer().getPluginManager().registerEvents(footstepListener, this);
        // 注册命令
        Bukkit.getPluginCommand("footprint").setExecutor(new CommandExc(this));
        // 注册占位符变量
        FootprintPlaceholderExpansion chatPlaceholderExpansion = FootprintPlaceholderExpansion.getInstance();
        chatPlaceholderExpansion.register();
        logger.info("§6===========[FootstepParticles加载完毕]===========");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        databaseManager.closeDataBase();
    }
}
