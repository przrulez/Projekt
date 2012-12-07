package pkginterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Pyciak
 */
public class movieTable
{
    JPanel panel = new JPanel();
    JTable movies;
    JButton sortuj;
    JComboBox comboSortuj;
    Object data[][];
    
    movieTable(Object dane[][])
    {
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
        
        for (int i = 0; i < dane.length; i++)
        {
            dane[i] = push(dane[i]);
        }
        
        String opisy[] = {"Lp","Tytuł ","Kategoria","Utworzony","","","Operacje","",""};
        
        movies = new JTable(dane,opisy)
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
    private static Object[] push(Object[] array) 
    {
        String p1 = "Szczegóły";
        String p2 = "Oglądaj";
        String p3 = "Dodaj do listy";
        String p4 = "Edytuj";
        String p5 = "Usuń";
        
        Object[] longer = new Object[array.length + 5];
        for (int i = 0; i < array.length; i++)
        {
            longer[i] = array[i];
        }
        longer[array.length] = p1;
        longer[array.length+1] = p2;
        longer[array.length+2] = p3;
        longer[array.length+3] = p4;
        longer[array.length+4] = p5;
        return longer;
    }
    Action ogladaj = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            panel.removeAll();
            
            /*******************************************/ //player
            JLabel label = new JLabel("Film "+id);
            label.setSize(60,25);
            label.setLocation(400,250);
            /********************************************/
            
            JButton button = new JButton("Powrót");
            button.setSize(100,30);
            button.setLocation(15,490);
            
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    panel.removeAll();
                    panel.repaint();
                    JPanel temp;
                    temp = new movieTable(data).getPanel();
                    temp.setLocation(0, 0);
                    temp.setSize(820, 600);
                    panel.add(temp);
                }
            });
            
            panel.add(button);
            panel.add(label);
            panel.repaint();
            
        }
    };
    Action edytuj = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            movieEdit wnd = new movieEdit(id);
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
            
            listAddMovie wnd = new listAddMovie(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
}
