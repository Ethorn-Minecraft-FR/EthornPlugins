package fr.ethorn.main;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Main extends JavaPlugin {

    public static final Map<UUID, Integer> playerLink = new HashMap<>();


    public static String plugName = "EthornRaces"; //Cotiens une constante ayant le nom du pluginn
    public static Main instance;

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
        Main.instance = this;

        this.plugManager = getServer().getPluginManager();
        this.listenerPlayer = new ListenerPlayer(this);
        this.plugManager.registerEvents(this.listenerPlayer, this);
        this.getCommand("ethorn").setExecutor(new AddPlayerCommand());

        try{
            SQL = new SQLConnection("jdbc:mysql://", "localhost", "ethorn", "root", "root66");
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
