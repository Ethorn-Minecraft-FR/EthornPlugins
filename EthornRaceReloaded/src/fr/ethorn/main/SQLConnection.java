package fr.ethorn.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère les connexion à la base de données

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
