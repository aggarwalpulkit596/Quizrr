package quizrr.quizrr.quizrr.MODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pulkit-mac on 08/03/18.
 */

public class Leaderboard {
    @SerializedName("Topic")
    @Expose
    private String topic;
    @SerializedName("winners")
    @Expose
    private List<Winner> winners = null;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Winner> getWinners() {
        return winners;
    }

    public void setWinners(List<Winner> winners) {
        this.winners = winners;
    }
}
