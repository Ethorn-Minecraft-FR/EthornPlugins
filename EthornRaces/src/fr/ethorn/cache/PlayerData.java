package fr.ethorn.cache;

/**
 * Gère les données des joueurs, ce qui liera la race, le caste etc...
 * 
 * 
 * 
 * @author CIad
 * @version 1.0.0
 */
public class PlayerData 
{
	private String race, caste;
	@SuppressWarnings("unused")
	private int blockInterdict;
	
	

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}
}
