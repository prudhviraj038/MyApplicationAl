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
 * Created by T on 19-12-2016.
 */

public class CompetitorDetailPageAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<Competitors> competitors;
    ArrayList<Integer>  mbaners;


    protected CompetitorDetailPageAdapter(Context context,ArrayList<Competitors> competitors,ArrayList<Integer> baners){
        this.context = context;
        this.competitors = competitors;
        inflater = LayoutInflater.from(context);
        mbaners = baners;
        Log.e("output",String.valueOf(this.competitors.size()));
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
        final View item_view = inflater.inflate(R.layout.competitors_detail_page_items,null);
//        ImageView image = (ImageView) item_view.findViewById(R.id.image);
//        image.setImageResource(mbaners.get(position));
        TextView  item_title = (TextView) item_view.findViewById(R.id.item_title);
        item_title.setText(competitors.get(position).title);
        return item_view;
    }
}
