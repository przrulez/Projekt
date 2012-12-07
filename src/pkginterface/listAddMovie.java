package pkginterface;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class listAddMovie extends JFrame
{
    JButton button = new JButton("Dodaj");;
    JLabel label1 = new JLabel("Lista:");
    JComboBox categories;
    JFrame frame;
   
    listAddMovie(int id)
    {
        setTitle("Dodaj Film do Listy");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;
        
        /********************************************************/
        String items[] = {"Lista 1","Lista 2","Lista 3","Lista 4"}; //z bazy danych
        /**********************************************************/ 
        categories = new JComboBox(items);
             
        
        label1.setLocation(10,10);
        label1.setSize(60,25);
        categories.setLocation(80,10);
        categories.setSize(200,25);
 
        button.setLocation(220,40);
        button.setSize(60,25);
        
        add(label1);
        add(categories);
        add(button);
        
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Zapisanie
                frame.setVisible(false);
            }
        });
    }
}