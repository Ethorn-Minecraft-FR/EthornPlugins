package fr.ethorn.main;



import org.bukkit.entity.Player;

public class StatisticManager {

    public static double getDefenseStats(int castId, boolean stuff){
        double defensevalue = 0;

        switch (castId) {
            case 1:
                defensevalue = 0;
                break;
            case 2:
                if(stuff){
                    defensevalue = 3;
                    return defensevalue;
                }else{
                    defensevalue = 1;
                }
                break;
            case 3:
                if(stuff){
                    defensevalue = 3.5;
                    return defensevalue;
                }else {
                    defensevalue = 1.5;
                }
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
                if(stuff){
                    defensevalue = 6;
                    return defensevalue;
                }else {
                    defensevalue = 4;
                }
                break;
            case 9:
                if(stuff){
                    defensevalue = 5;
                    return defensevalue;
                }else {
                    defensevalue = 3;
                }
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
            case 14:
                defensevalue = 4;
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
            case 14:
                Damagevalue = 4;
                break;
            default:
                break;
        }


        return Damagevalue;
    }

    public static boolean returnStuffEquiped(int castId, Player player){

        boolean equiped;
        switch (castId) {
            case 1:
                equiped = false;
                break;
            case 2:
            case 3:
                if (player.getInventory().getBoots().getData().getItemType().toString().equals("GOLD_BOOTS")){
                    if (player.getInventory().getChestplate().getData().getItemType().toString().equals("GOLD_CHESTPLATE")){
                        if (player.getInventory().getHelmet().getData().getItemType().toString().equals("GOLD_HELMET")) {
                            if (player.getInventory().getLeggings().getData().getItemType().toString().equals("GOLD_LEGGINGS")) {

                                return true;
                            }{
                                equiped = false;
                            }
                        }else{
                            equiped = false;
                        }
                    }else{
                        equiped = false;
                    }
                }else{
                    equiped = false;
                }
                break;
            case 4:

                equiped = true;
                break;
            case 5:

                equiped = true;
                break;
            case 6:

                equiped = true;
                break;
            case 7:

                equiped = true;
                break;
            case 8:
            case 9:
                if (player.getInventory().getBoots().getData().getItemType().toString().equals("CHAINMAIL_BOOTS")){
                    if (player.getInventory().getChestplate().getData().getItemType().toString().equals("CHAINMAIL_CHESTPLATE")){
                        if (player.getInventory().getHelmet().getData().getItemType().toString().equals("CHAINMAIL_HELMET")) {
                            if (player.getInventory().getLeggings().getData().getItemType().toString().equals("CHAINMAIL_LEGGINGS")) {

                                return true;
                            }{
                                equiped = false;
                            }
                        }else{
                            equiped = false;
                        }
                    }else{
                        equiped = false;
                    }
                }else{
                    equiped = false;
                }
                break;
            case 10:

                equiped = true;
                break;
            case 11:

                equiped = true;
                break;
            case 12:

                equiped = true;
                break;
            case 13:

                equiped = true;
                break;
            default:
                equiped = false;

                break;
        }

        return equiped;
    }
}
