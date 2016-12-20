package app.mamac.albadiya;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by T on 20-12-2016.
 */

public class InstaSubscribe extends Fragment {
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.subscribe_page,container,false);
        return view;

    }
}
