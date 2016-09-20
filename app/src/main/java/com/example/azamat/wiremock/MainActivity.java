package com.example.azamat.wiremock;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;


public class MainActivity extends Activity {
    public static final String MODE_TT = "tt";
    private static String TAG = "MainActivity";
    private static String sms = "";
    public static String editText1;
    public static String editText2;
    public static String editText3;
    public static String editText4;
    public static String text1;
    public static String text2;
    public static String text3;
    public static String text4;

    Button but;
    Button butClean;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;


    public static void setsmsDetails(String SMSBody) {

        sms = SMSBody;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = (EditText) findViewById(R.id.editText);
        edit2 = (EditText) findViewById(R.id.editText2);
        edit3 = (EditText) findViewById(R.id.editText3);
        edit4 = (EditText) findViewById(R.id.editText4);
        but = (Button) findViewById(R.id.button);
        //butClean = (Button) findViewById(R.id.button2);
        but.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                SharedPreferences  pref = getSharedPreferences("main", MODE_PRIVATE);
                Editor editPref = pref.edit();

                text1 = edit1.getText().toString();
                if (!text1.isEmpty()) {
                    editPref.putString("edit1", text1);
                }

                text2 = edit2.getText().toString();
                if (!text2.isEmpty()) {
                    editPref.putString("edit2", text2);
                }

                text3 = edit3.getText().toString();
                if (!text3.isEmpty()) {
                    editPref.putString("edit3", text3);
                }

                text4 = edit4.getText().toString();
                if (!text4.isEmpty()) {
                    editPref.putString("edit4", text4);
                }
                editPref.commit();
                Log.d(TAG, "host " + text1);
                Log.d(TAG, "port " + text2);
                Log.d(TAG, "path " + text3);
                Log.d(TAG, "receiver " + text4);

                if (text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Не заполнены все поля!", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(), "Введенные значения сохранены!", Toast.LENGTH_LONG).show();
            }


        });
        SharedPreferences  pref = getSharedPreferences("main", MODE_PRIVATE);
        editText1 = pref.getString("edit1", "").toString();
        if (!editText1.isEmpty()) {
            edit1.setText(editText1);
      }
        editText2 = pref.getString("edit2", "").toString();
        if (!editText2.isEmpty()) {
            edit2.setText(editText2);
        }

        editText3 = pref.getString("edit3", "").toString();
        if (!editText3.isEmpty()) {
            edit3.setText(editText3);
        }
        editText4 = pref.getString("edit4", "").toString();
        if (!editText4.isEmpty()) {
            edit4.setText(editText4);
        }

        Log.d(TAG, "host " + editText1);
        Log.d(TAG, "port " + editText2);
        Log.d(TAG, "path " + editText3);
        Log.d(TAG, "receiver " + editText4);



/*
       butClean.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("main", MODE_PRIVATE);
                Editor edit = pref.edit();
                 edit.remove("edit1");
                 edit.remove("edit2");
                 edit.remove("edit3");
                 edit.remove("edit4");
                 edit.commit();
                Toast.makeText(getApplicationContext(), "Успешно очищены поля!", Toast.LENGTH_LONG).show();
            }
        });
*/
    try{
        if (!sms.isEmpty()) {
            //Intent i = new Intent();
          // i.setAction("com.example.azamat.wiremock.phone");
          //  i.putExtra(MODE_TT, editText4);
          //  sendBroadcast(i);

            Intent msgIntent = new Intent(this, IntentServiceTA.class);
            msgIntent.putExtra("edit1", editText1);
            msgIntent.putExtra("edit2", editText2);
            msgIntent.putExtra("edit3", editText3);
            startService(msgIntent);
        }

    }catch(Exception e) {
        e.printStackTrace();
    }
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
