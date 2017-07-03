package fr.ethorn.main;

import com.sun.webkit.plugin.PluginListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    public static String plugName = "EthornRaces"; //Cotiens une constante ayant le nom du pluginn


    public PluginManager plugManager;

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


    }

    @Override
    public void onDisable()
    {
        System.out.print("[Ethorn]Le plugin de race s'arrete");
    }

}
