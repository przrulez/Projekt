package pkginterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class search extends JFrame
{
    JLabel label1,label2,label3;
    JButton button;
    JTextField field1, field2;
    JFrame frame;
    JComboBox categories;
    
    search()
    {
        setTitle("Szukaj");
        setSize(540, 140);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        
        frame = this;
        
        /********************************************************/
        String items[] = {"Kategoria 1","Kategoria 2","Kategoria 3","Kategoria 4"}; //z bazy danych
        /**********************************************************/ 
        categories = new JComboBox(items);
        
        label1 = new JLabel("Kategoria:");
        label2 = new JLabel("Nazwa:");
        label3 = new JLabel("Opis:");
        
        button = new JButton("Szukaj");
        field1= new JTextField("");
        field2= new JTextField("");
        
        label1.setLocation(20,15);
        label1.setSize(50,25);
        categories.setLocation(20,45);
        categories.setSize(160,25);
        
        label2.setLocation(190,15);
        label2.setSize(50,25);
        field1.setLocation(190,45);
        field1.setSize(160,25);
        
        label3.setLocation(360,15);
        label3.setSize(50,25);
        field2.setLocation(360,45);
        field2.setSize(160,25);
        
        button.setLocation(450,75);
        button.setSize(70,25);
        
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Zapisanie
                
                frame.setVisible(false);
            }
        });
        
        add(label1);
        add(label2);
        add(label3);
        add(field1);
        add(field2);
        add(categories);
        add(button);  
    }
}
