package com.neusoft.activities;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.mycustom.view.TitleView;
import com.neusoft.defertrain.R;
import com.neusoft.tools.ConstantKey;
import com.zxing.camera.CameraManager;
import com.zxing.decoding.CaptureActivityHandler;
import com.zxing.decoding.DecodeFormatManager;
import com.zxing.decoding.InactivityTimer;
import com.zxing.view.MipcaActivityCapture;
import com.zxing.view.ViewfinderResultPointCallback;
import com.zxing.view.ViewfinderView;
import com.zxing.decoding.RGBLuminanceSource;

public class QRScanAty extends MipcaActivityCapture  implements Callback {

	private final String IMAGE_TYPE = "image/*";
	private final int IMAGE_CODE = 1000; 

	private TitleView titleView;
	
	private Button albumBtn;
	private Button lightBtn;
	
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.qrscan);
		
		CameraManager.init(getApplication());
		
		findView();
		
		init();
		
	}

	private void findView() {
		// TODO Auto-generated method stub
		titleView = (TitleView) findViewById(R.id.mytitleview);
		
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
//		surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		
		albumBtn = (Button) findViewById(R.id.albumBtn);
		lightBtn = (Button) findViewById(R.id.lightBtn);
	}

	private void init() {
		// TODO Auto-generated method stub
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		
		titleView.setTitleText(getString(R.string.homeTitle));
		titleView.setLeftIcon(R.drawable.ic_launcher);
		titleView.setRightIcon(R.drawable.ic_launcher);
		titleView.setLeftIconVisible(View.VISIBLE);
		titleView.setRightIconVisible(View.VISIBLE);
		titleView.setBackgroundColor(getResources().getColor(R.color.home_title));
		
		albumBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType(IMAGE_TYPE);
				startActivityForResult(intent, IMAGE_CODE);
			}
		});
		
		lightBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(ConstantKey.REGISTER_ACTION);
				startActivity(intent);
			}
		});
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		 if (resultCode==RESULT_OK && requestCode==IMAGE_CODE) {
			 
			 Bitmap bitmap = null;
			 
			 //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
		    ContentResolver resolver = getContentResolver();

		    try {

	            Uri originalUri = data.getData();        //获得图片的uri 
	            bitmap = MediaStore.Images.Media.getBitmap(resolver, originalUri);    //得到bitmap图片
	            RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
	            
	            //根据图片解码
	            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(3);

	    		if (decodeFormats == null || decodeFormats.isEmpty()) {
	    			decodeFormats = new Vector<BarcodeFormat>();
	    			decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
	    			decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
	    			decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
	    		}

	    		hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);

	    		if (characterSet != null) {
	    			hints.put(DecodeHintType.CHARACTER_SET, characterSet);
	    		}

	    		hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, new ViewfinderResultPointCallback(this.getViewfinderView()));
	    		
//	            DecodeHandler decodeHandler = new DecodeHandler(this, hints);
//	            decodeHandler.decode(Bitmap2Bytes(bm), bm.getWidth(), bm.getHeight());
	    		
	    		
	    		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
	    		QRCodeReader reader = new QRCodeReader();
	    		Result result;
	    		
	    		try {
	    			
	    			result = reader.decode(bitmap1, hints);
	    			handleDecode(result, null);
	    			
	    		} catch (NotFoundException e) {
//	    			Toast.makeText(this, getString(R.string.notQR), Toast.LENGTH_LONG).show();
	    			handleDecode(null, null);
	    		} catch (ChecksumException e) {
//	    			Toast.makeText(this, getString(R.string.notQR), Toast.LENGTH_LONG).show();
	    			handleDecode(null, null);
	    		} catch (FormatException e) {
//	    			Toast.makeText(this, getString(R.string.notQR), Toast.LENGTH_LONG).show();
	    			handleDecode(null, null);
	    		}
	            
		    }catch (IOException e) {
		    	Toast.makeText(this, getString(R.string.readFileErr), Toast.LENGTH_LONG).show();
	        }

		 }
		
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public Handler getHandler() {
		return handler;
	}

	@Override
	/**
	 * 处理扫描结果
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		
		if (result==null) {
			
		}else {
			
			String resultString = result.getText();
			Intent intent = new Intent();
			
			if ("".equals(resultString)) {
				
				failJump(intent);
				
			}else {
				
//				Toast.makeText(QRScanAty.this, "Scan success!", Toast.LENGTH_SHORT).show();
				
				if (checkQRValid(resultString)) {
					
					intent.setAction("");
					startActivity(intent);
					
				}else {
					
					failJump(intent);
				}
			}
		}
		
		finish();
	}

	private void failJump(Intent intent) {
		intent.setAction(ConstantKey.SCANFAIL_ACTION);
		startActivity(intent);
	}

	private boolean checkQRValid(String result) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
		}
	}
	
	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}
	
	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}
