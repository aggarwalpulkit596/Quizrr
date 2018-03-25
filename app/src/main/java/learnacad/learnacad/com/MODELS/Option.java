package learnacad.learnacad.com.MODELS;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Option {

    @SerializedName("choosenCount")
    @Expose
    private Integer choosenCount;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("value")
    @Expose
    private String value;

    public Integer getChoosenCount() {
        return choosenCount;
    }

    public void setChoosenCount(Integer choosenCount) {
        this.choosenCount = choosenCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
