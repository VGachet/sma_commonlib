package fr.smartapps.commonlib.video;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import fr.smartapps.commonlib.R;

/**
 * Created by rdupont on 19/09/2016.
 */

public class SMAVideoView extends FrameLayout {

    // View elements
    protected View view;
    protected Context context;
    protected ScalableVideoView scalableVideoView;
    protected LinearLayout bottomLayout;
    protected ImageView btnPlayPause;
    protected TextView currentTime, totalTime;
    protected SeekBar progressBar;
    protected TextView subtitles;
    protected ImageView subtitlesToggle;
    protected int FORCED_WIDTH = 0;
    protected int FORCED_HEIGHT = 0;

    protected boolean autoPlay = false;
    protected int isPlayerVisible = 0 ;
    protected boolean isSubtitleVisible = true;
    protected boolean isSubtitleForced = false;

    protected String subtitlesLocale;
    protected String subtitlesIconFilename = "subtitles.png";
    protected HashMap<String, String> subtitlesLocales = new HashMap<>();

    // Drawables
    protected Drawable thumb;
    protected Drawable btnPlay;
    protected Drawable btnPause;
    protected Drawable btnSubtitles;
    protected Drawable btnSubtitlesDisabled;

    // Colors
    private int colorBackgroundVideo = Color.parseColor("#000000");
    private int colorBackgroundPlayer = Color.parseColor("#555555");
    private int colorThumb = Color.parseColor("#FFFFFF");
    private int colorProgressFinished = Color.parseColor("#FFFFFF");
    private int colorProgressUnfinished = Color.parseColor("#999999");
    private int colorTime = Color.parseColor("#FFFFFF");
    private int colorButtonPlayPause = Color.parseColor("#FFFFFF");
    private int colorSubtitlesDisabled = Color.parseColor("#a0a0a0");

    // Timer
    protected ProgressTimerTask progressTimerTask;
    protected static Timer timer;
    protected android.os.Handler handler;
    protected int timerLength = 500;
    protected boolean isControlVisible = true;
    protected int milliSeconds = 0;
    protected int nbMilliSecondsVisible = 5000;

    private String SUBTITLES_DEFAULT_LOCALE = "eng";

    /**
     Constructors
     */
    public SMAVideoView(Context context) {
        super(context);
        this.context = context;
    }

    public SMAVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SMAVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     Public Methods init
     */

    // Drawables
    public SMAVideoView thumbDrawable(Drawable thumb) {
        this.thumb = thumb;
        return this;
    }

    public SMAVideoView playDrawable(Drawable play) {
        this.btnPlay = play;
        return this;
    }

    public SMAVideoView pauseDrawable(Drawable pause) {
        this.btnPause = pause;
        return this;
    }

    // Colors int
    public SMAVideoView setVideoBackgroundColor(int color) {
        this.colorBackgroundVideo = color;
        return this;
    }
    public SMAVideoView setPlayerBackgroundColor(int color) {
        this.colorBackgroundPlayer = color;
        return this;
    }
    public SMAVideoView setThumbColor(int color) {
        this.colorThumb = color;
        return this;
    }
    public SMAVideoView setProgressFinishedColor(int color) {
        this.colorProgressFinished = color;
        return this;
    }
    public SMAVideoView setProgressUnfinishedColor(int color) {
        this.colorProgressUnfinished = color;
        return this;
    }
    public SMAVideoView setTimeColor(int color) {
        this.colorTime = color;
        return this;
    }
    public SMAVideoView setPlayPauseBtnColor(int color) {
        this.colorButtonPlayPause = color;
        return this;
    }

    // Colors String
    public SMAVideoView setVideoBackgroundColor(String color) {
        if(color.startsWith("#"))
            this.colorBackgroundVideo = Color.parseColor(color);
        return this;
    }
    public SMAVideoView setPlayerBackgroundColor(String color) {
        if(color.startsWith("#"))
            this.colorBackgroundPlayer = Color.parseColor(color);
        return this;
    }
    public SMAVideoView setThumbColor(String color) {
        if(color.startsWith("#"))
            this.colorThumb = Color.parseColor(color);
        return this;
    }
    public SMAVideoView setProgressFinishedColor(String color) {
        if(color.startsWith("#"))
            this.colorProgressFinished = Color.parseColor(color);
        return this;
    }
    public SMAVideoView setProgressUnfinishedColor(String color) {
        if(color.startsWith("#"))
            this.colorProgressUnfinished = Color.parseColor(color);
        return this;
    }
    public SMAVideoView setTimeColor(String color) {
        if(color.startsWith("#"))
            this.colorTime = Color.parseColor(color);
        return this;
    }
    public SMAVideoView setPlayPauseBtnColor(String color) {
        if(color.startsWith("#"))
            this.colorButtonPlayPause = Color.parseColor(color);
        return this;
    }

    public SMAVideoView setSubtitlesIcon(String filename) {
        if(filename != null) {
            this.subtitlesIconFilename = filename;
        }
        return this;
    }

    // Others
    public SMAVideoView setPlayerVisibility(int isPlayerVisible) {
        this.isPlayerVisible =  isPlayerVisible;
        if(bottomLayout != null) {
            switch (isPlayerVisible) {
                case 0:
                    isControlVisible = true;
                    bottomLayout.setVisibility(VISIBLE);
                    milliSeconds = 0;
                    break;
                case 1:
                    bottomLayout.setVisibility(VISIBLE);
                    break;
                case 2:
                    bottomLayout.setVisibility(GONE);
            }
        }
        return this;
    }
    public SMAVideoView setVisibleTime(int milliSeconds) {
        this.nbMilliSecondsVisible = milliSeconds;
        return this;
    }

    public SMAVideoView setLocale(String locale) {
        this.subtitlesLocale = locale;
        return this;
    }

    public SMAVideoView autoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
        return this;
    }

    public SMAVideoView subtitlesForced(boolean isForced) {
        this.isSubtitleForced = isForced;
        return this;
    }


    // Init file
    public void create(String fileName) {
        init();
        initDesign();
        try {
            scalableVideoView.FORCED_WIDTH = FORCED_WIDTH;
            scalableVideoView.FORCED_HEIGHT = FORCED_HEIGHT;
            scalableVideoView.setAssetData(fileName);
            scalableVideoView.setVolume(1.0f, 1.0f);
            scalableVideoView.setLooping(false);
            scalableVideoView.prepareAsync(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    initSubtitles();
                    scalableVideoView.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
                        @Override
                        public void onTimedText(MediaPlayer mp, TimedText text) {
                            subtitles.setText(text.getText());
                        }
                    });
                    totalTime.setText(stringForTime(scalableVideoView.getDuration()));
                    progressBar.setMax(scalableVideoView.getDuration());
                    if (autoPlay) {
                        scalableVideoView.start();
                        togglePlayPause();
                        milliSeconds = 0;
                    }
                }
            });

            startProgressTimer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create(String fileName, int width, int height) {
        this.FORCED_HEIGHT = height;
        this.FORCED_WIDTH = width;
        create(fileName);
    }

    public void create(AssetFileDescriptor assetFileDescriptor, int width, int height) {
        this.FORCED_HEIGHT = height;
        this.FORCED_WIDTH = width;
        create(assetFileDescriptor);
    }

    public void create(AssetFileDescriptor assetFileDescriptor) {
        init();
        initDesign();
        try {
            scalableVideoView.FORCED_WIDTH = FORCED_WIDTH;
            scalableVideoView.FORCED_HEIGHT = FORCED_HEIGHT;
            scalableVideoView.setDataSource(assetFileDescriptor);
            scalableVideoView.setVolume(1.0f, 1.0f);

            scalableVideoView.setLooping(false);
            scalableVideoView.prepareAsync(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    totalTime.setText(stringForTime(scalableVideoView.getDuration()));
                    progressBar.setMax(scalableVideoView.getDuration());
                    initSubtitles();
                    if(scalableVideoView != null) {
                        scalableVideoView.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
                            @Override
                            public void onTimedText(MediaPlayer mp, TimedText text) {
                                if (subtitles != null && text != null && text.getText() != null) {
                                    if (text.getText().equals("###EOF")) {
                                        subtitles.setText("");
                                    } else {
                                        subtitles.setText(text.getText());
                                    }
                                }
                            }
                        });
                    }

                    if (autoPlay) {
                        scalableVideoView.start();
                        togglePlayPause();
                        milliSeconds = 0;
                    }
                }
            });

            startProgressTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     Public Methods
     */
    public boolean isPlaying() {
        return scalableVideoView.isPlaying();
    }

    public void play() {
        if(!isPlaying()) {
            scalableVideoView.start();
            togglePlayPause();
            milliSeconds = 0;
        }
    }

    public void pause() {
        if (isPlaying()) {
            scalableVideoView.pause();
            togglePlayPause();
        }
    }

    public  void setProgressVisible(boolean visible) {
        if (visible)
            bottomLayout.setVisibility(VISIBLE);
        else
            bottomLayout.setVisibility(GONE);
    }

    public void setProgress(int time) {
        scalableVideoView.seekTo(time);
    }

    public int getCurrentTime() {
        return scalableVideoView.getCurrentPosition();
    }

    public void setScalableType(ScalableType scalableType) {
        scalableVideoView.setScalableType(scalableType);
    }

    /**
     Protected Methods
     */
    protected void init() {
        view = View.inflate(context, R.layout.sma_video_view, this);
        scalableVideoView = (ScalableVideoView) view.findViewById(R.id.videoController);

        scalableVideoView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(!isControlVisible && isPlayerVisible == 0) {
                    isControlVisible = true;
                    milliSeconds = 0;
                    bottomLayout.setVisibility(View.VISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) subtitles.getLayoutParams();
                    params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.addRule(RelativeLayout.ABOVE, R.id.bottom_layout);
                    subtitles.setLayoutParams(params);
                    subtitles.requestLayout();
                }
            }
        });

        handler = new android.os.Handler();
        progressBar = (SeekBar) view.findViewById(R.id.progressBar);
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(scalableVideoView != null && fromUser){
                    scalableVideoView.seekTo(progress);
                    currentTime.setText(stringForTime(progress));
                }
            }
        });

        currentTime = (TextView) view.findViewById(R.id.currentTime);
        totalTime = (TextView) view.findViewById(R.id.totalTime);

        btnPlayPause = (ImageView) view.findViewById(R.id.play_pause);
        btnPlayPause.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                playPause();
            }
        });

        subtitles = (TextView) view.findViewById(R.id.subtitles_view);

        subtitlesToggle = (ImageView) view.findViewById(R.id.subtitles_toggle);
        subtitlesToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSubtitleVisible) {
                    isSubtitleVisible = false;
                    subtitles.setVisibility(View.GONE);
                    subtitlesToggle.setColorFilter(colorSubtitlesDisabled, PorterDuff.Mode.SRC_IN);
                } else {
                    isSubtitleVisible = true;
                    subtitles.setVisibility(View.VISIBLE);
                    subtitlesToggle.setColorFilter(colorButtonPlayPause, PorterDuff.Mode.SRC_IN);
                }
            }
        });
    }

    protected void initDesign() {

        view.setBackgroundColor(colorBackgroundVideo);

        bottomLayout = (LinearLayout) view.findViewById(R.id.bottom_layout);
        bottomLayout.setBackgroundColor(colorBackgroundPlayer);

        bottomLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (isPlayerVisible == 1) {
            bottomLayout.setVisibility(VISIBLE);
        }
        else if (isPlayerVisible == 2) {
            bottomLayout.setVisibility(GONE);
        }

        thumb = getDensityDrawable("scrubber_control.png", DisplayMetrics.DENSITY_XHIGH);
        if (thumb != null) {
            thumb.setColorFilter(colorThumb, PorterDuff.Mode.SRC_IN);
            progressBar.setThumb(thumb);
        }

        LayerDrawable layerDrawable = (LayerDrawable) progressBar.getProgressDrawable();
        layerDrawable.findDrawableByLayerId(R.id.backgroundProgressBar).setColorFilter(colorProgressUnfinished, PorterDuff.Mode.SRC_IN);
        layerDrawable.findDrawableByLayerId(R.id.progressColor).setColorFilter(colorProgressFinished, PorterDuff.Mode.SRC_IN);

        if (btnPlay == null) {
            btnPlay = getDensityDrawable("btn_play.png", DisplayMetrics.DENSITY_XHIGH);
        }
        if (btnPause == null) {
            btnPause = getDensityDrawable("btn_pause.png", DisplayMetrics.DENSITY_XHIGH);
        }
        if (btnSubtitles == null) {
            btnSubtitles = getDensityDrawable(subtitlesIconFilename, DisplayMetrics.DENSITY_XHIGH);
        }

        btnPlayPause.setColorFilter(colorButtonPlayPause, PorterDuff.Mode.SRC_IN);

        subtitlesToggle.setColorFilter(colorButtonPlayPause, PorterDuff.Mode.SRC_IN);
        subtitlesToggle.setImageDrawable(btnSubtitles);

        if(isSubtitleForced) {
            subtitlesToggle.setVisibility(GONE);
        }

        currentTime.setTextColor(colorTime);
        totalTime.setTextColor(colorTime);
    }

    public void initSubtitles() {
        MediaPlayer.TrackInfo[] trackInfos = scalableVideoView.getTrackInfos();

        int index = 0;
        int defaultIndex = -1;
        boolean localeFound = false;
        boolean hasSubtitles = false;

        // Supported locales
        subtitlesLocales.put("eng", "en");
        subtitlesLocales.put("fra", "fr");
        subtitlesLocales.put("deu", "de");
        subtitlesLocales.put("spa", "es");
        subtitlesLocales.put("nld", "nl");
        subtitlesLocales.put("por", "pt");
        subtitlesLocales.put("rus", "ru");
        subtitlesLocales.put("ita", "it");
        subtitlesLocales.put("zho", "zh");
        subtitlesLocales.put("ara", "ar");
        subtitlesLocales.put("bre", "br");
        subtitlesLocales.put("jpn", "ja");
        subtitlesLocales.put("kor", "ko");
        subtitlesLocales.put("pol", "pl");
        subtitlesLocales.put("swe", "sv");
        subtitlesLocales.put("heb", "he");
        subtitlesLocales.put("hin", "hi");
        subtitlesLocales.put("cat", "ca");

        for(MediaPlayer.TrackInfo info : trackInfos) {
            if(info.getTrackType() == MediaPlayer.TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT) {

                hasSubtitles = true;

                if (defaultIndex == -1) {
                    defaultIndex = index;
                }

                if(subtitlesLocales.containsKey(info.getLanguage()) && subtitlesLocales.get(info.getLanguage()).equals(subtitlesLocale)) {

                    scalableVideoView.selectTrack(index);
                    localeFound = true;

                }

            }

            index++;
        }
        if(!localeFound){
            if(defaultIndex != -1) {
                scalableVideoView.selectTrack(defaultIndex);
            }
        }
        if(!hasSubtitles) {
            subtitlesToggle.setVisibility(GONE);
        }
    }

    public void playPause() {
        if (scalableVideoView.isPlaying()) {
            scalableVideoView.pause();
            togglePlayPause();
        }
        else {
            scalableVideoView.start();
            togglePlayPause();
            milliSeconds = 0;
        }
    }

    protected void togglePlayPause() {
        if (scalableVideoView.isPlaying()) {
            btnPlayPause.setImageDrawable(btnPause);
            btnPlayPause.setContentDescription(context.getString(R.string.accessibility_talkback_pause));
        }
        else {
            btnPlayPause.setImageDrawable(btnPlay);
            btnPlayPause.setContentDescription(context.getString(R.string.accessibility_talkback_play));
        }
    }

    protected static String stringForTime(int timeMs) {
        if (timeMs <= 0 || timeMs >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        }
        else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /**
     *  ProgressBar Update
     */
    protected class ProgressTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(scalableVideoView != null) {
                        if (scalableVideoView.isPlaying()) {
                            if (milliSeconds < nbMilliSecondsVisible) {
                                milliSeconds += timerLength;
                            }
                            if (milliSeconds == nbMilliSecondsVisible && isPlayerVisible == 0) {

                                bottomLayout.setVisibility(View.GONE);
                                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) subtitles.getLayoutParams();
                                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                                subtitles.setLayoutParams(params);
                                subtitles.requestLayout();
                                isControlVisible = false;
                            }
                            progressBar.setProgress(scalableVideoView.getCurrentPosition());
                            currentTime.setText(stringForTime(scalableVideoView.getCurrentPosition()));
                        }
                        else
                            togglePlayPause();
                    }
                }
            });
        }
    }

    protected void startProgressTimer() {
        cancelProgressTimer();
        timer = new Timer();
        progressTimerTask = new ProgressTimerTask();
        timer.schedule(progressTimerTask, 0, timerLength);
    }

    protected void cancelProgressTimer() {
        if (timer != null) {
            timer.cancel();
        }
        if (progressTimerTask != null) {
            progressTimerTask.cancel();
        }
    }

    /**
     * Design
     */
    protected Drawable getDensityDrawable(String imageName, int density) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDensity = density;   // ex : DisplayMetrics.DENSITY_HIGH
        return Drawable.createFromResourceStream(context.getResources(), null, getInputStream(imageName), null, opts);
    }

    protected InputStream getInputStream(String fileName) {
        if(fileName == null || fileName.equals(""))
            return null;
        try {
            return context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        scalableVideoView.release();
        timer.cancel();
    }

    public void onComplete(MediaPlayer.OnCompletionListener listener) {
        scalableVideoView.onComplete(listener);
    }

    public int getDuration() {
        return scalableVideoView.getDuration();
    }

    public void setPlayButtonClickListener(OnClickListener listener){
        btnPlayPause.setOnClickListener(listener);
    }

    public MediaPlayer getMediaPlayer() {
        return this.scalableVideoView.getMediaPlayer();
    }

}
