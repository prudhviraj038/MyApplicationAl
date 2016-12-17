package app.mamac.albadiya;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.color;
import static android.R.attr.description;
import static android.R.attr.lines;
import static android.R.attr.popupAnimationStyle;
import static android.R.attr.resource;
import static android.R.attr.theme;

/**
 * Created by T on 08-12-2016.
 */

public class HomeProfileAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<Posts> posts;
    HashMap<Integer,Boolean> flags;
    HashMap<Integer,Integer> likes;
    HomeProfile homeProfile;
//    ArrayList<String> mnames;
//    ArrayList<Integer> mimages;


   // public HomeProfileAdapter(Context context,ArrayList<Integer> profile_image,ArrayList<String> names,ArrayList<Integer> images,ArrayList<Integer> bg_images){
     public HomeProfileAdapter(Context context,ArrayList<Posts> posts,HomeProfile homeProfile){
        this.context = context;
         this.posts = posts;
         this.homeProfile = homeProfile;
         inflater = LayoutInflater.from(context);
         flags = new HashMap<>();
         likes = new HashMap<>();
         Log.e("flagvalue",String.valueOf(this.posts.size()));

         for(int i =0;i<this.posts.size();i++){
                likes.put(i,Integer.parseInt(posts.get(i).total_likes));
             if(posts.get(i).member_like.equals("0")){
                 flags.put(i,Boolean.FALSE);
             }

             else {
                 flags.put(i, Boolean.TRUE);
             }

             Log.e("flagvalue",String.valueOf(flags.get(i)));
         }
//        mnames = names;
//        mimages = images;
     }
    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final  View item_view = inflater.inflate(R.layout.home_fragment,null);
        TextView  item_title  = (TextView) item_view.findViewById(R.id.item_title);
        TextView  description = (TextView) item_view.findViewById(R.id.description);
        final TextView  no_of_likes = (TextView) item_view.findViewById(R.id.no_of_likes);
        TextView  no_of_views = (TextView) item_view.findViewById(R.id.no_of_views);

        final ImageView item_image   = (ImageView) item_view.findViewById(R.id.item_image);
        final ImageView user_image   = (ImageView) item_view.findViewById(R.id.user_image);
//        item_image.setImageResource(mprofile_image.get(position));

        item_title.setText(posts.get(position).title);
        description.setText(posts.get(position).description);
        no_of_likes.setText(posts.get(position).total_likes);
        no_of_views.setText(posts.get(position).total_views);
        Ion.with(context)
                .load(posts.get(position).image)
                .withBitmap()
                .placeholder(R.drawable.amazon)
                .intoImageView(item_image);

        Ion.with(context)
                .load(posts.get(position).user_image)
                .withBitmap()
                .placeholder(R.drawable.ic_profile)
                .intoImageView(user_image);

        Picasso.with(context).load(posts.get(position).user_image).into(user_image);

        no_of_views.setText(posts.get(position).total_views);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            item_title.setTransitionName("trans_text" + position);
            item_image.setTransitionName("trans_image" + position);
        }

        final ImageView user_like = (ImageView) item_view.findViewById(R.id.user_like);
        if (flags.get(position) ) {
            user_like.setBackgroundResource(R.drawable.heart);
        }
        else {
            user_like.setBackgroundResource(R.drawable.ic_likes_vi);

        }
        user_like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                flags.put(position,!flags.get(position));

                if (flags.get(position) ) {
                    user_like.setBackgroundResource(R.drawable.heart);
                    likes.put(position,likes.get(position)+1);
                    no_of_likes.setText(String.valueOf(likes.get(position)));
                }
                else {
                    user_like.setBackgroundResource(R.drawable.ic_likes_vi);
                    likes.put(position,likes.get(position)-1);
                    no_of_likes.setText(String.valueOf(likes.get(position)));

                }

                    Ion.with(context)
                            .load(Settings.SERVER_URL+"like.php")
                            .setBodyParameter("post_id",posts.get(position).id)
                            .setBodyParameter("member_id",Settings.GetUserId(context))
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {

                                }
                            });
                }
        });



        ImageView share_it = (ImageView) item_view.findViewById(R.id.share_it);

        share_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,posts.get(position).title);
                i.putExtra(Intent.EXTRA_TEXT, posts.get(position).image);
                context.startActivity(Intent.createChooser(i,"Share via"));
            }
        });
    user_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            homeProfile.go_to_user_profile(posts.get(position).user_id);
        }
    });
        item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                homeProfile.go_to_user_profile(posts.get(position).user_id);
            }
        });


        return item_view;
    }


}
