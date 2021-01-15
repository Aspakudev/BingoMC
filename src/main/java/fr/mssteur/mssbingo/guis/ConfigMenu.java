package fr.mssteur.mssbingo.guis;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.mssteur.mssbingo.Main;
import fr.mssteur.mssbingo.objects.Game;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class ConfigMenu implements InventoryProvider {

    public int starter = 1;
    private Game plugin;

    public static final SmartInventory CONFIG = SmartInventory.builder()
            .id("configGui")
            .provider(new ConfigMenu())
            .size(5, 9)
            .title(ChatColor.DARK_PURPLE + "Config")
            .manager(JavaPlugin.getPlugin(Main.class).getInvManager())
            .build();

    @Override
    public void init(Player player, InventoryContents contents){

        ItemStack purpleglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10);
        ItemMeta purpleglassM = purpleglass.getItemMeta();
        purpleglassM.setDisplayName(" ");
        purpleglass.setItemMeta(purpleglassM);

        ItemStack grillebingo = new ItemStack(Material.PAINTING, 1);
        ItemMeta gbingoM = grillebingo.getItemMeta();
        gbingoM.setDisplayName(ChatColor.AQUA + "Afficher / Modifier le Bingo");
        gbingoM.setLore(Collections.singletonList(ChatColor.GRAY + "Affiche la grille de Bingo et permet de la modifier"));
        grillebingo.setItemMeta(gbingoM);

        ItemStack wlmanager = new ItemStack(Material.PAPER, 1);
        ItemMeta wlmm = wlmanager.getItemMeta();
        wlmm.setDisplayName(ChatColor.AQUA + "Modifier la WhiteList");
        wlmm.setLore(Collections.singletonList(ChatColor.GRAY + "Permet de gerer la WhiteList"));
        wlmanager.setItemMeta(wlmm);

        ItemStack doorleave = new ItemStack(Material.BARRIER, 1);
        ItemMeta dleavem = doorleave.getItemMeta();
        dleavem.setDisplayName(ChatColor.RED + "Fermer");
        doorleave.setItemMeta(dleavem);

        ItemStack startSlime = new ItemStack(Material.SLIME_BLOCK, 1);
        ItemMeta slimeM = startSlime.getItemMeta();
        slimeM.setDisplayName(ChatColor.GREEN + "Démarrer la partie");
        startSlime.setItemMeta(slimeM);

        ItemStack stopBlock = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta stopM = stopBlock.getItemMeta();
        stopM.setDisplayName(ChatColor.RED + "Annuler");
        stopBlock.setItemMeta(stopM);

        ItemStack bannerteam = new ItemStack(Material.BANNER, 1, (short)7);
        ItemMeta bannerteamM = bannerteam.getItemMeta();
        bannerteamM.setDisplayName(ChatColor.AQUA + "Gestionnaire de Team");
        bannerteamM.setLore(Collections.singletonList(ChatColor.GRAY + "Permet de gerer les différents parametres de Team"));
        bannerteam.setItemMeta(bannerteamM);

        ItemStack commandsutiles = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta cmmu = commandsutiles.getItemMeta();
        cmmu.setDisplayName(ChatColor.AQUA + "Commandes utiles");
        cmmu.setLore(Collections.singletonList(ChatColor.GRAY + "Affiche les commandes principales"));
        commandsutiles.setItemMeta(cmmu);

        contents.fill(ClickableItem.empty(new ItemStack(purpleglass)));

        contents.set(2, 2, ClickableItem.of(new ItemStack(grillebingo),
                e -> BingoEditorMenu.BINGOEDITOR.open(player)));
        contents.set(2, 6, ClickableItem.of(new ItemStack(wlmanager),
                e -> WhitelistEditorMenu.WHITELISTCONFIG.open(player)));
        contents.set(2, 4, ClickableItem.of(new ItemStack(bannerteam),
                e -> TeamManagerMenu.TEAM.open(player)));
        contents.set(3, 4, ClickableItem.of(new ItemStack(commandsutiles),
                e -> CommandsMenu.COMMANDS.open(player)));
        contents.set(0, 8, ClickableItem.of(new ItemStack(doorleave),
                e -> player.closeInventory()));
        if(starter == 1){
            contents.set(1, 4, ClickableItem.of(new ItemStack(startSlime),
                    e -> { plugin.launch(player);
                    }));
        }


    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {
        int state = inventoryContents.property("state", 0);
        inventoryContents.setProperty("state", state + 1);

        if(state % 5 != 0)
            return;
    }
}
