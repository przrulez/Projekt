package pl.prz.l04.appinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Pyciak
 */
public class ListEdit extends JFrame {

    JLabel label;
    JButton button;
    JTextField field;
    JFrame frame;

    ListEdit(final int id) {

        setTitle("Edytuj Listę");
        setSize(250, 135);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);

        frame = this;

        label = new JLabel("Nazwa:");
        button = new JButton("Zapisz");

        field = new JTextField(getCategoryName(id));

        label.setLocation(20, 20);
        label.setSize(50, 25);
        field.setLocation(60, 20);
        field.setSize(160, 25);
        button.setLocation(150, 60);
        button.setSize(70, 25);

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String nazwa;
                nazwa = field.getText();
                zmianaNazwy(nazwa,id);
                frame.setVisible(false);
                ListList wnd = new ListList();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
        });

        add(label);
        add(field);
        add(button);
    }

    private String getCategoryName(int id) {
        // wypisywanie nazwy listy ktora chcemy zmienic z bazy danych
        // łaczenie z baza
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
        String nazwa = "nazwa1";
        try {
            Statement st = connect.createStatement();
            ResultSet playList = st.executeQuery("SELECT name FROM play_lists WHERE id=" + id + "");  //pobranie z bazy danych nazwy listy z id
            while (playList.next()) {
                nazwa = playList.getString("name");
                licz++;
            }


        } catch (Exception e) {
            System.out.println("Błąd pobierania danych...");
        }
        return nazwa;

    }

    public void zmianaNazwy(String nazwa, int id){

         Connection c = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    c = DriverManager.getConnection("jdbc:mysql://localhost/movies", "root", "");
                    System.out.println("Udało się połączyć z bazą danych...");
                } catch (ClassNotFoundException ew) {
                    System.out.println("Problem z driverem bazy danych...");
                } catch (SQLException ew) {
                    System.out.println("Nie można połączyć się z baza danych...");

                } catch (Exception ew) {
                    System.out.println("Błąd przy połączeniu z bazą danych...");
                }

                try {
                    // PreparedStatement ps = c.prepareStatement("UPDATE play_lists set name = ‘Pawel’ where id = 1");
                    // ps.executeUpdate();
                   // Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                   // stmt.executeUpdate("update play_list set name = '"+ nazwa +"' where id = " + id);
                    Statement st = c.createStatement();
                    st.executeUpdate("UPDATE play_lists SET name='" + nazwa + "' WHERE id='" + id + "'");

                } catch (Exception ew) {
                    System.out.println("Błąd pobierania danych...");
                }



    }
}