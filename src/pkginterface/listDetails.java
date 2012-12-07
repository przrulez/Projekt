package pkginterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class listDetails extends JFrame
{
    JFrame frame;
    JTable lista;
    
    listDetails(int id)
    {
        setTitle("Filmy należace do listy");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;
        
        /*****************************************/
        Object dane[][] = {{"1","Film 1 "},{"2","Film 2"}}; //z bazy danych
        /*****************************************/
        
        for (int i = 0; i < dane.length; i++)
        {
            dane[i] = push(dane[i]);
        }
        
        Object opisy[] = {"Lp","Nazwa",""};
        lista = new JTable(dane,opisy)
        {
             public boolean isCellEditable(int row, int col)//zablokowanie możliwość edycji
             {
                 if(col>1) return true; //do buttonów
                 else return false;
             }
        };
        new ButtonColumn(lista, usun, 2);
        
        
        lista.setRowHeight(25);
        lista.getColumnModel().getColumn(0).setPreferredWidth(25);
        lista.getColumnModel().getColumn(1).setPreferredWidth(150);
        
        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setSize(495,200);
        scrollPane.setLocation(0,0);
        add(scrollPane);
        
    }
    private static Object[] push(Object[] array) 
    {
        String p1 = "Usuń";
        
        Object[] longer = new Object[array.length + 1];
        for (int i = 0; i < array.length; i++)
        {
            longer[i] = array[i];
        }
        longer[array.length] = p1;

        return longer;
    }
    Action usun = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int id = Integer.valueOf(e.getActionCommand());
            
            //Usunięcie filmu z listy
            JOptionPane.showMessageDialog(frame,"Usun Filmy z listy" +id);
        }
    };
}