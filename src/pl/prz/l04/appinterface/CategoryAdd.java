package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pl.prz.l04.database.Categories;
import pl.prz.l04.database.DataBase;

/**
 *
 * @author Pyciak
 */
public class CategoryAdd extends JFrame {

    JLabel label;
    JButton button;
    JTextField field;
    JFrame frame;
    JOptionPane wiadomosc;
    JCheckBox server;
    JCheckBox local;
    private Dao<Categories, Integer> Cat;

    CategoryAdd() {
        setTitle("Dodaj Kategorię");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);

        frame = this;
        label = new JLabel("Nazwa:");
        button = new JButton("Dodaj");
        field = new JTextField("");

        /*
         * by Jacek P
         *          */
        wiadomosc = new JOptionPane();
        local = new JCheckBox("lokalnie", false);
        server = new JCheckBox("serwer", false);

        label.setLocation(20, 20);
        label.setSize(50, 25);
        field.setLocation(60, 20);
        field.setSize(160, 25);
        button.setLocation(160, 60);
        button.setSize(60, 25);
        local.setLocation(20, 60);
        local.setSize(50, 25);
        server.setLocation(80, 60);
        server.setSize(50, 25);



        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == button) {
                    dodaj();
                }
            }
        });


        add(label);
        add(field);
        add(button);
        add(local);
        add(server);
        try 
        {
            Cat = DataBase.getInstance().getCategoriesDao();
        } catch (Exception e) {
            System.out.println("Błąd przy pobieraniu danych... CategoryAdd " + e);
        }
    }
    /*
     * by Jacek P
     *          */

    public static int wiadomosc(Component paren, String msg, String title) {
        Object[] opcje = {"Tak", "Nie", "Anuluj"};
        int showConfirmDialog = JOptionPane.showOptionDialog(paren,
                msg,
                title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcje,
                opcje[0]);
        return showConfirmDialog;
    }

    public void dodaj() {

        String dana = field.getText();
        field.setText("");
        Boolean loc = false, ser = false;
        if (local.isSelected()) {
            loc = true;
        }
        if (server.isSelected()) {
            ser = true;
        }
        int p = wiadomosc(null, "Czy chcesz dodać  Kategorie " + dana, "Dodaj");
        if (p == 0) {
            if (check(dana) == 1) {
                //addsql(dana, loc, ser);
                Categories newCategory = new Categories();
                newCategory.setName(dana);
                newCategory.setCreated(new Date());
                try {
                    Cat.create(newCategory);
                } catch (SQLException e) {
                    System.out.println("Błąd przy zapisywaniu kategorii do BD " + e);
                }
                wiadomosc.showMessageDialog(null, "Kategoria " + dana + " została dodana");
            } else {
                wiadomosc.showMessageDialog(null, "UWAGA!!! Kategoria " + dana + " już istnieje");
            }
        } else {
            return;
        }
    }
    String imie;
    Statement stm = null;
    ResultSet rs = null;
    
    int check(String name) {
        List<Categories> list = null;
        try {
            list = Cat.queryForEq("name", name);
        } catch (SQLException e) {
            System.out.println("Błąd przy pobieraniu danych o kategori.. CategoryAdd " + e);
        }
        if(list.isEmpty())
                return 1;
        return 0;
    }
}
