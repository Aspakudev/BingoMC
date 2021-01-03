package fr.mssteur.mssbingo;

import fr.minuskube.inv.InventoryManager;
import fr.mssteur.mssbingo.commands.CommandBingo;
import fr.mssteur.mssbingo.commands.CommandConfig;
import fr.mssteur.mssbingo.commands.CommandTeamInfo;
import fr.mssteur.mssbingo.listeners.MainListeners;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JavaPlugin {

    private final InventoryManager invManager = new InventoryManager(this);

    public List<Integer> getItemStack() {
        return itemStack;
    }

    private final List<Integer> itemStack = new ArrayList<>();

    private int[] banList = {0,55,51,8,83,68,178,364,64,190,90,92,118,149,74,73,62,380,125,176,329,345,141,34,63,94,181,144,104,244,132,374,177,43,26,55,59,71,36,100,9,245,113,115,153,376,75,326,377,300,10,11,73,127,150,339,119,325,245,117,93,197,193,194,195,196,8,140,344,247,341,105,124,142,166,138,7,99,327,305,303,302,304,16,137,422,56,122,133,129,403,120,121,384,60,358,385,2,79,21,52,97,110,399,174,2266,2256,2258,2257,2259,2260,2261,2262,2263,2264,2267,2265,397,383,19,387};
    Random random = new Random();

    public List<Integer> saver = new ArrayList<>();
    public List<String> id_list = new ArrayList<>();

    public int randommer(int size, int[] banId){
        int rdm = random.nextInt(size);
        List<Integer> temp = new ArrayList<>();
        for(int id:banId){
            temp.add(id);
        }
        while(temp.contains(rdm) || saver.contains(rdm)){
            rdm = random.nextInt(size);
        }
        saver.add(rdm);
        id_list.add(rdm + " (" + Material.values()[rdm].toString() + ")");
        return rdm;
    }

    @Override
    public void onEnable() {

        for(int i = 0; i<25; i++){
            itemStack.add(randommer(382, banList));
        }

        System.out.println(id_list);

        System.out.println("[MSSBingo] > Le plugin est desormais actif !");
        getCommand("bingo").setExecutor(new CommandBingo());
        getCommand("config").setExecutor(new CommandConfig());
        getCommand("teaminfo").setExecutor(new CommandTeamInfo());
        getServer().getPluginManager().registerEvents((Listener) new MainListeners(), this);
        invManager.init();

    }

    @Override
    public void onDisable() {
        System.out.println("Le plugin s'est correctement eteind.");
    }

    public InventoryManager getInvManager() {
        return invManager;
    }
}