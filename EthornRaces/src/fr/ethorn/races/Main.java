package fr.ethorn.races;

import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.ethorn.cache.PlayerDataManager;
import fr.ethorn.commands.InfoCasteCommand;
import fr.ethorn.commands.InfoRaceCommand;
import fr.ethorn.commands.JoinCasteCommand;
import fr.ethorn.listeners.ListenerBlock;
import fr.ethorn.listeners.ListenerPlayer;
import fr.ethorn.manager.EthnieManager;
import fr.ethorn.manager.PlayerManager;
import net.md_5.bungee.api.ChatColor;

/**
 * Contiens la classe principale du plugin
 * 
 * @author CIad
 * @version 1.0.1
 */
public class Main extends JavaPlugin
{
	public static String plugName = "EthornRaces"; //Cotiens une constante ayant le nom du pluginn

	/**
	 * Contiens la class SQLConnection
	*/
	public SQLConnection SQL; 
	
	/**
	 * Contiens une déclaration de PluginManager
	 */
	public PluginManager plugManager;

	/**
	 * Contiens la class ListenerPlayer
	 */
	public ListenerPlayer listenerPlayer;
	
	/**
	 * Contiens la class ListenerBlockBuild
	 */
	public ListenerBlock listenerBlockBuild;

	/**
	 * Contiens la class PlayerManager
	 */
	public PlayerManager playerManager;
	
	/**
	 * Contiens la class EthnieManager
	 */
	public EthnieManager ethnieManager;	

	/**
	 * Contiens la class PlayerDataManager

	 */
	public PlayerDataManager dataManager;
	
	/**
	 * hashmap qui permet de savoir quel joueur a quel Caste
	 */
	public static HashMap<Player,Caste> playerCaste = new HashMap<>();
	
	/**
	 * hashmap qui lie le caste à sa race
	 */
	public static HashMap<Caste, Race> raceCaste = new HashMap<>();
	
	
	/**
	 * Contiens toutes les déclarations et initialisation des classes
	 */
	public void init() throws SQLException {
		this.plugManager = getServer().getPluginManager();
		
		//Manager
		this.playerManager = new PlayerManager(this);
		this.ethnieManager = new EthnieManager(this);
		this.dataManager = new PlayerDataManager();
		
		//Listener
		this.listenerPlayer = new ListenerPlayer(this);
		this.listenerBlockBuild = new ListenerBlock();

		//
		this.plugManager.registerEvents(this.listenerPlayer, this);
		this.plugManager.registerEvents(this.listenerBlockBuild, this);

		//commandes
		//mettre toute les commandes dans cette méthodes
		this.addCommands();		

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
		try{
			SQL = new SQLConnection("jdbc:mysql://", "minecraft107.omgserv.com", "ethorn", "minecraft_135086", "ROOT66");
			SQL.connection();
		}catch (ClassCastException e) {
			e.printStackTrace();
		}


		try {
			init();
		} catch (SQLException e) {
			System.out.print("Une erreur dans l'initialisation c'est produit.");
			e.printStackTrace();
		}


	}

	@Override
	public void onDisable()
	{
		SQL.disconnect();
	}

	/**
	 * fonction utiliser au démarrage du plugin , elle vas instancier toute les commandes et les attribuer au bonnes classes(java et non caste)
	 */
	private void addCommands(){
		
		//la commande classes ou caste on verra affichera la liste des class disponible
		getCommand("caste").setExecutor(new InfoCasteCommand());
		
		//commande pour listé les races
		getCommand("race").setExecutor(new InfoRaceCommand());
		
		//commande pour listé les races
		getCommand("casteJoin").setExecutor(new JoinCasteCommand());
		
	}
	

}
