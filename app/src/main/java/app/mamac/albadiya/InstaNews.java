package app.mamac.albadiya;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;



import java.util.ArrayList;

/**
 * Created by T on 08-12-2016.
 */

public class InstaNews extends Fragment {
    GridView gridView;
    InstaNewsAdapter instaNewsAdapter;
    ArrayList<String> items;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.insta_results_items,container,false);
        gridView = (GridView) view.findViewById(R.id.results_items);

        items = new ArrayList<>();

        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");
        items.add("News");

        instaNewsAdapter = new InstaNewsAdapter(getActivity(),items);
        gridView.setAdapter(instaNewsAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),items.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
