package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import pl.prz.l04.database.DataBase;
import pl.prz.l04.database.Playlist;

/**
 *
 * @author Pyciak
 */
public class ListAdd extends JFrame {

    JLabel label;
    JButton button;
    JTextField field;
    JFrame frame;

    ListAdd() {
        setTitle("Dodaj Listę");
        setSize(250, 135);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);

        frame = this;

        label = new JLabel("Nazwa:");
        button = new JButton("Dodaj");
        field = new JTextField("");

        label.setLocation(20, 20);
        label.setSize(50, 25);
        field.setLocation(60, 20);
        field.setSize(160, 25);
        button.setLocation(160, 60);
        button.setSize(60, 25);

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String nazwa;
                nazwa = field.getText();
                dodajListe(nazwa);
                frame.setVisible(false);
            }
        });


        add(label);
        add(field);
        add(button);
    }

    public void dodajListe(String nazwa) {
        try {
            Playlist newPlaylist = new Playlist();
            newPlaylist.setName(nazwa);
            newPlaylist.setCreated(new java.util.Date());
            //dodanie nowej listy do listy list z nazwa wpisana w JTextField
            Dao<Playlist, Integer> playDao = DataBase.getInstance().getPlayDao();
            playDao.create(newPlaylist);

        } catch (SQLException ex) {
            System.out.println("Błąd tworzenia listy... ListAdd " + ex);
        }

    }
}