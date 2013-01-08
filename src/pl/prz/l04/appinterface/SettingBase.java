package pl.prz.l04.appinterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Pyciak
 */
public class SettingBase extends JFrame
{
    JLabel label1,label2;
    JButton button;
    JFrame frame;
    JCheckBox box1,box2;
    
    SettingBase()
    {
        setTitle("Zródło danych:");
        setSize(220, 155);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        
        frame = this;
        
        label1 = new JLabel("Baza lokalna:");
        label2 = new JLabel("Baza na serwerze:");
        box1 = new JCheckBox();
        box2 = new JCheckBox();
        button = new JButton("Zapisz");
        
        label1.setLocation(35,20);
        label1.setSize(100,25);
        box1.setLocation(155,20);
        box1.setSize(25,25);
        label2.setLocation(35,50);
        label2.setSize(100,25);
        box2.setLocation(155,50);
        box2.setSize(25,25);
        button.setLocation(105,80);
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
        add(box1);
        add(label2);
        add(box2);
        add(button);  
    }
}
