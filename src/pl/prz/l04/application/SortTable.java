/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prz.l04.application;

import com.j256.ormlite.dao.Dao;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import pl.prz.l04.database.CatMovies;
import pl.prz.l04.database.Categories;
import pl.prz.l04.database.DataBase;
import pl.prz.l04.database.Movies;

/**
 *
 * @author Krzysiek Ch.
 */
public class SortTable {

    //String nazwa;
    Object[][] dane = new Object[8][4];
    private Dao<Movies, Integer> Mov;
    private Dao<CatMovies, Integer> CatMov;
    List<Movies> list = new Vector<Movies>();

    public List<Movies> sortTable(Object benchmark) {
        try {
            Mov = DataBase.getInstance().getMoviesDao();
            CatMov = DataBase.getInstance().getCatMovDao();
            
            String order_by = "name";
            /*ResultSet rs = st.executeQuery("SELECT DISTINCT movies.id, movies.name, categories.name as kategoria, movies.created FROM movies"
             + " JOIN categories_movies ON(movies.id = categories_movies.movie_id)"
             + " JOIN categories ON (categories.id = categories_movies.category_id) ORDER BY movies.id");*/
            if (benchmark == "Data utworzenia") {
                order_by = "created";
            } else if (benchmark == "Kategoria"){
                order_by = "category";
            } else if (benchmark == "Nazwa") {
                order_by = "name";
            } else if (benchmark == "Kolejność") {
                order_by = "id";
            }
            
            list = Mov.query(
                    Mov.queryBuilder().selectColumns("name", "created")
                            .orderBy(order_by, true)
                            .prepare());
            
            int i = 0;
            for(Movies item : list) {
                int id = item.getId();
                String tytul = item.getName();
                //String kategoria = rs.getString("kategoria");
                String data = item.getCreated().toString();

                //nazwa = tytul;
                dane[i][0] = id;
                dane[i][1] = tytul;
                dane[i][2] = "";
                List<CatMovies> catMovList = CatMov.queryForEq("movie", id);
                int count = 0;
                for(CatMovies cmItem : catMovList) {
                    String kategoria = cmItem.getCategory().getName();
                    if (kategoria != null) {
                        if (count > 0) {
                            dane[i][2] = dane[i][2] + ", " + kategoria;
                        } else {
                            dane[i][2] = dane[i][2] + " " + kategoria;
                        }
                        count++;
                    }
                }
                dane[i][3] = data;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Błąd przy pobieraniu danych... SortTable " + e);
        }
//        return dane;
        return list;
    }
}