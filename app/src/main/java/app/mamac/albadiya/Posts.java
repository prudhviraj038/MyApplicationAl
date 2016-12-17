package app.mamac.albadiya;

import android.content.Context;
import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;



/**
 * Created by T on 26-11-2016.
 */

public class Posts {

    public String id,title,title_ar,image,description,description_ar,member_like,total_likes,total_views,user_image,user_id,user_name;

    public Posts(JsonObject jsonObject,Context context){
        id = jsonObject.get("id").getAsString();
        title = jsonObject.get("title").getAsString();
        title_ar = jsonObject.get("title_ar").getAsString();
        image = jsonObject.get("image").getAsString();
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

        try{
            if(jsonObject.has("views"))
                total_views = jsonObject.get("views").getAsString();
            else
                total_views = "99";

        }catch (Exception ex){
            ex.printStackTrace();
        }

        if(jsonObject.has("total_likes"))
        {
            user_id = jsonObject.get("posted").getAsJsonObject().get("id").getAsString();
            user_image = jsonObject.get("posted").getAsJsonObject().get("image").getAsString();
            user_name = jsonObject.get("posted").getAsJsonObject().get("name").getAsString();

        }else{
            user_id = "0";
            user_image = "no-image";
            user_name = "no-name";

        }


    }
}
