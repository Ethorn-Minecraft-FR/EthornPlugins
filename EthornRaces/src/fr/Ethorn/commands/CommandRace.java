package fr.Ethorn.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandRace implements CommandExecutor {

	//au moment de l'exucution d'une commande on se retrouve la
	@Override
	public boolean onCommand(CommandSender sender , Command command, String msg, String[] args) {
		
		if(sender instanceof Player){
		
			Player player = (Player) sender;
			
			
			//on construit la liste des races
			ArrayList<String> listRaces = new ArrayList<String>();
			
			//on recupère le fichier de conf sans passer par le main
			File fichConfig = new File("plugins/EthornRaces/config.yml");
					
			//on créer l'objet qui nous permet de manipulez des fichier yml de bukkit
			FileConfiguration fc = YamlConfiguration.loadConfiguration(fichConfig);
			
			ConfigurationSection sectionRace = fc.getConfigurationSection("Race"); //Récupère le tableau Race
			//si des races existes
			if(sectionRace != null){
				
				//on recupère les clées des races
				for(String key : sectionRace.getKeys(false)){
					listRaces.add(key);
				}
			}
			else{
				listRaces.add("aucune races trouvé");
			}
			
			//taille de l'interface des message : 53
			
			//on envoie la liste des castes
			player.sendMessage("§e--------------------");
			player.sendMessage("§e-    §cRaces       -");
			player.sendMessage("§e--------------------");
			
			//pour chaque élement dans la liste on fait un truc
			listRaces.forEach( casteName -> {
				
				//pour le coup on l'affiche au joueur ^^
				player.sendMessage("§e|§6- "+casteName);
				
			});
			
			player.sendMessage("§e-----------------");
			
		}
		
		return true;
	}

}
