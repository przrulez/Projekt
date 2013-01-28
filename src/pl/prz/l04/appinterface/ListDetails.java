package pl.prz.l04.appinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Pyciak
 */
public class ListDetails extends JFrame {

    JFrame frame;
    JTable lista;
    private int id_play;

    ListDetails(int id) {
        setTitle("Filmy należace do listy");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;

        int ja = id;
        id_play = ja;
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
        // String name = "dsa";
        int licz = 0;
        int a = 0;
        try {
            Statement st = connect.createStatement();
            ResultSet zlicz = st.executeQuery("SELECT movies.id, movies.name FROM movies "
                    + "JOIN playList_movies ON(movies.id = playList_movies.movie_id "
                    + "AND playList_movies.play_list_id = " + id + ")");
            while (zlicz.next()) {
                a++;
            }

            Object[][] listaFilmow = new Object[a][2];
            //wybieranie filmow ktore nalezą do listy konkretnej listy
            ResultSet playList = st.executeQuery("SELECT movies.id, movies.name FROM movies "
                    + "JOIN playList_movies ON(movies.id = playList_movies.movie_id "
                    + "AND playList_movies.play_list_id = " + id + ")");
            while (playList.next()) { //petla zapisuje wybrane filmy do tabllicy

                //String lp = playList.getInt("id");
                //name = playList.getString("name");
                //String user_id = playList.getString("user_id");
                listaFilmow[licz][0] = playList.getInt("id");
                listaFilmow[licz][1] = playList.getString("name");
                licz++;
            }

            for (int i = 0; i < listaFilmow.length; i++) {
                listaFilmow[i] = push(listaFilmow[i]);//wyswitlanie listy filmow
            }
            Object opisy[] = {"Lp", "Nazwa", ""};
            lista = new JTable(listaFilmow, opisy) {

                public boolean isCellEditable(int row, int col)//zablokowanie możliwość edycji
                {
                    if (col > 1) {
                        return true; //do buttonów
                    } else {
                        return false;
                    }
                }
            };
        } catch (Exception e) {
            System.out.println("Błąd pobierania danych...");
        }

        new ButtonColumn(lista, usun, 2);


        lista.setRowHeight(25);
        lista.getColumnModel().getColumn(0).setPreferredWidth(25);
        lista.getColumnModel().getColumn(1).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setSize(495, 200);
        scrollPane.setLocation(0, 0);
        add(scrollPane);

    }

    private static Object[] push(Object[] array) {
        String p1 = "Usuń";

        Object[] longer = new Object[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            longer[i] = array[i];
        }
        longer[array.length] = p1;

        return longer;
    }
    Action usun = new AbstractAction() {

        public void actionPerformed(ActionEvent e) {
            int id_movie = Integer.valueOf(e.getActionCommand());
            //Usunięcie filmu z listy
            frame.setVisible(false);
            usun(id_movie);
            JOptionPane.showMessageDialog(frame, "usunięto film " + id_movie);
        }
    };

    public void usun(int numer) {
        //łaczenie z baza
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
            //usuwanie z listy filmu
            int row = st.executeUpdate("DELETE FROM playList_movies WHERE (movie_id =" + numer + ") AND (play_list_id = " + id_play + ")");

        } catch (Exception e) {
            System.out.println("Błąd pobierania danych...");
        }
    }

    
}