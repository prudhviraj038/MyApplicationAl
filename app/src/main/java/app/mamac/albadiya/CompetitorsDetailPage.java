package app.mamac.albadiya;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by T on 19-12-2016.
 */

public class CompetitorsDetailPage extends Fragment {
    CircleImageView user_image;
    TextView item_title;
    ImageView item_image;
    TextView description;
    Competitors competitors;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.competitors_detail_page,container,false);
        CircleImageView user_image = (CircleImageView) view.findViewById(R.id.user_image);
        TextView    item_title  = (TextView) view.findViewById(R.id.item_title);
        ImageView   item_image  = (ImageView) view.findViewById(R.id.item_image);
        TextView    description = (TextView) view.findViewById(R.id.description);

        competitors = (Competitors) getArguments().getSerializable("competitor");
        return view;
    }
}
