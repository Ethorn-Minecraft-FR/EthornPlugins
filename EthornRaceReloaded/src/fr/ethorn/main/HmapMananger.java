package fr.ethorn.main;

import java.util.UUID;

/**
 * Created by marin on 07/07/2017.
 */
public class HmapMananger {

    /**
     * @param uuid The uuid of the player to add in the hmap
     * @param cast The cast int value.
     *
     * Permet d'ajouter un joueur dans la hmap ce qui permet de faire un traitement de donnée par la suite.
     */
    public static void ajoutHmap(UUID uuid,int cast){
        Main.playerLink.put(uuid,cast);
    }

    /**
     * @param uuid The uuid of the player to add in the hmap
     * @param cast The cast int value.
     * @return booleen true if value exist in hmap, false if value doesnt exist
     * Permet de savoir si la valeur est existante dans la hmap.
     */
    public static boolean valueExistHmap(UUID uuid,int cast){

       //todo
        return true;
    }

    public static void valueRemoveHmap(UUID uuid,int cast){

        Main.playerLink.remove(uuid,cast);

    }
}
