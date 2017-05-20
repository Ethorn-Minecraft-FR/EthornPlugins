package fr.Ethorn.Races;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.Ethorn.Cache.PlayerDataManager;
import fr.Ethorn.Listeners.ListenerBlock;
import fr.Ethorn.Listeners.ListenerPlayer;
import fr.Ethorn.Manager.EthnieManager;
import fr.Ethorn.Manager.PlayerManager;
import net.md_5.bungee.api.ChatColor;

/**
 * Contiens la classe principale du plugin
 * 
 * @author CIad
 * @version 1.0.0
 */
public class Main extends JavaPlugin
{
	public static String plugName = "EthornRaces"; //Contiens une constante ayant le nom du plugin

	/**
	 * Contiens la class SQLConnection
	 * 
	 * @author CIad
	 */
	public SQLConnection SQL; 
	
	/**
	 * Contiens une déclaration de PluginManager
	 * 
	 * @author CIad
	 */
	public PluginManager plugManager;
	
	//Listener
	
	/**
	 * Contiens la class ListenerPlayer
	 * 
	 * @author CIad
	 */
	public ListenerPlayer listenerPlayer;
	
	/**
	 * Contiens la class ListenerBlockBuild
	 * 
	 * @author CIad
	 */
	public ListenerBlock listenerBlockBuild;
	
	
	//Manager
	
	/**
	 * Contiens la class PlayerManager
	 * 
	 * @author CIad
	 */
	public PlayerManager playerManager;
	
	/**
	 * Contiens la class EthnieManager
	 * 
	 * @author CIad
	 */
	public EthnieManager ethnieManager;
	
	//DATA
	
	/**
	 * Contiens la class PlayerDataManager
	 * 
	 * @author CIad
	 */
	public PlayerDataManager dataManager;
	
	
	/**
	 * Contiens toutes les déclarations et initialisation des classes
	 */
	public void init()
	{
		this.plugManager = getServer().getPluginManager();
		
		//Manager
		this.playerManager = new PlayerManager(this);
		this.ethnieManager = new EthnieManager(this);
		this.dataManager = new PlayerDataManager();

		//Listener
		this.listenerPlayer = new ListenerPlayer(this);
		this.listenerBlockBuild = new ListenerBlock();
		
		this.plugManager.registerEvents(this.listenerPlayer, this);
		this.plugManager.registerEvents(this.listenerBlockBuild, this);
		
		SQL.truncate("users");
	}
	

	/**
	 * Fonction qui est appelé au lancement du plugin
	 * 
	 */
	@Override
	public void onEnable()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();

		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "\n " + Main.plugName + " Races Activate");

		//Connexion à la base de données
		SQL = new SQLConnection("jdbc:mysql://", "localhost", "ethorn", "root", "");
		SQL.connection();
		
		init();


	}

	@Override
	public void onDisable()
	{
		SQL.disconnect();
	}

	

}

