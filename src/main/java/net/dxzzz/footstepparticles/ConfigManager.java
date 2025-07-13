package net.dxzzz.footstepparticles;

import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {
    private final FootstepParticles plugin;


    public int interval;
    public int count;
    public float offsetX, offsetY, offsetZ;
    public String db_host;
    public int db_port;
    public String db_name;
    public String db_user;
    public String db_pass;
    public int preview_duration;
    public int preview_interval;
    public ConfigManager(FootstepParticles plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfig();
        interval = config.getInt("footprint.interval-ticks", 5);
        count = config.getInt("footprint.count", 3);
        offsetX = (float) config.getDouble("footprint.offset-x", 0.2);
        offsetY = (float) config.getDouble("footprint.offset-y", 0.1);
        offsetZ = (float) config.getDouble("footprint.offset-z", 0.2);
        preview_duration = config.getInt("footprint.preview_duration", 60);
        preview_interval = config.getInt("footprint.preview_interval", 5);
        db_host = config.getString("DataBase.MySQL.Host", "localhost");
        db_port = config.getInt("DataBase.MySQL.Port", 3306);
        db_name = config.getString("DataBase.MySQL.Name", "footprint");
        db_user = config.getString("DataBase.MySQL.Username", "root");
        db_pass = config.getString("DataBase.MySQL.Password", "");

    }



}
