package se.lexicon.daniel.Mysql_Jdbc_Assignment7.model;

import java.util.Objects;

public class CityModel {
	private int id;
	private String name;
	private String countryCode;
	private String district;
	private int population;
	
	public CityModel(int id, String name, String countryCode, String district, int population) {
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n *** [City Info] *** \n");
		
		sb.append("\n [Id] = ");
		sb.append(id + "\n");
		
		sb.append(" [Name] = ");
		sb.append(name + "\n");
		
		sb.append(" [CountryCode] = ");
		sb.append(countryCode + "\n");
		
		sb.append(" [District] = ");
		sb.append(district + "\n");
		
		sb.append(" [Population] = ");
		sb.append(population + "\n");
		return sb.toString(); 
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryCode, district, id, name, population);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityModel other = (CityModel) obj;
		return Objects.equals(countryCode, other.countryCode) && Objects.equals(district, other.district)
				&& id == other.id && Objects.equals(name, other.name) && population == other.population;
	}
}