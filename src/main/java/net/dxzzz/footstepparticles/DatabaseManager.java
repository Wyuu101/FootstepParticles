package net.dxzzz.footstepparticles;

import co.aikar.idb.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {

    private final Database userDb;
    public DatabaseManager(String username, String password, String dbname, String host, int port){
        String address = host+":"+port;
        DatabaseOptions userDb_options=DatabaseOptions.builder().mysql(username, password, dbname, address).build();
        userDb = PooledDatabaseOptions.builder().options(userDb_options).createHikariDatabase();
    }

    public Database getUserDb (){
        return userDb;
    }

    public void createForm_PersonalSettings() {
        try {
            userDb.executeUpdate("CREATE TABLE IF NOT EXISTS PersonalSettings (" +
                    "uuid VARCHAR(255) PRIMARY KEY NOT NULL, " +
                    "username VARCHAR(255) NOT NULL, " +
                    "particle VARCHAR(255)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 更新玩家设置内容
    public void updatePersonalSettings(String uuid, String username,String particle){
        try{
            userDb.executeUpdate("INSERT INTO PersonalSettings (uuid, username, particle) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE  username = VALUES(username),  particle = VALUES(particle);",uuid,username,particle);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 获取玩家设置的粒子效果
    public String getParticle(String uuid){
        try{
           return userDb.getFirstColumn("SELECT particle FROM PersonalSettings WHERE uuid = ?",uuid);
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeDataBase(){
        if(userDb!= null){
            userDb.close();
        }
        DB.close();
    }

}
