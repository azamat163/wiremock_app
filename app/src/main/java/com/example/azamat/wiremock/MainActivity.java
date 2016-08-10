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
        but.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                text1 = edit1.getText().toString();
                if (!text1.isEmpty()) {
                    SharedPreferences pref1 = getSharedPreferences("main", MODE_PRIVATE);
                    Editor editPref1 = pref1.edit();
                    editPref1.putString("edit1", text1);
                    editPref1.commit();
                }

                text2 = edit2.getText().toString();
                if (!text2.isEmpty()) {
                    SharedPreferences pref2 = getSharedPreferences("main", MODE_PRIVATE);
                    Editor editPref2 = pref2.edit();
                    editPref2.putString("edit2", text2);
                    editPref2.commit();
                }

                text3 = edit3.getText().toString();
                if (!text3.isEmpty()) {
                    SharedPreferences pref3 = getSharedPreferences("main", MODE_PRIVATE);
                    Editor editPref3 = pref3.edit();
                    editPref3.putString("edit3", text3);
                    editPref3.commit();
                }

                text4 = edit4.getText().toString();
                if (!text4.isEmpty()) {
                    SharedPreferences pref4 = getSharedPreferences("main", MODE_PRIVATE);
                    Editor editPref4 = pref4.edit();
                    editPref4.putString("edit4", text4);
                    editPref4.commit();
                }

                Log.d(TAG, "Text1 " + text1);
                Log.d(TAG, "Text2 " + text2);
                Log.d(TAG, "Text3 " + text3);
                Log.d(TAG, "Text4 " + text4);
                if (text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty())
                    Toast.makeText(getApplicationContext(), "Не заполнены все поля!", Toast.LENGTH_LONG).show();
                SharedPreferences pref = getSharedPreferences("main", MODE_PRIVATE);
                editText1 = pref.getString("edit1", "").toString();
                edit1.setText(editText1);
                editText2 = pref.getString("edit2", "").toString();
                edit2.setText(editText2);
                editText3 = pref.getString("edit3", "").toString();
                edit3.setText(editText3);
                editText4 = pref.getString("edit4", "").toString();
                edit4.setText(editText4);

                Log.d(TAG, "editText1 " + editText1);
                Log.d(TAG, "editText2 " + editText2);
                Log.d(TAG, "editText3 " + editText3);
                Log.d(TAG, "editText4 " + editText4);

            }
        });


        if (!sms.isEmpty()) {
            Intent msgIntent = new Intent(MainActivity.this, IntentServiceTA.class);
            startService(msgIntent);
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
