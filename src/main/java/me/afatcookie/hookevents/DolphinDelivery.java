package me.afatcookie.hookevents;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class DolphinDelivery extends HookEventBuilder {
    @Override
    String eventName() {
        return "Dolphin Delivery";
    }

    @Override
    String eventDescription() {
        return "Spawns a Dolphin which gives loot!";
    }

    @Override
    int eventChance() {
        return 50;
    }


    @Override
    void execute(PlayerFishEvent e, Player player, Location location) {
            Dolphin dolphinOne = location.getWorld().spawn(location.add(1, 1, 0), Dolphin.class);
            dolphinOne.setRotation(0, -90);
            dolphinOne.setInvulnerable(true);
            dolphinOne.setCustomName(ChatColor.GOLD + "Delivery!");
            dolphinOne.setAI(true);
            dolphinOne.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
            player.sendMessage(ChatColor.GOLD + "DOLPHIN DELIVERY");
            player.sendMessage(ChatColor.GOLD + "You've received a: " + ChatColor.AQUA + "DIAMOND!");
            player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
            new BukkitRunnable(){
                int t = 0;
                @Override
                public void run() {
                    t++;

                    if (t == 3){
                        dolphinOne.remove();
                        this.cancel();
                    }
                }

            }.runTaskTimer(HookEvents.getInstance(), 0, 20L);
        }
    }
