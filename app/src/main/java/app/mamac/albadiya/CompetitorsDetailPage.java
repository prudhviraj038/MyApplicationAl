package app.mamac.albadiya;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ProgressBar;
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
    Competitors competitors;
    String comp_id="";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competitors_detail_page);
        gridView = (GridView) findViewById(R.id.gallery_images);
        comp_id = getIntent().getStringExtra("id");
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
        back_btn = (ImageView) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompetitorsDetailPage.this.onBackPressed();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompetitorsDetailPage.this,CompetitorAddPost.class);
                startActivity(intent);
            }
        });



       get_competitors_data(comp_id);
    }

     public void get_competitors_data(String comp_id){
         final ProgressDialog progressDialog =  new ProgressDialog(this);
         progressDialog.setMessage("please wait....");
         progressDialog.setCancelable(false);
         progressDialog.show();
         Ion.with(this)
                 .load(Settings.SERVER_URL + "competitions.php")
                 .setBodyParameter("competition_id",comp_id)
                 .asJsonArray()
                 .setCallback(new FutureCallback<JsonArray>() {
                     @Override
                     public void onCompleted(Exception e, JsonArray result) {
                         if (progressDialog!=null)
                             progressDialog.dismiss();
                          JsonObject jsonObject = result.get(0).getAsJsonObject();
                             item_name.setText(String.valueOf(jsonObject.get("title").getAsString()));
                             Ion.with(CompetitorsDetailPage.this)
                                     .load(jsonObject.get("image").getAsString())
                                     .withBitmap()
                                     .placeholder(R.drawable.ic_profile)
                                     .intoImageView(item_image);
                             end_date.setText(jsonObject.get("end_date").getAsString());
                             participants.setText(String.valueOf(jsonObject.get("images").getAsJsonArray().size()));
                         }



                 });
     }



}
