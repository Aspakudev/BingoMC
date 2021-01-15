package fr.mssteur.mssbingo.objects;

import fr.mssteur.mssbingo.listeners.MainListeners;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    ArrayList<Player> players = new ArrayList<Player>();



    public State state;
    private MainListeners plugin;

    public ArrayList<Player> getPlayers(){
        return players;
    }
                                                                //     X
                                                                //
    public int n1x(int initX){                                   // n1 n2 n3     (1;1)(0;1)(-1;1)
                                                                 // n4 init n6 Z (1;0)(0;0)(-1;0)
        int x = initX + 1;                                       // n7 n8 n9     (1;-1)(0;-1)(-1;-1)
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

    public

    Random rdm = new Random();

    int high = 2000;
    int low = -2000;

    public void create(){
        this.state=State.Open;
    }

    public void launch(Player player){
        this.state=State.Launched;

        int gameSize = getPlayers().size();

        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.getActivePotionEffects().clear();

        for(int i = 0; i < gameSize; i++){
            Location location = new Location(Bukkit.getServer().getWorld("world"), rdm.nextInt(high-low) + low, 99, rdm.nextInt(high-low) + low);
            location.setPitch(location.getPitch() + 90f);

            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);
            location.getBlock().setType(Material.GLASS);

            player.teleport(location);
        }

    }

    public void win(Player player){
        this.state=State.End;
    }
}