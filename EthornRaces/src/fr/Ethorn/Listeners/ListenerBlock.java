package fr.Ethorn.Listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Gère les écoutes de block
 * 
 * @author CIad
 * @version 1.0.0
 */
public class ListenerBlock implements Listener
{
	/**
	 * Gère les écoutes pour posez des blocks
	 * 
	 * @param BlockPlaceEvent Evenement
	 */
	@SuppressWarnings("unused")
	@EventHandler
	public void blockPlaceEvent(BlockPlaceEvent e)
	{
		Block block = e.getBlock(); //Récupère le block placé
		Player player = e.getPlayer(); //Récupère le joueur qui place le block
		
		
		/** 
		 * En test par CIad
		 * 
		if(block instanceof Redstone && player.hasPermission("redstone.use"))
		{
			e.setBuild(true);
		} else {
			player.sendMessage(ChatColor.GOLD + "Tu ne peux pas utiliser la redstone.");
			e.setBuild(false);
		}
		*/
		
	}
}
