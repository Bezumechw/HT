package kg.sunrise.hightime.Lessons.Char_lesson;

public class Videos {

    String type;
    int id;
    String slug;
    String name;
    String thumbnail;
    String duration;
    boolean active;
    int lesson_id;
    String url_high;
    String url_low;

    public String getUrl_youtube() {
        return url_youtube;
    }

    public void setUrl_youtube(String url_youtube) {
        this.url_youtube = url_youtube;
    }

    String url_youtube;
    int video;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

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
