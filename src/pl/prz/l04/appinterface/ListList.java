package pl.prz.l04.appinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class ListList extends JFrame
{
    JFrame frame;
    JTable lista;
    
    ListList()
    {
        setTitle("Listy odtwarzania");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;
        
        /*****************************************/
        Object dane[][] = {{"1","Lista 1 "},{"2","Lista 2"}}; //z bazy danych
        /*****************************************/
        
        for (int i = 0; i < dane.length; i++)
        {
            dane[i] = push(dane[i]);
        }
        
        Object opisy[] = {"Lp","Nazwa","","",""};
        lista = new JTable(dane,opisy)
        {
             public boolean isCellEditable(int row, int col)//zablokowanie możliwość edycji
             {
                 if(col>1) return true; //do buttonów
                 else return false;
             }
        };
        new ButtonColumn(lista, szczegolyList, 2);
        new ButtonColumn(lista, edytujList, 3);
        new ButtonColumn(lista, usunList, 4);
        
        
        lista.setRowHeight(25);
        lista.getColumnModel().getColumn(0).setPreferredWidth(25);
        lista.getColumnModel().getColumn(1).setPreferredWidth(150);
        lista.getColumnModel().getColumn(3).setPreferredWidth(40);
        lista.getColumnModel().getColumn(4).setPreferredWidth(40);
        
        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setSize(495,200);
        scrollPane.setLocation(0,0);
        add(scrollPane);
        
    }
    private static Object[] push(Object[] array) 
    {
        String p1 = "Szczegóły";
        String p2 = "Edytuj";
        String p3 = "Usuń";
        
        Object[] longer = new Object[array.length + 3];
        for (int i = 0; i < array.length; i++)
        {
            longer[i] = array[i];
        }
        longer[array.length] = p1;
        longer[array.length+1] = p2;
        longer[array.length+2] = p3;

        return longer;
    }
    Action edytujList = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            ListEdit wnd = new ListEdit(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
    Action szczegolyList = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            ListDetails wnd = new ListDetails(id);
            wnd.setLocationRelativeTo(null);
            wnd.setVisible(true);
        }
    };
    Action usunList = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            //Usunięcie listy z bazy danych
            JOptionPane.showMessageDialog(frame,"Usun Listę" +id);
        }
    };
}
