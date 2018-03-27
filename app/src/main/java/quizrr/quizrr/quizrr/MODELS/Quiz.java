package quizrr.quizrr.quizrr.MODELS;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pulkit-mac on 09/03/18.
 */

public class Quiz implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("winners")
    @Expose
    private List<Object> winners = null;
    @SerializedName("questions")
    @Expose
    private List<String> questions = null;
    @SerializedName("prize")
    @Expose
    private List<Prize> prize = null;
    @SerializedName("isWinnerDeclared")
    @Expose
    private Boolean isWinnerDeclared;
    @SerializedName("delayInGame")
    @Expose
    private Integer delayInGame;
    @SerializedName("Date")
    @Expose
    private Long date;
    @SerializedName("Topic")
    @Expose
    private String topic;

    public String getPrizUrl() {
        return prizUrl;
    }

    public void setPrizUrl(String prizUrl) {
        this.prizUrl = prizUrl;
    }

    @SerializedName("prizeUrl")
    @Expose
    private String prizUrl;
    @SerializedName("timeOfQuestion")
    @Expose
    private Integer timeOfQuestion;
    @SerializedName("timeBetweenQuestion")
    @Expose
    private Integer timeBetweenQuestion;
    @SerializedName("endTime")
    @Expose
    private Integer endTime;
    @SerializedName("isLive")
    @Expose
    private Boolean isLive;
    @SerializedName("isLate")
    @Expose
    private Boolean isLate;
    @SerializedName("index")
    @Expose
    private int index;

    public Quiz(String id, List<Object> winners, List<String> questions, List<Prize> prize, Boolean isWinnerDeclared, Integer delayInGame, Long date, String topic, Integer timeOfQuestion, Integer timeBetweenQuestion, Integer endTime, Boolean isLive, Boolean isLate, int index) {
        this.id = id;
        this.winners = winners;
        this.questions = questions;
        this.prize = prize;
        this.isWinnerDeclared = isWinnerDeclared;
        this.delayInGame = delayInGame;
        this.date = date;
        this.topic = topic;
        this.timeOfQuestion = timeOfQuestion;
        this.timeBetweenQuestion = timeBetweenQuestion;
        this.endTime = endTime;
        this.isLive = isLive;
        this.isLate = isLate;
        this.index = index;
    }

    public Quiz() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Object> getWinners() {
        return winners;
    }

    public void setWinners(List<Object> winners) {
        this.winners = winners;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
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

    public Integer getDelayInGame() {
        return delayInGame;
    }

    public void setDelayInGame(Integer delayInGame) {
        this.delayInGame = delayInGame;
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

    public Integer getTimeOfQuestion() {
        return timeOfQuestion;
    }

    public void setTimeOfQuestion(Integer timeOfQuestion) {
        this.timeOfQuestion = timeOfQuestion;
    }

    public Integer getTimeBetweenQuestion() {
        return timeBetweenQuestion;
    }

    public void setTimeBetweenQuestion(Integer timeBetweenQuestion) {
        this.timeBetweenQuestion = timeBetweenQuestion;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }

    public Boolean getIsLate() {
        return isLate;
    }

    public void setIsLate(Boolean isLate) {
        this.isLate = isLate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
