package quizrr.quizrr.quizrr.SignUpActivites;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import quizrr.quizrr.quizrr.Adapters.MyPreferenceManager;
import quizrr.quizrr.quizrr.Adapters.MyReceiver;
import quizrr.quizrr.quizrr.Adapters.SmsListener;
import quizrr.quizrr.quizrr.Activities.HomeActivity;
import quizrr.quizrr.quizrr.MODELS.User;
import quizrr.quizrr.quizrr.QuizApp;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.REST.ApiClients;
import quizrr.quizrr.quizrr.REST.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_SMS;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.otpedittext1)
    EditText editText1;

    @BindView(R.id.otpedittext2)
    EditText editText2;

    @BindView(R.id.otpedittext3)
    EditText editText3;

    @BindView(R.id.otpedittext4)
    EditText editText4;

    @BindView(R.id.otpresettimer)
    TextView timerTextView;

    @BindView(R.id.otpsumbitbutton)
    Button submitButton;

    @BindView(R.id.otpResendButton)
    Button otpResendButton;

    @BindView(R.id.optconstraintlayout)
    RelativeLayout constraintLayout;

    CountDownTimer timer;
    boolean isAllowedToRead;
    private String otp1, otp2, otp3, otp4;
    int count = 1;
    String phoneno;
    boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        Intent i = getIntent();
        phoneno = i.getStringExtra("phoneno");

        if (!checkPermission()) {
            requestPermission();
        }


        sendotp(phoneno);
        verifyOTP();

        settimer();
        listeners();

        otpResendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++count;
                if (count <= 3) {
                    sendotp(phoneno);
                    Snackbar snackBar = Snackbar.make(constraintLayout, "OTP sent again.", Snackbar.LENGTH_LONG);
                    snackBar.show();
                    deactivateresentbutton();
                    settimer();
                } else {
                    Snackbar snackBar = Snackbar.make(constraintLayout, "Try After Some Time.", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }
            }
        });
    }

    private void listeners() {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!editText1.getText().toString().isEmpty()) {
                    editText2.requestFocus();
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (editText2.getText().toString().isEmpty())
                    editText1.requestFocus();
                else
                    editText3.requestFocus();
            }
        });
        editText2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_DEL) {
                    // this is for backspace
                    editText1.requestFocus();
                }
                return false;
            }
        });

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText3.getText().toString().isEmpty())
                    editText2.requestFocus();
                else
                    editText4.requestFocus();
            }
        });
        editText3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_DEL) {
                    // this is for backspace
                    editText2.requestFocus();
                }
                return false;
            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText4.getText().toString().isEmpty())
                    editText3.requestFocus();
                else
                    editText4.requestFocus();
                if (editText1.getText().toString().isEmpty() && editText2.getText().toString().isEmpty() && editText3.getText().toString().isEmpty() && editText4.getText().toString().isEmpty()) {
                    submitButton.setAlpha((float) 0.5);
                    submitButton.setOnClickListener(null);
                }else {
                    submitButton.setAlpha(1);
                    submitButton.setOnClickListener(OtpActivity.this);
                }
            }
        });
        editText4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_DEL) {
                    // this is for backspace
                    editText3.requestFocus();
                }
                return false;
            }
        });
    }


    private void settimer() {
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timerTextView.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {

                timerTextView.setText("");
                activateResendButton();


            }
        }.start();
    }

    private void verifyOTP() {

        MyReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                editText1.setText("*");
                editText2.setText("*");
                editText3.setText("*");
                editText4.setText("*");
                timer.cancel();
                Toast.makeText(OtpActivity.this, "Recieved", Toast.LENGTH_SHORT).show();
                checkotp(messageText);


            }
        });

    }

    private void requestPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_SMS}, 2501);
        }
    }

    private boolean checkPermission() {

        int readSMS = ContextCompat.checkSelfPermission(this, READ_SMS);


        isAllowedToRead = (readSMS == PackageManager.PERMISSION_GRANTED);
        return isAllowedToRead;

    }

    public void activateResendButton() {

        otpResendButton.setEnabled(true);
        otpResendButton.setTextColor(Color.parseColor("#ffffff"));
        float x = otpResendButton.getX();


        ValueAnimator animator = ValueAnimator.ofFloat(x, x + 100);
        animator.setInterpolator(new DemoOvershootInterpolator());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                otpResendButton.setX(x);
            }
        });
        animator.start();
    }

    public void deactivateresentbutton() {
        otpResendButton.setEnabled(false);
        otpResendButton.setTextColor(Color.parseColor("#8a8a8a"));
        float x = otpResendButton.getX();


        ValueAnimator animator = ValueAnimator.ofFloat(x, x - 100);
        animator.setInterpolator(new DemoOvershootInterpolator());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                otpResendButton.setX(x);
            }
        });
        animator.start();
    }

    private boolean validateEditText() {

        otp1 = editText1.getText().toString().trim();
        otp2 = editText2.getText().toString().trim();
        otp3 = editText3.getText().toString().trim();
        otp4 = editText4.getText().toString().trim();

        if (TextUtils.isEmpty(otp1) || TextUtils.isEmpty(otp2) || TextUtils.isEmpty(otp3) || TextUtils.isEmpty(otp4) ||
                (otp1.length() != 1) || (otp2.length() != 1) || (otp3.length() != 1) || (otp4.length() != 1)) {


            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timerTextView.setText(" ");
    }

    @Override
    public void onClick(View view) {
        if (validateEditText()) {

            StringBuilder sb = new StringBuilder(otp1 + otp2 + otp3 + otp4);

            try {

                if (checkotp(sb.toString())) {
                    timerTextView.setText(" ");
                    timer.cancel();
                }
            } catch (NumberFormatException e) {

//                        otpError();
            }

        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText4.getWindowToken(), 0);
            Snackbar snackBar = Snackbar.make(constraintLayout, "Invalid OTP.Please try again.", Snackbar.LENGTH_LONG);
            View sbView = snackBar.getView();
            sbView.setBackgroundColor(Color.parseColor("#d63f3a"));
            snackBar.show();
        }
    }


    class DemoOvershootInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {

            float extra = 0.5f;
            float k = (1 + extra) / (1 + 2 * extra);

            if (input < k) {
                return input * ((1 + extra) / k);
            } else {
                float time = input - k;
                return 1 + extra - (extra / (1 - k)) * time;

            }
        }
    }

    private void sendotp(String s) {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);

        Call<JsonObject> call = apiService.sendOtp(s);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                Log.i("TAG", "onResponse: " + object);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private boolean checkotp(final String verificationCode) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.verifyOtp(phoneno, verificationCode);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                if (response.isSuccessful()) {
                    if (object.get("success").getAsString().equals("true")) {
                        progressDialog.dismiss();
                        if (object.get("isExisting").getAsString().equals("true")) {
                            signIn(verificationCode);
                        } else {
                            status = true;
                            Intent i = new Intent(OtpActivity.this, ProfileActivity.class);
                            i.putExtra("otp", verificationCode);
                            i.putExtra("phone", phoneno);
                            startActivity(i);
                            finish();
                        }
                    }
                } else {
                    progressDialog.dismiss();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText4.getWindowToken(), 0);
                    Snackbar snackBar = Snackbar.make(constraintLayout, "Incorrect OTP. Try again.", Snackbar.LENGTH_LONG);
                    View sbView = snackBar.getView();
                    sbView.setBackgroundColor(Color.parseColor("#d63f3a"));
                    snackBar.show();

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("POST_API", t.getCause() + "");
            }
        });
        return status;
    }

    private void signIn(final String verificationCode) {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<User> call = apiService.signin(phoneno, verificationCode);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    User object = response.body();
                    status = true;
                    Intent i = new Intent(OtpActivity.this, HomeActivity.class);
                    i.putExtra("token", object.getToken());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.KEY_ACCESS_TOKEN, object.getToken());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERNAME,object.getUser().getUserName());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERIMAGE, object.getUser().getImageUrl());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERID, object.getUser().getId());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("POST_API", t.getCause() + "");
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    public void backbtn(View view) {
        onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MyReceiver.unbindListener();

    }
}
