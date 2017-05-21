package fr.Ethorn.Races;

public class Race 
{
	/**
	 * Gère le nom de la race
	 */
	private String name;

	/**
	 * Constructeur de base
	 * 
	 * @param pName
	 */
	public Race(String pName)
	{
		this.name = pName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
