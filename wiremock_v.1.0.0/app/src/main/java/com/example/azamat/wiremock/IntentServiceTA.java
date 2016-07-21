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



/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentServiceTA extends IntentService {
    public static final String HOST = MainActivity.editText1;
    public static final Integer PORT = Integer.parseInt(MainActivity.editText2);
    public static final String PATH = MainActivity.editText3;
   //public static final String PROTOCOL = "http";

    private static String sms = "";
    private static String TAG = "IntentServiceTA";



    public static void setSMSGetails( String SMSBody) {
        sms = SMSBody;
    }


    public IntentServiceTA() {
        super("IntentServiceTA");
    }

  @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
       // String host = intent.getStringExtra(HOST);
    //  String path = intent.getStringExtra(PATH);
       //int port = intent.getIntExtra("port", PORT);
       //String http = intent.getStringExtra(PROTOCOL);
        try {
            if (!sms.isEmpty() && !HOST.isEmpty() && !PATH.isEmpty() && PORT != null) {
                startServer();
                try {
                    if (isConnected()) {
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
                    }
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

    private  void startServer() {
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


