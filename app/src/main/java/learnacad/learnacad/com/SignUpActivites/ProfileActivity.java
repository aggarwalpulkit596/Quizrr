package learnacad.learnacad.com.SignUpActivites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.MODELS.Quiz;
import learnacad.learnacad.com.MODELS.User;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.REST.ApiClients;
import learnacad.learnacad.com.REST.ApiInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;


public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profileconstraintlayout)
    RelativeLayout constraintLayout;
    @BindView(R.id.profilenameedittext)
    EditText usernamedittext;
    @BindView(R.id.profilereferraledittext)
    EditText referraledittext;
    @BindView(R.id.profilesumbitbutton)
    Button sumbitbtn;

    @BindView(R.id.userimage2nd)
    CircleImageView userImage;
    String otp;
    String phoneno;
    Uri resultUri;
    File compressedImageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        Intent i = getIntent();
        otp = i.getStringExtra("otp");
        phoneno = i.getStringExtra("phone");
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.dialog_referral, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey,Download Quizrr  " + "http://bit.ly/playquizrr");
                try {
                    startActivity(Intent.createChooser(whatsappIntent, "Share Code"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ProfileActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
                setdata(usernamedittext.getText().toString(), referraledittext.getText().toString(), compressedImageFile);

            }
        });

        usernamedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernamedittext.setCompoundDrawables(null, null, null, null);
                if (usernamedittext.isFocused()) {
                    sumbitbtn.setAlpha(1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (usernamedittext.getText().toString().isEmpty()) {
                    sumbitbtn.setAlpha((float) 0.5);
                    sumbitbtn.setClickable(false);
                } else {
                    sumbitbtn.setClickable(true);
                }

            }
        });
        sumbitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                Drawable dr = getResources().getDrawable(R.drawable.ic_error_black_24dp);
                //add an error icon to yur drawable files
                dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                imm.hideSoftInputFromWindow(usernamedittext.getWindowToken(), 0);
                if (usernamedittext.getText().toString().isEmpty()) {
                    usernamedittext.setCompoundDrawables(null, null, dr, null);
                    Snackbar snackBar = Snackbar.make(constraintLayout, "Oops! Username field can’t be empty.", Snackbar.LENGTH_SHORT);
                    snackBar.show();
                } else if (usernamedittext.getText().toString().length() < 4) {
                    usernamedittext.setCompoundDrawables(null, null, dr, null);
                    Snackbar snackBar = Snackbar.make(constraintLayout, "Oops! Username is too short", Snackbar.LENGTH_SHORT);
                    snackBar.show();
                } else {
                    if (checkusername(usernamedittext.getText().toString())) {
                        Snackbar snackBar = Snackbar.make(constraintLayout, "Username should Contain Numbers as well as Digits", Snackbar.LENGTH_SHORT);
                        snackBar.show();
                    }
                    if (referraledittext.getText().toString().isEmpty()) {
                        deleteDialog.show();
                    } else if (!referraledittext.getText().toString().isEmpty()) {
                        setdata(usernamedittext.getText().toString(), referraledittext.getText().toString(), compressedImageFile);

                    }
                }
            }
        });


    }

    private void setdata(String s, String s1, File file) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        MultipartBody.Part body;
        if (file != null) {
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            body =
                    MultipartBody.Part.createFormData("file", file.getAbsolutePath(), requestFile);
        } else {
            body = null;
        }
        RequestBody username =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), s);
        RequestBody number =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), phoneno);
        RequestBody pin =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), otp);
        RequestBody referral =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), s1);


        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<User> call = apiService.signUp(number, pin, username, referral, body);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.i("TAG", "onResponse:signup111 " + response.message() + response.body() + response);
                if (response.isSuccessful()) {
                    User object = response.body();
                    Intent i = new Intent(ProfileActivity.this, SigUpComplete.class);
                    i.putExtra("token", object.getToken());
                    i.putExtra("username", object.getUser().getUserName());
                    if (object.getUser().getReferrer() != null)
                        i.putExtra("referrer", true);
                    else
                        i.putExtra("referrer", false);
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.KEY_ACCESS_TOKEN, object.getToken());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERNAME, object.getUser().getUserName());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERIMAGE, object.getUser().getImageUrl());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERID, object.getUser().getId());

                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                    progressDialog.dismiss();
                    finish();
                } else {
                    progressDialog.dismiss();
                    Snackbar snackBar = Snackbar.make(constraintLayout, "Oops! Invalid Username or Referral Code try again.", Snackbar.LENGTH_SHORT);
                    snackBar.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("onResponse:signup", t.getCause() + "");

                progressDialog.dismiss();
            }
        });
    }

    private boolean checkusername(String s) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9]");
        Log.i("TAG", "checkusername: " + pattern.matcher(s).matches());
        return (pattern.matcher(s).matches());
    }


    public void setimage(View view) {
//         start picker to get image for cropping and then use the image in cropping activity
        CropImage.activity()
                .setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMinCropWindowSize(500, 500)
                .start(ProfileActivity.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                compressedImageFile = null;
                try {
                    compressedImageFile = new Compressor(this).compressToFile(new File(resultUri.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                userImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void backbtn(View view) {
        onBackPressed();
    }
}
