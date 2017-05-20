package fr.Ethorn.Manager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import fr.Ethorn.Races.Main;
import net.md_5.bungee.api.ChatColor;

/**
 * Gère les ethnies du serveur
 * 
 * @author CIad
 * @version 1.0.0
 */
public class EthnieManager 
{
	/**
	 * Instance de la classe principale
	 */
	private Main main;

	public EthnieManager(Main main)
	{
		this.main = main;

		this.raceConfig();
	}

	/**
	 * 
	 * Découpe le fichier config du plugin
	 * 
	 * @author CIad
	 * @version 1.0.0
	 * 
	 */
	private void raceConfig()
	{

		ConfigurationSection sec = main.getConfig().getConfigurationSection("Race"); //Récupère le tableau Race

		String[] table = {"races", "castes"};
		main.SQL.truncate(table);

		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[EthornRace] Découpage du fichier de configuration en cours..."); //Trace Console
		
		/**
		 * Récupère les premier tableaux de race
		 * 
		 * Ex:
		 * 
		 * Race:
		 *	  unknown:                       <- Celle ci
		 *	    id: 0
		 *	    name: unknown
		 *	
		 *	  ethnique:                      <- Celle-ci
		 *	    id: 1
		 *	    name: Ethniens
		 *	    class:
		 * 
		 */
		for(String key : sec.getKeys(false))
		{
			try {
				PreparedStatement pS = main.SQL.getConnection().prepareStatement("INSERT INTO races(name) VALUES (?)");
				pS.setString(1, key);
				pS.execute();
				pS.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ConfigurationSection race = sec.getConfigurationSection(key);

			ConfigurationSection casteSec = race.getConfigurationSection("class"); //Contiens les castes

			Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + " - " + race.getString("name") + " est ajouté dans la liste des races."); //Trace Console

			//Vérifie si, la race contiens une caste
			if(casteSec != null)
			{
				for(String classKey : casteSec.getKeys(false))
				{
					ConfigurationSection caste = casteSec.getConfigurationSection(classKey); //Contiens les données de la caste

					Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "   - " + caste.getString("name") + " est ajoutés à la liste des classes de la race.");

					
					try {
						PreparedStatement pS = 
								main.SQL.getConnection().prepareStatement("INSERT INTO castes(name, health, strength, speed, shield, fk_races_id) VALUES (?, ?, ?, ?, ?, ?)");
						pS.setString(1, caste.getString("name"));
						pS.setInt(2, caste.getInt("health"));
						pS.setInt(3, caste.getInt("strength"));
						pS.setInt(4, caste.getInt("speed"));
						pS.setInt(5, caste.getInt("shield"));
						pS.setInt(6, race.getInt("id"));
						pS.execute();
						pS.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}			
				}
			}
		}

		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[EthornRace] Découpage réussie"); //Trace Console
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "===================================\n"); //Trace Console

	}
}
