package quizrr.quizrr.quizrr.REST;

import com.google.gson.JsonObject;

import java.util.List;

import quizrr.quizrr.quizrr.MODELS.CMS;
import quizrr.quizrr.quizrr.MODELS.Instruction;
import quizrr.quizrr.quizrr.MODELS.Leaderboard;
import quizrr.quizrr.quizrr.MODELS.PastQuizes;
import quizrr.quizrr.quizrr.MODELS.Quiz;
import quizrr.quizrr.quizrr.MODELS.ReviewQuiz;
import quizrr.quizrr.quizrr.MODELS.User;
import quizrr.quizrr.quizrr.MODELS.userfields;
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

    @GET("quiz/getfutureevnts")
    Call<List<Quiz>> futureevents(@Header("token") String token);

    @GET("cms")
    Call<CMS> cms();


}
