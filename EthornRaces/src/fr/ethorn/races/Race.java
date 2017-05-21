package fr.ethorn.races;

import java.util.ArrayList;

public class Race 
{
	public static ArrayList<Race> races = new ArrayList<Race>();
	
	/**
	 * Gère le nom de la race
	 */
	private String name;

	/**
	 * Constructeur de base
	 * 
	 */
	public Race()
	{
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Race)
		{
			Race tmp = (Race)obj;
			
			if (tmp.getName().equalsIgnoreCase(this.getName()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Constructeur de base
	 * 
	 * @param pName
	 */
	public Race(String pName)
	{
		this.name = pName;
		
		races.add(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
