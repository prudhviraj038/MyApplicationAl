package app.mamac.albadiya;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by T on 20-01-2017.
 */

public class PostItems extends Fragment {
    ListView listView;
    PostItemsAdapter postItemsAdapter;
    ArrayList<String> title;
    ArrayList<Integer> image;
    ArrayList<Posts> postsfrom_api;
    HomeProfile.UserProfileSelectedListner mCallback;
    public interface UserProfileSelectedListner {

        public void onUserSelected(String member_id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (HomeProfile.UserProfileSelectedListner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.post_list,container,false);
        listView = (ListView) view.findViewById(R.id.posts_list);


        title   = new ArrayList<>();
        image = new ArrayList<>();
        postsfrom_api = new ArrayList<>();


        title.add("Amazon");
        title.add("Yellowsoft");
        title.add("Yahoo");
        title.add("Snapdeal");


        image.add(R.drawable.banner);
        image.add(R.drawable.banner1);
        image.add(R.drawable.banner3);
        image.add(R.drawable.timeline);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
       //get_posts();
        get_member_details();
        return view;
    }




    public void get_member_details(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String url = Settings.SERVER_URL+"member-details.php";
        Ion.with(getActivity())
                .load(url)
                .setBodyParameter("member_id",Settings.GetUserId(getContext()))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        try {
                            JsonObject jsonObject = result.get(0).getAsJsonObject();
                            JsonArray posts_aray = jsonObject.get("posts").getAsJsonArray();
                            for (int i = 0; i < posts_aray.size(); i++) {
                                Posts posts = new Posts(posts_aray.get(i).getAsJsonObject(), getActivity());
                                postsfrom_api.add(posts);
                            }

                            postItemsAdapter = new PostItemsAdapter(getActivity(),postsfrom_api,PostItems.this);
                            listView.setAdapter(postItemsAdapter);
                            //postItemsAdapter.notifyDataSetChanged();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });
    }



    private void get_posts() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("plase wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Ion.with(this)
                .load(Settings.SERVER_URL+"posts.php")
                .setBodyParameter("member_id",Settings.GetUserId(getActivity()))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        // do stuff with the result or error
                        //{"status":"Failure","message":"Please Enter Your Type"}
                        if (progressDialog != null)
                            progressDialog.dismiss();
                        if (e != null) {
                            e.printStackTrace();
                        } else {
                            Log.e("response", String.valueOf(result.size()));
                            for (int i = 0; i < result.size(); i++) {
                                Posts posts = new Posts(result.get(i).getAsJsonObject(), getActivity());
                                postsfrom_api.add(posts);
                            }
                            postItemsAdapter = new PostItemsAdapter(getActivity(),postsfrom_api,PostItems.this);
                            listView.setAdapter(postItemsAdapter);


                            // homeProfileAdapter.notifyDataSetChanged();

                        }

                    }
                });
    }


    public void go_to_user_profile(String member_id){
        mCallback.onUserSelected(member_id);

    }
}
