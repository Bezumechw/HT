package kg.sunrise.hightime.Lessons.Char_lesson.Test;

import java.util.ArrayList;

public class Tests {
    int id;
    String slug;
    String name;
    String content;
    int lesson_id;
    int video_id;
    String answer;
    ArrayList<Answers> answers;

    public ArrayList<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answers> answers) {
        this.answers = answers;
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

    public String getContent() {
        return content;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public String getAnswer() {
        return answer;
    }

}
