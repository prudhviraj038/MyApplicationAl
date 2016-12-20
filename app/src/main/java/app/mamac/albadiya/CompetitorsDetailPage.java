package app.mamac.albadiya;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by T on 19-12-2016.
 */

public class CompetitorsDetailPage extends Activity {
    GridView gridView;
    CompetitorDetailPageAdapter competitorDetailPageAdapter;
    ImageView back_btn;
    TextView item_name;
    ImageView item_image;
    TextView end_date;
    TextView participants;
    TextView add_btn;
    ArrayList<Competitors> competitersfrom_api;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competitors_detail_page);
        gridView = (GridView) findViewById(R.id.gallery_images);
        competitersfrom_api = new ArrayList<>();
        competitorDetailPageAdapter = new CompetitorDetailPageAdapter(this,competitersfrom_api);
        gridView.setAdapter(competitorDetailPageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        item_name = (TextView) findViewById(R.id.item_name);
        item_image = (ImageView) findViewById(R.id.item_image);
        end_date  = (TextView) findViewById(R.id.end_date);
        participants = (TextView) findViewById(R.id.participants);
        add_btn   = (TextView) findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        get_competitors_data();
    }

     public void get_competitors_data(){
         Ion.with(this)
                 .load(Settings.SERVER_URL + "competitions.php")
                 .asJsonArray()
                 .setCallback(new FutureCallback<JsonArray>() {
                     @Override
                     public void onCompleted(Exception e, JsonArray result) {
                         JsonObject jsonObject = result.get(0).getAsJsonObject();
                         item_name.setText(jsonObject.get("title").getAsString());
                         Ion.with(CompetitorsDetailPage.this)
                                 .load(jsonObject.get("image").getAsString())
                                 .withBitmap()
                                 .placeholder(R.drawable.ic_profile)
                                 .intoImageView(item_image);
                         end_date.setText(jsonObject.get("end_date").getAsString());


                     }
                 });
     }



}
