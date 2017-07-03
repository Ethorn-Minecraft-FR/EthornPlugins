package fr.ethorn.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
        UUID uuid =  e.getPlayer().getUniqueId();
        System.out.print("[Ethorn-dev] Le joueur " + e.getPlayer().getName() + " avec pour uuid  "+ uuid + " a rejoint le serveur");

        try {
            PreparedStatement pS = main.SQL.getConnection().prepareStatement("SELECT COUNT(id) FROM user WHERE uuid = ?");
            pS.setString(1, uuid.toString());
            pS.execute();
            ResultSet test =  pS.getResultSet();
            if (test.next()){
                System.out.print("test 1 " + test.getInt(1)); //id = 1 si cela existe
                System.out.print("test 2 " + test.getMetaData().getColumnCount());
                int enregister = test.getInt(1);
                System.out.print("Enregistrer est egal a " +  enregister);
            }
            pS.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}
