package me.afatcookie.hookevents;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ChestOpenListener implements Listener {

    @EventHandler
    public static void openChestListener(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getClickedBlock().getType() == Material.CHEST){
                final Player player = e.getPlayer();
                if (e.getClickedBlock().hasMetadata("Davy Chest")){
                    e.setCancelled(true);
                    e.getClickedBlock().setType(Material.AIR);
                    player.getInventory().addItem(lootPool());
                }
            }
        }
    }

    private static ItemStack[] lootPool(){
        return new ItemStack[]{new ItemStack(Material.DIAMOND, 1), new ItemStack(Material.EMERALD, 10),
        new ItemStack(Material.DRAGON_EGG, 1)};
    }
}
