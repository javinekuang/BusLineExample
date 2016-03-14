package k.javine.buslineexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.ParseException;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = (TextView) findViewById(R.id.result);
        getBusLineData();
    }

    private void getBusLineData() {
        String url = "http://www.zhbuswx.com/BusLine/WS.asmx/SearchLine";
        String url_baidu = "https://www.baidu.com/con?from=zhidao";
        RequestParams params = new RequestParams();
        params.add("key", "22");
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader(HttpHeaders.HOST,"www.zhbuswx.com");
        client.addHeader(HttpHeaders.CONNECTION,"keep-alive");
        client.addHeader(HttpHeaders.CONTENT_LENGTH,"12");
        client.addHeader(HttpHeaders.CONTENT_TYPE,"application/json; charset=UTF-8");
        client.addHeader(HttpHeaders.ACCEPT,"*/*");
        client.addHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        client.addHeader(HttpHeaders.REFERER,"http://www.zhbuswx.com/BusLine/WFRealTimeQuery.aspx");
        client.addHeader(HttpHeaders.ACCEPT_ENCODING,"gzip, deflate");
        client.addHeader(HttpHeaders.ACCEPT_LANGUAGE,"zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");

        client.get(url,params, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                throwable.printStackTrace();
                resultTextView.setText("Error: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                resultTextView.setText(responseString);
                Log.d("Javine", headers.toString());
            }
        });
    }
}
