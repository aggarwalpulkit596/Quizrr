package quizrr.quizrr.quizrr.MODELS;

/**
 * Created by pulkit-mac on 08/03/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Prize implements Serializable {

    @SerializedName("amount")
    @Expose
    private Integer prize;
    @SerializedName("position")
    @Expose
    private Integer position;

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}