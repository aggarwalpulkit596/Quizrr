package quizrr.quizrr.quizrr.MODELS;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pulkit-mac on 08/03/18.
 */

public class InstructionList {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("instruction")
    @Expose
    private List<Instruction> instruction = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Instruction> getInstruction() {
        return instruction;
    }

    public void setInstruction(List<Instruction> instruction) {
        this.instruction = instruction;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
