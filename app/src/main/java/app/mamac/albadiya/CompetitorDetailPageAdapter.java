package app.mamac.albadiya;

import android.content.Context;
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
    ArrayList<Competitors> competiters;

    protected CompetitorDetailPageAdapter(Context context,ArrayList<Competitors> competiters){
        this.context = context;
        this.competiters = competiters;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return competiters.size();
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
        View item_view = inflater.inflate(R.layout.competitors_detail_page_items,null);
        return item_view;
    }
}
