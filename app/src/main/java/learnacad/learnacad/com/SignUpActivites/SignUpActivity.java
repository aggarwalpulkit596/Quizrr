package learnacad.learnacad.com.SignUpActivites;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnacad.learnacad.com.Constants;
import learnacad.learnacad.com.R;

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.signupnumberedittext)
    EditText signupnumberedittext;

    @BindView(R.id.signupconstraintlayout)
    RelativeLayout relativeLayout;

    @BindView(R.id.button3)
    Button continuebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        continuebtn.setClickable(false);

        signupnumberedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                signupnumberedittext.setCompoundDrawables(null, null, null, null);
                if (signupnumberedittext.isFocused()) {
                    continuebtn.setAlpha(1);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (signupnumberedittext.getText().toString().isEmpty() || signupnumberedittext.getText().toString().length() < 10) {
                    continuebtn.setAlpha((float) 0.5);
                    continuebtn.setClickable(false);
                } else {
                    continuebtn.setClickable(true);
                }
            }
        });


    }


    public void signupbtn(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(signupnumberedittext.getWindowToken(), 0);
        Drawable dr = getResources().getDrawable(R.drawable.ic_error_black_24dp);
        //add an error icon to yur drawable files
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
        if (signupnumberedittext.getText().toString().isEmpty()) {
            signupnumberedittext.setCompoundDrawables(null, null, dr, null);
            Snackbar snackBar = Snackbar.make(relativeLayout, "Field can't be Empty", Snackbar.LENGTH_LONG);
            snackBar.show();
        } else if (signupnumberedittext.getText().toString().length() < 10) {
            signupnumberedittext.setCompoundDrawables(null, null, dr, null);
            Snackbar snackBar = Snackbar.make(relativeLayout, "Invalid number. Please check.", Snackbar.LENGTH_LONG);
            snackBar.show();
        } else {
            if (Constants.isConnected(this)) {
                Intent i = new Intent(SignUpActivity.this, OtpActivity.class);
                i.putExtra("phoneno", signupnumberedittext.getText().toString());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            } else {
                Snackbar snackBar = Snackbar.make(relativeLayout, "No Internet Connection", Snackbar.LENGTH_LONG);
                snackBar.show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }

    public void backbtn(View view) {
        onBackPressed();

    }
}
