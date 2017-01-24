package app.mamac.albadiya;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by T on 03-01-2017.
 */

public class FollowingFragmentAdapter  extends BaseAdapter{
    LayoutInflater inflater;
    Context context;
    //ArrayList<Posts> mimages;
    ArrayList<Integer> mimages;
    ArrayList<String> mnames;

    protected FollowingFragmentAdapter(Context context,ArrayList<Integer> images,ArrayList<String> names){
        mimages = images;
        mnames = names;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mimages.size();
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
        View item_view = inflater.inflate(R.layout.following_list,null);
        ImageView user_image = (ImageView) item_view.findViewById(R.id.user_image);
        user_image.setImageResource(mimages.get(position));
        TextView name = (TextView) item_view.findViewById(R.id.name);
        name.setText(mnames.get(position));
        return item_view;
    }
}
