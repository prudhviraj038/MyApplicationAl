package app.mamac.albadiya;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import static android.R.attr.action;

/**
 * Created by T on 12-12-2016.
 */

public class LikeFragment extends Fragment {
    TextView no_of_likes;
    LikeFragmentAdapter likeFragmentAdapter;
    ArrayList<Integer> images;
    GridView gridView;
    ImageView back_btn;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.likes_fragment,container,false);

        gridView = (GridView) view.findViewById(R.id.like_images);
        item_image = (ImageView) view.findViewById(R.id.item_image);
        item_name  = (TextView) view.findViewById(R.id.item_name);
        back_btn   = (ImageView) view.findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        postsfrom_api = new ArrayList<>();
        images = new ArrayList<>();
        images.add(R.drawable.banner3);
        images.add(R.drawable.timeline);
        images.add(R.drawable.amazon);
        images.add(R.drawable.banner1);
        images.add(R.drawable.banner);

        likeFragmentAdapter = new LikeFragmentAdapter(getActivity(),postsfrom_api);
        gridView.setAdapter(likeFragmentAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        no_of_likes = (TextView) view.findViewById(R.id.no_of_likes);
        get_member_details();
        return view;

    }

    ImageView item_image;
    TextView item_name;



    ArrayList<Posts> postsfrom_api;
    public void get_member_details(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String url = Settings.SERVER_URL+"member-details.php";
        Ion.with(getActivity())
                .load(url)
                .setBodyParameter("member_id",Settings.GetUserId(getActivity()))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        try {
                            JsonObject jsonObject = result.get(0).getAsJsonObject();
                            item_name.setText(jsonObject.get("name").getAsString());
                            no_of_likes.setText(String.valueOf(jsonObject.get("post_likes").getAsJsonArray().size()));
                            JsonArray posts_aray = jsonObject.get("posts").getAsJsonArray();
                            for (int i = 0; i < posts_aray.size(); i++) {
                                Posts posts = new Posts(posts_aray.get(i).getAsJsonObject(), getActivity());
                                postsfrom_api.add(posts);
                            }
                            likeFragmentAdapter.notify();

                            Ion.with(getActivity())
                                    .load(jsonObject.get("image").getAsString())
                                    .withBitmap()
                                    .placeholder(R.drawable.ic_profile)
                                    .intoImageView(item_image);

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });
    }


    }

