/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.sql.*;

/**
 *
 * @author Krzysiek Ch.
 */
public class sortTable {

    //String nazwa;
    Object[][] dane = new Object[8][4];

    public Object[][] sortTable(Object benchmark) {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/movies", "root", "");
            System.out.println("Udało się połączyć z bazą danych...");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem z driverem bazy danych...");
        } catch (SQLException e) {
            System.out.println("Nie można połączyć się z baza danych...");

        } catch (Exception e) {
            System.out.println("Błąd przy połączeniu z bazą danych...");
        }
        try {
            Statement st = connect.createStatement();
            Statement st2 = connect.createStatement();
            /*ResultSet rs = st.executeQuery("SELECT DISTINCT movies.id, movies.name, categories.name as kategoria, movies.created FROM movies"
             + " JOIN categories_movies ON(movies.id = categories_movies.movie_id)"
             + " JOIN categories ON (categories.id = categories_movies.category_id) ORDER BY movies.id");*/
            ResultSet movies = st.executeQuery("SELECT id, name, created FROM movies");;
            if (benchmark == "Data utworzenia") {
                movies = st.executeQuery("SELECT id, name, created FROM movies ORDER BY created");
            } else if (benchmark == "Nazwa") {
                movies = st.executeQuery("SELECT id, name, created FROM movies ORDER BY name");
            } else if (benchmark == "Losowo") {
                movies = st.executeQuery("SELECT id, name, created FROM movies ORDER BY RAND()");
            } else if (benchmark == "Kolejność") {
                movies = st.executeQuery("SELECT id, name, created FROM movies ORDER BY id");
            }
            int i = 0;
            while (movies.next()) {
                int id = movies.getInt("id");
                String tytul = movies.getString("name");
                //String kategoria = rs.getString("kategoria");
                String data = movies.getString("created");

                //nazwa = tytul;
                dane[i][0] = id;
                dane[i][1] = tytul;
                dane[i][2] = "";
                ResultSet category = st2.executeQuery("SELECT categories.name FROM categories"
                        + " JOIN categories_movies ON(categories.id = categories_movies.category_id AND movie_id = " + id + ")");
                int count = 0;
                while (category.next()) {
                    String kategoria = category.getString("name");
                    if (kategoria != null) {
                        if (count > 0) {
                            dane[i][2] = dane[i][2] + ", " + kategoria;
                        } else {
                            dane[i][2] = dane[i][2] + " " + kategoria;
                        }
                        count++;
                    }
                }
                category.close();
                dane[i][3] = data;
                i++;
            }
            movies.close();
        } catch (Exception e) {
            System.out.println("Błąd przy pobieraniu danych...");
        }
        return dane;
    }
}