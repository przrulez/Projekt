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
