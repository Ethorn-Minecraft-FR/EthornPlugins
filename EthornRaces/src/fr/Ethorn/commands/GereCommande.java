package fr.Ethorn.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * 
 * @author Tardigradeus
 *
 */
public class GereCommande implements CommandExecutor {

	//au moment de l'exucution d'une commande on se retrouve la
	@Override
	public boolean onCommand(CommandSender sender , Command command, String msg, String[] args) {
		
		//si c'et le modo qui fait le con on l'engeule XD
		if (sender instanceof ConsoleCommandSender){
			
			sender.sendMessage("§r ARRETE DE FAIRE LE CON");
		}
		//si c'est un joueur on lui répond gentiment
		else if(sender instanceof Player){
			
			Player player = (Player) sender;
			
			String nameCommand = command.getName();
			
			//witch case de la commande , c'est plus classe et propres 
			switch (nameCommand){
			
				case "caste" :
					//on envoie a la méthode qui gère la commande pour eviter de blinder la méthode oncommand
					gereCommandeCaste(player,args);
					break;
					
				case "race" :
					gereCommandeRace(player);
					break;
			} 
			
			/** ancienne version avec des if else if partout
			//si c'est la commande classes on vas chopper toutes les classes pour les afficher
			if(command.getName().equalsIgnoreCase("caste")){
				
			}
			
			else if (command.getName().equalsIgnoreCase("race")){
				
				gereCommandeRace(player);
				
			}**/
			
		}
		//impossible d'arriver la XD mais on sait jamais
		else{
			sender.sendMessage("heuuu Oo comment tu est arrivé ici ?");
		}
		
		return true ;
	}

	private void gereCommandeRace(Player player) {
		
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

	/**
	 * gère la commande caste
	 * @param player le player qui a envoyer la commande
	 * @param args les arguments
	 */
	private void gereCommandeCaste(Player player,String[] args) {
		
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

}
