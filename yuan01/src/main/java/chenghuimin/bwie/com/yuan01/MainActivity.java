package chenghuimin.bwie.com.yuan01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cricle cricle=new Cricle(getApplicationContext());
        cricle.HttpDong(new Cricle.getName() {
            @Override
            public Void Name(View view) {
                return null;
            }
        });
    }
}
