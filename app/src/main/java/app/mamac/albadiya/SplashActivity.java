package app.mamac.albadiya;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by mac on 12/10/16.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LanguageActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }




}
