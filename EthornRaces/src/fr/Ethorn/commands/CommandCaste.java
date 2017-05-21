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

public class CommandCaste implements CommandExecutor {

	//au moment de l'exucution d'une commande on se retrouve la
	@Override
	public boolean onCommand(CommandSender sender , Command command, String msg, String[] args) {
		//si c'est un joueur on lui répond gentiment
		if(sender instanceof Player){
					
			Player player = (Player) sender;
					
			//variable qui dit si on recherche les caste par un race en particulier ou non
			boolean searchCasteRace = false;
			
			String raceName = "";
			
			if(args.length != 0){
				
				//on met la variable qui indique que l'on vas executer la recherche par race
				searchCasteRace = true;
				
				//on donne la race demander en arguement
				raceName = args[0] ;
			}
				
			
			//on construit la liste des castes
			ArrayList<String> listCaste = new ArrayList<String>();
			
			//on recupère le fichier de conf sans passer par le main
			File fichConfig = new File("plugins/EthornRaces/config.yml");
					
			//on créer l'objet qui nous permet de manipulez des fichier yml de bukkit
			FileConfiguration fc = YamlConfiguration.loadConfiguration(fichConfig);
			
			ConfigurationSection sectionRace = fc.getConfigurationSection("Race"); //Récupère le tableau Race
			
			if(sectionRace != null){
				
				//si la recherche par race est activé
				if(searchCasteRace){
					
					//on créer la variable qui vas recevoir la race demandé
					ConfigurationSection race;
					
					//si la race demandé existe on la récupère
					 if( (  race = sectionRace.getConfigurationSection(raceName) ) != null){
						
						 ConfigurationSection casteSec = race.getConfigurationSection("class"); //on chope les castes qui appartienne a la race
						 
						//verification de si il y a des castes XD
						if(casteSec != null){
								
							//on parcoure les castes dans la race
							for(String classKey : casteSec.getKeys(false)){
								
								ConfigurationSection caste = casteSec.getConfigurationSection(classKey); //Contiens les données de la caste
									
								listCaste.add(caste.getString("name"));
							}
						}
						 
					 }
					 //si la race n'a pas été trouvé
					 else{
						 //petit message pour dire que sa existe pas ça merde
						 listCaste.add("aucune classes trouvé pour la race : "+raceName);
					 }
				}
				
				//si la recherche pas race n'est pas active on parse toutes les castes de toute les races
				else{
					
					//on recupère les clées des races
					for(String key : sectionRace.getKeys(false)){
						
						ConfigurationSection race = sectionRace.getConfigurationSection(key);//on chope la race
						
						ConfigurationSection casteSec = race.getConfigurationSection("class"); //on chope les castes qui appartienne a la race
						
						//verification de si il y a des castes XD
						if(casteSec != null){
							
							//on parcoure les castes dans la race
							for(String classKey : casteSec.getKeys(false)){
								
								ConfigurationSection caste = casteSec.getConfigurationSection(classKey); //Contiens les données de la caste
								
								listCaste.add(caste.getString("name"));
							}
						}
					}
				}
			}
			//si la liste des classes est vide
			if(listCaste.size() == 0){
				player.sendMessage("§r aucune classe de disponible");
			}
			else{
				
				//on envoie la liste des castes
				player.sendMessage("§e-----------------");
				player.sendMessage("§e-    §cCastes     ");
				player.sendMessage("§e-----------------");
				
				//pour chaque élement dans la liste on fait un truc
				listCaste.forEach( casteName -> {
					
					//pour le coup on l'affiche au joueur ^^
					player.sendMessage("§e|§6- "+casteName);
					
				});
				
				player.sendMessage("§e-----------------");
			}
		}
		
		
		
		return true ;
	}

}
