package fr.ethorn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.ethorn.races.Caste;
import fr.ethorn.races.Main;
import fr.ethorn.races.Race;
import net.md_5.bungee.api.ChatColor;

/**
 * Gère la commands caste
 * 
 * @author Tardigradeus
 * @version 1.0.1
 */
public class InfoCasteCommand implements CommandExecutor 
{

	/**
	 * InfoCasteCommand 
	 * 
	 *  [caste <raceName> <casteName>]
	 * 
	 *  [Args:0] - liste les différentes castes
	 *  [Args:1] - liste les castes d'une race
	 *  [Args:2] - Affiche une caste précise
	 *  
	 *  @author Tardigradeus
	 *  @verion 1.0.1
	 */
	@Override
	public boolean onCommand(CommandSender sender , Command command, String msg, String[] args) 
	{
		if(command.getLabel().equalsIgnoreCase("caste"))
		{
			//on envoie la liste des castes
			sender.sendMessage(ChatColor.YELLOW + "-----------------");
			sender.sendMessage(ChatColor.RED +    "      Castes     ");
			sender.sendMessage(ChatColor.YELLOW + "-----------------");

			//Si, il y a deux arguments
			if(args.length > 0 && args.length <= 2) 
			{
				//Crée un objet Race avec le nom mis en premier argument
				Race tmpRace = new Race();
				tmpRace.setName(args[0]);

				//Si, la race est contenu dans la hashmap raceCaste
				if (Main.raceCaste.containsValue(tmpRace))
				{
					//Si, il n'y à qu'un seul argument notemment la race
					if (args.length == 1)
					{
						//Boucle dans le hashmap pour afficher toute les caste de la race
						Main.raceCaste.forEach((b, a) -> {	
							if (a.equals(tmpRace))
							{
								sender.sendMessage(ChatColor.GOLD + "\t" + b.getName());
							}
						});
					} else {
						//Boucle pour afficher les info de la caste
						Main.raceCaste.forEach((keyCaste, valueRace) -> {
							if (valueRace.equals(tmpRace) && keyCaste.getName().equals(args[1]))
							{
								sender.sendMessage(ChatColor.GREEN + "\t" + valueRace.getName());
								sender.sendMessage(ChatColor.GREEN + "\t" + keyCaste.getName());
								sender.sendMessage(ChatColor.BLUE + "Health:\t" + keyCaste.getHealth());
								sender.sendMessage(ChatColor.BLUE + "Strength:\t" + keyCaste.getStrength());
								sender.sendMessage(ChatColor.BLUE + "Speed:\t" + keyCaste.getSpeed());
								sender.sendMessage(ChatColor.BLUE + "Shield:\t" + keyCaste.getShield());
								sender.sendMessage(ChatColor.BLUE + "Description:\t");
								sender.sendMessage(keyCaste.getDescription());

								
								
								return;
							}
						});
					}
				} else {
					sender.sendMessage(ChatColor.RED + "La caste demandé n'existe pas.");
				}

				//Si, juste la commande sans argument
			} else if (args.length == 0){

				//Affiche toute les castes
				Caste.castes.forEach( a -> {
					sender.sendMessage(ChatColor.DARK_RED + "\t" + a.getName());
				});
			} else {
				return false;
			}
		}

		return true;
	}
}
