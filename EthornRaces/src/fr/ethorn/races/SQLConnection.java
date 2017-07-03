package fr.ethorn.races;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Gère les connexion à la base de données
 * 
 * @author CIad
 * @version 1.0.0
 */
public class SQLConnection {

	/**
	 * Contiens la connexion à la base de données ainsi que toutes les données.
	 */
	private Connection connection;

	/**
	 * Contiens les paramètres de connexion à la base de données
	 */
	private String urlBase, host, database, user, password;

	/**
	 * Constructeur attribuant les données de connexion
	 * 
	 * @param pUrlBase
	 * @param pHost
	 * @param pDatabase
	 * @param pUser
	 * @param pPassword
	 */
	public SQLConnection(String pUrlBase, String pHost, String pDatabase, String pUser, String pPassword) 
	{
		this.urlBase = pUrlBase;
		this.host = pHost;
		this.database = pDatabase;
		this.user = pUser;
		this.password = pPassword;
	}

	/**
	 * Gère les dump dans la base de données grâce à un tableau de String
	 * 
	 * @param pTable contiens les nom de tables à vider.
	 */
	public void truncate(String[] pTable)
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + Main.plugName + " Vidange des tables de la base de données en cours..."); //Trace Console

		try {
			for(String tmp : pTable)
			{
				String t = "TRUNCATE " + tmp;
				PreparedStatement pS = this.getConnection().prepareStatement(t);
				pS.execute();
				pS.close();

				Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + Main.plugName + " - " + tmp + " vidée."); //Trace Console
			}

			Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + Main.plugName + " Vidange terminée avec succès"); //Trace Console


		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + Main.plugName + " Vidange raté"); //Trace Console

			e.printStackTrace();
		}
	}

	/**
	 * Truncate une table dans la bdd sql.
	 * @param pTable est le nom de la table a supp de la bdd.
	 */
	public void truncate(String pTable) throws SQLException {
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + Main.plugName + " Suppressions de la table "+ pTable +  " de la base de données en cours..."); //Trace Console

		String table = "TRUNCATE " + pTable;
		PreparedStatement pS = this.getConnection().prepareStatement(table);
		pS.execute();
		pS.close();
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + Main.plugName + " - " + pTable + " à été correctement vidée.");

	}

	/**
	 * Réalise la connexion à la base de données
	 */
	public void connection()
	{
		if(!isConnected())
		{
			try {
				connection = DriverManager.getConnection(urlBase + host + "/" + database, user, password);
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[" + Main.plugName + "] Connexion SQL reussie.");
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[" + Main.plugName + "] Connexion impossible");

				Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "[" + Main.plugName + "] : [UrlBase, " + this.urlBase + "]");
				Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "[" + Main.plugName + "] : [Host, " + this.host + "]");
				Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "[" + Main.plugName + "] : [Database, " + this.database + "]");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Vérifie, si la base de données est connecté
	 * @return true, si elle est connecté.
	 */
	public boolean isConnected()
	{
		return connection != null;
	}

	/**
	 * Réalise la déconnexion à la base de données
	 */
	public void disconnect()
	{
		if (isConnected())
		{
			try {
				connection.close();
				Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "[" + Main.plugName + "] Deconnexion SQL reussie.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @return la connexion
	 */
	public Connection getConnection() {
		return connection;
	}
}
