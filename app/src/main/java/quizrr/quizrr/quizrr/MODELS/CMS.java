package quizrr.quizrr.quizrr.MODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pulkit-mac on 25/03/18.
 */

public class CMS {
    @SerializedName("rules")
    @Expose
    private List<Rule> rules = null;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
