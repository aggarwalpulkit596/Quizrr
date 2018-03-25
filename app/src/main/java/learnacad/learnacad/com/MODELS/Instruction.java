package learnacad.learnacad.com.MODELS;

/**
 * Created by pulkit-mac on 08/03/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instruction {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
