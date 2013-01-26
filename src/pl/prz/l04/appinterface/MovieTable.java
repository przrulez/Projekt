package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pl.prz.l04.application.SortTable;
import pl.prz.l04.database.CatMovies;
import pl.prz.l04.database.DataBase;
import pl.prz.l04.database.Movies;
/**
 *
 * @author Pyciak
 */
public class MovieTable
{

    private String getCategoriesFor(Movies movie) {
        String result = new String("");
        java.util.List<CatMovies> categoryList = new Vector<CatMovies>();
        try {
            categoryList = CatMov.queryForEq("movie_id", movie.getId());
        } catch (SQLException ex) {
            System.out.println("Błąd przy pobieraniu listy fim-kategoria... MovieTable" + ex);
        }
        
        for(CatMovies item : categoryList)
        {
            result += item.getCategory().getName().toString();
        }
        
        return result;
    }
    JPanel panel = new JPanel();
    JTable movies;
    JButton sortuj;
    JComboBox comboSortuj;
    Vector<Movies> data;
    private Dao<CatMovies, Integer> CatMov;
    
    MovieTable(Vector<Movies> dane)
    {
        PlayerUI.getInstance().setMainJPanel(panel);
        
        data = dane;
        String items[] = {"Kategoria","Kolejność","Nazwa","Losowo"};
        comboSortuj = new JComboBox(items);
        sortuj = new JButton("Sortuj");
        
        sortuj.setSize(100,30);
        sortuj.setLocation(170,5);
        
        comboSortuj.setSize(150,30);
        comboSortuj.setLocation(10, 5);
        
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(800,600));
        panel.add(comboSortuj);
        panel.add(sortuj);
        sortuj.addActionListener(new menuActionListener());//dodanie słuchacza zdarzeń przycisku sortuj
        
        CatMov = DataBase.getInstance().getCatMovDao();
        
        Object gotoweDane[][] = new Object[dane.size()][];
        
        for (int i = 0; i < dane.size(); i++)
        {
            String p1 = "Szczegóły";
            String p2 = "Oglądaj";
            String p3 = "Dodaj do listy";
            String p4 = "Edytuj";
            String p5 = "Usuń";

            Object[] longer = new Object[9];
            longer[0] = i;
            longer[1] = dane.get(i).getName();
            longer[2] = getCategoriesFor(dane.get(i));
            longer[3] = dane.get(i).getCreated();
            longer[4] = p1;
            longer[5] = p2;
            longer[6] = p3;
            longer[7] = p4;
            longer[8] = p5;
            gotoweDane[i] = longer;
        }
        
        String opisy[] = {"Lp","Tytuł ","Kategoria","Utworzony","","","Operacje","",""};
        
        movies = new JTable(gotoweDane,opisy)
        {
             public boolean isCellEditable(int row, int col)//zablokowanie możliwość edycji
             {
                 if(col>3) return true; //do buttonów
                 else return false;
             }
        };
        movies.setRowHeight(25);
        movies.getColumnModel().getColumn(0).setPreferredWidth(25);
        movies.getColumnModel().getColumn(1).setPreferredWidth(230);
        movies.getColumnModel().getColumn(3).setPreferredWidth(75);
        movies.getColumnModel().getColumn(4).setMinWidth(85);
        movies.getColumnModel().getColumn(5).setMinWidth(50);
        movies.getColumnModel().getColumn(6).setMinWidth(100);
        movies.getColumnModel().getColumn(7).setMinWidth(40);
        movies.getColumnModel().getColumn(8).setMinWidth(40);
        movies.setBackground(null);
        
        new ButtonColumn(movies, szczegoly, 4);
        new ButtonColumn(movies, ogladaj, 5);
        new ButtonColumn(movies, lista, 6);
        new ButtonColumn(movies, edytuj, 7);
        new ButtonColumn(movies, usun, 8);
        
        JScrollPane scrollPaneMovies = new JScrollPane(movies);
        scrollPaneMovies.setSize(800,490);
        scrollPaneMovies.setLocation(10,40);

        panel.add(scrollPaneMovies);
        
    }
    
    public JPanel getPanel()
    {
        return panel;
    }
    
    private Object[] expand(Movies movie, int i) 
    {
        String p1 = "Szczegóły";
        String p2 = "Oglądaj";
        String p3 = "Dodaj do listy";
        String p4 = "Edytuj";
        String p5 = "Usuń";
        
        Object[] longer = new Object[9];
        longer[0] = i;
        longer[1] = movie.getName();
        longer[2] = getCategoriesFor(movie);
        longer[3] = movie.getCreated();
        longer[4] = p1;
        longer[5] = p2;
        longer[6] = p3;
        longer[7] = p4;
        longer[8] = p5;
        return longer;
    }
    

    
    Action ogladaj = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            panel.removeAll();
            panel.repaint();
            
            PlayerUI.getInstance().preparePanel();
            
            // powrot
            JButton button = new JButton("Powrót");
            button.setSize(100,30);
            button.setLocation(15,500);
            
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    PlayerUI.getInstance().stop();
                    PlayerUI.getInstance().stopRefreshing();
                    panel.removeAll();
                    panel.repaint();
                    JPanel temp;
                    temp = new MovieTable(data).getPanel();
                    temp.setLocation(0, 0);
                    temp.setSize(820, 600);
                    panel.add(temp);
                }
            });
            
            panel.add(button);
        }
    };
    Action edytuj = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            MovieEdit wnd = new MovieEdit(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
    Action szczegoly = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            JOptionPane.showMessageDialog(panel,"Szczegóły " +id);
        }
    };
    Action usun = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            //usunięcie filmu
            JOptionPane.showMessageDialog(panel,"Usuń " +id);
        }
    };
    Action lista = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            ListAddMovie wnd = new ListAddMovie(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
    //obsługa zdarzenia naciśnięcia przycisku sortuj
    private class menuActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == sortuj) { 
                panel.removeAll();
                
                
                SortTable sort = new SortTable();
                Object sortOrder = comboSortuj.getSelectedItem(); //odczytanie wybranej wartości comboBoxa
                java.util.List<Movies> sortedData = sort.sortTable(sortOrder); //pobranie posortowanej tablicy
                
                JPanel temp = new MovieTable(new Vector<Movies>(sortedData)).getPanel();
                temp.setLocation(0, 0);
                temp.setSize(820, 600);

                panel.add(temp);
                panel.repaint();

            }

        }
    }

}
