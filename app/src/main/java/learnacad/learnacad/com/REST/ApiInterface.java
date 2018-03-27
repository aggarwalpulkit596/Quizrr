package learnacad.learnacad.com.REST;

import com.google.gson.JsonObject;

import java.util.List;

import learnacad.learnacad.com.MODELS.CMS;
import learnacad.learnacad.com.MODELS.Instruction;
import learnacad.learnacad.com.MODELS.InstructionList;
import learnacad.learnacad.com.MODELS.Leaderboard;
import learnacad.learnacad.com.MODELS.PastQuizes;
import learnacad.learnacad.com.MODELS.Quiz;
import learnacad.learnacad.com.MODELS.QuizResults;
import learnacad.learnacad.com.MODELS.ReviewQuiz;
import learnacad.learnacad.com.MODELS.User;
import learnacad.learnacad.com.MODELS.userfields;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface ApiInterface {
    @POST("users/otpsend")
    @FormUrlEncoded
    Call<JsonObject> sendOtp(@Field("contact") String Number);

    @POST("users/otpcheck")
    @FormUrlEncoded
    Call<JsonObject> verifyOtp(@Field("contact") String Number, @Field("otp") String opt);

    @POST("users/signin")
    @FormUrlEncoded
    Call<User> signin(@Field("contact") String Number, @Field("otp") String opt);

    @Multipart
    @POST("users/signup")
    Call<User> signUp(@Part("contact") RequestBody Number, @Part("otp") RequestBody otp, @Part("username") RequestBody username, @Part("referral") RequestBody refcode, @Part MultipartBody.Part image);

    @GET("quiz")
    Call<Quiz> quiz();

    @GET("quiz/quizreview/{quizid}/{number}")
    Call<ReviewQuiz> revquestion(@Header("token") String token, @Path("quizid") String quizid, @Path("number") int quizno);

    @GET("users/quiz/leaderboard/{quizid}")
    Call<Leaderboard> leaderboard(@Header("token") String token,@Path("quizid") String quizid);

    @GET("instruction/{quizid}")
    Call<List<Instruction>> quizinstructions(@Path("quizid") String quizid);

    @GET("users")
    Call<userfields> user(@Header("token") String token);

    @Multipart
    @POST("users/changeprofile")
    Call<JsonObject> changeprofile(@Header("token") String token, @Part MultipartBody.Part image);

    @GET("quiz/listofquiz")
    Call<List<PastQuizes>> pastquiz(@Header("token") String token);

    @GET("cms")
    Call<CMS> cms();


}
