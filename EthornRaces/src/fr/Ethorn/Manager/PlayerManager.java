package fr.Ethorn.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import fr.Ethorn.Races.Main;

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
