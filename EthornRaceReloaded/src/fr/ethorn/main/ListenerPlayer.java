package fr.ethorn.main;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
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
 * 12 = Divine -->  dieu unique KamiTehto
 * 13 = Divine --> dieu antique
 * 14 = Akulaz | Race divine, antique | Damné
 * 15 =  Nysis | Race Divine, antique | Pur
 * 16 = M0nz3rnam3 | Race Divine, antique | Tellurien
 * 17 = Goldoeur | Race divine, antique | Ethnien
 */
                        switch (dataload.getObject(1).toString()){
                            case "1":
                                p.setHealthScale(20D);
                                p.setMaxHealth(20D);
                                HmapMananger.ajoutHmap(uuid,1);
                                p.sendMessage("Vous devez choisir un cast !");
                                p.setWalkSpeed(0.2F);
                                break;
                            case "2":
                                p.setHealthScale(30);
                                p.setMaxHealth(30D);
                                p.setWalkSpeed(0.3F);
                                HmapMananger.ajoutHmap(uuid,2);

                                break;
                            case "3":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D );
                                p.setWalkSpeed(0.3F);
                                HmapMananger.ajoutHmap(uuid,3);
                                break;
                            case "4":
                                p.setHealthScale(30);
                                p.setMaxHealth(30D);
                                p.setWalkSpeed(0.175F);
                                HmapMananger.ajoutHmap(uuid,4);
                                break;
                            case "5":
                                p.setHealthScale(40);
                                p.setMaxHealth(40D);
                                p.setWalkSpeed(0.175F);
                                HmapMananger.ajoutHmap(uuid,5);
                                break;
                            case "6":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D);
                                p.setWalkSpeed(0.3F);
                                HmapMananger.ajoutHmap(uuid,6);
                                break;
                            case "7":
                                p.setHealthScale(16);
                                p.setMaxHealth(16D);
                                p.setWalkSpeed(0.275F);
                                HmapMananger.ajoutHmap(uuid,7);
                                break;
                            case "8":
                                p.setHealthScale(16D);
                                p.setMaxHealth(16D );
                                p.setWalkSpeed(0.15F);
                                HmapMananger.ajoutHmap(uuid,8);
                                break;
                            case "9":
                                p.setHealthScale(12);
                                p.setMaxHealth(12D);
                                p.setWalkSpeed(0.15F);
                                HmapMananger.ajoutHmap(uuid,9);
                                break;
                            case "10":
                                p.setWalkSpeed(0.2F);
                                HmapMananger.ajoutHmap(uuid,10);

                                break;
                            case "11":
                                p.setHealthScale(26);
                                p.setMaxHealth(26D );
                                p.setWalkSpeed(0.3F);
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
                            case "14":
                                p.setHealthScale(50);
                                p.setMaxHealth(50D);
                                HmapMananger.ajoutHmap(uuid,14);
                                break;
                            case "15":
                                p.setHealthScale(50);
                                p.setMaxHealth(50D);
                                HmapMananger.ajoutHmap(uuid,15);
                                break;
                            case "16":
                                p.setHealthScale(50);
                                p.setMaxHealth(50D);
                                HmapMananger.ajoutHmap(uuid,16);
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
    private void entityDamageEvent(EntityDamageByEntityEvent e){

/**
 * L'on récupère les entité
 */
        final Entity damaged = e.getEntity();
        double defense = 0;

/**
 * process qui vérifie que la personne qui ce fait taper est un joueur est lui calcul la def.
 */
        if(damaged instanceof Player){

            UUID damagedUuid = damaged.getUniqueId();
            Integer data2 = Main.playerLink.get(damagedUuid);
            Player player = (Player) damaged;
            boolean stuff = StatisticManager.returnStuffEquiped(data2,player);
            System.out.print("[Ethorn]Le nom de la personne qui c'est fait taper est :" + damaged.getName());
            defense = StatisticManager.getDefenseStats(data2,stuff);
        }

/**
 * Process qui verifie que la personne qui tape est un joeuur et applique les degat est la def.
 */
        if(e.getDamager().getType().equals(EntityType.PLAYER) ){
            System.out.println("[Ethorn] Le joueur " + e.getDamager().getName() + "a call EntityDamageEvent");
            UUID DamagerUuid = e.getDamager().getUniqueId();
            Integer data = Main.playerLink.get(DamagerUuid);
            double degat = StatisticManager.getDamageStats(data);

            double degatJoueur;
            degatJoueur = e.getDamage() + degat;
            if(degatJoueur - defense <= 0){
                degatJoueur = 0;
            }else{
                degatJoueur = degatJoueur - defense;
            }
            if (e.getDamager() instanceof Arrow)
            {
                e.setDamage(degatJoueur);
                System.out.print("[ETHORN]damage : " +e.getDamage());
            }else{
                e.setDamage(degatJoueur);
                System.out.print("[ETHORN] damage : " +e.getDamage());
            }

        }else if(e.getDamager().getType().isAlive() && !e.getDamager().getType().equals(EntityType.PLAYER)){
            if(damaged instanceof Player){
                double degatEntite = e.getDamage();
                if(degatEntite - defense <= 0){
                    degatEntite = 0;
                }else{
                    degatEntite = degatEntite - defense;
                }
                e.setDamage(degatEntite);
                System.out.println("[Ethorn] L'entité " + e.getDamager().getName() + "a call le elseif EntityDamageEvent et causera "+ degatEntite + "degats");

            }
        }
    }



    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        Integer data = Main.playerLink.get(uuid);
        HmapMananger.valueRemoveHmap(uuid,data);
    }

    @EventHandler
    public void playerEatEvent(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        UUID UUID = p.getUniqueId();
        Integer data = Main.playerLink.get(UUID);
        Material item = e.getItem().getType();
        p.sendMessage("[ETHORN-DEV] Vous utilisez l'iem suivant :" + item);

    }
}

