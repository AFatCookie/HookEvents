package me.afatcookie.hookevents;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DavyJonesChest extends HookEventBuilder {
    @Override
    String eventName() {
        return "Devy Jones's Chest";
    }

    @Override
    String eventDescription() {
        return "Spawns a Chest at the location!";
    }

    @Override
    int eventChance() {
        return 50;
    }

    @Override
    void execute(PlayerFishEvent e, Player player, Location location) {
        e.setExpToDrop(0);
        location.getBlock().setType(Material.CHEST, true);
        location.getBlock().setMetadata("Davy Chest", new FixedMetadataValue(HookEvents.getInstance(), "true"));
        new BukkitRunnable(){

            int t = 0;

            @Override
            public void run() {
                t++;

                if (t != 5 && location.getBlock().getType() != Material.CHEST){
                    this.cancel();
                }
                if (t == 5){
                    location.getBlock().setType(Material.AIR);
                    this.cancel();
                }
            }
        }.runTaskTimer(HookEvents.getInstance(), 0, 20L);

    }
}
