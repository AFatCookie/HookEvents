package me.afatcookie.hookevents;

import org.bukkit.plugin.java.JavaPlugin;

public final class HookEvents extends JavaPlugin {

    private static HookEvents plugin;

    public static HookEvents getInstance(){
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new HookEventListener(), this);
        getServer().getPluginManager().registerEvents(new ChestOpenListener(), this);
    }
}
