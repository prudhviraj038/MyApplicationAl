package app.mamac.albadiya;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import static android.R.attr.action;
import static android.R.attr.name;

/**
 * Created by T on 12-12-2016.
 */

public class FollowingFragment extends Fragment {
    FollowingFragmentAdapter followingFragmentAdapter;
    ArrayList<Integer> images;
    ArrayList<String> names;
    ListView listView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.following_listview,container,false);
        listView = (ListView) view.findViewById(R.id.following_items);

        postsfrom_api = new ArrayList<>();
        images = new ArrayList<>();
        names = new ArrayList<>();

        images.add(R.drawable.ic_profile);
        images.add(R.drawable.ic_profile);
        images.add(R.drawable.ic_profile);

        names.add("banner");
        names.add("timeline");
        names.add("yellowsoft");

        followingFragmentAdapter = new FollowingFragmentAdapter(getActivity(),images,names);
        listView.setAdapter(followingFragmentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return view;

    }

    ArrayList<Posts> postsfrom_api;

}

