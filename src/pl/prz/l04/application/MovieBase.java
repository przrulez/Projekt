/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prz.l04.application;

import java.net.URL;
import javax.swing.UIManager;
import pl.prz.l04.appinterface.MainWindow;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

/**
 *
 * @author Przemysław
 */
public class MovieBase {
    
    public static void main(String[] args)
    {
        try
        {
              UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){}

        new NativeDiscovery().discover();

        MainWindow window = new MainWindow();
        window.setVisible(true);

        instance = new MovieBase();
        MovieBase.getInstance().setMainWindow(window);
    }
    
    /**
     * Instancja klasy MovieBase (singleton).
     */
    private static MovieBase instance;
    /**
     * Metoda zwracajaca instancje klasy MovieBase (singleton).
     * @return instancja klasy (singleton)
     */
    public static MovieBase getInstance() {
        return instance;
    }
    
    private MainWindow mainWindow;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private MediaPlayer mediaPlayer;
    
    /**
     * Prywatny konstruktor (singleton).
     */
    private MovieBase() {
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
        
        mediaPlayer = new MediaPlayer(mediaPlayerComponent);
    }
    
    public URL getResource(String name) {
        return this.getClass().getResource("/pl/prz/l04/resources/" + name);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public EmbeddedMediaPlayerComponent getMediaPlayerComponent() {
        return mediaPlayerComponent;
    }
    
    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
