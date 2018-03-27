package quizrr.quizrr.quizrr.MODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pulkit-mac on 09/03/18.
 */

public class userfields {


    @SerializedName("contestWon")
    @Expose
    private Integer contestWon;
    @SerializedName("amountWon")
    @Expose
    private Integer amountWon;
    @SerializedName("isConnected")
    @Expose
    private Boolean isConnected;
    @SerializedName("contests")
    @Expose
    private List<Object> contests = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("lifeLine")
    @Expose
    private Integer lifeLine;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("referrer")
    @Expose
    private String referrer;


    public userfields(Integer contestWon, Integer amountWon, Boolean isConnected, List<Object> contests, String id, String userName, String contact, Integer lifeLine) {
        this.contestWon = contestWon;
        this.amountWon = amountWon;
        this.isConnected = isConnected;
        this.contests = contests;
        this.id = id;
        this.userName = userName;
        this.contact = contact;
        this.lifeLine = lifeLine;
    }
    public userfields(Integer amountWon, String id, String userName, Integer lifeLine, String imageUrl){
        this.amountWon = amountWon;
        this.id = id;
        this.userName = userName;
        this.lifeLine = lifeLine;
        this.imageUrl = imageUrl;
    }

    public Integer getContestWon() {
        return contestWon;
    }

    public void setContestWon(Integer contestWon) {
        this.contestWon = contestWon;
    }

    public Integer getAmountWon() {
        return amountWon;
    }

    public void setAmountWon(Integer amountWon) {
        this.amountWon = amountWon;
    }

    public Boolean getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(Boolean isConnected) {
        this.isConnected = isConnected;
    }

    public List<Object> getContests() {
        return contests;
    }

    public void setContests(List<Object> contests) {
        this.contests = contests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getLifeLine() {
        return lifeLine;
    }

    public void setLifeLine(Integer lifeLine) {
        this.lifeLine = lifeLine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }
}