package fr.mssteur.mssbingo.commands;

import fr.mssteur.mssbingo.guis.ConfigMenu;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            player.updateInventory();

            ItemStack configurer = new ItemStack(Material.COMPASS, 1);
            ItemMeta customM = configurer.getItemMeta();
            customM.setDisplayName("§5Config");
            customM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
            customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            configurer.setItemMeta(customM);

            if(!player.getInventory().contains(Material.COMPASS)){
                player.getInventory().setItem(4, configurer);
                player.updateInventory();
            }

            ConfigMenu.CONFIG.open(player);
        }

        return true;
    }
}
