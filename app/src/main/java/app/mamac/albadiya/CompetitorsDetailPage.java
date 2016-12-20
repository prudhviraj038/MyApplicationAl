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
    String comp_id;
    String title;
    String image;
    String date;
    String participant;
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

       if (getIntent()!=null){
           comp_id = getIntent().getStringExtra("id");
           title = getIntent().getStringExtra("title");
           item_name.setText(title);
           date  = getIntent().getStringExtra("end_date");
           end_date.setText(date);
           image = getIntent().getStringExtra("image");
           Ion.with(this)
                   .load(image)
                   .withBitmap()
                   .placeholder(R.drawable.ic_profile)
                   .intoImageView(item_image);
           participant= getIntent().getStringExtra("participants");
           participants.setText(participant);

       }
    }
}
