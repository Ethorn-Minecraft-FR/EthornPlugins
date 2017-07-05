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
    public void playerJoinEvent(PlayerJoinEvent e)
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
                        PreparedStatement pS2 = main.SQL.getConnection().prepareStatement("INSERT INTO `user` (`uuid`, `rang` , `playername`) VALUES (?,?,?)");
                        pS2.setString(1, uuid.toString());
                        pS2.setString(2, "neophyte");
                        pS2.setString(3, e.getPlayer().getName());

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
                                p.setHealthScale(30);
                                p.setMaxHealth(30D);
                                break;
                            case "3":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D );
                                break;
                            case "4":
                                p.setHealthScale(30);
                                p.setMaxHealth(30D);
                                break;
                            case "5":
                                p.setHealthScale(40);
                                p.setMaxHealth(40D);
                                break;
                            case "6":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D);
                                break;
                            case "7":
                                p.setHealthScale(16);
                                p.setMaxHealth(16D);
                                break;
                            case "8":
                                p.setHealthScale(12);
                                p.setMaxHealth(12D);
                                break;
                            case "9":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D );
                                break;
                            case "10":
                                break;
                            case "11":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D );
                                break;
                            case "12":
                                p.setHealthScale(60);
                                p.setMaxHealth(60D);
                                break;
                            case "13":
                                //todo  p.setStatistic(Statistic.DAMAGE_DEALT,p.getStatistic(Statistic.DEATHS) + 2);

                                p.setHealthScale(50);
                                p.setMaxHealth(50D);
                                break;
                             default:
                                 System.out.print("le joueur : " +e.getPlayer().getName() + "N'est pas valide dans la bdd");
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
