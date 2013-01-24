package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import pl.prz.l04.database.CatMovies;
import pl.prz.l04.database.Categories;
import pl.prz.l04.database.DataBase;
import pl.prz.l04.database.Movies;

/**
 *
 * @author Pyciak
 */
public class Search extends JFrame {

    JLabel label1, label2, label3;
    JButton button;
    JTextField field1, field2;
    JFrame frame;
    JComboBox categories;
    private Dao<Categories, Integer> Cat = null;
    Object data[][];
    JPanel rightPanel;
    private final Dao<Movies, Integer> Mov;
    private final Dao<CatMovies, Integer> CatMov;

    Search(JPanel mainPanel) {
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
        Mov = DataBase.getInstance().getMoviesDao();
        CatMov = DataBase.getInstance().getCatMovDao();
    }

    public String[] Connection() {

        String[] name = new String[50]; //zmienna na kategorie pobrane z bazy
        // łączymy się z bazą danych
        
        int size = 1;
        // wykonujemy SELECT i wypisujemy wynik zapytania na ekran
        try {
            Cat = DataBase.getInstance().getCategoriesDao();
            List<Categories> rs = Cat.queryForAll();
            int i = 1;
            
            name[0] = "Wszystkie kategorie";
            for(Categories item : rs) {
                name[i] = item.getName();
                i++;
                size++;
            }
        } catch (Exception e) {
            System.out.println("Błąd przy pobieraniu danych o kategoriach... Search");
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
            List<Movies> moviesList = null;

            // If conditions are not set, broadest possible search will be made
            if(nazwa.isEmpty())
                nazwa = "*";
            if(opis.isEmpty())
                opis = "*";
            if(category.isEmpty())
                category = "*";
            
            if (category == "Wszystkie kategorie") {
                moviesList =
                        Mov.query(
                        Mov.queryBuilder().where()
                        .like("name", nazwa)
                        .and()
                        .like("content", opis)
                        .prepare());
            } else if (category != "Wszystkie kategorie") {
                List<Categories> lookUpCat = Cat.queryForEq("name", category);
                List<CatMovies> lookUpCatMov = 
                        CatMov.query(
                        CatMov.queryBuilder()
                        .selectColumns("movies")
                        .where()
                        .eq("name", lookUpCat.get(0).getId())
                        .prepare());
                moviesList =
                        Mov.query(
                        Mov.queryBuilder().where()
                        .like("name", nazwa)
                        .and()
                        .like("content", opis)
                        .in("id", lookUpCatMov)
                        .prepare());
            }
            
            int size = moviesList.size();
            if( size == 0)
                size++;             // miejsce na "brak wyników"
            data = new Object[size][4];
            String films;           // nazwa filmu
            String created;         // nazwa kateogri
            int i = 0;              // inkdes tablicy
            int id;                 // id filmu
            for(Movies item : moviesList) {     // wpisanie do tablicy wszystkich istniejacych filmow
                films = item.getName();
                created = item.getCreated().toString();
                id = item.getId();
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
            
        } catch (Exception e) {
            System.out.println("Błąd przy pobieraniu danych... Search");
        }

        
        Object dane[][] = data;
        rightPanel.removeAll();
        JPanel panel = new MovieTable(dane).getPanel();

        panel.setLocation(0, 0);
        panel.setSize(820, 600);

        rightPanel.add(panel);
        rightPanel.repaint();

    }
}
