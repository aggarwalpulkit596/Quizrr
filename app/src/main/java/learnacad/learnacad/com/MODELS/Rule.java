package learnacad.learnacad.com.MODELS;

/**
 * Created by pulkit-mac on 25/03/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rule {

    @SerializedName("cmsType")
    @Expose
    private String cmsType;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("text")
    @Expose
    private String text;

    public String getCmsType() {
        return cmsType;
    }

    public void setCmsType(String cmsType) {
        this.cmsType = cmsType;
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

}
