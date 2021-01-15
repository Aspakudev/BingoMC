package fr.mssteur.mssbingo.guis;

import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.mssteur.mssbingo.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamSelectorMenu implements InventoryProvider {

    public static final SmartInventory TEAMSELECTOR = SmartInventory.builder()
            .id("teamSelectorGui")
            .provider(new TeamSelectorMenu())
            .size(5, 9)
            .title(ChatColor.DARK_PURPLE + "TeamSelector")
            .manager(JavaPlugin.getPlugin(Main.class).getInvManager())
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
