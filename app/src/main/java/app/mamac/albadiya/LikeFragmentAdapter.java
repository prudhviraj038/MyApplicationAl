package app.mamac.albadiya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by T on 03-01-2017.
 */

public class LikeFragmentAdapter  extends BaseAdapter{
    LayoutInflater inflater;
    Context context;
    ArrayList<Posts> mimages;

    protected LikeFragmentAdapter(Context context,ArrayList<Posts> images){
        mimages = images;
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
        View item_view = inflater.inflate(R.layout.likes_fragment,null);
        ImageView item_image = (ImageView) item_view.findViewById(R.id.item_image);
        Ion.with(context)
                .load(mimages.get(position).image)
                .withBitmap()
                .placeholder(R.drawable.ic_profile)
                .intoImageView(item_image);
        return item_view;
    }
}
