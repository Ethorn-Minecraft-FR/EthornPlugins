package fr.ethorn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.ethorn.races.Race;
import net.md_5.bungee.api.ChatColor;

public class InfoRaceCommand implements CommandExecutor {

	/**
	 * InfoRaceCommand
	 * 
	 * TODO Système d'argument
	 *  [race]
	 *  
	 *  @author Tardigradeus
	 *  @verion 1.0.0
	 */	
	@Override
	public boolean onCommand(CommandSender sender , Command command, String msg, String[] args) {

		if(command.getLabel().equalsIgnoreCase("race"))
		{
			//taille de l'interface des message : 53

			//on envoie la liste des races
			sender.sendMessage(ChatColor.YELLOW + "--------------------");
			sender.sendMessage(ChatColor.RED + "      Races        ");
			sender.sendMessage(ChatColor.YELLOW + "--------------------");

			//pour chaque élement dans la liste on fait un truc
			Race.races.forEach( raceName -> {

				//TODO : hidden-
				//pour le coup on l'affiche au joueur ^^
				sender.sendMessage(ChatColor.DARK_RED + "\t " + raceName.getName());


			});

			sender.sendMessage(ChatColor.YELLOW + "-----------------");
			return true;
		}

		return false;
	}

}
