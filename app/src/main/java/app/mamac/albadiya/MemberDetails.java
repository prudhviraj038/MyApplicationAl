package app.mamac.albadiya;

import android.content.Context;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by T on 01-02-2017.
 */

public class MemberDetails  {
    public String id,name,email,phone,image,total_following,total_post_likes;
    public ArrayList<Posts> posts;

    public MemberDetails(JsonObject jsonObject, Context context){
        id = jsonObject.get("id").getAsString();
        name = jsonObject.get("name").getAsString();
        email = jsonObject.get("email").getAsString();
        phone = jsonObject.get("phone").getAsString();
        image = jsonObject.get("image").getAsString();
        total_following = jsonObject.get("total_following").getAsString();
        total_post_likes = jsonObject.get("total_post_likes").getAsString();
        posts = new ArrayList<>();
        for(int i=0;i<jsonObject.get("posts").getAsJsonArray().size();i++){

            Posts mem_posts = new Posts(jsonObject.get("posts").getAsJsonArray().get(i).getAsJsonObject(),context);

            posts.add(mem_posts);

        }

    }

    public class Posts{
        public  String id,title,title_ar,image,video,views,likes,member_liked,description,description_ar,time,time_ar;
        public ArrayList<PostItems> following;

        public Posts(JsonObject jsonObject,Context context){
            id = jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            image = jsonObject.get("image").getAsString();
            video = jsonObject.get("video").getAsString();
            views = jsonObject.get("views").getAsString();
            likes = jsonObject.get("likes").getAsString();
            member_liked = jsonObject.get("member_liked").getAsString();
            description = jsonObject.get("description").getAsString();
            description_ar = jsonObject.get("description_ar").getAsString();
            time = jsonObject.get("time").getAsString();
            time_ar = jsonObject.get("time_ar").getAsString();
            following = new ArrayList<>();
            for (int i=0;i<jsonObject.get("following").getAsJsonArray().size();i++){
                PostItems mem_following = new PostItems(jsonObject.get("following").getAsJsonArray().get(i).getAsJsonObject(),context);
                following.add(mem_following);
            }
        }

    }

    public class PostItems{
        public String id,name;
        public ArrayList<PostItemsAdapter> likes;

        public PostItems(JsonObject jsonObject,Context context){
            id = jsonObject.get("id").getAsString();
            name = jsonObject.get("name").getAsString();
            likes = new ArrayList<>();
            for (int i=0;i<jsonObject.get("post_likes").getAsJsonArray().size();i++){
                PostItemsAdapter mem_likes = new PostItemsAdapter(jsonObject.get("post_likes").getAsJsonArray().get(i).getAsJsonObject(),context);
                likes.add(mem_likes);
            }
        }
    }

    public class PostItemsAdapter{
        public String id,title,title_ar,image,video,views,likes,description,description_ar,user_id,user_name,user_image;
        public PostItemsAdapter(JsonObject jsonObject,Context context){
            id= jsonObject.get("id").getAsString();
            title = jsonObject.get("title").getAsString();
            title_ar = jsonObject.get("title_ar").getAsString();
            image = jsonObject.get("image").getAsString();
            video = jsonObject.get("video").getAsString();
            views = jsonObject.get("views").getAsString();
            likes = jsonObject.get("likes").getAsString();
            description = jsonObject.get("description").getAsString();
            description_ar = jsonObject.get("description_ar").getAsString();
            user_id = jsonObject.get("posted_by").getAsJsonObject().get("id").getAsString();
            user_image = jsonObject.get("posted_by").getAsJsonObject().get("image").getAsString();
            user_name = jsonObject.get("posted_by").getAsJsonObject().get("name").getAsString();


        }
    }

}
