package net.dxzzz.footstepparticles;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class FootprintPlaceholderExpansion extends PlaceholderExpansion {
    private static FootprintPlaceholderExpansion instance;
    private static String identifier;
    private static JavaPlugin plugin = JavaPlugin.getProvidingPlugin(FootprintPlaceholderExpansion.class);

    public static FootprintPlaceholderExpansion getInstance() {
        if (instance == null) {
            instance = new FootprintPlaceholderExpansion("footprint");
        }
        return instance;
    }

    public FootprintPlaceholderExpansion(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getAuthor() {
        return "wyuu";
    }

    @Override
    public String getVersion() {
        return "1.2";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.startsWith("amount")) {
            String[] parts = identifier.split("_");
            if (parts.length < 2) {
                return null;
            } else {
                String tag = parts[1];
                PermissionManager permissionManager = new PermissionManager(player, plugin);
                List<String> havingPermission = permissionManager.getUsingPermissions("footprint.have");
                List<String> usingPermissions = permissionManager.getUsingPermissions("footprint.using");
                if(tag.equalsIgnoreCase("empty")) {
                    if(usingPermissions.isEmpty()) {
                        return "64";
                    }
                    else {
                        return "2";
                    }
                }
                else {
                    if (usingPermissions.contains("footprint.using." + tag)) {
                        return "64";
                    }
                    if (havingPermission.contains("footprint.have." + tag)) {
                        return "2";
                    }
                    return "1";
                }
            }

        } else if(identifier.startsWith("enchant")) {
            String[] parts = identifier.split("_");
            if (parts.length < 2) {
                return "0";
            } else {
                String tag = parts[1];
                PermissionManager permissionManager = new PermissionManager(player, plugin);
                List<String> havingPermission = permissionManager.getUsingPermissions("footprint.have");
                List<String> usingPermissions = permissionManager.getUsingPermissions("footprint.using");
                if(tag.equalsIgnoreCase("empty")) {
                    if(usingPermissions.isEmpty()) {
                        return "1";
                    }
                    else {
                        return "0";
                    }
                }
                else {
                    if (usingPermissions.contains("footprint.using." + tag)) {
                        return "1";
                    }
                    if (havingPermission.contains("footprint.have." + tag)) {
                        return "0";
                    }
                    return "0";
                }
            }
        }
        else if (identifier.startsWith("state")) {
            String[] parts = identifier.split("_");
            if (parts.length < 2) {
                return "错误的ID";
            } else {
                String tag = parts[1];
                PermissionManager permissionManager = new PermissionManager(player, plugin);
                List<String> havingPermission = permissionManager.getUsingPermissions("footprint.have");
                List<String> usingPermissions = permissionManager.getUsingPermissions("footprint.using");
                if(tag.equalsIgnoreCase("empty")) {
                    if(usingPermissions.isEmpty()) {
                        return "§a§l✔已选择";
                    }
                    else {
                        return "§a§l✔点击选择";
                    }
                }
                else {
                    if (usingPermissions.contains("footprint.using." + tag)) {
                        return "§a§l✔已选择";
                    }
                    if (havingPermission.contains("footprint.have." + tag)) {
                        return "§a§l✔点击选择";
                    }
                    return "错误的ID";
                }
            }
        }
        else {
            return null;
        }

    }
}
