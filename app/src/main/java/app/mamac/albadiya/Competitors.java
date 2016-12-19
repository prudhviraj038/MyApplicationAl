package app.mamac.albadiya;

import android.content.Context;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by T on 19-12-2016.
 */

public class Competitors implements Serializable{

    public String id,title,title_ar,description,description_ar,end_date,image;
    public ArrayList<Images> images;
//fsfsdf
    public Competitors(JsonObject jsonObject, Context context){
        id             = jsonObject.get("id").getAsString();
        title          = jsonObject.get("title").getAsString();
        title_ar       = jsonObject.get("title_ar").getAsString();
        description    = jsonObject.get("description").getAsString();
        description_ar = jsonObject.get("description_ar").getAsString();
        end_date       = jsonObject.get("end_date").getAsString();
        image = jsonObject.get("image").getAsString();
        images = new ArrayList<>();
        for(int i=0;i<jsonObject.get("images").getAsJsonArray().size();i++){

            Images comp_image = new Images(jsonObject.get("images").getAsJsonArray().get(i).getAsJsonObject(),context);

            images.add(comp_image);

        }


    }

    private class Images implements  Serializable{

        public String id,title,description,image,mid,mname,mimage;

        public Images(JsonObject jsonObject,Context context){

            id             = jsonObject.get("id").getAsString();
            title          = jsonObject.get("title").getAsString();
            description    = jsonObject.get("description").getAsString();
            image = jsonObject.get("image").getAsString();
            mid            = jsonObject.get("member").getAsJsonObject().get("id").getAsString();
            mname          = jsonObject.get("member").getAsJsonObject().get("name").getAsString();
            mimage            = jsonObject.get("member").getAsJsonObject().get("image").getAsString();
        }
    }

}
