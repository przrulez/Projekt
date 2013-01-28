/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prz.l04.database;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 *
 * @author przemoli
 */
@DatabaseTable(tableName = "movies")
public class Movies {
    @DatabaseField(generatedId = true, width = 10)
    private Integer id;
    @DatabaseField(canBeNull = false, width = 100)
    private String name;
    @DatabaseField(canBeNull = false, dataType = DataType.LONG_STRING)
    private String content;
    @DatabaseField(canBeNull = false, dataType = DataType.LONG_STRING)
    private String file;
    @DatabaseField(canBeNull = false)
    private Date created;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
