package learnacad.learnacad.com.MODELS;

/**
 * Created by pulkit-mac on 13/03/18.
 */

public class Response {
    private String user_game_status;
    private String gifUrl;


    public String getUser_game_status() {
        return user_game_status;
    }

    public void setUser_game_status(String user_game_status) {
        this.user_game_status = user_game_status;
    }

    public int getOption1_count() {
        return option1_count;
    }

    public void setOption1_count(int option1_count) {
        this.option1_count = option1_count;
    }

    public int getOption2_count() {
        return option2_count;
    }

    public void setOption2_count(int option2_count) {
        this.option2_count = option2_count;
    }

    public int getOption3_count() {
        return option3_count;
    }

    public void setOption3_count(int option3_count) {
        this.option3_count = option3_count;
    }

    public int getOption4_count() {
        return option4_count;
    }

    public void setOption4_count(int option4_count) {
        this.option4_count = option4_count;
    }

    public int getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(int correct_option) {
        this.correct_option = correct_option;
    }

    public String getLeast_time_taken() {
        return least_time_taken;
    }

    public void setLeast_time_taken(String least_time_taken) {
        this.least_time_taken = least_time_taken;
    }

    private int option1_count;
    private int option2_count;
    private int option3_count;

    public Response(String user_game_status, int option1_count, int option2_count, int option3_count, int option4_count, int correct_option, String least_time_taken, String url) {
        this.user_game_status = user_game_status;
        this.option1_count = option1_count;
        this.option2_count = option2_count;
        this.option3_count = option3_count;
        this.option4_count = option4_count;
        this.correct_option = correct_option;
        this.least_time_taken = least_time_taken;
        this.gifUrl = url;
    }

    private int option4_count;
    private int correct_option;
    private String least_time_taken;

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }
}
