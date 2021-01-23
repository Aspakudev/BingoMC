package fr.mssteur.mssbingo.guis;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.mssteur.mssbingo.Main;
import fr.mssteur.mssbingo.listeners.MainListeners;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class BingoMenu implements InventoryProvider {
    private final Random random = new Random();

    public static final SmartInventory BINGO = SmartInventory.builder()
            .id("bingoGui")
            .provider(new BingoMenu())
            .size(5, 9)
            .title(ChatColor.DARK_PURPLE + "Bingo")
            .manager(JavaPlugin.getPlugin(Main.class).getInvManager())
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        Main main = JavaPlugin.getPlugin(Main.class);

        int[] x = {2,3,4,5,6};
        int[] y = {0,1,2,3,4};
        int xi = 0;
        int yi = 0;

        contents.fillColumn(0, ClickableItem.empty(new ItemStack(Material.STAINED_GLASS_PANE)));
        contents.fillColumn(1, ClickableItem.empty(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10)));
        contents.fillColumn(7, ClickableItem.empty(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10)));
        contents.fillColumn(8, ClickableItem.empty(new ItemStack(Material.STAINED_GLASS_PANE)));

        for(Integer i: main.getItemStack()){
            contents.set(y[yi], x[xi], ClickableItem.empty(new ItemStack(Material.values()[i])));
            xi = (xi + 1)%5;
            if(xi == 0){
                yi = (yi + 1)%5;
            }
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 5 != 0)
            return;

        short rdmer = (short) random.nextInt(15);

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, rdmer);
        contents.fillColumn(0, ClickableItem.empty(glass));
        contents.fillColumn(8, ClickableItem.empty(glass));

    }
}