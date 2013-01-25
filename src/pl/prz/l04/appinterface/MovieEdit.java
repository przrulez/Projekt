package pl.prz.l04.appinterface;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import pl.prz.l04.database.DataBase;
/**
 *
 * @author Pyciak
 */
public class MovieEdit extends JFrame
{
    JButton button = new JButton("Zapisz");;
    JLabel label1 = new JLabel("Kategoria:");
    JLabel label2 = new JLabel("Nazwa:");
    JLabel label3 = new JLabel("Opis:");
    JLabel label4 = new JLabel("Plik:");
    JComboBox categories;
    JTextField name;
    JFrame frame;
    JTextArea desc;
    JFileChooser fileChooser = new JFileChooser();
    JButton fileButton = new JButton("Wybierz plik..");
    String choosenFileName = new String();
    
    MovieEdit(int id)
    {
        setTitle("Dodaj Film");
        setSize(300, 355);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;
        
        /********************************************************/
        Vector items = DataBase.returnCategoriesList();
        /**********************************************************/ 
        categories = new JComboBox(items);
             
        
        label1.setLocation(10,10);
        label1.setSize(60,25);
        categories.setLocation(80,10);
        categories.setSize(200,25);
        
        label2.setLocation(10,50);
        label2.setSize(60,25);
        name.setLocation(80,50);
        name.setSize(200,25);
        
        label3.setLocation(10,90);
        label3.setSize(60,25);
        desc.setLocation(80,90);
        desc.setSize(200,150);
        desc.setFont(new Font("Dialog",Font.PLAIN,12));
        
        label4.setLocation(10,250);
        label4.setSize(60,25);
        fileButton.setLocation(80,250);
        fileButton.setSize(200,25);
        
        button.setLocation(210,290);
        button.setSize(70,25);
        
        add(label1);
        add(categories);
        add(label2);
        add(name);
        add(label3);
        add(desc);
        add(label4);
        add(fileButton);
        add(button);
    
        fileButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fileChooser.showOpenDialog(frame);
                
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    choosenFileName = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });
        
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