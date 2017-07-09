package fr.ethorn.main;



public class StatisticManager {

    public static double getDefenseStats(int castId){
        double defensevalue = 0;

        switch (castId) {
            case 1:
                defensevalue = 0;
                break;
            case 2:
                defensevalue = 1;
                break;
            case 3:
                defensevalue = 1.5;
                break;
            case 4:
                defensevalue = 3;
                break;
            case 5:
                defensevalue = 4;
                break;
            case 6:
                defensevalue = 2;
                break;
            case 7:
                defensevalue = 1;
                break;
            case 8:
                defensevalue = 4;
                break;
            case 9:
                defensevalue = 3;
                break;
            case 10:
                defensevalue = 1;
                break;
            case 11:
                defensevalue = 3;
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
