package fr.ethorn.races;

import java.util.ArrayList;

/**
 * Gère les castes des races
 * 
 * @author CIad
 * @version 1.0.1
 */
public class Caste 
{
	/**
	 * Gère l'id de la caste
	 */
	private int id;
	
	
	/**
	 * Gère la race de la caste
	 */
	private Race race;
	
	/**
	 * Gère le nom de la caste
	 */
	private String name;
	
	/**
	 * Nombre de point de vie
	 */
	private int health;
	
	/**
	 * Nombre de point de force
	 */
	private int strength;
	
	/**
	 * Nombre de point de vitesse
	 */
	private int speed;
	
	/**
	 * Nombre de point de bouclier
	 */
	private int shield;

	/**
	 * Description de la caste
	 */
	private String description;
	
	/**
	 * Liste contenant les castes et la race
	 */
	public static ArrayList<Caste> castes = new ArrayList<Caste>();
	
	/**
	 * Constructeur de base
	 * 
	 * @param pRace
	 * @param pName
	 * @param pHealth
	 * @param pStrength
	 * @param pSpeed
	 * @param pShield
	 */
	public Caste(Race pRace, String pName, int pHealth, int pStrength, int pSpeed, int pShield, String pDescription)
	{
		this.name = pName;
		this.health = pHealth;
		this.strength = pStrength;
		this.speed = pSpeed;
		this.shield = pShield;
		this.setDescription(pDescription);
		
		castes.add(this);
		Main.raceCaste.put(this, pRace);
	}
	
	/**
	 * Renvoie la caste actuelle
	 * @return Caste
	 */
	public Caste getInstance()
	{
		return this;
	}

	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getHealth() {
		return health;
	}



	public void setHealth(int health) {
		this.health = health;
	}



	public int getStrength() {
		return strength;
	}



	public void setStrength(int strength) {
		this.strength = strength;
	}



	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public int getShield() {
		return shield;
	}



	public void setShield(int shield) {
		this.shield = shield;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
