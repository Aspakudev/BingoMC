package fr.mssteur.mssbingo.commands;

import fr.mssteur.mssbingo.guis.BingoMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBingo implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            BingoMenu.BINGO.open(player);
        }
        return true;
    }
}