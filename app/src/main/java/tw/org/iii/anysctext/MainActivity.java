package tw.org.iii.anysctext;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  MyTask mytask;
    private TextView mesg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mesg =(TextView)findViewById(R.id.mesg);
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
    private  class  MyTask extends AsyncTask<String,Object,Void>{
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
                                protected void onProgressUpdate(Object... values) {

                                    super.onProgressUpdate(values);
                                    Log.d("brad","onProgressUpdate:");
                                    mesg.setText((Integer)values[0]+":"+(String)values[1]+":"+ values[2]);
                                }

                                @Override
                                protected Void doInBackground(String... params) {
                                    Log.d("brad","doInBackground");

                                    int i=0 ;
                                    i++;
                                    for(String name : params){
                                        Log.d("brad","Hello,"+name);
                                        publishProgress(i,name,i+1000);
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    return null;
                                }

                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                    Log.d("brad","onPreExecute");
                                }
                            }


}
