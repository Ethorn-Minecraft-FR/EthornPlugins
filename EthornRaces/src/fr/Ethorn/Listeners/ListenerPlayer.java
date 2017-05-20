package fr.Ethorn.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.Ethorn.Races.Main;

/**
 * Classe qui gère les écoutes joueurs
 * 
 * - PlayerJoinEvent : Dès qu'un joueur rejoint le serveur
 * - PlayerQuitEvent : Dès qu'un joueur quitte le serveur
 * 
 * @author CIad
 * @version 1.0.0
 */
public class ListenerPlayer implements Listener
{
	/**
	 * Instance de la classe principale
	 */
	private Main main;
	
	/**
	 * Constructeur de la class
	 * @param main Classe principale
	 */
	public ListenerPlayer(Main main) 
	{
		this.main = main;
	}
	
	/**
	 * Dès qu'un joueur rejoins le serveur
	 * @param PlayerJointEvent e : Evenement d'écoute
	 */
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e)
	{
		Player player = e.getPlayer(); //Récupère le joueur qui se connecte
		
		main.playerManager.createAccount(player);
		main.dataManager.loadPlayerData(player);
	}
	
	/**
	 * Dès qu'un joueur quitte le serveur
	 * @param PlayerQuitEvent e : Evenement d'écoute
	 */
	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent e)
	{
		Player player = e.getPlayer(); //récupère le joueur qui se déconnecte
		
		main.dataManager.savePlayerData(player);
	}
}
