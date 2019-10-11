package kg.sunrise.hightime.News.NewsSecond;

import com.google.gson.annotations.SerializedName;

public class NewsSecond {

    int id;
    String name;//заголовок

    @SerializedName("short")
    String content;//1 содержание

    String thumbmailL;// фото

    boolean status;
    @SerializedName("content")
    String contentrus;
    //Отдельно
    String name_kg;
    String short_kg;
    String content_kg;
    //Отдельно
    String name_en;
    String short_en;
    String content_en;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setThumbmailL(String thumbmailS) {
        this.thumbmailL = thumbmailS;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContentrus() {
        return contentrus;
    }

    public void setContentrus(String contentrus) {
        this.contentrus = contentrus;
    }

    public String getName_kg() {
        return name_kg;
    }

    public void setName_kg(String name_kg) {
        this.name_kg = name_kg;
    }

    public String getShort_kg() {
        return short_kg;
    }

    public void setShort_kg(String short_kg) {
        this.short_kg = short_kg;
    }

    public String getContent_kg() {
        return content_kg;
    }

    public void setContent_kg(String contentkg) {
        this.content_kg = contentkg;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getShort_en() {
        return short_en;
    }

    public void setShort_en(String short_en) {
        this.short_en = short_en;
    }

    public String getContent_en() {
        return content_en;
    }

    public void setContent_en(String content_en) {
        this.content_en = content_en;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getThumbmailL() {
        return thumbmailL;
    }

    public boolean isStatus() {
        return status;
    }

}
