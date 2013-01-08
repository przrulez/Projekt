package pl.prz.l04.appinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class ListEdit extends JFrame
{
    JLabel label;
    JButton button;
    JTextField field;
    JFrame frame;
    
    ListEdit(int id)
    {
        setTitle("Edytuj Listę");
        setSize(250, 135);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        
        frame = this;
        
        label = new JLabel("Nazwa:");
        button = new JButton("Zapisz");
        
        field = new JTextField(getCategoryName(id));
        
        label.setLocation(20,20);
        label.setSize(50,25);
        field.setLocation(60,20);
        field.setSize(160,25);
        button.setLocation(150,60);
        button.setSize(70,25);
        
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Zapisanie
                
                frame.setVisible(false);
            }
        });
        
        add(label);
        add(field);
        add(button); 
    }
    private String getCategoryName(int id)
    {
        //pobranie z bazy danych nazwy listy z id
        
        return "nazwa"+id; //tymczasowe
    }
}
