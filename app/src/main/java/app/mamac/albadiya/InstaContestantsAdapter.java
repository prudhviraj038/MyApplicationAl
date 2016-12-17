package app.mamac.albadiya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Created by T on 08-12-2016.
 */

public class InstaContestantsAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<Integer> mimages;
    ArrayList<String>  mnames;
    ArrayList<String>  mcomments;

    public   InstaContestantsAdapter(Context context,ArrayList<Integer> images,ArrayList<String> names,ArrayList<String> comments){
        this.context = context;
        mimages = images;
        mnames  = names;
        mcomments = comments;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mnames.size();
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
        TextView  name       = (TextView) item_view.findViewById(R.id.name);
        item_image.setImageResource(mimages.get(position));
        item_name.setText(mnames.get(position));
        name.setText(mcomments.get(position));
        return item_view;
    }
}
