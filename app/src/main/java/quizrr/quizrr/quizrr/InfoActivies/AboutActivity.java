package quizrr.quizrr.quizrr.InfoActivies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import quizrr.quizrr.quizrr.Activities.HomeActivity;
import quizrr.quizrr.quizrr.Activities.TourActivity;
import quizrr.quizrr.quizrr.QuizApp;
import quizrr.quizrr.com.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.aboutusterms)
    TextView aboutusterms;
    @BindView(R.id.aboutuspolicy)
    TextView aboutuspolicy;
    @BindView(R.id.appversiontext)
    TextView Appversion;
    @BindViews({R.id.logoutbuttontext, R.id.ratebuttontext, R.id.rulesButtontext, R.id.howtoplayButtontext})
    TextView[] texts;
    @BindViews({R.id.instabutton, R.id.fbbutton, R.id.twitterbutton, R.id.rulesButton, R.id.logoutbutton, R.id.ratebutton, R.id.howtoplayButton})
    ImageButton[] buttons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        ButterKnife.bind(this);
        aboutuspolicy.setOnClickListener(this);
        aboutusterms.setOnClickListener(this);
        Appversion.setText("App Version 4.0");
        for (int i = 0; i < 7; i++)
            buttons[i].setOnClickListener(this);
        for (int i = 0; i < 4; i++)
            texts[i].setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aboutusterms:
                Intent i = new Intent(AboutActivity.this, TermsandPolicyActivity.class);
                i.putExtra("type", "terms");
                startActivity(i);
                break;
            case R.id.aboutuspolicy:
                i = new Intent(AboutActivity.this, TermsandPolicyActivity.class);
                i.putExtra("type", "policy");
                startActivity(i);
                break;
            case R.id.howtoplayButton:
                i = new Intent(AboutActivity.this, TourActivity.class);
                startActivity(i);
                break;

            case R.id.logoutbutton:
                AlertDialog.Builder builder = new AlertDialog.Builder(AboutActivity.this);
                builder.setTitle("Confirm");
                builder.setCancelable(false);
                builder.setMessage("Are you sure you want to Logout?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QuizApp.getPreferenceManager().clearLoginData();

                        Intent over = new Intent(AboutActivity.this, HomeActivity.class);
                        startActivity(over);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
                final AlertDialog dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#000000"));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#000000"));

                    }
                });

                dialog.show();
                break;
            case R.id.instabutton:
                i = new Intent(Intent.ACTION_VIEW);
                Uri a = Uri.parse("https://www.instagram.com/playquizrr");
                i.setData(a);
                startActivity(i);
                break;
            case R.id.twitterbutton:
                i = new Intent(Intent.ACTION_VIEW);
                a = Uri.parse("https://twitter.com/playquizrr");
                i.setData(a);
                startActivity(i);
                break;
            case R.id.fbbutton:
                i = new Intent(Intent.ACTION_VIEW);
                a = Uri.parse("https://www.facebook.com/playquizrr");
                i.setData(a);
                startActivity(i);
                break;
            case R.id.rulesButton:
                i = new Intent(AboutActivity.this, TermsandPolicyActivity.class);
                startActivity(i);
                break;
            case R.id.ratebutton:
                i = new Intent(Intent.ACTION_VIEW);
                a = Uri.parse("http://bit.ly/playquizrr");
                i.setData(a);
                startActivity(i);
                break;
            case R.id.rulesButtontext:
                i = new Intent(AboutActivity.this, TermsandPolicyActivity.class);
                startActivity(i);
                break;
            case R.id.ratebuttontext:
                i = new Intent(Intent.ACTION_VIEW);
                a = Uri.parse("http://bit.ly/playquizrr");
                i.setData(a);
                startActivity(i);
                break;
            case R.id.logoutbuttontext:
                builder = new AlertDialog.Builder(AboutActivity.this);
                builder.setTitle("Confirm");
                builder.setCancelable(false);
                builder.setMessage("Are you sure you want to Logout?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QuizApp.getPreferenceManager().clearLoginData();

                        Intent over = new Intent(AboutActivity.this, HomeActivity.class);
                        startActivity(over);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
                final AlertDialog dialog2 = builder.create();
                dialog2.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog2.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#000000"));
                        dialog2.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#000000"));

                    }
                });

                dialog2.show();
                break;
            case R.id.howtoplayButtontext:
                i = new Intent(AboutActivity.this, TourActivity.class);
                startActivity(i);
                break;


        }

    }

    public void backbtn(View view) {
        onBackPressed();
    }

}
