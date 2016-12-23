package app.mamac.albadiya;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by T on 23-12-2016.
 */

public class ChatScreen extends Activity {
    ListView listView;
    ChatScreenAdapter chatScreenAdapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlist_screen);
        listView = (ListView) findViewById(R.id.chat_list);
        chatScreenAdapter = new ChatScreenAdapter(this);
        listView.setAdapter(chatScreenAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
}
