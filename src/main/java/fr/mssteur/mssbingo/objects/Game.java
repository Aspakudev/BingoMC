package fr.mssteur.mssbingo.objects;

import fr.mssteur.mssbingo.listeners.MainListeners;
import fr.mssteur.mssbingo.utils.TitleUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    ArrayList<Player> players = new ArrayList<Player>();

    HashMap<Player, Integer> xPosInit = new HashMap<Player, Integer>();
    HashMap<Player, Integer> zPosInit = new HashMap<Player, Integer>();

    public State state;
    private MainListeners plugin;

    public int pvpTime;

    public int tick;

    public boolean isPvPEnabled;

    public ArrayList<Player> getPlayers(){
        return players;
    }
                                                                   //     X
                                                                 //n10 n11 n12 n13 n14
    public int n1x(int initX){                                   //    n1 n2 n3                       (1;1)(0;1)(-1;1)
                                                                 //    n4 init n6 Z                   (1;0)(0;0)(-1;0)
        int x = initX + 1;                                       //    n7 n8 n9                       (1;-1)(0;-1)(-1;-1)
        return x;
    }
    public int n1z(int initZ){
        int z =  initZ + 1;
        return z;
    }

    public int initX(){

        return rdm.nextInt(high-low) + low;

    }
    public int initZ(){

        return rdm.nextInt(high-low) + low;

    }

    public int n2z(int initZ){
        int z = initZ + 1;
        return z;
    }

    public int n3x(int initX){
        int x = initX - 1;
        return x;
    }
    public int n3z(int initZ){
        int z = initZ + 1;
        return z;
    }

    public int n4x(int initX){
        int x = initX + 1;
        return x;
    }

    public int n6x(int initX){
        int x = initX - 1;
        return x;
    }

    public int n7x(int initX){
        int x = initX + 1;
        return x;
    }
    public int n7z(int initZ){
        int z = initZ - 1;
        return z;
    }

    public int n8z(int initZ){
        int z = initZ - 1;
        return z;
    }

    public int n9x(int initX){
        int x = initX - 1;
        return x;
    }
    public int n9z(int initZ){
        int z = initZ - 1;
        return z;
    }

    public int n10x(int initX){
        int x = initX + 2;
        return x;
    }
    public int n10z(int initZ){
        int z = initZ + 2;
        return z;
    }

    public int n11x(int initX){
        int x = initX + 1;
        return x;
    }
    public int n11z(int initZ){
        int z = initZ + 2;
        return z;
    }

    public int n12z(int initZ){
        int z = initZ + 2;
        return z;
    }

    public int n13x(int initX){
        int x = initX - 1;
        return x;
    }
    public int n13z(int initZ){
        int z = initZ + 2;
        return z;
    }

    public int n14x(int initX){
        int x = initX - 2;
        return x;
    }
    public int n14z(int initZ){
        int z = initZ + 2;
        return z;
    }

    public int n15x(int initX){
        int x = initX + 2;
        return x;
    }
    public int n15z(int initZ){
        int z = initZ + 1;
        return z;
    }

    public int n16x(int initX){
        int x = initX + 2;
        return x;
    }

    public int n17x(int initX){
        int x = initX + 2;
        return x;
    }
    public int n17z(int initZ){
        int z = initZ - 1;
        return z;
    }

    public int n18x(int initX){
        int x = initX + 2;
        return x;
    }
    public int n18z(int initZ){
        int z = initZ - 2;
        return z;
    }

    public int n19x(int initX){
        int x = initX + 1;
        return x;
    }
    public int n19z(int initZ){
        int z = initZ - 2;
        return z;
    }

    public int n20z(int initZ){
        int z = initZ - 2;
        return z;
    }

    public int n21x(int initX){
        int x = initX - 1;
        return x;
    }
    public int n21z(int initZ){
        int z = initZ - 2;
        return z;
    }

    public int n22x(int initX){
        int x = initX - 2;
        return x;
    }
    public int n22z(int initZ){
        int z = initZ + 1;
        return z;
    }

    public int n23x(int initX){
        int x = initX - 2;
        return x;
    }

    public int n24x(int initX){
        int x = initX - 2;
        return x;
    }
    public int n24z(int initZ){
        int z = initZ - 1;
        return z;
    }

    public int n25x(int initX){
        int x = initX - 2;
        return x;
    }
    public int n25z(int initZ){
        int z = initZ - 2;
        return z;
    }

    Random rdm = new Random();

    int high = 2000;
    int low = -2000;

    public void create(){
        this.state=State.Open;
        pvpTime=600;
        isPvPEnabled=false;
    }

    public void launch(){
        tick = 0;

        this.state=State.Launched;

        int gameSize = getPlayers().size();

        for(int i = 0; i < gameSize; i++){
            for(Player player:players) {

                player.getInventory().clear();
                player.setGameMode(GameMode.SURVIVAL);
                player.getActivePotionEffects().clear();
                player.setFoodLevel(40);
                player.setHealth(20);
                player.setExp(0);

                player.getPlayer().getLocation().getWorld().setTime(0);
                player.getPlayer().getLocation().getWorld().setStorm(false);
                player.getPlayer().getLocation().getWorld().setThundering(false);

                Location location = new Location(Bukkit.getServer().getWorld("world"), rdm.nextInt(high - low) + low, 200, rdm.nextInt(high - low) + low);
                location.setPitch(location.getPitch() + 90f);

                xPosInit.put(player, (int) location.getX());
                zPosInit.put(player, (int) location.getZ());

                Location n1 = new Location(Bukkit.getServer().getWorld("world"), n1x(xPosInit.get(player)), 200, n1z(zPosInit.get(player)));
                Location n2 = new Location(Bukkit.getServer().getWorld("world"), xPosInit.get(player), 200, n2z(zPosInit.get(player)));
                Location n3 = new Location(Bukkit.getServer().getWorld("world"), n3x(xPosInit.get(player)), 200, n3z(zPosInit.get(player)));
                Location n4 = new Location(Bukkit.getServer().getWorld("world"), n4x(xPosInit.get(player)), 200, zPosInit.get(player));

                Location n6 = new Location(Bukkit.getServer().getWorld("world"), n6x(xPosInit.get(player)), 200, zPosInit.get(player));
                Location n7 = new Location(Bukkit.getServer().getWorld("world"), n7x(xPosInit.get(player)), 200, n7z(zPosInit.get(player)));
                Location n8 = new Location(Bukkit.getServer().getWorld("world"), xPosInit.get(player), 200, n8z(zPosInit.get(player)));
                Location n9 = new Location(Bukkit.getServer().getWorld("world"), n9x(xPosInit.get(player)), 200, n9z(zPosInit.get(player)));
                Location n10 = new Location(Bukkit.getServer().getWorld("world"), n10x(xPosInit.get(player)), 200, n10z(zPosInit.get(player)));
                Location n11 = new Location(Bukkit.getServer().getWorld("world"), n11x(xPosInit.get(player)), 200, n11z(zPosInit.get(player)));
                Location n12 = new Location(Bukkit.getServer().getWorld("world"), xPosInit.get(player), 200, n12z(zPosInit.get(player)));
                Location n13 = new Location(Bukkit.getServer().getWorld("world"), n13x(xPosInit.get(player)), 200, n13z(zPosInit.get(player)));
                Location n14 = new Location(Bukkit.getServer().getWorld("world"), n14x(xPosInit.get(player)), 200, n14z(zPosInit.get(player)));
                Location n15 = new Location(Bukkit.getServer().getWorld("world"), n15x(xPosInit.get(player)), 200, n15z(zPosInit.get(player)));
                Location n16 = new Location(Bukkit.getServer().getWorld("world"), n16x(xPosInit.get(player)), 200, zPosInit.get(player));
                Location n17 = new Location(Bukkit.getServer().getWorld("world"), n17x(xPosInit.get(player)), 200, n17z(zPosInit.get(player)));
                Location n18 = new Location(Bukkit.getServer().getWorld("world"), n18x(xPosInit.get(player)), 200, n18z(zPosInit.get(player)));
                Location n19 = new Location(Bukkit.getServer().getWorld("world"), n19x(xPosInit.get(player)), 200, n19z(zPosInit.get(player)));
                Location n20 = new Location(Bukkit.getServer().getWorld("world"), xPosInit.get(player), 200, n20z(zPosInit.get(player)));
                Location n21 = new Location(Bukkit.getServer().getWorld("world"), n21x(xPosInit.get(player)), 200, n21z(zPosInit.get(player)));
                Location n22 = new Location(Bukkit.getServer().getWorld("world"), n22x(xPosInit.get(player)), 200, n22z(zPosInit.get(player)));
                Location n23 = new Location(Bukkit.getServer().getWorld("world"), n23x(xPosInit.get(player)), 200, zPosInit.get(player));
                Location n24 = new Location(Bukkit.getServer().getWorld("world"), n24x(xPosInit.get(player)), 200, n24z(zPosInit.get(player)));
                Location n25 = new Location(Bukkit.getServer().getWorld("world"), n25x(xPosInit.get(player)), 200, n25z(zPosInit.get(player)));

                location.getBlock().setType(Material.GLASS);
                n1.getBlock().setType(Material.GLASS);
                n2.getBlock().setType(Material.GLASS);
                n3.getBlock().setType(Material.GLASS);
                n4.getBlock().setType(Material.GLASS);
                n6.getBlock().setType(Material.GLASS);
                n7.getBlock().setType(Material.GLASS);
                n8.getBlock().setType(Material.GLASS);
                n9.getBlock().setType(Material.GLASS);
                n10.getBlock().setType(Material.GLASS);
                n11.getBlock().setType(Material.GLASS);
                n12.getBlock().setType(Material.GLASS);
                n13.getBlock().setType(Material.GLASS);
                n14.getBlock().setType(Material.GLASS);
                n15.getBlock().setType(Material.GLASS);
                n16.getBlock().setType(Material.GLASS);
                n17.getBlock().setType(Material.GLASS);
                n18.getBlock().setType(Material.GLASS);
                n19.getBlock().setType(Material.GLASS);
                n20.getBlock().setType(Material.GLASS);
                n21.getBlock().setType(Material.GLASS);
                n22.getBlock().setType(Material.GLASS);
                n23.getBlock().setType(Material.GLASS);
                n24.getBlock().setType(Material.GLASS);
                n25.getBlock().setType(Material.GLASS);

                System.out.println("etape 1");

                player.teleport(location);

                System.out.println("etape 2");
            }
            Bukkit.getScheduler().runTaskLater((Plugin) this, ()->
            {for (Player player : players){
                player.getPlayer().setBedSpawnLocation(player.getPlayer().getLocation());}
            }, 100);

        }
    }

    public void win(Player player){
        this.state=State.End;
    }
    public void tick(){
        if (tick==pvpTime*20 && state.equals(State.Launched)){
            for(Player player:players){
                TitleUtils.sendTitle(player.getPlayer(), "§cLe pvp est activé !");
            }
            isPvPEnabled=true;
        }
        if (tick==200 && state.equals(State.Launched)){

            for(Player player:players){
                TitleUtils.sendTitle(player.getPlayer(), ChatColor.GREEN + "Les dégats sont désormais actifs !");
            }

        }
        tick++;
    }

    public void setSpectatorMode(Player player, String msg){

        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatColor.GREEN + msg);

    }

    public void setPvpTime(int nbr){
        if (nbr>=0){
            pvpTime=nbr;
        }
        else{
            pvpTime=0;
        }
    }

    public String getTime(int s) {
        int m = 0;
        int h = 0;

        while (s >= 60) {
            m++;
            s -= 60;
        }
        while (m >= 60) {
            h++;
            m -= 60;
        }

        String msg = "";
        if (h > 0) {
            if (h < 10) {
                msg += "0" + h + ":";
            } else {
                msg += h + ";";
            }
        }
        if (m < 10) {
            msg += "0" + m + ":";
        } else {
            msg += m + ":";
        }
        if (s < 10) {
            msg += "0" + s;
        } else {
            msg += s;
        }

        return msg;
    }

}