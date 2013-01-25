/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prz.l04.appinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
//import javax.swing.border.StrokeBorder;
import pl.prz.l04.application.MediaPlayer;
import pl.prz.l04.application.MovieBase;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author Przemysław
 */
public class PlayerUI {
    /**
     * Instancja klasy PlayerUI (singleton).
     */
    private static PlayerUI instance = new PlayerUI();
    /**
     * Metoda zwracajaca instancje klasy PlayerUI (singleton).
     * @return instancja klasy (singleton)
     */
    public static PlayerUI getInstance() {
        return instance;
    }
    
    private JPanel mainJPanel;
    private JButton togglePlayJButton;
    private JButton stopJButton;
    private JLabel playtimeJLabel;
    
    private Timer playtimeTimer;
    
    private PlayerUI() {
    }
    
    public void preparePanel() {

        String title = "test odtwarzacza";
        
        JLabel label = new JLabel("Film: "+ title);
        label.setSize(600,25);
        label.setLocation(15,10);
        mainJPanel.add(label);

        JPanel videoJPanel = new JPanel();
        videoJPanel.setLayout(new BorderLayout());
        videoJPanel.setLocation(100, 40);
        videoJPanel.setSize(640, 390);

        EmbeddedMediaPlayerComponent mediaPlayerComponent = MovieBase.getInstance().getMediaPlayerComponent();
        mediaPlayerComponent.setSize(640, 390);
        mediaPlayerComponent.getVideoSurface().setSize(640, 390);
        videoJPanel.add(mediaPlayerComponent, BorderLayout.CENTER);

        mainJPanel.add(videoJPanel);
        
        //########### PLAY BUTTON
        togglePlayJButton = new JButton();
        togglePlayJButton.setIcon(new ImageIcon(MovieBase.getInstance().getResource("play.png")));
        togglePlayJButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerUI.getInstance().togglePlay();
            }
        });
        togglePlayJButton.setSize(30, 30);
        togglePlayJButton.setLocation(15, 450);
        mainJPanel.add(togglePlayJButton);
        
        
        //########### STOP BUTTON
        stopJButton = new JButton();
        stopJButton.setIcon(new ImageIcon(MovieBase.getInstance().getResource("stop.png")));
        stopJButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerUI.getInstance().stop();
            }
        });
        stopJButton.setSize(30, 30);
        stopJButton.setLocation(50, 450);
        mainJPanel.add(stopJButton);
        
        playtimeJLabel = new JLabel("Test");
        playtimeJLabel.setSize(120, 30);
        playtimeJLabel.setLocation(100, 450);
        mainJPanel.add(playtimeJLabel);
        
        MovieBase.getInstance().getMediaPlayer().prepareMedia();
        
        playtimeTimer = new Timer();
        startRefreshing();
    }
    
    public void togglePlay() {
        MediaPlayer mediaPlayer = MovieBase.getInstance().getMediaPlayer();
        
        if (mediaPlayer.isPlaying()) {
            togglePlayJButton.setIcon(new ImageIcon(MovieBase.getInstance().getResource("play.png")));
            mediaPlayer.pause();
        } else {
            togglePlayJButton.setIcon(new ImageIcon(MovieBase.getInstance().getResource("pause.png")));
            mediaPlayer.play();
        }
    }
    
    public void startRefreshing() {
        playtimeTimer.scheduleAtFixedRate(new RefreshTimerTask(), 0, 100);
    }
    
    public void stopRefreshing() {
        playtimeTimer.cancel();
    }
    
    public void refreshPlaytime() {
        String text = MovieBase.getInstance().getMediaPlayer().getFormattedTime() + "/" 
                + MovieBase.getInstance().getMediaPlayer().getFormattedLength();
        playtimeJLabel.setText(text);
    }
    
    public void stop() {
        togglePlayJButton.setIcon(new ImageIcon(MovieBase.getInstance().getResource("play.png")));
        MovieBase.getInstance().getMediaPlayer().stop();
    }
    
    //############ GETTERS / SETTERS
    
    public JPanel getMainJPanel() {
        return mainJPanel;
    }

    public void setMainJPanel(JPanel mainJPanel) {
        this.mainJPanel = mainJPanel;
    }
    
    private class RefreshTimerTask extends TimerTask {

        @Override
        public void run() {
            refreshPlaytime();
        }
        
    }
}
