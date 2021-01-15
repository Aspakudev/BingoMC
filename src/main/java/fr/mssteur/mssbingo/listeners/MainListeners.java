package fr.mssteur.mssbingo.listeners;

import fr.mssteur.mssbingo.Main;
import fr.mssteur.mssbingo.guis.ConfigMenu;
import fr.mssteur.mssbingo.guis.TeamSelectorMenu;
import fr.mssteur.mssbingo.objects.Game;
import fr.mssteur.mssbingo.objects.State;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainListeners implements Listener{

    private Main plugin;

    private Game game;

    public MainListeners(Main plugin){
        this.plugin = plugin;
    }

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

        ItemStack bannerTeam = new ItemStack(Material.BANNER, 1, (short)7);
        ItemMeta bannerTeamM = bannerTeam.getItemMeta();
        bannerTeamM.setDisplayName("§5Teams");
        bannerTeam.setItemMeta(bannerTeamM);

        player.getInventory().clear();
        player.getInventory().setItem(4, configurer);

        if(!plugin.game.state.equals(State.Launched)){
            player.getInventory().setItem(0, bannerTeam);
        }

        player.updateInventory();

        if(player instanceof Player && !plugin.game.state.equals(State.Launched)){
            player.setFoodLevel(25);
        }

        //TP Les joueurs
        Location location = new Location(Bukkit.getServer().getWorld("lobby"), 0, 99, 0);
        location.setPitch(location.getPitch() + 60f);
        player.teleport(location);

        Player pName = player.getPlayer();

        game.getPlayers().add(pName);

    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event){

        Player player = event.getPlayer();

        game.getPlayers().remove(player);

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

        if(it.getType() == Material.BANNER && !plugin.game.state.equals(State.Launched)){
            TeamSelectorMenu.TEAMSELECTOR.open(player);
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(!plugin.game.state.equals(State.Launched)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(!plugin.game.state.equals(State.Launched)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        if(!plugin.game.state.equals(State.Launched)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onIlaFaim(FoodLevelChangeEvent event){
        if(!plugin.game.state.equals(State.Launched)){
            event.setCancelled(true);
        }
    }

    //Cancel la possibilité de poser un bloc quand la partie n'est pas start
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){

        if(!plugin.game.state.equals(State.Launched)){
            event.setCancelled(true);
        }
    }

    //Cancel la possibilité de récup un item quand la partie n'est pas start
    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent event){

        if(!plugin.game.state.equals(State.Launched)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event){

        if(!plugin.game.state.equals(State.Launched)){
            event.setCancelled(true);
        }

    }

}