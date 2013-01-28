/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prz.l04.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author przemoli
 */
@DatabaseTable(tableName = "playlist_movies")
public class PlayMovies {
    @DatabaseField(generatedId = true, width = 10)
    private Integer id;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Playlist playlist;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Movies movie;   

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the playlist
     */
    public Playlist getPlaylist() {
        return playlist;
    }

    /**
     * @param playlist the playlist to set
     */
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * @return the movie
     */
    public Movies getMovie() {
        return movie;
    }

    /**
     * @param movie the movie to set
     */
    public void setMovie(Movies movie) {
        this.movie = movie;
    }
}
