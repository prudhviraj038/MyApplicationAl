package app.mamac.albadiya;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;



import java.util.ArrayList;

/**
 * Created by T on 08-12-2016.
 */

public class InstaContestants extends Fragment {
    ListView listView;
    InstaContestantsAdapter instaContestantsAdapter;
    ArrayList<Integer> images;
    ArrayList<String>  names;
    ArrayList<String>  comments;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.insta_contestants_items,container,false);
        listView = (ListView) view.findViewById(R.id.contestants_items);

        images   = new ArrayList<>();
        names    = new ArrayList<>();
        comments = new ArrayList<>();

        images.add(R.drawable.yellowsoft);
        images.add(R.drawable.housejoy);
        images.add(R.drawable.yahoo);
        images.add(R.drawable.amazon);

        names.add("Yellowsoft");
        names.add("Housejoy");
        names.add("Yahoo");
        names.add("Amazon");

        comments.add("Yellowsoft");
        comments.add("Housejoy");
        comments.add("Yahoo");
        comments.add("Amazon");

        instaContestantsAdapter = new InstaContestantsAdapter(getActivity(),images,names,comments);
        listView.setAdapter(instaContestantsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),names.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
