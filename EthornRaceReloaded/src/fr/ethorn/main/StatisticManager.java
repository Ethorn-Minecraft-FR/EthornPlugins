package fr.ethorn.main;

import org.bukkit.entity.Entity;

/**
 * Created by marin on 08/07/2017.
 */
public class StatisticManager {

    public static int getDefenseStats(int castId){
        int defensevalue = 0;

        switch (castId) {
            case 1:
                defensevalue = 0;
                break;
            case 2:
                defensevalue = 0;
                break;
            case 3:
                defensevalue = 0;
                break;
            case 4:
                defensevalue = 0;
                break;
            case 5:
                defensevalue = 0;
                break;
            case 6:
                defensevalue = 0;
                break;
            case 7:
                defensevalue = 0;
                break;
            case 8:
                defensevalue = 0;
                break;
            case 9:
                defensevalue = 0;
                break;
            case 10:
                defensevalue = 0;
                break;
            case 11:
                defensevalue = 0;
                break;
            case 12:
                defensevalue = 0;
                break;
            case 13:
                defensevalue = 0;
                break;
            default:
                break;
        }


        return defensevalue;
    }

    public static double getDamageStats(int castId){
        double Damagevalue = 0;

        switch (castId) {
            case 1:
                Damagevalue = 0;
                break;
            case 2:
                Damagevalue = 1;
                break;
            case 3:
                Damagevalue = 1.5;
                break;
            case 4:
                Damagevalue = 3;
                break;
            case 5:
                Damagevalue = 4;
                break;
            case 6:
                Damagevalue = 1;
                break;
            case 7:
                Damagevalue = 1;
                break;
            case 8:
                Damagevalue = 3;
                break;
            case 9:
                Damagevalue = 2;
                break;
            case 10:
                Damagevalue = 1;
                break;
            case 11:
                Damagevalue = 4;
                break;
            case 12:
                Damagevalue = 1;
                break;
            case 13:
                Damagevalue = 1;
                break;
            default:
                break;
        }


        return Damagevalue;
    }
}
