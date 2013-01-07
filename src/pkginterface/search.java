package pkginterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Pyciak
 */
public class search extends JFrame {

    JLabel label1, label2, label3;
    JButton button;
    JTextField field1, field2;
    JFrame frame;
    JComboBox categories;
    Connection c = null;
    Object data[][];
    JPanel rightPanel;

    search(JPanel mainPanel) {
        String name[];
        name = Connection();
        rightPanel = mainPanel;
        setTitle("Szukaj");
        setSize(540, 140);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);

        frame = this;

        /**
         * *****************************************************
         */
        String items[] = name; //z bazy danych
        /**
         * *******************************************************
         */
        categories = new JComboBox(items);

        label1 = new JLabel("Kategoria:");
        label2 = new JLabel("Nazwa:");
        label3 = new JLabel("Opis:");

        button = new JButton("Szukaj");
        field1 = new JTextField("");
        field2 = new JTextField("");

        label1.setLocation(20, 15);
        label1.setSize(50, 25);
        categories.setLocation(20, 45);
        categories.setSize(160, 25);

        label2.setLocation(190, 15);
        label2.setSize(50, 25);
        field1.setLocation(190, 45);
        field1.setSize(160, 25);

        label3.setLocation(360, 15);
        label3.setSize(50, 25);
        field2.setLocation(360, 45);
        field2.setSize(160, 25);

        button.setLocation(450, 75);
        button.setSize(70, 25);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Zapisanie
                String nazwa;
                String opis;
                String category;
                Object o;
                nazwa = field1.getText();
                opis = field2.getText();
                o = categories.getSelectedItem();
                category = (String) o;
                frame.setVisible(false);
                PrintMovies(nazwa, opis, category);
            }
        });

        add(label1);
        add(label2);
        add(label3);
        add(field1);
        add(field2);
        add(categories);
        add(button);
    }

    public String[] Connection() {

        String[] name = new String[50]; //zmienna na kategorie pobrane z bazy
        // łączymy się z bazą danych
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:mysql://localhost/movies", "root", "");
            System.out.println("Udało się połączyć z bazą danych...");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem z driverem bazy danych...");
        } catch (SQLException e) {
            System.out.println("Nie można połączyć się z baza danych...");
        } catch (Exception e) {
            System.out.println("Błąd przy połączeniu z bazą danych...");
        }
        int size = 1;
        // wykonujemy SELECT i wypisujemy wynik zapytania na ekran
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM categories");
            int i = 1;
            
            name[0] = "Wszystkie kategorie";
            while (rs.next()) {
                name[i] = rs.getString("name");
                i++;
                size++;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Błąd przy pobieraniu danych...");
        }

        // zamykamy połączenie (jeżeli było otwarte)
        if (c != null) {
            try {
//                c.close();
//                System.out.println("Połączenie z bazą danych zamknięte...");
            } catch (Exception e) {
                System.out.println("Błąd przy zamykaniu bazy danych...");
            }
        }
        //zmniejszenie wielkosci tablicy dla lepszego wyswietlenia
        String[] kategorie = new String[size];
        for(int i = 0; i < size; i++){
            kategorie[i] = name[i];
        }
        return kategorie;
    }

    public void PrintMovies(String nazwa, String opis, String category) {
        int znalezionoFilm = 0;  //ustawiane na 1, gdy znajdzie jakis film
        try {
            String query = null;
            String warunek = null;

            if (category != "Wszystkie kategorie") {
                warunek = "AND m.id=cm.movie_id AND cm.category_id=c.id AND c.name='" + category + "'";
            } else {
                warunek = "";
            }
            Statement st = c.createStatement();
            // szukanie po nazwie  i kategorii
            if (opis.isEmpty() && !nazwa.isEmpty()) {
                query = "SELECT  DISTINCT(m.id), m.name, m.created FROM movies m, categories_movies cm, categories c"
                        + " WHERE m.name LIKE '%" + nazwa + "%' " + warunek + " ";
            }
            //szukanie po opisie i kategorii
            if (!opis.isEmpty() && nazwa.isEmpty()) {
                query = "SELECT DISTINCT(m.id), m.name, m.created FROM movies m, categories_movies cm, categories c"
                        + " WHERE m.content LIKE '%" + opis + "%' " + warunek + " ";
            }
            //szukanie po opisie kategorii i nazwie
            if (!opis.isEmpty() && !nazwa.isEmpty()) {
                query = "SELECT DISTINCT(m.id), m.name, m.created FROM movies m, categories_movies cm, categories c"
                        + " WHERE m.content LIKE '%" + opis + "%' AND m.name LIKE '%" + nazwa + "%' " + warunek + " ";
            }
            //szukanie tylko po kategorii
            if (opis.isEmpty() && nazwa.isEmpty()) {
                query = "SELECT DISTINCT(m.id), m.name, m.created FROM movies m, categories_movies cm, categories c"
                        + " WHERE 1=1 " + warunek + " ";
            }

            ResultSet rs = st.executeQuery(query);
            int size = 1;           // wartosc 1, miejsce na informacje o braku wynikow
            //rozmiar dla tablicy
            while (rs.next()) {
                size++;
            }
            if(size > 1)
                size--;             // znaleziono film, usuniecie miejsca na info o braku wynikow
            rs = st.executeQuery(query);
            data = new Object[size][4];
            String films;           // nazwa filmu
            String created;         // nazwa kateogri
            int i = 0;              // inkdes tablicy
            int id;                 // id filmu
            while (rs.next()) {     // wpisanie do tablicy wszystkich istniejacych filmow
                films = rs.getString("name");
                created = rs.getString("created");
                id = rs.getInt("id");
                data[i][0] = id;
                data[i][1] = films;
                data[i][2] = category;
                data[i][3] = created;
                i++;
                znalezionoFilm = 1;

            }
            // wpisanie informacji jesli nie znaleziono zadnego filmu
             if(znalezionoFilm == 0)   {
               data[i][0] = 1;
               data[i][1] = "Brak wyników";
               data[i][2] = "-";
               data[i][3] = "-";
             }
            
            
            rs.close();
        } catch (Exception e) {
            System.out.println("Błąd przy pobieraniu danych...");
        }

        
        Object dane[][] = data;
        rightPanel.removeAll();
        JPanel panel = new movieTable(dane).getPanel();

        panel.setLocation(0, 0);
        panel.setSize(820, 600);

        rightPanel.add(panel);
        rightPanel.repaint();

    }
}
