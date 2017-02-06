package app.mamac.albadiya;

import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;

import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;


/**
 * Created by mac on 1/19/17.
 */

public class ExoPlayerActivity extends AppCompatActivity {

    private ExoPlayer exoPlayer;
    private SurfaceView surfaceView;
    private int RENDERER_COUNT = 300000;
    private int minBufferMs =    250000;
    private final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private final int BUFFER_SEGMENT_COUNT = 256;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exoplayerview);

        surfaceView= (SurfaceView) findViewById(R.id.surfaceView);

        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:40.0) Gecko/20100101 Firefox/40.0";
        String url = "http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_5mb.mp4";
        Allocator allocator = new DefaultAllocator(minBufferMs);
        DataSource dataSource = new DefaultUriDataSource(this,null,userAgent);


        ExtractorSampleSource sampleSource = new ExtractorSampleSource( Uri.parse(url), dataSource, allocator,
                BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);

        MediaCodecVideoTrackRenderer videoRenderer = new
                MediaCodecVideoTrackRenderer(this, sampleSource, MediaCodecSelector.DEFAULT,
                MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);

        MediaCodecAudioTrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource, MediaCodecSelector.DEFAULT);


        exoPlayer = ExoPlayer.Factory.newInstance(RENDERER_COUNT);
        exoPlayer.prepare(videoRenderer, audioRenderer);
        exoPlayer.sendMessage(videoRenderer,
                MediaCodecVideoTrackRenderer.MSG_SET_SURFACE,
                surfaceView.getHolder().getSurface());
        exoPlayer.setPlayWhenReady(true);

    }
}