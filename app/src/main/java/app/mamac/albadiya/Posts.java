package app.mamac.albadiya;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.visibility_utils.items.ListItem;

import java.io.IOException;
import java.io.Serializable;

import app.mamac.albadiya.video_list_demo.adapter.items.BaseVideoItem;
import app.mamac.albadiya.video_list_demo.adapter.items.ItemFactory;


/**
 * Created by T on 26-11-2016.
 */

public class Posts implements Serializable,ListItem {

    public String id,title,title_ar,image,video,description,description_ar,member_like,total_likes,total_views,user_image,user_id,user_name,time;
    public BaseVideoItem videoItem;

    public Posts(JsonObject jsonObject, Context context){

    }
    public Posts(JsonObject jsonObject, Context context, Activity activity, VideoPlayerManager<MetaData> playerManager){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();
        image = jsonObject.get("image").getAsString();

        //video = jsonObject.get("video").getAsString();
        video = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

        description = jsonObject.get("description").getAsString();
        description_ar = jsonObject.get("description_ar").getAsString();
        if(jsonObject.has("member_like"))
            member_like = jsonObject.get("member_like").getAsString();
        else
            member_like = "0";

        if(jsonObject.has("total_likes"))
            total_likes = jsonObject.get("total_likes").getAsString();
        else
            total_likes = "0";
        user_id = "0";
        user_image = "no-image";
        user_name = "no-name";

        try{
            if(jsonObject.has("views"))
                total_views = jsonObject.get("views").getAsString();
            else
                total_views = "99";

            user_id = jsonObject.get("posted").getAsJsonObject().get("id").getAsString();
            user_image = jsonObject.get("posted").getAsJsonObject().get("image").getAsString();
            user_name = jsonObject.get("posted").getAsJsonObject().get("name").getAsString();



        }catch (Exception ex){
            ex.printStackTrace();
        }

        time = jsonObject.get("time").getAsString();
        try {
            videoItem = ItemFactory.createItemFromAsset(video,user_image,activity,playerManager);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getVisibilityPercents(View view) {

        return 0;
    }

    @Override
    public void setActive(View newActiveView, int newActiveViewPosition) {

    }

    @Override
    public void deactivate(View currentView, int position) {

    }
}
