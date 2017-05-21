package fr.ethorn.manager;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.ethorn.races.Caste;
import fr.ethorn.races.Main;

/**
 * Gère les joueurs du serveur
 * 
 * @author CIad
 * @version 1.0.0
 */
public class PlayerManager
{
	/**
	 * Instance de la classe principale
	 */
	private Main main;
	
	/**
	 * Constructeur de base
	 * 
	 * @param main
	 */
	public PlayerManager(Main main)
	{
		this.main = main;
	}
	
	/**
	 * Fonction qui ajoute une entrée dans la base de données pour chaque joueurs
	 * 
	 * @param pPlayer
	 */
	public void createAccount(Player pPlayer)
	{
		if(hasAccount(pPlayer) == false)
		{
			try {
				PreparedStatement pS = 
						main.SQL.getConnection().prepareStatement("INSERT INTO users (uuid, fk_castes_id) VALUES (?, 0)");
				pS.setString(1, pPlayer.getUniqueId().toString());
				pS.execute();
				pS.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		createEntryYml(pPlayer);
		
	}
	
	
	
	public static void createEntryYml(Player pPlayer) {
		//on recupère le fichier de conf sans passer par le main
		File fichConfig = new File("plugins/EthornRaces/players.yml");
				
		//on créer l'objet qui nous permet de manipulez des fichier yml de bukkit
		FileConfiguration fc = YamlConfiguration.loadConfiguration(fichConfig);
		
		//on créer la section nom du joueur si elle n'existe pas 
		ConfigurationSection nom;
		if( (nom = fc.getConfigurationSection("nom")) == null){
			
			//on crée la section
			fc.createSection("nom");
			
			//on la selectionne
			nom = fc.getConfigurationSection("nom");
		
		}
		
		//on regarde ensuite si le joueur existe déja ou pas dans la section
		ConfigurationSection nomPlayer;
		if( (nomPlayer = nom.getConfigurationSection(pPlayer.getName())) == null){
			
			//si il n'existe pas on le créer
			nom.createSection(pPlayer.getName());
			
			//on le selectionne
			nomPlayer = nom.getConfigurationSection(pPlayer.getName());
		}
		
		String pCasteName = "";
		
		//on regarde s'il a déja une caste ou pas 
		if( (pCasteName = nomPlayer.getString("caste")) != null){
			System.out.println("le player : " + pPlayer.getName()+" entre dans le serveur avec la cast "+ pCasteName);
			
			
			//si le joueur n'existe pas dans la hashmap on créer une nouvelle entré
			if( !Main.playerCaste.containsKey(pPlayer)){
				
				//on recupère l'id de la caste dans la liste des caste
				int id = idCaste(pCasteName);
				
				Caste pCaste = Caste.castes.get(id);
				
				//on l'enregistre dans la haspmap
				Main.playerCaste.put(pPlayer, pCaste);

			}
		}
		else{
			System.out.print("caste du joueur pas dans le fichier");
			//si le joueur n'est pas enregistrer dans le fichier il l'est peu etre dans la hash map car il vien peut être dans rejoindre une via la bonne commande
			if( Main.playerCaste.containsKey(pPlayer)){
				System.out.println("mais dans la hasmap si");
				//on ajoute la caste au fichier pour c'est futurs connection
				nomPlayer.set("caste", Main.playerCaste.get(pPlayer).getName());
			}
			
			
		}
			
		/*
		//on verifie que le player existe dans la hashmap playerCaste çela permet d'être sur qu'il est enregistrer et qu'il a une classe d'attribuer
		if( Main.playerCaste.containsKey(pPlayer)){
			
			//on prend la castes du player
			Caste pCaste = Main.playerCaste.get(pPlayer);
			
			//on entre ça caste dans le fichier
			nomPlayer.set("caste", pCaste.getName());
		}
		else{
			//si il n'est pas enregistrer c'est qu'il n'a pas encore choisi de castes donc c'est un noob quoi
			nomPlayer.set("caste", "noob");
		}*/
		
		try{
			fc.save(fichConfig);
		}catch(IOException e ){
			e.printStackTrace();
		}
		
		
	}

	private static int idCaste(String caste){
		
		//on parcoure la liste de nos castes
		for(int i = 0 ; i < Caste.castes.size() ; i++){
			
			//on recupère la Caste qui correspond a la ième position
			Caste listCast =  Caste.castes.get(i);
					
			//si on trouve une occurence one la retourne
			if(listCast.getName().equals(caste)){
				return i;
			}
		}
		
		//si on a rien trouvé bas c'est que nada ça existe pas
		return -1;
	}
	/**
	 * Fonction qui permet de vérifier, si un joueur existe déjà dans la base de données.
	 * 
	 * @param pPlayer
	 * @return False / True en fonction de si le joueur est déjà dans la bdd
	 */
	public boolean hasAccount(Player pPlayer)
	{
		try {
			PreparedStatement pS = 
					main.SQL.getConnection().prepareStatement("SELECT uuid FROM users WHERE uuid = ?");
			
			pS.setString(1, pPlayer.getUniqueId().toString());
			
			ResultSet result = pS.executeQuery();
			boolean hasAccount = result.next();
			pS.close();
			
			return hasAccount;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	

}
