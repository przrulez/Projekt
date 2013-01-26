package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import pl.prz.l04.database.CatMovies;
import pl.prz.l04.database.Categories;
import pl.prz.l04.database.DataBase;
import pl.prz.l04.database.Movies;

/**
 *
 * @author Pyciak
 */
public class MainWindow extends JFrame
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
    private Dao<Categories, Integer> Cat;
    private Dao<Movies, Integer> Mov;
    private Dao<CatMovies, Integer> CatMov;
    
    public MainWindow()
    {
        setTitle("Katalog Filmów");
        setSize(1000, 600);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//wycentorwanie
        
        Cat = DataBase.getInstance().getCategoriesDao();
        Mov = DataBase.getInstance().getMoviesDao();
        CatMov = DataBase.getInstance().getCatMovDao();
        
        makeMenuBar();
        makeLeftPanel();
        makeRightPanel();

        add(leftPanel,BorderLayout.WEST);
        add(rightPanel,BorderLayout.CENTER);
    }
    
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
        
        java.util.List<Movies> moviesList = new Vector<Movies>();
        try {
            moviesList = Mov.queryForAll();
        } catch (SQLException ex) {
            System.out.println("Błąd przy pobieraniu listy filmów... MainWindow " + ex);
        }
        
        if(moviesList.isEmpty())
        {
            Movies newMovie = new Movies();
            newMovie.setName("Brak filmów!!!!!!");
            moviesList.add( newMovie);
        }
        

        JPanel temp = new MovieTable(new Vector<Movies>(moviesList)).getPanel();
        temp.setLocation(0, 0);
        temp.setSize(820, 600);
        
        rightPanel.add(temp);
    }
    
    private class menuActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource() == MenuCategoryAdd || e.getSource() == AddCategory)
            {
                JFrame wnd = new CategoryAdd();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuCategoryList)
            {
                JFrame wnd = new CategoryList();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuMovieAdd || e.getSource() == AddMovie)
            {
                JFrame wnd = new MovieAdd();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == Search)
            {
                JFrame wnd = new Search(rightPanel);
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
                
            }
            else if(e.getSource() == MenuUstawieniaBase)
            {
                JFrame wnd = new SettingBase();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuListAdd)
            {
                JFrame wnd = new ListAdd();
                wnd.setLocationRelativeTo(null);
                wnd.setVisible(true);
            }
            else if(e.getSource() == MenuListList)
            {
                JFrame wnd = new ListList();
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