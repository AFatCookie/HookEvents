package me.afatcookie.hookevents;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FishVomitEvent extends HookEventBuilder{
    @Override
    String eventName() {
        return "Fish Vomit";
    }

    @Override
    String eventDescription() {
        return "Shoot fish from ur mouth";
    }

    @Override
    int eventChance() {
        return 20;
    }

    @Override
    void execute(PlayerFishEvent e, Player player, Location location) {
        player.sendMessage(ChatColor.GOLD + "Might wanna lay off of fish for awhile");
        new BukkitRunnable() {
            int t = 0;
            @Override
            public void run() {
                if (t != 30) {
                    player.launchProjectile(Snowball.class, player.getEyeLocation().getDirection().multiply(2)).setItem(new ItemStack(Material.TROPICAL_FISH, 1));
                    t++;
                }
                if (t >= 10){
                    this.cancel();
                }
            }
        }.runTaskTimer(HookEvents.getInstance(), 0, 2L);
    }
}
