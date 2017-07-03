package fr.ethorn.main;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    @Override
    public void onEnable()
    {
        System.out.print("[Ethorn]Le plugin de race démarre");
    }

    @Override
    public void onDisable()
    {
        System.out.print("[Ethorn]Le plugin de race s'arrete");
    }

}
