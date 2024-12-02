package org.catastream.db.sqlLite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WishList")
public class WishList {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String overview;
    private String posterPath;

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }

    public String getPosterPath() { return posterPath; }
    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }
}
