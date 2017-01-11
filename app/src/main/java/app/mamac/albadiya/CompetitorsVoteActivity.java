package app.mamac.albadiya;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by T on 09-01-2017.
 */

public class CompetitorsVoteActivity extends Activity {
    ImageView item_image;
    TextView click_vote;
    ImageView back_btn;
    String image;
    ArrayList<Competitors> competions;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competitors_vote);

        competions = new ArrayList<>();

        item_image = (ImageView) findViewById(R.id.item_image);
        back_btn   = (ImageView) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompetitorsVoteActivity.this.onBackPressed();
            }
        });
        click_vote = (TextView) findViewById(R.id.click_vote);
        click_vote.setTag(1);
        click_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Ion.with(CompetitorsVoteActivity.this)
                        .load(Settings.SERVER_URL + "vote.php")
                        .setBodyParameter("member_id",Settings.GetUserId(CompetitorsVoteActivity.this))
                        .setBodyParameter("image_id","2")
                        .setBodyParameter("competition_id","1")
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result.get("status").getAsString().equals("Success")){
                                    final int status =(Integer) v.getTag();
                                    if(status == 1) {
                                        click_vote.setText("voted");
                                        Toast.makeText(CompetitorsVoteActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                        v.setTag(0);
                                    } else {
                                        click_vote.setText("vote");
                                        v.setTag(1);
                                    }
                                }else{
                                    Toast.makeText(CompetitorsVoteActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        item_image.setImageResource(bundle.getInt("image"));
        //int item_image = bundle.getInt("image");
    }
}
