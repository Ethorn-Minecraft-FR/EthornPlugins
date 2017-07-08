package fr.ethorn.main;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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
                                p.setHealthScale(20D);
                                p.setMaxHealth(20D);
                                HmapMananger.ajoutHmap(uuid,1);
                                p.sendMessage("Vous devez choisir un cast !");
                                break;
                            case "2":
                                p.setHealthScale(30);
                                p.setMaxHealth(30D);
                                HmapMananger.ajoutHmap(uuid,2);
                                break;
                            case "3":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D );
                                HmapMananger.ajoutHmap(uuid,3);
                                break;
                            case "4":
                                p.setHealthScale(30);
                                p.setMaxHealth(30D);
                                HmapMananger.ajoutHmap(uuid,4);
                                break;
                            case "5":
                                p.setHealthScale(40);
                                p.setMaxHealth(40D);
                                HmapMananger.ajoutHmap(uuid,5);
                                break;
                            case "6":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D);
                                HmapMananger.ajoutHmap(uuid,6);
                                break;
                            case "7":
                                p.setHealthScale(16);
                                p.setMaxHealth(16D);
                                HmapMananger.ajoutHmap(uuid,7);
                                break;
                            case "8":
                                p.setHealthScale(16D);
                                p.setMaxHealth(16D );
                                HmapMananger.ajoutHmap(uuid,8);
                                break;
                            case "9":
                                p.setHealthScale(12);
                                p.setMaxHealth(12D);
                                HmapMananger.ajoutHmap(uuid,9);
                                break;
                            case "10":
                                HmapMananger.ajoutHmap(uuid,10);

                                break;
                            case "11":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D );
                                HmapMananger.ajoutHmap(uuid,11);
                                break;
                            case "12":
                                p.setHealthScale(60);
                                p.setMaxHealth(60D);
                                HmapMananger.ajoutHmap(uuid,12);
                                break;
                            case "13":
                                p.setHealthScale(50);
                                p.setMaxHealth(50D);
                                HmapMananger.ajoutHmap(uuid,13);
                                break;
                            default:
                                System.out.print("le joueur : " +e.getPlayer().getName() + "N'est pas valide dans la bdd");
                                e.getPlayer().kickPlayer("Contact un admin sur le discord du serveur. Erreur (Not in database)");
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
    @EventHandler
    public void entityDamageEvent(EntityDamageByEntityEvent e){
        if(e.getDamager().getType().equals(EntityType.PLAYER) ){
            System.out.println("[Ethorn] Le joueur " + e.getDamager().getName() + "a call EntityDamageEvent");
            Player player = (Player) e.getEntity();
            UUID uuid = e.getDamager().getUniqueId();
            Integer data = Main.playerLink.get(uuid);
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
            switch (data){
                case 1:
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 2:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 1);
                    }
                    e.setDamage(e.getDamage() + 1);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 3:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 1.5);
                    }
                    e.setDamage(e.getDamage() + 1.5);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 4:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 3);
                    }
                    e.setDamage(e.getDamage() + 3);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 5:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 4);
                    }
                    e.setDamage(e.getDamage() + 4);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 6:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 1);
                    }
                    e.setDamage(e.getDamage() + 1);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 7:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 1);
                    }
                    e.setDamage(e.getDamage() + 1);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 8:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 3);
                    }
                    e.setDamage(e.getDamage() + 3);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 9:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 2);
                    }
                    e.setDamage(e.getDamage() + 2);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 10:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 1);
                    }
                    e.setDamage(e.getDamage() + 1);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 11:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 4);
                    }
                    e.setDamage(e.getDamage() + 4);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 12:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 1);
                    }
                    e.setDamage(e.getDamage() + 1);
                    System.out.print("damage : " +e.getDamage());
                    break;
                case 13:
                    if (e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();
                        e.setDamage(e.getDamage() + 1);
                    }
                    e.setDamage(e.getDamage() + 1);
                    System.out.print("damage : " +e.getDamage());
                    break;
                default:
                    player.kickPlayer("Merci de contacter un dev erreur (data value default)");
                    break;
            }

        }else if(e.getDamager().getType().isAlive() && !e.getDamager().getType().equals(EntityType.PLAYER)){
            System.out.println("[Ethorn] L'entité " + e.getDamager().getName() + "a call le elseif EntityDamageEvent");
        }
    }

    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        Integer data = Main.playerLink.get(uuid);
        HmapMananger.valueRemoveHmap(uuid,data);
    }
}

