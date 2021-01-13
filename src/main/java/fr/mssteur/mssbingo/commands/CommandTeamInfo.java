package fr.mssteur.mssbingo.commands;

import fr.mssteur.mssbingo.guis.TeamManagerMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTeamInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        Bukkit.broadcastMessage("La partie est composée de XTeam de ");

        return true;
    }
}
