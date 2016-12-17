package app.mamac.albadiya;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;



/**
 * Created by T on 08-12-2016.
 */

public class InstaCategories extends Fragment {
    TextView contestants,subscribe,results,news;
    FrameLayout cat_fragment;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.insta_categories,container,false);
        contestants    = (TextView) view.findViewById(R.id.contestants);
        subscribe      = (TextView) view.findViewById(R.id.subscribe);
        results        = (TextView) view.findViewById(R.id.results);
        news           = (TextView) view.findViewById(R.id.news);
        cat_fragment   = (FrameLayout) view.findViewById(R.id.cat_fragment);

        contestants.setBackgroundColor(Color.parseColor("#ffffff"));
        subscribe.setBackgroundColor(Color.parseColor("#f6ef98"));
        results.setBackgroundColor(Color.parseColor("#f6ef98"));
        news.setBackgroundColor(Color.parseColor("#f6ef98"));
        InstaContestants instaContestants = new InstaContestants();
        getFragmentManager().beginTransaction().replace(R.id.cat_fragment,instaContestants).commit();

        contestants.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                contestants.setBackgroundColor(Color.parseColor("#ffffff"));
                subscribe.setBackgroundColor(Color.parseColor("#f6ef98"));
                results.setBackgroundColor(Color.parseColor("#f6ef98"));
                news.setBackgroundColor(Color.parseColor("#f6ef98"));
                InstaContestants instaContestants = new InstaContestants();
                getFragmentManager().beginTransaction().replace(R.id.cat_fragment,instaContestants).commit();
            }

        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contestants.setBackgroundColor(Color.parseColor("#f6ef98"));
                subscribe.setBackgroundColor(Color.parseColor("#ffffff"));
                results.setBackgroundColor(Color.parseColor("#f6ef98"));
                news.setBackgroundColor(Color.parseColor("#f6ef98"));
                InstaSubscribe instaSubscribe = new InstaSubscribe();
                getFragmentManager().beginTransaction().replace(R.id.cat_fragment,instaSubscribe).commit();
            }
        });

        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contestants.setBackgroundColor(Color.parseColor("#f6ef98"));
                subscribe.setBackgroundColor(Color.parseColor("#f6ef98"));
                results.setBackgroundColor(Color.parseColor("#ffffff"));
                news.setBackgroundColor(Color.parseColor("#f6ef98"));
                InstaResults instaResults = new InstaResults();
                getFragmentManager().beginTransaction().replace(R.id.cat_fragment,instaResults).commit();
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contestants.setBackgroundColor(Color.parseColor("#f6ef98"));
                subscribe.setBackgroundColor(Color.parseColor("#f6ef98"));
                results.setBackgroundColor(Color.parseColor("#f6ef98"));
                news.setBackgroundColor(Color.parseColor("#ffffff"));
                InstaNews instaNews = new InstaNews();
                getFragmentManager().beginTransaction().replace(R.id.cat_fragment,instaNews).commit();
            }
        });

        return view;


    }
}
