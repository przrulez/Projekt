package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import pl.prz.l04.database.Categories;
import pl.prz.l04.database.DataBase;

/**
 *
 * @author Pyciak
 */
public class CategoryList extends JFrame {

    JFrame frame;
    JTable lista;
    Object dane;
    String name;
    int i = 0;
    String[] board = new String[20];
    private Dao<Categories, Integer> Cat;

    CategoryList() {
        Cat = DataBase.getInstance().getCategoriesDao();
        Show();
    }

    /*
     * by Jacek P
     *          */
    private void Show() {

        setTitle("Lista Kategorii");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;

        board = wyswietl();
        while (board[i] != null) {
            i++;
        }
        int liczba = i;
        i = 0;
        Object[][] dane = new Object[liczba][2];
        while (board[i] != null) {
            dane[i][0] = i + 1;
            dane[i][1] = board[i];
            i++;
        }

        for (int i = 0; i < dane.length; i++) {
            dane[i] = push(dane[i]);
        }

        Object opisy[] = {"Lp", "Nazwa", "", ""};
        lista = new JTable(dane, opisy) {
            public boolean isCellEditable(int row, int col)//zablokowanie możliwość edycji
            {
                if (col > 1) {
                    return true; //do buttonów
                } else {
                    return false;
                }
            }
        };
        new ButtonColumn(lista, edytuj, 2);
        new ButtonColumn(lista, usun, 3);

        lista.setRowHeight(25);
        lista.getColumnModel().getColumn(0).setPreferredWidth(25);
        lista.getColumnModel().getColumn(1).setPreferredWidth(150);
        lista.getColumnModel().getColumn(2).setPreferredWidth(40);
        lista.getColumnModel().getColumn(3).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setSize(395, 200);
        scrollPane.setLocation(0, 0);
        add(scrollPane);
    }

    private static Object[] push(Object[] array) {
        String p1 = "Edytuj";
        String p2 = "Usuń";

        Object[] longer = new Object[array.length + 2];
        for (int i = 0; i < array.length; i++) {
            longer[i] = array[i];
        }
        longer[array.length] = p1;
        longer[array.length + 1] = p2;

        return longer;
    }
    Action edytuj = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {

            int id = Integer.valueOf(e.getActionCommand());
            String nazwa = board[id - 1];
            CategoryEdit wnd = new CategoryEdit(nazwa);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
            setVisible(false);
            dispose();


        }
    };
    Action usun = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            int id = Integer.valueOf(e.getActionCommand());
            String nazwa = board[id - 1];
            usun(nazwa);
            JOptionPane.showMessageDialog(frame, "Usunięto  kategorię" + nazwa);
            //System.exit(0);
            setVisible(false);
            dispose();
            CategoryList a = new CategoryList();
            a.setLocationRelativeTo(null);
            a.setVisible(true);
        }
    };

    public String[] wyswietl() {//funkcja pobierająca dane z bazy o dostępnych kategoriach
        String[] tablica = new String[20];
        List<Categories> list;
        int i = 0;
        try {
            list = Cat.queryForAll();
            for(Categories item : list) {
                name = item.getName();
                tablica[i] = name;
                i++;//pobiera dostępne kategorie zapisuje je w tablicy stringów i zwraca
            }
            i = 0;
        } catch (Exception e) {
            System.err.println("Błąd przy pobieraniu ketegorii... CategoryList");
        }
        return tablica;
    }

    public void usun(String element) {//funkcja usuwa daną kategorie z bazy
        try {
            Categories delCat = Cat.queryForEq("name", element).get(0);
            Cat.delete(delCat);
        } catch (Exception e) {
            System.out.println("Błąd przy usówaniu kategorii... CategoryList");
        }
    }
}
