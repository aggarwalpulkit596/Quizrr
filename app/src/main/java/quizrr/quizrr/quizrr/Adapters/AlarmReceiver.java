package quizrr.quizrr.quizrr.Adapters;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import quizrr.quizrr.quizrr.Activities.HomeActivity;
import quizrr.quizrr.quizrr.QuizApp;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                int hours = Integer.parseInt(QuizApp.getPreferenceManager().getString(MyPreferenceManager.QUIZHOUR));
                int minutes = Integer.parseInt(QuizApp.getPreferenceManager().getString(MyPreferenceManager.QUIZMIN));
                NotificationScheduler.setReminder(context, AlarmReceiver.class, hours, minutes);
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        NotificationScheduler.showNotification(context, HomeActivity.class,
                "Settle down!", "Game is going to start in 10 min.");

    }
}