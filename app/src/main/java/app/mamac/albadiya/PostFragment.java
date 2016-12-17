package app.mamac.albadiya;

import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mac on 12/12/16.
 */

public class PostFragment extends Fragment implements TakeVideoFragment.TakeVideoListner,TakePictureFragment.TakePictureListner{
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    ImageView video_btn,camera_btn,gallery_btn;
    private CameraPreview mPreview;
    private RelativeLayout mLayout;
    private Camera.PictureCallback mPicture;
    private MediaRecorder mediaRecorder;
    private boolean recording;
    ImageView click_btn;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.post_fragment, container, false);
        video_btn = (ImageView) view.findViewById(R.id.video_btn);
        camera_btn = (ImageView) view.findViewById(R.id.camera_btn);
        gallery_btn = (ImageView) view.findViewById(R.id.gallery_btn);
        mLayout = (RelativeLayout)view.findViewById(R.id.camera_holder);
        camera_btn = (ImageView)view.findViewById(R.id.camera_btn);

        pagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);

        reset_icons(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                // Check if this is the page you want.

                    reset_icons(position+1);

            }
        });


                return view;
    }

private void reset_icons(int pos){

    video_btn.setImageResource(R.drawable.ic_video_in);
    camera_btn.setImageResource(R.drawable.ic_camera_in);
    gallery_btn.setImageResource(R.drawable.ic_gallery_in);

    switch (pos){
        case 1:
            setcamera_in_videomode();
            video_btn.setImageResource(R.drawable.ic_video);
            break;
        case 2:
            setcamera_in_picturemode();
            camera_btn.setImageResource(R.drawable.ic_camera);
            break;
        case 3:
            reset_camera();
            gallery_btn.setImageResource(R.drawable.ic_gallery);
            break;
    }

}

    @Override
    public void onVideobtn() {
        Log.e("video","clicked");

    }

    @Override
    public void onPicturebtn() {

        mPicture = getPictureCallback();
        mPreview.mCamera.takePicture(null,null,mPicture);
        Log.e("photo","clicked");

    }

    public void onGallerySelected(String path){
        Intent intent = new Intent(getActivity(),AddPost.class);
        File file = new File(path);
        intent.putExtra("image", file.toURI().toString());
        startActivity(intent);

    }

    private void setcamera_in_picturemode(){
        reset_camera();
        mPreview = new CameraPreview(getActivity(), 1, CameraPreview.LayoutMode.FitToParent);
        RelativeLayout.LayoutParams previewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayout.addView(mPreview, 0, previewLayoutParams);
        mPicture = getPictureCallback();

    }

    private void setcamera_in_videomode(){
        reset_camera();
        mPreview = new CameraPreview(getActivity(), 1, CameraPreview.LayoutMode.FitToParent);
        RelativeLayout.LayoutParams previewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayout.addView(mPreview, 0, previewLayoutParams);
        mPicture=null;

    }

    private void reset_camera(){

        if(mPreview!=null){
            mPreview.stop();
            mLayout.removeView(mPreview);
            mPreview = null;
        }
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:{

                    return new TakeVideoFragment();

                }
                case 1:{

                    return new TakePictureFragment();

                }
                case 2:{

                    return new TakeGalleryFragment();

                }
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Set the second argument by your choice.
        // Usually, 0 for back-facing camera, 1 for front-facing camera.
        // If the OS is pre-gingerbreak, this does not have any effect.
        // Un-comment below lines to specify the size.
        //previewLayoutParams.height = 500;
        //previewLayoutParams.width = 500;

        // Un-comment below line to specify the position.
        //mPreview.setCenterPosition(270, 130);
        reset_icons(1);


    }
    @Override
    public void onPause() {
        super.onPause();

        // Set the second argument by your choice.
        // Usually, 0 for back-facing camera, 1 for front-facing camera.
        // If the OS is pre-gingerbreak, this does not have any effect.
        // Un-comment below lines to specify the size.
        //previewLayoutParams.height = 500;
        //previewLayoutParams.width = 500;

        // Un-comment below line to specify the position.
        //mPreview.setCenterPosition(270, 130);



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
           }

    private Camera.PictureCallback getPictureCallback() {

        Camera.PictureCallback picture = new Camera.PictureCallback() {


            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
//make a new picture file

                File pictureFile = getOutputMediaFile();
                if (pictureFile == null) {
                    return;
                }
                try {
//write the file
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                    Toast toast = Toast.makeText(getActivity(), "Picture saved: " + pictureFile.getName(), Toast.LENGTH_LONG);
                    toast.show();

                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                }

//refresh camera to continue preview
                mPreview.mCamera.startPreview();
                //  saving_pic = false;
// files[image_count]=pictureFile.toURI().toString();
                //  image_path.add(pictureFile.getAbsolutePath());
                //image_path_id.add("-1");
// display_image(pictureFile);
//show_image_edit(pictureFile);
                Intent intent = new Intent(getActivity(),AddPost.class);
                intent.putExtra("image",pictureFile.toURI().toString());
                startActivity(intent);


            }
        };
        return picture;
    }

    private static File getOutputMediaFile() {
//make a new file directory inside the "sdcard" folder
        File mediaStorageDir = new File("/sdcard/", "Albadiaya");

        if (!mediaStorageDir.exists()) {
//if you cannot make this folder return
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

//take the current timeStamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
//and make a media file:
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    private boolean prepareMediaRecorder() {

        mediaRecorder = new MediaRecorder();

        mPreview.mCamera.unlock();
        mediaRecorder.setCamera(mPreview.mCamera);

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));

        mediaRecorder.setOutputFile("/sdcard/myvideo.mp4");
        mediaRecorder.setMaxDuration(600000); //set maximum duration 60 sec.
        mediaRecorder.setMaxFileSize(50000000); //set maximum file size 50M

        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            releaseMediaRecorder();
            return false;
        }
        return true;

    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset(); // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = null;
            mPreview.mCamera.lock(); // lock camera for later use
        }
    }

}
