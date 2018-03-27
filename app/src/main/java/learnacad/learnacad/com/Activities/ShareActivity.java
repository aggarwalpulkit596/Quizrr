package learnacad.learnacad.com.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        TextView username = findViewById(R.id.shareuseranme);
        username.setText(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME));
    }


    public void sharetowhatsapp(View view) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey, I am playing live quizzes on Quizrr and winning cash prizes.\n" +
                "\n" +
                "Download Quizrr from http://bit.ly/playquizrr. To earn a free lifeline, join using my referral code \"" + QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME)+"\"");
        try {
            startActivity(Intent.createChooser(whatsappIntent, "Share Code"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ShareActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void share(View view) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey, I am playing live quizzes on Quizrr and winning cash prizes.\n" +
                "\n" +
                "Download Quizrr from http://bit.ly/playquizrr. To earn a free lifeline, join using my referral code \"" + QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME)+"\"");
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ShareActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    public void backbtn(View view) {
        onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

}
