package app.mamac.albadiya.video_list_demo.adapter.items;

import android.app.Activity;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;
import app.mamac.albadiya.video_list_demo.adapter.holders.VideoViewHolder;

/**
 * Use this class if you have direct path to the video source
 */
public class DirectLinkVideoItem extends BaseVideoItem {

    private final String mDirectUrl;
    private final String mTitle;
    private final Activity mActivity;

    private final Picasso mImageLoader;
    private final String mImageResource;

    public DirectLinkVideoItem(String title, String directUr, VideoPlayerManager videoPlayerManager, Picasso imageLoader, String imageResource, Activity activity) {
        super(videoPlayerManager);
        mDirectUrl = directUr;
        mTitle = title;
        mImageLoader = imageLoader;
        mImageResource = imageResource;
        mActivity = activity;

    }

    @Override
    public void update(int position, VideoViewHolder viewHolder, VideoPlayerManager videoPlayerManager) {
        viewHolder.mTitle.setText(mTitle);
        viewHolder.mCover.setVisibility(View.VISIBLE);

        if(!mImageResource.equals("no_image"))
        mImageLoader.load(mImageResource).into(viewHolder.mCover);
    }

    @Override
    public void playNewVideo(MetaData currentItemMetaData, VideoPlayerView player, VideoPlayerManager<MetaData> videoPlayerManager) {
        videoPlayerManager.playNewVideo(currentItemMetaData, player,  mDirectUrl);
    }

    @Override
    public void stopPlayback(VideoPlayerManager videoPlayerManager) {
        videoPlayerManager.stopAnyPlayback();
    }
}
