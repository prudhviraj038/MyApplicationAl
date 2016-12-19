package app.mamac.albadiya;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by T on 19-12-2016.
 */

public class Competitors implements Serializable{

    public String id,title,title_ar,description,description_ar,end_date,images;

    public Competitors(JsonObject jsonObject, Context context){
        id             = jsonObject.get("id").getAsString();
        title          = jsonObject.get("title").getAsString();
        title_ar       = jsonObject.get("title_ar").getAsString();
        description    = jsonObject.get("description").getAsString();
        description_ar = jsonObject.get("description_ar").getAsString();
        end_date       = jsonObject.get("end_date").getAsString();


    }

}
