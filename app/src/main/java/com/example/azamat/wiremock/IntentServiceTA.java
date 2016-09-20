package com.example.azamat.wiremock;

import android.app.IntentService;
import android.content.Intent;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.client.WireMock;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentServiceTA extends IntentService {
    public  final String HOST = MainActivity.text1;
    public  final Integer PORT = Integer.parseInt(MainActivity.text2);
    public  final String PATH = MainActivity.text3;
   //public static final String PROTOCOL = "http";

    private String sms;
    private static String TAG = "IntentServiceTA";


    public IntentServiceTA() {
        super("IntentServiceTA");
    }

  @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
     // HOST = intent.getStringExtra("edit1");
    //  PORT = intent.getIntExtra("edit2",8888);
    //  PATH = intent.getStringExtra("edit3");
      sms = intent.getStringExtra("smsIntent");
        try {
            if (!sms.isEmpty() && !HOST.isEmpty() && !PATH.isEmpty() && PORT != null) {
                Log.d(TAG, "sms:  " + sms);
                Log.d(TAG, "host:  " + HOST);
                Log.d(TAG, "port:  " + PORT);
                Log.d(TAG, "PATH:  " + PATH);
                try {
                    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        startServer();
                        Log.d(TAG, "Inside DoBackground");
                        URL url = new URL("http", HOST, PORT, PATH);
                        URLConnection conn = url.openConnection();
                        conn.setDoInput(true);
                        conn.setAllowUserInteraction(true);
                        conn.connect();
                        StringBuilder sb = new StringBuilder();
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        String line;

                        while ((line = in.readLine()) != null) {
                            sb.append(line);
                        }
                        Log.d(TAG, "After request");
                        Log.d(TAG, "Returned String " + sb.toString());
                    }else
                        Toast.makeText(getApplicationContext(),"Turn on Wi-Fi",Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Log.e(TAG, "Exception occurred " + e);
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private   void startServer() {
        try {
            WireMock.configureFor(HOST, PORT);
            stubFor(get(urlEqualTo(PATH))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(sms)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "sms:  " + sms);
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;

         else
            return false;
    }
}


