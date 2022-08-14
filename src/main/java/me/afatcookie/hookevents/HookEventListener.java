package me.afatcookie.hookevents;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;
import java.util.Random;

public class HookEventListener implements Listener {

    private static final ArrayList<HookEventBuilder> hookEvents = new ArrayList<>();

    public HookEventListener(){
        hookEvents.add(new DolphinDelivery());
        hookEvents.add(new DavyJonesChest());
        hookEvents.add(new SeaGodEvent());
        hookEvents.add(new FishVomitEvent());
    }

    @EventHandler
    public static void HookListener(PlayerFishEvent e) {
        final Player player = e.getPlayer();
        final Location hookLocation = e.getHook().getLocation();
        final String heldItem = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        Random random = new Random();
        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            e.setCancelled(true);
            switch (heldItem.toLowerCase()){
                case "dolphin delivery":
                    getHookEvents().get(0).execute(e, player, hookLocation);
                    break;
                case "davy jones":
                    getHookEvents().get(1).execute(e,player,hookLocation);
                    break;
                case "sea god":
                    getHookEvents().get(2).execute(e,player,hookLocation);
                    break;
                case "fish vomit":
                    getHookEvents().get(3).execute(e,player,hookLocation);
                    break;
                default:
                    int randomNumber = random.nextInt(getHookEvents().size());
                    getHookEvents().get(randomNumber).execute(e,player,hookLocation);
                    break;
            }
        }
    }

    private static ArrayList<HookEventBuilder> getHookEvents() {
        return hookEvents;
    }

}
