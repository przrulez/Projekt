package pkginterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 *
 * @author Pyciak
 */
public class mainWindow extends JFrame
{
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JMenuBar MenuBar;
    
    JMenuItem MenuCategoryAdd;
    JMenuItem MenuCategoryList;
    JMenuItem MenuMovieAdd;
    JMenuItem MenuListAdd;
    JMenuItem MenuListList;
    JMenuItem MenuUstawieniaBase;
            
    JButton AddMovie;
    JButton AddCategory;
    JButton Search;
    
    private void makeMenuBar()
    {
        JMenu MenuCategory = new JMenu("Kategorie");
        MenuCategoryAdd = new JMenuItem("Dodaj");
        MenuCategoryList = new JMenuItem("Lista");
        JMenu MenuMovie = new JMenu("Filmy");
        MenuMovieAdd = new JMenuItem("Dodaj");
        JMenu MenuList = new JMenu("Listy Odtwarzania");
        MenuListAdd = new JMenuItem("Dodaj");
        MenuListList = new JMenuItem("Lista");
        JMenu MenuUstawienia = new JMenu("Ustawienia");
        MenuUstawieniaBase = new JMenuItem("Baza");
        MenuBar = new JMenuBar();
        
        MenuCategory.add(MenuCategoryAdd);
        MenuCategory.add(MenuCategoryList);
        
   
        MenuMovie.add(MenuMovieAdd);
        MenuList.add(MenuListAdd);
        MenuList.add(MenuListList);
        MenuUstawienia.add(MenuUstawieniaBase);
        MenuBar.add(MenuCategory);
        MenuBar.add(MenuMovie);
        MenuBar.add(MenuList);
        MenuBar.add(MenuUstawienia);
        setJMenuBar(MenuBar);
        
        MenuCategoryAdd.addActionListener(new menuActionListener());
        MenuCategoryList.addActionListener(new menuActionListener());
        MenuMovieAdd.addActionListener(new menuActionListener());
        MenuListAdd.addActionListener(new menuActionListener());
        MenuListList.addActionListener(new menuActionListener());
        MenuUstawieniaBase.addActionListener(new menuActionListener());
    }
    private void makeLeftPanel()
    {
        AddMovie = new JButton("Dodaj Film");
        AddCategory = new JButton("Dodaj Kategorię");
        Search = new JButton("Szukaj");
        
        AddMovie.addActionListener(new menuActionListener());
        AddCategory.addActionListener(new menuActionListener());
        Search.addActionListener(new menuActionListener());
                
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setPreferredSize(new Dimension(160,80));
        

        AddMovie.setLocation(5,80);
        AddMovie.setSize(150,40);
        AddCategory.setLocation(5,125);
        AddCategory.setSize(150,40);
        Search.setLocation(5,170);
        Search.setSize(150,40);
        
        leftPanel.add(AddMovie);
        leftPanel.add(AddCategory);
        leftPanel.add(Search);
    }
    
    
    private void makeRightPanel()
    {
        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setPreferredSize(new Dimension(820,600));
        
        Object dane[][] = {{"1","Tytuł 1","ICK","12.10.2012"},
                            {"2","Tytuł 2","bICK","12.10.2012"},
                            {"3","Tytuł5","aICK","14.10.2012"},
                            {"4","Tytuł 4","ICKx","12.10.2012"},
                            {"5","Tytuł Filmu może być długi","ICK x","12.11.2012"},
                            {"6","Tytul","ICK s","12.10.2011"},
                            {"7","Tytuł","ICK","12.10.2012"}};
        

        JPanel temp = new movieTable(dane).getPanel();
        temp.setLocation(0, 0);
        temp.setSize(820, 600);
        
        rightPanel.add(temp);
    }
    mainWindow()
    {
        setTitle("Katalog Filmów");
        setSize(1000, 600);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//wycentorwanie
        
        makeMenuBar();
        makeLeftPanel();
        makeRightPanel();

        add(leftPanel,BorderLayout.WEST);
        add(rightPanel,BorderLayout.CENTER);
    }
    private class menuActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource() == MenuCategoryAdd || e.getSource() == AddCategory)
            {
                JFrame wnd = new categoryAdd();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuCategoryList)
            {
                JFrame wnd = new categoryList();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuMovieAdd || e.getSource() == AddMovie)
            {
                JFrame wnd = new movieAdd();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == Search)
            {
                JFrame wnd = new search();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuUstawieniaBase)
            {
                JFrame wnd = new settingBase();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuListAdd)
            {
                JFrame wnd = new listAdd();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuListList)
            {
                JFrame wnd = new listList();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else
            {
                System.out.println(e.getActionCommand());
            }
        }
    }
}