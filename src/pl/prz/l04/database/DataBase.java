/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prz.l04.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author przemoli
 */
public class DataBase {
    private static DataBase db;
    public  static DataBase getInstance()
    {
        if(db == null)
            db = new DataBase();
        return db;
    }
    
    private DataBase()
    {
        try {
            conn = new JdbcConnectionSource("jdbc:sqlite:movies.sqlite");
        } catch (SQLException e) {
            System.out.println("Błąd przy połączeniu z bazą danych... DataBase " + e);
        }
        try {
            TableUtils.createTableIfNotExists(conn, Movies.class);
            TableUtils.createTableIfNotExists(conn, Categories.class);
            TableUtils.createTableIfNotExists(conn, CatMovies.class);
        } catch (SQLException e) {
            System.out.println("Błąd przy próbie tworzenia tabel... DataBase " + e);
        }
        
        try {
            moviesDao = DaoManager.createDao(conn, Movies.class);
            categoriesDao = DaoManager.createDao(conn, Categories.class);
            catMovDao = DaoManager.createDao(conn, CatMovies.class);
            
        } catch (SQLException e) {
            System.out.println("Błąd przy tworzeniu Dao... DataBase " + e);
        }
        System.out.println("Ustawiono BD!");
    }
    
    private ConnectionSource conn;
    private Dao<Movies, Integer> moviesDao;
    private Dao<Categories, Integer> categoriesDao;
    private Dao<CatMovies, Integer> catMovDao;
    
    public static Vector<String> returnCategoriesList() {
        Dao<Categories,Integer> Cat = DataBase.getInstance().getCategoriesDao();
        List<Categories> catList = new Vector<Categories>();
        Vector<String> catNamesList = new Vector<String>();
        try {
            catList = Cat.queryForAll();
        } catch (SQLException ex) {
            System.out.println("Błąd przy pobieraniu kategorii... MovieAdd " + ex);
        }
        for( Categories item : catList)
        {
            catNamesList.add(item.getName());
        }
        if(catNamesList.isEmpty())
            catNamesList.add("Brak kategorii!!");
        return catNamesList;
    }
    
    public static String getMoviesIdsFor(String category) throws SQLException {
        Dao<CatMovies, Integer> CatMov = DataBase.getInstance().getCatMovDao();
        Dao<Categories, Integer> Cat = DataBase.getInstance().getCategoriesDao();
        List<Categories> lookUpCat = Cat.queryForEq("name", category);
        List<CatMovies> lookUpCatMov =
                CatMov.query(
                CatMov.queryBuilder().selectColumns("movie_id").where().eq("category_id", lookUpCat.get(0).getId()).prepare());
        String catMovString = "";
        for(CatMovies item : lookUpCatMov)
        {
            catMovString += item.getMovie().getId().toString() + ", ";
        }
        catMovString = catMovString.substring(0, catMovString.length() - 2);
        return catMovString;
        }
    
        /**
     * @return the moviesDao
     */
    public Dao<Movies, Integer> getMoviesDao() {
        return moviesDao;
    }

    /**
     * @return the categoriesDao
     */
    public Dao<Categories, Integer> getCategoriesDao() {
        return categoriesDao;
    }

    /**
     * @return the catMovDao
     */
    public Dao<CatMovies, Integer> getCatMovDao() {
        return catMovDao;
    }
}
