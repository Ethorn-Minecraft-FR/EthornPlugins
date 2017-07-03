package fr.ethorn.main;

import com.sun.webkit.plugin.PluginListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main extends JavaPlugin {

    public static String plugName = "EthornRaces"; //Cotiens une constante ayant le nom du pluginn


    public PluginManager plugManager;

    /**
     * Contiens la class SQLConnection
     */
    public SQLConnection SQL;

    /**
     * Contiens la class ListenerPlayer
     */
    public ListenerPlayer listenerPlayer;

    @Override
    public void onEnable()
    {
        System.out.print("[Ethorn]Le plugin de race démarre");


        this.plugManager = getServer().getPluginManager();
        this.listenerPlayer = new ListenerPlayer(this);
        this.plugManager.registerEvents(this.listenerPlayer, this);

        try{
            SQL = new SQLConnection("jdbc:mysql://", "minecraft107.omgserv.com", "minecraft_135086", "minecraft_135086", "root66");
            SQL.connection();
        }catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable()
    {
        System.out.print("[Ethorn]Le plugin de race s'arrete");
    }

}
