package app.mamac.albadiya;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.ArrayList;

/**
 * Created by T on 07-12-2016.
 */

public class InstaSearchAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<String> mimages;
    ArrayList<String> mtitle;
    public InstaSearchAdapter(Context context, ArrayList<String> images,ArrayList<String> title){
        this.context = context;
        mimages = images;
        mtitle  = title;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mtitle.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View item_view = inflater.inflate(R.layout.insta_search,null);

        ImageView item_image = (ImageView) item_view.findViewById(R.id.item_image);
        TextView title  = (TextView) item_view.findViewById(R.id.title);
        title.setText(mtitle.get(position));

        Picasso.with(context).load(new File(mimages.get(position))).fit().into(item_image);

        return item_view;
    }


    /**
     * Created by T on 07-12-2016.
     */

}
