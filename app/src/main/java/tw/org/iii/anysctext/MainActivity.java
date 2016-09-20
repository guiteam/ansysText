package tw.org.iii.anysctext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private  MyTask mytask;
    private TextView mesg;
    private ImageView imegeView;
    private UIHandler handler;
    private Bitmap bmp ;
    // Bitmap 圖片處理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mesg =(TextView)findViewById(R.id.mesg);
        imegeView =(ImageView)findViewById(R.id.imageView);
        handler = new UIHandler();
    }
    public  void text3(View v){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.androidcentral.com/sites/androidcentral.com/files/styles/w700/public/postimages/%5Buid%5D/podcast-ac-new.jpg?itok=tKuELSVP");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection() ;
                    conn.connect();
                    bmp = BitmapFactory.decodeStream(conn.getInputStream());
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {

                }
            }
        }.start();




    }
    private  class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imegeView.setImageBitmap(bmp);
        }
    }


    public  void text1(View v){
        mytask = new MyTask();
        mytask.execute("brad","brad","brad","brad");

    }
    public  void text2(View v){
        if(mytask!=null&&!mytask.isCancelled()){
            mytask.cancel(true);
        }

    }
                            //大寫Void 泛型實體物件
                            //非同步
    private  class  MyTask extends AsyncTask<String,Object,String>{
                                @Override
                                protected void onCancelled(String end) {
                                    super.onCancelled(end);
                                    Log.d("brad","onCancelled:"+end);


                                }

                                @Override
                                protected void onPostExecute(String end) {
                                    Log.d("brad","onPostExecute"+end);
                                    super.onPostExecute(end);
                                }

                                @Override
                                protected void onProgressUpdate(Object... values) {

                                    super.onProgressUpdate(values);
                                    Log.d("brad","onProgressUpdate:");
                                    //物件強制轉型後變成物件
                                    mesg.setText((Integer)values[0]+":"+(String)values[1]+":"+ values[2]);
                                }

                                @Override
                                protected String doInBackground(String... params) {
                                    Log.d("brad","doInBackground");

                                    int i=0 ; boolean isCancel =false ;
                                    i++;
                                    for(String name : params){
                                        if(isCancelled()){
                                            isCancel =true ;
                                            break;
                                        }
                                        Log.d("brad","Hello,"+name);
                                        publishProgress(i,name,i+1000);
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    return isCancel?"Cancel":"Game Over";
                                }

                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                    Log.d("brad","onPreExecute");
                                }
                            }


}
