package fr.ethorn.main;

import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
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
            ResultSet datalogin =  pS.getResultSet();
            if (datalogin.next()){
                int FirstConnection = datalogin.getInt(1);
                /**
                 * Si l'utilsateur ce connecte pour la premiere fois alors l'on l'ajoute a la bdd.
                 * Sinon On charge les données.
                 */
                if(FirstConnection == 0){
                    try {
                        PreparedStatement pS2 = main.SQL.getConnection().prepareStatement("INSERT INTO `user` (`uuid`, `rang`) VALUES (?,?)");
                        pS2.setString(1, uuid.toString());
                        pS2.setString(2, "neophyte");
                        pS2.execute();
                        pS2.close();
                    } catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }else{
                    try {
                        PreparedStatement ps3 = main.SQL.getConnection().prepareStatement("SELECT id_classe FROM user WHERE uuid=?");
                        ps3.setString(1,uuid.toString());
                        ps3.execute();
                        ResultSet dataload =  ps3.getResultSet();
                        dataload.next();

                        Player p = e.getPlayer();
                        /**
                         * 1 = neophyte.
                         * 2 = celeste --> séraphin
                         * 3 = celeste --> déchu
                         * 4 = maudite --> demon
                         * 5 = maudite --> dragon
                         * 6 = pure --> elfe
                         * 7 = pure -->  fée
                         * 8 = Tellurique --> Nain
                         * 9 = Tellurique --> Gnome
                         * 10 = Ethnique --> Humain
                         * 11 = Ethnique --> Homme-bête
                         * 12 = Divine -->  dieu unique
                         * 13 = Divine --> dieu antique
                         */
                        switch (dataload.getObject(1).toString()){
                            case "1":
                                break;
                            case "2":
                                p.setHealthScale(p.getHealthScale() + 10);
                                p.setMaxHealth(p.getMaxHealth() + 10D );
                                break;
                            case "3":
                                p.setHealthScale(p.getHealthScale() + 6);
                                p.setMaxHealth(p.getMaxHealth() + 6D );
                                break;
                            case "4":
                                p.setHealthScale(p.getHealthScale() + 10);
                                p.setMaxHealth(p.getMaxHealth() + 10D );
                                break;
                            case "5":
                                p.setHealthScale(p.getHealthScale() + 20);
                                p.setMaxHealth(p.getMaxHealth() + 20D );
                                break;
                            case "6":
                                p.setHealthScale(p.getHealthScale() + 6);
                                p.setMaxHealth(p.getMaxHealth() + 6D );
                                break;
                            case "7":
                                p.setHealthScale(p.getHealthScale() - 4);
                                p.setMaxHealth(p.getMaxHealth() - 4D );
                                break;
                            case "8":
                                p.setHealthScale(p.getHealthScale() + -8);
                                p.setMaxHealth(p.getMaxHealth() + -8D );
                                break;
                            case "9":
                                p.setHealthScale(p.getHealthScale() + 6);
                                p.setMaxHealth(p.getMaxHealth() + 6D );
                                break;
                            case "10":
                                break;
                            case "11":
                                p.setHealthScale(p.getHealthScale() + 6);
                                p.setMaxHealth(p.getMaxHealth() + 6D );
                                break;
                            case "12":
                                p.setHealthScale(p.getHealthScale() + 40);
                                p.setMaxHealth(p.getMaxHealth() + 40D );
                                break;
                            case "13":
                                p.setHealthScale(p.getHealthScale() + 30);
                                p.setMaxHealth(p.getMaxHealth() + 30D );

                                break;
                        }

                        ps3.close();
                    } catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            pS.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
