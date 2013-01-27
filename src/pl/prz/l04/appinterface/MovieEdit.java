package pl.prz.l04.appinterface;

import com.j256.ormlite.dao.Dao;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import pl.prz.l04.database.CatMovies;
import pl.prz.l04.database.Categories;
import pl.prz.l04.database.DataBase;
import pl.prz.l04.database.Movies;

/**
 *
 * @author Pyciak
 */
public class MovieEdit extends JFrame {

    JButton button = new JButton("Zapisz");
    JLabel label1 = new JLabel("Kategoria:");
    JLabel label2 = new JLabel("Nazwa:");
    JLabel label3 = new JLabel("Opis:");
    JLabel label4 = new JLabel("Plik:");
    JComboBox categories = new JComboBox();
    JTextField name = new JTextField();
    JFrame frame = new JFrame();
    JTextArea desc = new JTextArea();
    JFileChooser fileChooser = new JFileChooser();
    JButton fileButton = new JButton("Wybierz plik..");
    String choosenFileName = new String();
    Movies editedMovie = new Movies();
    private  Dao<Movies, Integer> Mov;
    private  Dao<Categories, Integer> Cat;
    private  Dao<CatMovies, Integer> CatMov;

    MovieEdit(Movies movie) {
        setTitle("Dodaj Film");
        setSize(300, 355);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayout(null);
        frame = this;

        /********************************************************/
        Vector items = DataBase.returnCategoriesList();
        Mov = DataBase.getInstance().getMoviesDao();
        Cat = DataBase.getInstance().getCategoriesDao();
        CatMov = DataBase.getInstance().getCatMovDao();
        /**********************************************************/
        categories = new JComboBox(items);
        editedMovie = movie;


        label1.setLocation(10, 10);
        label1.setSize(60, 25);
        categories.setLocation(80, 10);
        categories.setSize(200, 25);

        label2.setLocation(10, 50);
        label2.setSize(60, 25);
        name.setLocation(80, 50);
        name.setSize(200, 25);
        name.setText(movie.getName());
        
        label3.setLocation(10, 90);
        label3.setSize(60, 25);
        desc.setLocation(80, 90);
        desc.setSize(200, 150);
        desc.setFont(new Font("Dialog", Font.PLAIN, 12));
        desc.setText(movie.getContent());


        label4.setLocation(10, 250);
        label4.setSize(60, 25);
        fileButton.setLocation(80, 250);
        fileButton.setSize(200, 25);

        button.setLocation(210, 290);
        button.setSize(70, 25);

        add(label1);
        add(categories);
        add(label2);
        add(name);
        add(label3);
        add(desc);
        add(label4);
        add(fileButton);
        add(button);
        
        System.out.println("Done!");
        
        

        fileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(frame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    choosenFileName = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                List<CatMovies> linksForDeletion = new Vector<CatMovies>();
                try {
                    linksForDeletion = CatMov.queryForEq("movie_id", editedMovie.getId());
                    if(linksForDeletion == null)
                        linksForDeletion = new Vector<CatMovies>();
                    CatMov.delete(linksForDeletion);
                } catch (SQLException ex) {
                    System.out.println("Błąd przy usuwaniu Film-Kategoria... MovieEdit" + ex);
                }
                
                System.out.println("Done!!");
                editedMovie.setName(name.getText());
                editedMovie.setContent(desc.getText());
                editedMovie.setFile(choosenFileName);
                editedMovie.setCreated(new Date());

                try {
                    Mov.update(editedMovie);
                } catch (SQLException ex) {
                    System.out.println("Błąd przy zapisywaniu nowego filmu... MovieAdd " + ex);
                }
                
                System.out.println("Done!!!");
                String catName = (String) categories.getSelectedItem();
                Categories category = new Categories();
                try {
                    category = Cat.queryForEq("name", catName).get(0);
                } catch (SQLException ex) {
                    System.out.println("Błąd przy szukaniu kategorii... MovieAdd " + ex);
                }
                
                CatMovies catMovLink = new CatMovies();
                catMovLink.setMovie(editedMovie);
                catMovLink.setCategory(category);
                try {
                    CatMov.create(catMovLink);
                } catch (SQLException ex) {
                    System.out.println("Błąd przy zapisywaniu kategoria-film... MovieAdd " + ex);
                }
                frame.setVisible(false);
            }
        });
    }
}