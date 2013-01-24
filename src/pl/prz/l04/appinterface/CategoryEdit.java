package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
public class CategoryEdit extends JFrame {

    JLabel label;
    JButton button;
    JTextField field;
    JFrame frame;
    String st_nazwa;
    private Dao<Categories, Integer> Cat;

    CategoryEdit(String nazwa) {
        setTitle("Edytuj Kategorię");
        setSize(250, 135);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);

        frame = this;

        label = new JLabel("Nazwa:");
        button = new JButton("Zapisz");
        st_nazwa = nazwa;
        field = new JTextField(nazwa);


        label.setLocation(20, 20);
        label.setSize(50, 25);
        field.setLocation(60, 20);
        field.setSize(160, 25);
        button.setLocation(150, 60);
        button.setSize(70, 25);

        button.addActionListener(new ActionListener() /*
         * by Jacek P
         *          */ {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button) {
                    save();
                }
                setVisible(false);
                dispose();
                CategoryList a = new CategoryList();
                a.setLocationRelativeTo(null);
                a.setVisible(true);
            }
        });
        add(label);
        add(field);
        add(button);
        
        Cat = DataBase.getInstance().getCategoriesDao();
    }

    public void save() {
        String nazwa;
        nazwa = field.getText();
        edition(nazwa, st_nazwa);

    }

    void edition(String name_n, String name_o) {
        List<Categories> list = null;
        try {
             list = Cat.queryForEq("name", name_o);
        } catch (SQLException ex) {
            System.out.println("Błąd przy pobieraniu ketegorii... CategoryEdit");
        }
        list.get(0).setName(name_n);
        try {
            Cat.update(list.get(0));
        } catch (SQLException ex) {
            System.out.println("Błąd przy aktualizacji ketegorii... CategoryEdit");
        }
    }

    public String time() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String s = (sdf.format(now));
        return s;
    }
}
