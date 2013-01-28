package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pl.prz.l04.database.DataBase;
import pl.prz.l04.database.Movies;
import pl.prz.l04.database.PlayMovies;
import pl.prz.l04.database.Playlist;

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
    private final Dao<Playlist, Integer> Play;
    private final Dao<PlayMovies, Integer> PlayMov;
    private Movies selectedMovie;
    

    ListAddMovie(Movies selMovie) {
        setTitle("Dodaj Film do Listy");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;
        selectedMovie = selMovie;
        
        Play = DataBase.getInstance().getPlayDao();
        PlayMov = DataBase.getInstance().getPlayMovDao();

        wyswietlanie();  //wyswietlanie liisty list w comboboxie do ktorych mozna dodac film
        
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
                dodajFilm(nazwa, selectedMovie);
                frame.setVisible(false);
            }
        });
    }

    public void wyswietlanie() {
        Vector<String> listaList = new Vector<String>();
        List<Playlist> playlisty = new Vector<Playlist>();
        try {
            playlisty = Play.queryForAll();
        } catch (SQLException ex) {
             System.out.println("Błąd przy pobieraniu playlist... ListAddMovie" + ex);
        }
        for(Playlist item : playlisty)
        {
            listaList.add(item.getName());
        }
        categories = new JComboBox(listaList);
    }

    public void dodajFilm(String nazwa_listy, Movies selectedMovie) {
        Playlist Playlist = new Playlist();
        try {
             Playlist = Play.queryForEq("name",nazwa_listy).get(0);
        } catch (SQLException ex) {
            System.out.println("Błąd przy pobieraniu playlisty... ListAddMovie" + ex);
        }
            PlayMovies newPlayMov = new PlayMovies();
            newPlayMov.setMovie(selectedMovie);
            newPlayMov.setPlaylist(Playlist);
        try {
            PlayMov.create(newPlayMov);
        } catch (SQLException ex) {
            System.out.println("Błąd przy dodawaniu Playlista-Film... ListAddMovie" + ex);
        }

    }
}