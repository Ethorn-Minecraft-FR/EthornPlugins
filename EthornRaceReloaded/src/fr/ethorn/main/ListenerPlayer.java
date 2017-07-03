package fr.ethorn.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerPlayer implements Listener {

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


    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e)
    {
        System.out.print("[Ethorn-dev] Le joueur " + e.getPlayer().getName() + " a rejoint le serveur");
    }
}
