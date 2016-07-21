package com.example.azamat.wiremock;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.util.Log;


/**
 * Created by azamat on 21.10.15.
 */
public class SmsReceiver extends BroadcastReceiver
{  final String LOG_TAG = "SmsReceiver";
    public static final String phoneNumber = MainActivity.editText4;

    @Override
    public void onReceive(Context context, Intent intent) {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String messageReceived = "";
        String phone = "";
        if (bundle != null && intent !=null && intent.getAction() != null) {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                phone = msgs[i].getOriginatingAddress ();
                if (phoneNumber.equals(phone)) {
                    messageReceived += msgs[i].getMessageBody().toString();
                    messageReceived += "\n";
                    MainActivity.setsmsDetails(messageReceived);
                    IntentServiceTA.setSMSGetails(messageReceived);
                    Intent msgIntent = new Intent(context, IntentServiceTA.class);
                    intent.putExtra("sms", messageReceived);
                    context.startService(msgIntent);
                }
            }


            //---display the new SMS message---
            Log.d(LOG_TAG, "smsphone "  + messageReceived);
            Log.d(LOG_TAG, "phonenumber "  + phone);
            Log.d(LOG_TAG,"phoneNumberconst "  + phoneNumber);
            Toast.makeText(context, messageReceived, Toast.LENGTH_LONG).show();

            // Get the Sender Phone Number

           // String senderPhoneNumber=msgs[0].getOriginatingAddress ();

          Toast.makeText(context, phone, Toast.LENGTH_LONG).show();

        }
    }
}