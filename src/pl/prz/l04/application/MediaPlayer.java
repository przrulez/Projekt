/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prz.l04.application;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author Przemysław
 */
public class MediaPlayer {
    
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    public MediaPlayer(EmbeddedMediaPlayerComponent mediaPlayerComponent) {
        this.mediaPlayerComponent = mediaPlayerComponent;
    }
    
    public Boolean prepareMedia() {
        //String mrl = "file:///E:/utorrent/downloaded/Supernatural.S08E05.HDTV.x264-LOL.mp4";
        String mrl = "http://www.youtube.com/watch?v=YZ7ASpL_Rkk";
        //mediaPlayerComponent.getMediaPlayer().playMedia(mrl);
        return mediaPlayerComponent.getMediaPlayer().prepareMedia(mrl);
    }
    
    public void play() {
        mediaPlayerComponent.getMediaPlayer().play();
    }
    
    public void pause() {
        mediaPlayerComponent.getMediaPlayer().pause();
    }
    
    public void stop() {
        mediaPlayerComponent.getMediaPlayer().stop();
    }
    
    public Boolean isPlaying() {
        return mediaPlayerComponent.getMediaPlayer().isPlaying();
    }
    
    public long getLength(boolean formatted) {
        return mediaPlayerComponent.getMediaPlayer().getLength();
    }
    
    public String getFormattedLength() {
        long length = mediaPlayerComponent.getMediaPlayer().getLength() / 1000;
        
        int hours = (int) (length / 3600);
        int remainder = (int) (length % 3600);
        int minutes = remainder / 60;
        int seconds = remainder % 60;

        return ( (hours < 10 ? "0" : "") + hours
            + ":" + (minutes < 10 ? "0" : "") + minutes
            + ":" + (seconds< 10 ? "0" : "") + seconds );
    }
    
    public long getTime() {
        return mediaPlayerComponent.getMediaPlayer().getTime();
    }
    
    public String getFormattedTime() {
        long time = mediaPlayerComponent.getMediaPlayer().getTime() / 1000;
        
        int hours = (int) (time / 3600);
        int remainder = (int) (time % 3600);
        int minutes = remainder / 60;
        int seconds = remainder % 60;

        return ( (hours < 10 ? "0" : "") + hours
            + ":" + (minutes < 10 ? "0" : "") + minutes
            + ":" + (seconds< 10 ? "0" : "") + seconds );
    }
}
