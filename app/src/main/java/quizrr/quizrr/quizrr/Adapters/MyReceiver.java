package quizrr.quizrr.quizrr.Adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;


public class MyReceiver extends BroadcastReceiver {

    private static SmsListener mListener;
    String abcd;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                abcd = messageBody.replaceAll("[^0-9]", "");
                mListener.messageReceived(abcd);
            }
        }


    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

    public static void unbindListener() {
        mListener = null;
    }
}
