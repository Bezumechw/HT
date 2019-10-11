package kg.sunrise.hightime.Lessons.Char_lesson;

import java.util.ArrayList;

public class Char {
    int id;
    String slug;
    String name;
    String short_description;
    String description;
    boolean trial;
    boolean avtive;
    int level_id;
    ArrayList<Videos> videos;

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTrial() {
        return trial;
    }

    public boolean isAvtive() {
        return avtive;
    }

    public int getLevel_id() {
        return level_id;
    }

    public ArrayList<Videos> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Videos> videos) {
        this.videos = videos;
    }
}
