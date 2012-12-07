package pkginterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class listAdd extends JFrame
{
    JLabel label;
    JButton button;
    JTextField field;
    JFrame frame;
    
    listAdd()
    {
        setTitle("Dodaj Listę");
        setSize(250, 135);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        
        frame = this;
        
        label = new JLabel("Nazwa:");
        button = new JButton("Dodaj");
        field = new JTextField("");
        
        label.setLocation(20,20);
        label.setSize(50,25);
        field.setLocation(60,20);
        field.setSize(160,25);
        button.setLocation(160,60);
        button.setSize(60,25);
        
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
}
