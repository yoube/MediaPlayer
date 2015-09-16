package com.example.videoplayer;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VideoActivity extends Activity {

	private SurfaceView surfaceView;
	MediaPlayer mediaPlayer;
	private Button paly;
	private View mVideoImg;
	private View mVideoLayout;
	private View mVideoFLayout;
	private View mvideoprogress;
	private View mVideoTool;
	private ImageView mVideoexpImg;
	private ImageView mVideoplayImg;
	private int curredProgreass;
	private SeekBar mVideoToolSeetbar;
	String url = "/storage/sdcard1/1.rmvb";
//	String url = "http://flv2.bn.netease.com/videolib3/1509/14/eWTZv6096/SD/eWTZv6096-mobile.mp4";
//	String url = "http://192.168.9.108:8080/12.rmvb";
	private boolean threadFlag = true;
	
	//更新控件
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(!threadFlag) return;
			if(msg.what==1){
				mVideoToolSeetbar.setProgress(mediaPlayer.getCurrentPosition());
			}else{
				mVideoTool.setVisibility(View.GONE);
			}
			
		};
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.view_video_list_item);
		
		if(getResources().getConfiguration().orientation== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
			initView();
		}else{
			//设置全屏
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
			initLandView();
		}
		Log.w("wwww","onCreate");
	}

	private void initLandView() {
		mVideoLayout = findViewById(R.id.video_rl);
		mVideoImg = findViewById(R.id.video_img_rl);
		mVideoTool = findViewById(R.id.video_tool_rl);
		mvideoprogress = findViewById(R.id.video_progress);
		mVideoplayImg  = (ImageView) findViewById(R.id.video_tool_play_img);
		mVideoexpImg  = (ImageView) findViewById(R.id.video_tool_expand);
		
		//切换横竖屏
		mVideoexpImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(getResources().getConfiguration().orientation== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
					//横屏切换
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}else{
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				
			}
			
		});
		
		mVideoToolSeetbar = (SeekBar) findViewById(R.id.video_tool_seetbar);
		//播放暂停
		mVideoplayImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
					mediaPlayer.pause();
					mVideoplayImg.setImageResource(R.drawable.night_biz_audio_btn_play);
					
				}else{
					mediaPlayer.start();
					mVideoplayImg.setImageResource(R.drawable.night_biz_audio_btn_pause);
				}
				
			}
		});
		//点击图片播放
		mVideoImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mVideoLayout.setVisibility(View.VISIBLE);
				mVideoImg.setVisibility(View.GONE);
			}
		});
		
		surfaceView = (SurfaceView) findViewById(R.id.video_surface);
		// 设置Holder生命回调方法
		surfaceView.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
//				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//					mediaPlayer.pause();
//					curredProgreass = mediaPlayer.getCurrentPosition();
//				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				play();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}
		});
		//隐藏 显示播放工具
		surfaceView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					
				if(mVideoTool.getVisibility()==View.GONE){
					mVideoTool.setVisibility(View.VISIBLE);
				}else{
					mVideoTool.setVisibility(View.GONE);
				}
				
			}
		});
		//进度拖放
		mVideoToolSeetbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					mediaPlayer.seekTo(progress);
				}
				
			}
		});
		mediaPlayer = new MediaPlayer();
		
	}
	//控件初始化
	private void initView() {
		mVideoLayout = findViewById(R.id.video_rl);
		mVideoFLayout = findViewById(R.id.video_fl);
		mVideoImg = findViewById(R.id.video_img_rl);
		mVideoTool = findViewById(R.id.video_tool_rl);
		mvideoprogress = findViewById(R.id.video_progress);
		mVideoplayImg  = (ImageView) findViewById(R.id.video_tool_play_img);
		
		mVideoexpImg  = (ImageView) findViewById(R.id.video_tool_expand);
		mVideoexpImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(getResources().getConfiguration().orientation== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}else{
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
			}
			
		});
		
		mVideoToolSeetbar = (SeekBar) findViewById(R.id.video_tool_seetbar);
		mVideoplayImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
					mediaPlayer.pause();
					mVideoplayImg.setImageResource(R.drawable.night_biz_audio_btn_play);
					
				}else{
					mediaPlayer.start();
					mVideoplayImg.setImageResource(R.drawable.night_biz_audio_btn_pause);
				}
				
			}
		});
		mVideoImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mVideoLayout.setVisibility(View.VISIBLE);
				mVideoImg.setVisibility(View.GONE);
			}
		});
		surfaceView = (SurfaceView) findViewById(R.id.video_surface);
		// 设置Holder生命回调方法
		surfaceView.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
//				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//					mediaPlayer.pause();
//					curredProgreass = mediaPlayer.getCurrentPosition();
//				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				play();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}
		});
		surfaceView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					
				if(mVideoTool.getVisibility()==View.GONE){
					mVideoTool.setVisibility(View.VISIBLE);
				}else{
					mVideoTool.setVisibility(View.GONE);
				}
			}
		});
		mVideoToolSeetbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					mediaPlayer.seekTo(progress);
				}
				
			}
		});
//		surfaceView.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//
//				mediaPlayer.seekTo((int) (mediaPlayer.getCurrentPosition() + event
//						.getX() * 100));
//				return false;
//			}
//		});
		mediaPlayer = new MediaPlayer();
//		mediaPlayer.setOn
	}

	//当横竖屏切换时回调方法
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if(getResources().getConfiguration().orientation== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
			//竖屏
			//显示状态栏
//			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
			mVideoFLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.video_List_item_heigth)));
			Log.e("wwww", "竖屏");
		}else{
			//横屏
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			mVideoFLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Log.e("wwww", "横屏");
			
		}
//		Log.w("wwww","onConfigurationChanged");
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
		mediaPlayer.release();
		threadFlag = false;
	}

	public void play() {
		try {
			mediaPlayer.reset();
			// 设置视频显示的位置
			mediaPlayer.setDisplay(surfaceView.getHolder());
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepareAsync();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					mVideoToolSeetbar.setMax(mediaPlayer.getDuration());//
					mediaPlayer.start();
					mvideoprogress.setVisibility(View.GONE);
					mVideoTool.setVisibility(View.VISIBLE);
					if (curredProgreass>0) {
						Log.e("curredProgreass",""+curredProgreass);
						mediaPlayer.seekTo(curredProgreass);
					}
					new Thread(){
						int count ;
						public void run() {
							while(threadFlag){
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								handler.sendEmptyMessage(1);
								if(mVideoTool.getVisibility()!=View.GONE){
									count++;
									if(count>5){
										handler.sendEmptyMessage(2);
										count=0;
									}
								}
							}
							
						};
					}.start();
				}
			});

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
