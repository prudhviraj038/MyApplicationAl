package app.mamac.albadiya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by T on 08-12-2016.
 */

public class InstaContestantsAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
//    ArrayList<Integer> mimages;
//    ArrayList<String>  mnames;
//    ArrayList<String>  mcomments;
    ArrayList<Competitors> competitors;

    public   InstaContestantsAdapter(Context context,ArrayList<Competitors> competitors){
        this.context = context;
        this.competitors =  competitors;
//        mimages = images;
//        mnames  = names;
//        mcomments = comments;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return competitors.size();
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
        View item_view = inflater.inflate(R.layout.insta_contestants,null);
        ImageView item_image = (ImageView) item_view.findViewById(R.id.item_image);
        TextView  item_name  = (TextView) item_view.findViewById(R.id.item_name);
        item_name.setText(competitors.get(position).title);
        Ion.with(context)
                .load(competitors.get(position).image)
                .withBitmap()
                .placeholder(R.drawable.ic_profile)
                .intoImageView(item_image);
        return item_view;
    }
}
