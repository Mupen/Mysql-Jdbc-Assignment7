package se.lexicon.daniel.mysql_jdbc.data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.lexicon.daniel.mysql_jdbc.model.CityModel;


public class CityDaoJbdc implements CityDao {
	List<CityModel> defaultCities;

	private CityModel createCityFromResultSet(ResultSet rs) throws SQLException {
		return new CityModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	}

	@Override
	public CityModel findCityById(int id) {
		CityModel result = null;

		try(Connection conn = MySqlAccess.getConnection();
				PreparedStatement statement = createPreparedCityIdStatement(conn, id);
				ResultSet rs = statement.executeQuery()){

			while(rs.next()) {
				result =  createCityFromResultSet(rs);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(result != null) { return result; }
		else {
			CityModel defaultCity = new CityModel(0, "Default", "AFG", "District", 666666);
			return defaultCity;
		}
	}

	private PreparedStatement createPreparedCityIdStatement(Connection conn, int id) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE ID = ?");
		statement.setInt(1, id);
		return statement;
	}


	@Override
	public List<CityModel> findCitiesByName(String name) {
		if(name != null) {
			List<CityModel> resultList = new ArrayList<>();
			try(Connection conn = MySqlAccess.getConnection();
					PreparedStatement statement = createPreparedCityNameStatement(conn, name);
					ResultSet rs = statement.executeQuery()){

				while(rs.next()) {
					resultList.add(createCityFromResultSet(rs));
				}

			}catch(SQLException e) {e.printStackTrace();}
			return resultList;
		}
		CityModel defaultCity = new CityModel(0, "Default", "AFG", "District", 666666);
		defaultCities.add(defaultCity);
		return defaultCities;

	}

	private PreparedStatement createPreparedCityNameStatement(Connection conn, String name) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE name LIKE ?");
		statement.setString(1, "%" + name + "%");
		return statement;
	}

	@Override
	public List<CityModel> findCitiesByCode(String countryCode) {
		if(countryCode != null) {
			List<CityModel> resultList = new ArrayList<>();
			try(Connection conn = MySqlAccess.getConnection();
					PreparedStatement statement = createPreparedCityCodeStatement(conn, countryCode);
					ResultSet rs = statement.executeQuery()){

				while(rs.next()) {
					resultList.add(createCityFromResultSet(rs));
				}

			}catch(SQLException e) {e.printStackTrace();}
			return resultList;
		}
		CityModel defaultCity = new CityModel(0, "Default", "AFG", "District", 666666);
		defaultCities.add(defaultCity);
		return defaultCities;
	}

	private PreparedStatement createPreparedCityCodeStatement(Connection conn, String countryCode) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE CountryCode LIKE ?");
		statement.setString(1, countryCode);
		return statement;
	}

	@Override
	public List<CityModel> findAllCities() {
		List<CityModel> resultList = new ArrayList<>();
		try(Connection conn = MySqlAccess.getConnection();
				PreparedStatement statement = createPreparedCityAllStatement(conn);
				ResultSet rs = statement.executeQuery()){

			while(rs.next()) {
				resultList.add(createCityFromResultSet(rs));
			}

		}catch(SQLException e) {e.printStackTrace();}
		return resultList;
	}

	private PreparedStatement createPreparedCityAllStatement(Connection conn) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city");
		return statement;
	}	



	@Override
	public CityModel addCity(CityModel city) {
		if(city != null) {
			try (Connection conn = MySqlAccess.getConnection();
					PreparedStatement statement = createPreparedAddCityStatement(conn, city.getName(), city.getCountryCode(), city.getDistrict(), city.getPopulation());) {
				statement.executeUpdate();
			} 
			catch (SQLException e) {e.printStackTrace();}
			return city;
		}
		CityModel defaultCity = new CityModel(0, "Default", "AFG", "District", 666666);
		return defaultCity;

	}

	private PreparedStatement createPreparedAddCityStatement(Connection conn, String name, String countrycode, String district, int population) throws SQLException {
		String query= "INSERT INTO city (name, countrycode, district, population) values (?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, countrycode);
		statement.setString(3, district);
		statement.setInt(4, population);

		return statement;
	}


	@Override
	public CityModel updateCity(CityModel city) {
		if(city != null) {
			try 
			(Connection conn = MySqlAccess.getConnection();
					PreparedStatement statement = createUpdateStatement(conn, city);) {statement.executeUpdate();} 
			catch (SQLException e) {e.printStackTrace();}
			System.out.print(city.toString());
		}
		CityModel defaultCity = new CityModel(0, "Default", "AFG", "District", 666666);
		return defaultCity;
	}

	private PreparedStatement createUpdateStatement(Connection conn, CityModel city) throws SQLException {
		String query= "UPDATE city SET Name=?, CountryCode=?, District=?, Population=? WHERE ID=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, city.getName());
		statement.setString(2,  city.getCountryCode());
		statement.setString(3, city.getDistrict());
		statement.setInt(4, city.getPopulation());
		statement.setInt(5, city.getId());
		return statement;
	}

	@Override
	public CityModel deleteCity(CityModel city) {
		if(city != null) {
			try (Connection conn = MySqlAccess.getConnection();
					PreparedStatement statement = createDeleteStatement(conn, city);) {
				statement.executeUpdate();
				System.out.print(city.toString());
			} 
			catch (Exception e) {e.printStackTrace();}
			System.out.print("\n *** Deleted *** \n");
		}
		CityModel defaultCity = new CityModel(0, "Default", "AFG", "District", 666666);
		return defaultCity;
	}

	private PreparedStatement createDeleteStatement(Connection conn, CityModel city) throws SQLException {
		String query= "DELETE FROM city WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, city.getId());
		return statement;
	}
}
