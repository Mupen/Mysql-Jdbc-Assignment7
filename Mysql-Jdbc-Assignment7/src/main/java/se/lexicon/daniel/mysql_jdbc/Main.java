package se.lexicon.daniel.mysql_jdbc;

import java.util.List;

import se.lexicon.daniel.mysql_jdbc.data.CityDao;
import se.lexicon.daniel.mysql_jdbc.data.CityDaoJbdc;
import se.lexicon.daniel.mysql_jdbc.model.CityModel;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        CityDao dao = new CityDaoJbdc();

 		List<CityModel> acquiredAllCities = dao.findAllCities();
 		System.out.println(acquiredAllCities.toString());
 		
/*      CityModel acquiredCity = dao.findCityById(1);
        System.out.println(acquiredCity.toString());
        
		List<CityModel> acquiredCitieWithNames = dao.findCitiesByName("San Miguel");
		System.out.println(acquiredCitieWithNames.toString());
		
 		List<CityModel> acquiredCitieWithCode = dao.findCitiesByCode("PAN");
		System.out.println(acquiredCitieWithCode.toString());   
        

 		
 		CityModel city = new CityModel(0, "Name", "Code", "District", 666666);
        dao.addCity(city);
        
 		*/


    }
}