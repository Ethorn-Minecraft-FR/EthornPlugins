package fr.ethorn.main;


import org.bukkit.Material;

public class FoodManager {


    /**
     * 1 = Gâteau
     * 2 = Ragoût de lapin
     * 3 = Porc cuit
     * 4 = Steak
     * 5 = Tarte à la citrouille
     * 6 = Mouton cuit
     * 7 = Ragoût de champignons
     * 8 = Soupe de betteraves
     * 9 = Poulet rôti
     * 10 = Pomme de terre cuite
     * 11 = Lapin cuit
     * 12 = Pain
     * 13 = Poisson cuit
     * 14 = Pomme dorée enchantée
     * 15 = Carotte
     * 16 = Pomme rouge
     * 17 = Chorus
     * 18 = Chair putréfiée
     * 19 = Porc cru
     * 20 = Bœuf cru
     * 21 =  Lapin cru
     * 22 = Mouton cru
     * 23 = Poisson cru
     * 24 = Tranche de pastèque
     * 25 = Poulet cru
     * 26 = Cookie
     * 27 = Betterave
     * 28 = Pomme de terre	
     *
     */
    public static int getFoodType(Material item){
        if(item == Material.CAKE){
            return 1;
        }else if(item == Material.RABBIT_STEW){
            return 2;

        } else if(item == Material.GRILLED_PORK){
            return 3;
        }else if(item == Material.COOKED_BEEF){
            return 4;

        }else if(item == Material.PUMPKIN_PIE){
            return 5;

        }else if(item == Material.COOKED_MUTTON){
            return 6;

        }else if(item == Material.MUSHROOM_SOUP){
            return 7;


        }else if(item == Material.BEETROOT_SOUP){
            return 8;

        }else if(item == Material.COOKED_CHICKEN){
            return 9;

        }else if(item == Material.BAKED_POTATO){
            return 10;

        }else if(item == Material.COOKED_RABBIT){
            return 11;

        }else if(item == Material.BREAD){
            return 12;

        }else if(item == Material.COOKED_FISH){
            return 13;

        }else if(item == Material.GOLDEN_APPLE){
            return 14;
        }else if(item == Material.CARROT){
            return 15;

        } else if(item == Material.APPLE){
            return 16;

        }else if(item == Material.CHORUS_FRUIT){
            return 17;

        }else if(item == Material.ROTTEN_FLESH){
            return 18;

        }else if(item == Material.PORK){
            return 19;

        }else if(item == Material.RAW_BEEF){
            return 20;

        }else if(item == Material.RABBIT_FOOT){
            return 21;

        }else if(item == Material.MUTTON){
            return 22;

        }else if(item == Material.RAW_FISH){
            return 23;

        }else if(item == Material.MELON){
            return 24;

        }else if(item == Material.RAW_CHICKEN){
            return 25;

        }else if(item == Material.COOKIE){
            return 26;

        }else if(item == Material.BEETROOT){
            return 27;

        }else if(item == Material.POTATO){
            return 28;

        }

        return 0;
    }
}
