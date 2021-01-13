package fr.mssteur.mssbingo.guis;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.mssteur.mssbingo.Main;
import fr.mssteur.mssbingo.commands.CommandTeamInfo;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamManagerMenu  extends CommandTeamInfo implements InventoryProvider {

    public int team_size = 2;

    public static final SmartInventory TEAM = SmartInventory.builder()
            .id("teamManagerGui")
            .provider(new TeamManagerMenu())
            .size(3, 9)
            .title(ChatColor.DARK_PURPLE + "TeamManager")
            .manager(JavaPlugin.getPlugin(Main.class).getInvManager())
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {

        ItemStack blackglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta blackglassM = blackglass.getItemMeta();
        blackglassM.setDisplayName(" ");
        blackglass.setItemMeta(blackglassM);

        ItemStack back = new ItemStack(Material.RED_ROSE);
        ItemMeta backM = back.getItemMeta();
        backM.setDisplayName(ChatColor.RED + "Retour");
        back.setItemMeta(backM);

        ItemStack redglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta redglassM = redglass.getItemMeta();
        redglassM.setDisplayName(" ");
        redglass.setItemMeta(redglassM);

        ItemStack item = new ItemStack(Material.SKULL_ITEM, team_size, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(ChatColor.GOLD + "Team de " + team_size);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);

        ItemStack solo = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skulll = (SkullMeta) solo.getItemMeta();
        skulll.setDisplayName(ChatColor.GOLD + "FFA");
        skulll.setOwner(player.getName());
        solo.setItemMeta(skulll);

        contents.set(0, 8, ClickableItem.of(new ItemStack(back),
                e -> ConfigMenu.CONFIG.open(player)));

        contents.set(1, 0, ClickableItem.empty(new ItemStack(redglass)));
        contents.set(1, 2, ClickableItem.empty(new ItemStack(redglass)));
        contents.set(1, 3, ClickableItem.empty(new ItemStack(redglass)));
        contents.set(1, 5, ClickableItem.empty(new ItemStack(redglass)));
        contents.set(1, 6, ClickableItem.empty(new ItemStack(redglass)));
        contents.set(1, 8, ClickableItem.empty(new ItemStack(redglass)));

        ItemStack itemm = new ItemStack(Material.SKULL_ITEM, team_size + 1, (short) 3);
        SkullMeta skullm = (SkullMeta) itemm.getItemMeta();
        skullm.setDisplayName(ChatColor.GOLD + "Team de " + team_size);
        skullm.setOwner(player.getName());
        itemm.setItemMeta(skullm);

        if (team_size == 1) {
            contents.set(1, 4, ClickableItem.empty(new ItemStack(solo)));
        } else if (team_size > 1) {
            contents.set(1, 4, ClickableItem.empty(new ItemStack(item)));
        }

        contents.fillRect(0, 0, 0, 7, ClickableItem.empty(new ItemStack(blackglass)));
        contents.fillRect(2, 0, 2, 8, ClickableItem.empty(new ItemStack(blackglass)));

    }

    @Override
    public void update(Player player, InventoryContents contents) {

        ItemStack item = new ItemStack(Material.SKULL_ITEM, team_size, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(ChatColor.GOLD + "Team de " + team_size);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);

        ItemStack solo = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skulll = (SkullMeta) solo.getItemMeta();
        skulll.setDisplayName(ChatColor.GOLD + "FFA");
        skulll.setOwner(player.getName());
        solo.setItemMeta(skulll);

        contents.set(1, 4, ClickableItem.of(new ItemStack(item), e -> {
            if (e.isLeftClick()) {
                team_size++;
            } else if (e.isRightClick() && team_size != 1) {
                team_size--;
            }
        }));
    }
}
