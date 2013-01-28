package pl.prz.l04.appinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Pyciak
 */
public class ListList extends JFrame {

    JFrame frame;
    JTable lista;

    ListList() {
        setTitle("Listy odtwarzania");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;

        wyswietlanieListy();

        /*****************************************/
        //Object dane[][] = {{"1", "lista1"},{"2", "lista2"}}; //z bazy danych
        /*****************************************/
        new ButtonColumn(lista, szczegolyList, 2);
        new ButtonColumn(lista, edytujList, 3);
        new ButtonColumn(lista, usunList, 4);


        lista.setRowHeight(25);
        lista.getColumnModel().getColumn(0).setPreferredWidth(25);
        lista.getColumnModel().getColumn(1).setPreferredWidth(150);
        lista.getColumnModel().getColumn(3).setPreferredWidth(40);
        lista.getColumnModel().getColumn(4).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setSize(495, 200);
        scrollPane.setLocation(0, 0);
        add(scrollPane);

    }

    private static Object[] push(Object[] array) {
        String p1 = "Szczegóły";
        String p2 = "Edytuj";
        String p3 = "Usuń";

        Object[] longer = new Object[array.length + 3];
        for (int i = 0; i < array.length; i++) {
            longer[i] = array[i];
        }
        longer[array.length] = p1;
        longer[array.length + 1] = p2;
        longer[array.length + 2] = p3;

        return longer;
    }
    Action edytujList = new AbstractAction() {

        public void actionPerformed(ActionEvent e) {
            int id = Integer.valueOf(e.getActionCommand());

            frame.setVisible(false);
            ListEdit wnd = new ListEdit(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
    Action szczegolyList = new AbstractAction() {

        public void actionPerformed(ActionEvent e) {
            int id = Integer.valueOf(e.getActionCommand());

            ListDetails wnd = new ListDetails(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
    Action usunList = new AbstractAction() {

        public void actionPerformed(ActionEvent e) {
            int id = Integer.valueOf(e.getActionCommand());


            //Usunięcie listy z bazy danych
            usun(id);
            //wywołanie konstruktowa w celu odswiezenia listy
            frame.setVisible(false);
            ListList wnd = new ListList();
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
          
            JOptionPane.showMessageDialog(frame, "Usunieto liste " + id);
            
        }
    };

    public void wyswietlanieListy() {
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
            ResultSet zlicz = st.executeQuery("SELECT id, name, user_id FROM play_lists");
            while (zlicz.next()) {
                a++;
            }

            Object[][] listaList = new Object[a][2];

            ResultSet playList = st.executeQuery("SELECT id, name, user_id FROM play_lists");
            while (playList.next()) {
                //String lp = playList.getInt("id");
                //name = playList.getString("name");
                //String user_id = playList.getString("user_id");
                listaList[licz][0] = playList.getInt("id");
                listaList[licz][1] = playList.getString("name");
                licz++;
            }

            for (int i = 0; i < listaList.length; i++) {
                listaList[i] = push(listaList[i]);
            }

            Object opisy[] = {"Lp", "Nazwa", "", "", ""};
            lista = new JTable(listaList, opisy) {

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
    }

    public void usun(int numer) {

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
            int row = st.executeUpdate("DELETE FROM play_lists WHERE id ="+ numer +"");

        } catch (Exception e) {
            System.out.println("Błąd pobierania danych...");
        }


    }
}