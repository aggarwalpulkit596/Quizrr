package learnacad.learnacad.com.MODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ReviewQuiz {
    @SerializedName("Topic")
    @Expose
    private String topic;
    @SerializedName("questionText")
    @Expose
    private String questionText;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;
    @SerializedName("correctAnswer")
    @Expose
    private Integer correctAnswer;
    @SerializedName("leastTime")
    @Expose
    private Integer leastTime;
    @SerializedName("hasAttempted")
    @Expose
    private Boolean hasAttempted;
    @SerializedName("isCorrect")
    @Expose
    private Boolean isCorrect;
    @SerializedName("chosenAnswer")
    @Expose
    private Integer chosenAnswer;
    @SerializedName("timeTaken")
    @Expose
    private Integer timeTaken;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getLeastTime() {
        return leastTime;
    }

    public void setLeastTime(Integer leastTime) {
        this.leastTime = leastTime;
    }

    public Boolean getHasAttempted() {
        return hasAttempted;
    }

    public void setHasAttempted(Boolean hasAttempted) {
        this.hasAttempted = hasAttempted;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Integer getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(Integer chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public Integer getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

}

