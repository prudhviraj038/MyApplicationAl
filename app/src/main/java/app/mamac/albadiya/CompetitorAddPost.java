package app.mamac.albadiya;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by T on 20-12-2016.
 */

public class CompetitorAddPost extends Activity {
    ImageView back_btn;
    EditText item_title;
    EditText item_description;
    ImageView item_image;
    ImageView submit_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competitor_addpost);
        back_btn          = (ImageView) findViewById(R.id.back_btn);
        item_title        = (EditText) findViewById(R.id.item_title);
        item_description  = (EditText) findViewById(R.id.item_description);
        item_image        = (ImageView)findViewById(R.id.item_image);
        item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_images();
            }
        });
        submit_btn        = (ImageView) findViewById(R.id.submit_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompetitorAddPost.this,CompetitorsDetailPage.class);
                startActivity(intent);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_string = item_title.getText().toString();
                String description_string = item_description.getText().toString();
                if (title_string.equals("")){
                    Toast.makeText(CompetitorAddPost.this,"please enter title",Toast.LENGTH_SHORT).show();
                }else if (description_string.equals("")){
                    Toast.makeText(CompetitorAddPost.this,"Please enter description",Toast.LENGTH_SHORT).show();
                }else {
                    Ion.with(CompetitorAddPost.this)
                            .load(Settings.SERVER_URL+"add-post.php")
                            .setBodyParameter("member_id",Settings.GetUserId(CompetitorAddPost.this))
                            .setBodyParameter("title",title_string)
                            .setBodyParameter("description",description_string)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {

                                    if (result.get("status").getAsString().equals("Success")){
                                        //   Toast.makeText(lawyerEditProfile.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                        if(selected_image_path.equals("")){
                                            addpost_success();
                                        }else {
                                            upload_images(result.get("post_id").getAsString());
                                        }
                                    }else {
                                        Toast.makeText(CompetitorAddPost.this,result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        if(getIntent().hasExtra("image")){
            Log.e("image_in_activity",getIntent().getStringExtra("image"));
            Picasso.with(this).load(Uri.parse(getIntent().getStringExtra("image"))).into(item_image);
            selected_image_path = getRealPathFromURI(Uri.parse(getIntent().getStringExtra("image")));
        }

    }


    public void show_images(){
        final CharSequence[] items = {"camera","gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("select_image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(items[item].equals("camera")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

                }else if(items[item].equals("gallery")){
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto,1);
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    String selected_image_path = "";
    protected void onActivityResult(int requestCode,int resultCode, Intent imageReturnedIntent){
        super.onActivityResult(requestCode,resultCode,imageReturnedIntent);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    item_image.setImageURI(selectedImage);
                    selected_image_path = getRealPathFromURI(selectedImage);
                    Log.e("image_path",selected_image_path);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    item_image.setImageURI(selectedImage);
                    File new_file = new File(selectedImage.getPath());
                    selected_image_path = getRealPathFromURI(selectedImage);
                    Log.e("image_path",selected_image_path);
                }
                break;
        }
    }

    public void upload_images(String post_id){
        final ProgressBar progressBar = new ProgressBar(this);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait image is loading..");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Ion.with(this)
                .load(Settings.SERVER_URL+"add-post-image-ios.php")
                .uploadProgressBar(progressBar)
                .uploadProgressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        progressDialog.setMax((int) total);
                        progressDialog.setProgress((int) downloaded);
                    }
                })
                .setMultipartParameter("post_id",post_id)
                .setMultipartParameter("type","1")
                .setMultipartFile("file","image/png",new File(selected_image_path))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (progressDialog!=null)
                            progressDialog.dismiss();
                        if (e!=null){
                            e.printStackTrace();
                        }else if (result.isJsonNull()){
                            Log.e("json_null",null);
                        }else {
                            Log.e("image_path_response",result.toString());
                            addpost_success();
                        }
                    }
                });
    }



    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void addpost_success(){
        Toast.makeText(CompetitorAddPost.this,"post added succesfully",Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(AddPost.this,HomeProfile.class);
        //startActivity(intent);
        finish();

    }


}
