package fr.mssteur.mssbingo;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.InventoryManager;
import fr.mssteur.mssbingo.commands.CommandBingo;
import fr.mssteur.mssbingo.commands.CommandConfig;
import fr.mssteur.mssbingo.commands.CommandTeamInfo;
import fr.mssteur.mssbingo.listeners.MainListeners;
import fr.mssteur.mssbingo.objects.Game;
import fr.mssteur.mssbingo.objects.Loader;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
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



    private static Main instance;

    public final Game game = new Game();
    public PluginManager manager;

    public List<Integer> saver = new ArrayList<>();
    public List<String> id_list = new ArrayList<>();

    //public ClickableItem getClickableItem(ItemStack item){
    //    ClickableItem cItem = ClickableItem.of(item, e -> );
    //}

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

    /*public int rdmer(int size, int[] banId){
        int rdm = random.nextInt(size);
        List<Integer> temp2 = new ArrayList<>();
        for(int id:banId){
            temp2.add(id);
        }
        while(temp2.contains(rdm) || saver.contains(rdm)){
            rdm = random.nextInt(size);
        }
        saver.add(rdm);
        id_list.add(rdm + " (" + Material.values()[rdm].toString() + ")");
        return rdm;
    }*/

    @Override
    public void onEnable() {

        instance = this;

        System.out.println("[MSSBingo] > Le plugin est en train de s'activer...");

        manager=getServer().getPluginManager();
        manager.registerEvents(new MainListeners(this), this);
        game.create();

        for(int i = 0; i<25; i++){
            itemStack.add(randommer(382, banList));
        }

        System.out.println(id_list);

        getCommand("bingo").setExecutor(new CommandBingo());
        getCommand("config").setExecutor(new CommandConfig());
        getCommand("teaminfo").setExecutor(new CommandTeamInfo());
        invManager.init();

        //Création du jeu
        WorldCreator wc = new WorldCreator("world_the_end");
        wc.environment(World.Environment.THE_END);
        wc.createWorld();
        wc = new WorldCreator("world_nether");
        wc.environment(World.Environment.NETHER);
        wc.createWorld();
        wc = new WorldCreator("world");
        wc.environment(World.Environment.NORMAL);
        wc.createWorld();
        wc = new WorldCreator("lobby");
        wc.environment(World.Environment.NORMAL);
        wc.createWorld();

        Loader.init(this);
        game.create();

        World lobby = Bukkit.getWorld("lobby");
        lobby.setGameRuleValue("doMobSpawning", "false");
        lobby.setGameRuleValue("doDaylightCycle", "false");

        System.out.println("[MSSBingo] > Le plugin est en maintenant actif !");

        invManager.init();
        Bukkit.getScheduler().runTaskTimer(this, () -> game.tick(), 1, 1);

    }

    public InventoryManager getInvManager() {
        return invManager;
    }

    public Game getGame(){
        return game;
    }

    public Main getInstance(){
        return instance;
    }
}