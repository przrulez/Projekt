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

@DatabaseTable(tableName = "categories_movies")
public class CatMovies {
    @DatabaseField(generatedId = true, width = 10)
    private Integer id;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Categories category;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Movies movie;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the category
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Categories category) {
        this.category = category;
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
