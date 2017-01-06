package app.mamac.albadiya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by T on 23-12-2016.
 */

public class ChatScreenAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<Chats> chats;

    public ChatScreenAdapter(Context context,ArrayList<Chats> chats){
        this.context = context;
        this.chats = chats;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return chats.size();
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
        final View item_view = inflater.inflate(R.layout.chatlist_item,null);
        TextView description = (TextView) item_view.findViewById(R.id.description);
        description.setText(chats.get(position).description);
        return item_view;
    }
}
