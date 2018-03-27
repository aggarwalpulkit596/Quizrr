package quizrr.quizrr.quizrr.MODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pulkit-mac on 08/03/18.
 */

public class User {

    @SerializedName("user")
    @Expose
    private userfields user;
    @SerializedName("token")
    @Expose
    private String token;

    public User(userfields user, String token) {
        this.user = user;
        this.token = token;
    }

    public userfields getUser() {
        return user;
    }

    public void setUser(userfields user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
