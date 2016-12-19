package app.mamac.albadiya;

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

public class CompetitorsDetailPage extends Fragment {
    GridView gridView;
    CompetitorDetailPageAdapter competitorDetailPageAdapter;
    ImageView back_btn;
    TextView item_name;
    ImageView item_image;
    TextView end_date;
    TextView participants;
    ArrayList<Competitors> competitersfrom_api;
    @Override
    public View onCreateView(final LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.competitors_detail_page,container,false);
        gridView = (GridView) view.findViewById(R.id.gallery_images);
        competitersfrom_api = new ArrayList<>();

        competitorDetailPageAdapter = new CompetitorDetailPageAdapter(getActivity(),competitersfrom_api);
        gridView.setAdapter(competitorDetailPageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        get_competitors_items();
        return view;
    }


    private void get_competitors_items(){
        Ion.with(getActivity())
                .load(Settings.SERVER_URL +"conpetitions.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        JsonObject jsonObject = result.get(0).getAsJsonObject();
                        item_name.setText(jsonObject.get("titile").getAsString());

                    }
                });
    }
}
