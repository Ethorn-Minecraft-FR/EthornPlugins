package fr.ethorn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import fr.ethorn.manager.PlayerManager;
import fr.ethorn.races.Caste;
import fr.ethorn.races.Main;

public class JoinCasteCommand implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		
		

		
		//si c'et le modo qui fait le con on l'engeule XD
		if (sender instanceof ConsoleCommandSender){
			
			sender.sendMessage("§r ARRETE DE FAIRE LE CON");
			
		}
		//si c'est un joueur on gère la commandes
		else if(sender instanceof Player){
			
			Player player = (Player) sender;
			
			//on verifie que la commende est la bonne
			if(cmd.getName().equalsIgnoreCase("castejoin")){
				
				//on verifie que l'on a bien des arguments
				if(args.length != 0){
					
					//on recupère la caste en argument
					String caste = args[0];
					
					//le message que le joueur recevra s'il ne peut pas rejoindre la caste au cas ou il y aurais plusieur raison
					String message = ""; 
					
					//si le joueur peut rejoindre une caste
					if( (message = playerCanJoinCaste(player,caste)).equals("ok")){
						
						int indexCaste = -1;
						//si la caste existe elle nous renvoie l'id de la position dans la liste des Caste sinon -1
						if( (indexCaste = isCasteExiste(caste)) != -1){
							
							//on recupère la class caste qui correspond pour la mettre dans la hashmap
							Caste casteClass = Caste.castes.get(indexCaste);
							
							//on l'ajoute a la liste des joueur et des caste qui leur corespond
							Main.playerCaste.put(player, casteClass);
							
							//on ecrit le changement dans le fichier player .yml
							PlayerManager.createEntryYml(player);
							
							//message de confirmation
							player.sendMessage("vous avez rejoins la caste : "+caste);
							
							
						}
						//la caste demander n'existe pas
						else{
							
							player.sendMessage("la caste : "+caste+" n'existe pas");
							
						}
					}
					//le joueur ne peut pas pretendre a aller dans cette caste
					else{
						
						player.sendMessage(message);
						
					}

					
				}
				else{
					player.sendMessage("/casteJoin <nom_de_la_caste>");
				}
			}
			
			
		}
		else{
			sender.sendMessage("impossible Oo");
		}
		return true;
		
	}
	
	
	private String playerCanJoinCaste(Player player,String caste) {
		
		//a si le joueur est connu c'est déja mauvais XD
		if(Main.playerCaste.containsKey(player)){
			
			//si le joueur est connue alors on cherche sa caste
			String pCaste = Main.playerCaste.get(player).getName();
			return "impossible de rejoindre la caste : " + caste+" car vous faite partie des : "+pCaste;
		
		}
		
		return "ok";
	}

	private int isCasteExiste(String caste){
		
		//on parcoure la liste de nos castes
		for(int i = 0 ; i < Caste.castes.size() ; i++){
			
			//on recupère la Caste qui correspond a la ième position
			Caste listCast =  Caste.castes.get(i);
					
			//si on trouve une occurence one la retourne
			if(listCast.getName().equals(caste)){
				return i;
			}
		}
		
		//si on a rien trouvé bas c'est que nada ça existe pas
		return -1;
	}
	
	
	

}
