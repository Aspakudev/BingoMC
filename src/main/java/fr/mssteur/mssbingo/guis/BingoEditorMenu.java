package fr.mssteur.mssbingo.guis;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.mssteur.mssbingo.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

//Ouvrir avec un click sur "PAINTING" ("grillebingo") dans ConfigMenu
//Permet de configurer le Bingo
public class BingoEditorMenu implements InventoryProvider {
    public static final SmartInventory BINGOEDITOR = SmartInventory.builder()
            .id("bingoEditoGui")
            .provider(new BingoEditorMenu())
            .size(5, 9)
            .title(ChatColor.DARK_PURPLE + "Bingo" + ChatColor.RED + "Editor")
            .manager(JavaPlugin.getPlugin(Main.class).getInvManager())
            .build();

    private final Random random = new Random();

    @Override
    public void init(Player player, InventoryContents contents) {

        int arr = 0;
        while(arr == 0){
            Main main = JavaPlugin.getPlugin(Main.class);
            int[] x = {2,3,4,5,6};
            int[] y = {0,1,2,3,4};
            int xi = 0;
            int yi = 0;

            ItemStack blackglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
            ItemMeta blackglassM = blackglass.getItemMeta();
            blackglassM.setDisplayName(" ");
            blackglass.setItemMeta(blackglassM);

            ItemStack redglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
            ItemMeta redglassM = redglass.getItemMeta();
            redglassM.setDisplayName(ChatColor.BLACK + "Bingo" + ChatColor.DARK_PURPLE + "Editor");
            redglass.setItemMeta(redglassM);

            ItemStack lightblackglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
            ItemMeta lbM = lightblackglass.getItemMeta();
            lbM.setDisplayName(" ");
            lightblackglass.setItemMeta(lbM);

            ItemStack clock = new ItemStack(Material.WATCH, 1);
            ItemMeta clM = clock.getItemMeta();
            clM.setDisplayName(" ");
            clock.setItemMeta(clM);

            ItemStack back = new ItemStack(Material.RED_ROSE);
            ItemMeta backM = back.getItemMeta();
            backM.setDisplayName(ChatColor.RED + "Retour");
            back.setItemMeta(backM);

            ItemStack orangeglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1);
            ItemMeta orangeglassM = orangeglass.getItemMeta();
            orangeglassM.setDisplayName(" ");
            orangeglass.setItemMeta(orangeglassM);

            contents.fillColumn(1, ClickableItem.empty(new ItemStack(lightblackglass)));
            contents.fillColumn(7, ClickableItem.empty(new ItemStack(lightblackglass)));

            contents.set(0, 0, ClickableItem.empty(new ItemStack(redglass)));
            contents.set(2, 0, ClickableItem.empty(new ItemStack(redglass)));
            contents.set(4, 0, ClickableItem.empty(new ItemStack(redglass)));

            contents.set(2, 8, ClickableItem.empty(new ItemStack(redglass)));
            contents.set(4, 8, ClickableItem.empty(new ItemStack(redglass)));

            contents.set(1, 0, ClickableItem.empty(new ItemStack(blackglass)));
            contents.set(3, 0, ClickableItem.empty(new ItemStack(blackglass)));

            contents.set(1, 8, ClickableItem.empty(new ItemStack(blackglass)));
            contents.set(3, 8, ClickableItem.empty(new ItemStack(blackglass)));

            contents.set(0, 8, ClickableItem.of(new ItemStack(back),
                    e -> ConfigMenu.CONFIG.open(player)));

            for(Integer i: main.getItemStack()){
                contents.set(y[yi], x[xi], ClickableItem.of(new ItemStack(Material.values()[i]), e -> {
                    e.setCurrentItem(new ItemStack(Material.values()[main.randommer()]));
                }));
                xi = (xi + 1)%5;
                if(xi == 0){
                    yi = (yi + 1)%5;
                }
            }

            arr = arr + 1;
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {}
}
