package kg.sunrise.hightime.Level.Test;

import java.util.ArrayList;

public class Test {

    String title;
    String content;
    ArrayList<Answer> answers;

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
