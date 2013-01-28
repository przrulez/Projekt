package pl.prz.l04.appinterface;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Pyciak
 */
public class ListAddMovie extends JFrame {

    JButton button = new JButton("Dodaj");

    ;
    JLabel label1 = new JLabel("Lista:");
    JComboBox categories;
    JFrame frame;

    ListAddMovie(final int id) {
        setTitle("Dodaj Film do Listy");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;

        wyswietlanie();  //wyswietlanie liisty list w comboboxie do ktorych mozna dodac film


        /********************************************************/
        // String items[] = listaList[]; //z bazy danych
        /**********************************************************/
        label1.setLocation(10, 10);
        label1.setSize(60, 25);
        categories.setLocation(80, 10);
        categories.setSize(200, 25);

        button.setLocation(220, 40);
        button.setSize(60, 25);

        add(label1);
        add(categories);
        add(button);

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String nazwa;
                Object o;
                o = categories.getSelectedItem();
                nazwa = (String) o;
                dodajFilm(nazwa, id);
                frame.setVisible(false);
            }
        });
    }

    public void wyswietlanie() {

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
            ResultSet zlicz = st.executeQuery("SELECT name FROM play_lists");
            while (zlicz.next()) {
                a++;
            }

            Object[] listaList = new Object[a];

            ResultSet playList = st.executeQuery("SELECT name FROM play_lists");
            while (playList.next()) {
                //String lp = playList.getInt("id");
                //name = playList.getString("name");
                //String user_id = playList.getString("user_id");
                listaList[licz] = playList.getString("name");
                licz++;
            }
            categories = new JComboBox(listaList);

        } catch (Exception e) {
            System.out.println("Błąd pobierania danych...");
        }
    }

    public void dodajFilm(String nazwa_listy, int id_filmu) {

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
        //Object[] listaList = new Object[1];

        try {
            int Id_playListy = 0;
            int licz = 0;
            int a = 0;

            Statement st = connect.createStatement();
            ResultSet zlicz = st.executeQuery("SELECT id FROM play_lists where name='" + nazwa_listy + "'");
            while (zlicz.next()) {
                a++;
            }

            Object[] listaList = new Object[a];

            ResultSet playList = st.executeQuery("SELECT id FROM play_lists where name='" + nazwa_listy + "'");
            while (playList.next()) {
                listaList[licz] = playList.getInt("id");
                licz++;
                if (licz == a) {
                    Id_playListy = playList.getInt("id");
                }
            }
            //System.out.println(Id_playListy);



            int id_movie_playList = 0;
            int b = 0;
            int lic = 0;
            // pobieranie ID ostatniego rekordu z listy aby moc dodac rekord
            Statement st2 = connect.createStatement();
            ResultSet zliczanie = st2.executeQuery("SELECT id FROM playList_movies");
            while (zliczanie.next()) {
                b++;
            }

            Object[] obiekt = new Object[b];

            ResultSet playLis = st2.executeQuery("SELECT id FROM playList_movies");
            while (playLis.next()) {
                obiekt[lic] = playLis.getInt("id");
                lic++;
                if (lic == b) {
                    id_movie_playList = playLis.getInt("id");
                }
            }
           // System.out.println(id_movie_playList);
            id_movie_playList++;


            Statement st1 = connect.createStatement();
            st1.executeUpdate("INSERT playList_movies VALUES ('" + id_movie_playList + "' , '" + Id_playListy + "', '" + id_filmu + "')");

        } catch (Exception e) {
            System.out.println("Błąd pobierania danych...");
        }
    }
}