package kg.sunrise.hightime.News;

import com.google.gson.annotations.SerializedName;

public class News {
      int id;
      String name;

      @SerializedName("short")
      String shortInfo;

      String thumbmailS;

      boolean status;


      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getShortInfo() {
            return shortInfo;
      }

      public void setShortInfo(String shortInfo) {
            this.shortInfo = shortInfo;
      }

      public String getThumbmailS() {
            return thumbmailS;
      }

      public void setThumbmailS(String thumbmailS) {
            this.thumbmailS = thumbmailS;
      }

      public boolean isStatus() {
            return status;
      }

      public void setStatus(boolean status) {
            this.status = status;
      }
}
