package kg.sunrise.hightime.model;

public class Video {
    int id;
    String slug;
    String name;
    String thumbnail;
    String duration;
    boolean active;
    int lesson_id;
    String url_high;
    String url_low;

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDuration() {
        return duration;
    }

    public boolean isActive() {
        return active;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public String getUrl_high() {
        return url_high;
    }

    public String getUrl_low() {
        return url_low;
    }
}
