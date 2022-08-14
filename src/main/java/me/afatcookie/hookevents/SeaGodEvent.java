package me.afatcookie.hookevents;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SeaGodEvent extends HookEventBuilder {
    @Override
    String eventName() {
        return "Sea God";
    }

    @Override
    String eventDescription() {
        return "You're Basically Screwed";
    }

    @Override
    int eventChance() {
        return 1;
    }

    @Override
    void execute(PlayerFishEvent e, Player player, Location location) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location.add(0, 5, 0), EntityType.ARMOR_STAND);
        armorStand.getEquipment().setHelmet(new ItemStack(Material.BEACON, 1));
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(ChatColor.RED + "Water God");
        armorStand.setGravity(false);
        player.sendMessage("You're screwed Lol");
        new BukkitRunnable(){
            int t = 0;
            int yaw = 0;
            @Override
            public void run() {
                t++;
                yaw += 45;
                armorStand.setRotation(yaw, 0);
                if (t != 60){
                    for (double i = 0; i < 20; i+= 0.2) {
                        Location start = armorStand.getLocation();
                        Location end = player.getLocation();
                        Vector finish = end.subtract(start).toVector();
                        finish.multiply(i);
                        start.add(finish);
                        player.getWorld().spawnParticle(Particle.HEART, start, 1);
                        start.subtract(finish);
                        finish.normalize();
                    }
                }

                if (t == 30){
                    armorStand.remove();
                    player.sendMessage("Your dead now");
                    this.cancel();
                }

            }

        }.runTaskTimer(HookEvents.getInstance(), 0, 5L);
    }
}
