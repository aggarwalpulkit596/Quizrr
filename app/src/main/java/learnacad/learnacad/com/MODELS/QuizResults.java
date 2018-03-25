package learnacad.learnacad.com.MODELS;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizResults
{
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("winners")
    @Expose
    private List<Winner> winners = null;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("instructionId")
    @Expose
    private String instructionId;
    @SerializedName("prize")
    @Expose
    private List<Prize> prize = null;
    @SerializedName("isWinnerDeclared")
    @Expose
    private Boolean isWinnerDeclared;
    @SerializedName("Topic")
    @Expose
    private String topic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Winner> getWinners() {
        return winners;
    }

    public void setWinners(List<Winner> winners) {
        this.winners = winners;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public List<Prize> getPrize() {
        return prize;
    }

    public void setPrize(List<Prize> prize) {
        this.prize = prize;
    }

    public Boolean getIsWinnerDeclared() {
        return isWinnerDeclared;
    }

    public void setIsWinnerDeclared(Boolean isWinnerDeclared) {
        this.isWinnerDeclared = isWinnerDeclared;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}

