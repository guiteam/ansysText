package tw.org.iii.anysctext;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private  MyTask mytask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public  void text1(View v){
        mytask = new MyTask();
        mytask.execute();

    }
    public  void text2(View v){
        if(mytask!=null&&!mytask.isCancelled()){
            mytask.cancel(true);
        }

    }
                            //大寫Void 泛型實體物件
                            //非同步
    private  class  MyTask extends AsyncTask<Void,Void,Void>{
                                @Override
                                protected void onCancelled(Void aVoid) {
                                    super.onCancelled(aVoid);
                                    Log.d("brad","onCancelled");


                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    Log.d("brad","onPostExecute");
                                    super.onPostExecute(aVoid);
                                }

                                @Override
                                protected void onProgressUpdate(Void... values) {
                                    Log.d("brad","onProgressUpdate");
                                    super.onProgressUpdate(values);
                                }

                                @Override
                                protected Void doInBackground(Void... params) {
                                    Log.d("brad","doInBackground");
                                    return null;
                                }

                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                    Log.d("brad","onPreExecute");
                                }
                            }


}
