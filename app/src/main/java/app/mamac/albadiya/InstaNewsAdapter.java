package app.mamac.albadiya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Created by T on 08-12-2016.
 */

public class InstaNewsAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<String> mitems;
    public InstaNewsAdapter(Context context,ArrayList<String> items){
        this.context = context;
        mitems = items;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mitems.size();
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
        View item_view = inflater.inflate(R.layout.insta_news,null);
        TextView item_news = (TextView) item_view.findViewById(R.id.item_news);
        item_news.setText(mitems.get(position));
        return item_view;
    }
}
