package quizrr.quizrr.quizrr.MODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pulkit-mac on 20/03/18.
 */

public class PastQuizes implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Date")
    @Expose
    private Long date;
    @SerializedName("Topic")
    @Expose
    private String topic;
    @SerializedName("questions")
    @Expose
    private int questionsno;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQuestionsno() {
        return questionsno;
    }

    public void setQuestionsno(int questionsno) {
        this.questionsno = questionsno;
    }
}
