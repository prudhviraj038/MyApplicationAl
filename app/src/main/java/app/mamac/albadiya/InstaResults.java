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

public class InstaResults extends Fragment {
    InstaResultsAdapter instaResultsAdapter;
    GridView gridView;
    ArrayList<String> name;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.insta_results_items,container,false);
        gridView = (GridView) view.findViewById(R.id.results_items);

        name = new ArrayList<>();

        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");
        name.add("Results");

        instaResultsAdapter = new InstaResultsAdapter(getActivity(),name);
        gridView.setAdapter(instaResultsAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),name.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
