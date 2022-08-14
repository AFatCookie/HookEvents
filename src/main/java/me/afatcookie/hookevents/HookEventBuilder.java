package me.afatcookie.hookevents;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;

public abstract class HookEventBuilder {

     abstract String eventName();

    abstract String eventDescription();

    abstract int eventChance();

     abstract void execute(PlayerFishEvent e, Player player, Location location);

}
