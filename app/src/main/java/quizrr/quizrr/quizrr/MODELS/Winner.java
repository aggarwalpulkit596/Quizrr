package quizrr.quizrr.quizrr.MODELS;

/**
 * Created by pulkit-mac on 08/03/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Winner {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("totalTimeTaken")
    @Expose
    private Integer totalTimeTaken;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public void setTotalTimeTaken(Integer totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

}