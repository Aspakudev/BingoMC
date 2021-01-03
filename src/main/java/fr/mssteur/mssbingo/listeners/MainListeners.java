package fr.mssteur.mssbingo.listeners;

import fr.mssteur.mssbingo.guis.ConfigMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainListeners implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        player.updateInventory();

        ItemStack configurer = new ItemStack(Material.COMPASS, 1);
        ItemMeta customM = configurer.getItemMeta();
        customM.setDisplayName("§5Config");
        customM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
        customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        configurer.setItemMeta(customM);

        player.getInventory().clear();
        player.getInventory().setItem(4, configurer);

        player.updateInventory();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();

        if(it == null) return;

        if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§5Config")){
            ConfigMenu.CONFIG.open(player);
        }
    }
}