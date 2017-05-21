package fr.ethorn.boite;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import fr.ethorn.races.Caste;

/**
 * une class ou on mettre que des méthodes en static qui permet de facilité certaine opération
 * a considerer comme une boite noir ou le code n'est pas forcémnet a comprendre , en effet 
 * je vais utiliser des fonctionnalité java 8 assez compliquer a expliquer mais bon tant que ça marche ^^
 * 
 * @author kyllian
 *
 */
public class BoiteOutils {
	
	/**
	 * on veut savoir si la caste existe ou non
	 * 
	 * @param nameCaste
	 * @return un boolean qui dit si oui ou non la caste existe
	 */
	public static boolean isCasteExist(String nameCaste){
		
		
		
		//alors expliquer ca ca vas être compliquer , en gros fait une sorte de for each et sa applique la condition a chaque élément et sa retourne tru des que sa tombe sur un truc qui match la condition
		return Caste.castes.stream().anyMatch( caste -> {
			return caste.getName().equals(nameCaste) ;
		});
		//autre facon de faire comme vous le voyez pas de crochet on balance la condition direct c'est une sorte de if general sur toute la list
		//Caste.castes.stream().anyMatch( caste -> caste.getName() == nameCaste  );
	}
	
	/**
	 *	on veut savoir dans le cas ou la caste existe quel est son index dans le tableaux Caste.caste
	 * 
	 * @param nameCaste
	 * @return
	 */
	public static int idCaste(String nameCaste){
		//si la caste existe
		if(BoiteOutils.isCasteExist(nameCaste)){
			//alors la XDD je sais comment ça fonctionne 
			OptionalInt index = IntStream.range(0, Caste.castes.size()).filter( i -> {
				return Caste.castes.get(i).getName().equals(nameCaste);} ).findFirst();
			return index.getAsInt();
		
		}
		//on retourne -1 si elle n'existe pas
		return -1 ;
	}
	
}
