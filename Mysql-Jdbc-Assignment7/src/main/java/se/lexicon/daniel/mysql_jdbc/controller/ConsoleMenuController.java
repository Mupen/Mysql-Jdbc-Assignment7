package se.lexicon.daniel.mysql_jdbc.controller;
import se.lexicon.daniel.mysql_jdbc.data.CityDao;
import se.lexicon.daniel.mysql_jdbc.data.CityDaoJbdc;
import se.lexicon.daniel.mysql_jdbc.model.CityModel;
import se.lexicon.daniel.mysql_jdbc.utility.KeyboardInput;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ConsoleMenuController {
	private boolean running;
	CityDao dao = new CityDaoJbdc();
	CityModel addCity;
	CityModel acquiredCity;
	List<CityModel> acquiredAllCities;
	List<CityModel> acquiredCities;

	public ConsoleMenuController() {
		running = true;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void run() {
		startingUserInterface(); // What action do you want to take?
	}

	private void startingUserInterface() {

		String userInput = KeyboardInput.getString("\n What action do you want to take? \n" +
				"\n" +
				" [Find]   Find a city or Cities \n" +
				" [Add]   Add City to database \n" +
				" [Update]   Update City in the database \n" +
				" [Delete]   Delete City in the database \n" +
				" [Quit]   Immediately terminate the program...\n" +
				" \n" +
				" Your selection... "
				);

		switch (userInput.toLowerCase()) {
		case "find":
			System.out.println("");
			System.out.println("|-----------------------|");
			System.out.println("| Find a city or Cities |");
			System.out.println("|-----------------------|");
			System.out.println("");
			findInterface();
			break;
		case "add":
			System.out.println("");
			System.out.println("|----------------------|");
			System.out.println("| Add City to database |");
			System.out.println("|----------------------|");
			System.out.println("");
			addInterface();
			break;
		case "update":
			System.out.println("");
			System.out.println("|-----------------------------|");
			System.out.println("| Update City in the database |");
			System.out.println("|-----------------------------|");
			System.out.println("");
			updateInterface();
			break;
		case "delete":
			System.out.println("");
			System.out.println("|-----------------------------|");
			System.out.println("| Delete City in the database |");
			System.out.println("|-----------------------------|");
			System.out.println("");
			deleteInterface();
			break;
		case "quit":
			System.out.println("");
			System.out.println("|--------------------------------------|");
			System.out.println("| Immediately terminate the program... |");
			System.out.println("|--------------------------------------|");
			System.out.println("");
			setRunning(false);
			break;
		default:
			System.out.println("");
			System.out.println("|-------------------|");
			System.out.println("| No such choice... |");
			System.out.println("|-------------------|");
			System.out.println("");
		}
	}


	private void findInterface() {
		String userInput = KeyboardInput.getString("\n What action do you want to take? \n" +
				"\n" +
				" [Id]   Find a city or Cities \n" +
				" [Code]   Find all cities with code \n" +
				" [Name]   Find all cities with name \n" +
				" [All]   Find all cities \n" +
				" [Quit]   Immediately terminate the program...\n" +
				" \n" +
				" Your selection... "
				);
		switch(userInput.toLowerCase()) {
		case "id": 
			System.out.println("\n Type in id for the city you are looking for ");
			int userIdInput = KeyboardInput.getInt();
			acquiredCity = dao.findCityById(userIdInput);
			System.out.println(acquiredCity.toString());
			break;
		case "code": 
			String userCodeInput = KeyboardInput.getString("\n Type in code for the cities you are looking for ");
			acquiredCities = dao.findCitiesByCode(userCodeInput);
			System.out.println(acquiredCity.toString());
			break;
		case "name": 
			String userNameInput = KeyboardInput.getString("\n Type in code for the cities you are looking for ");
			acquiredCities = dao.findCitiesByName(userNameInput);
			System.out.println(acquiredCity.toString());
			break;
		case "all": 
			acquiredAllCities = dao.findAllCities();
			System.out.println(acquiredAllCities.toString());
			break;
		default:
		}
	}

	private void addInterface() { 
		System.out.println("add a city with name*Name, countryCode*AFG, district*District, population*666666");
		CityModel addCity = new CityModel(0, "Name", "AFG", "District", 666666);
		dao.addCity(addCity);

	} 

	private void updateInterface() {
		System.out.print("\n Type in id of the object you want to update. ");
		int userIdInput = KeyboardInput.getInt();
		acquiredCity = dao.findCityById(userIdInput);

		String userNameInput = KeyboardInput.getString("\n Type in new name the old one was " + acquiredCity.getName() + " ");
		acquiredCity.setName(userNameInput);

		String userCodeInput = KeyboardInput.getString("\n Type in new code the old one was " + acquiredCity.getCountryCode() + " ");
		acquiredCity.setCountryCode(userCodeInput);

		String userDistrictInput = KeyboardInput.getString("\n Type in new district the old one was " + acquiredCity.getDistrict() + " ");
		acquiredCity.setDistrict(userDistrictInput);

		System.out.print("\n Type in new population the old one was " + acquiredCity.getPopulation() + " ");
		int userPopulationInput = KeyboardInput.getInt();
		acquiredCity.setPopulation(userPopulationInput);

		dao.updateCity(acquiredCity);
	}

	private void deleteInterface() {
		// TODO Auto-generated method stub
		System.out.print("\n Type in id of the object you want to delete. ");
		int userIdInput = KeyboardInput.getInt();
		acquiredCity = dao.findCityById(userIdInput);
		dao.deleteCity(acquiredCity);
	}

}
