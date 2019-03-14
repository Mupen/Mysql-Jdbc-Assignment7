package se.lexicon.daniel.mysql_jdbc.data;

import java.util.List;

import se.lexicon.daniel.mysql_jdbc.model.CityModel;

public interface CityDao {

	CityModel findCityById( int id);
	CityModel addCity(CityModel city);
	CityModel updateCity(CityModel city);
	int deleteCity(CityModel city);
	
	List<CityModel> findCitiesByCode(String code);
	List<CityModel> findCitiesByName(String name);
	List<CityModel> findAllCities();

}