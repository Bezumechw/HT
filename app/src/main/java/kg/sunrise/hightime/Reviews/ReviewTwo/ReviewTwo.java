package kg.sunrise.hightime.Reviews.ReviewTwo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class ReviewTwo {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public File getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(File thumbnail) {
        this.thumbnail = thumbnail;
    }

//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("thumbnail")
    @Expose
    private File thumbnail;
}
