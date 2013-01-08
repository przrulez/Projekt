package pl.prz.l04.appinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class CategoryList extends JFrame
{
    JFrame frame;
    JTable lista;
    
    CategoryList()
    {
        setTitle("Lista Kategorii");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;
        
        /*****************************************/
        Object dane[][] = {{"1","ICK"},{"2","Wizja"}};
        /*****************************************/
        
        for (int i = 0; i < dane.length; i++)
        {
            dane[i] = push(dane[i]);
        }
        
        Object opisy[] = {"Lp","Nazwa","",""};
        lista = new JTable(dane,opisy)
        {
             public boolean isCellEditable(int row, int col)//zablokowanie możliwość edycji
             {
                 if(col>1) return true; //do buttonów
                 else return false;
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
        scrollPane.setSize(395,200);
        scrollPane.setLocation(0,0);
        add(scrollPane);
        
    }
    private static Object[] push(Object[] array) 
    {
        String p1 = "Edytuj";
        String p2 = "Usuń";
        
        Object[] longer = new Object[array.length + 2];
        for (int i = 0; i < array.length; i++)
        {
            longer[i] = array[i];
        }
        longer[array.length] = p1;
        longer[array.length+1] = p2;

        return longer;
    }
    Action edytuj = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            CategoryEdit wnd = new CategoryEdit(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
    Action usun = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            //Usunięcie kategorii z bazy danych
            JOptionPane.showMessageDialog(frame,"Usun kategorię" +id);
        }
    };
}
