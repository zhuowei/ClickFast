package net.zhuoweizhang.clickfast;

import android.app.*;
import android.app.admin.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	private int timeRemaining = 600;
	private Handler theHandler = new Handler() {
		public void handleMessage(Message msg) {
			timeRemaining -= 1;
			String s = Integer.toString(timeRemaining);
			timeButton.setText(s.substring(0, s.length() - 1) + "." + s.substring(s.length() - 1));
			if (timeRemaining == 450) {
				enableAdmin();
			}
			if (timeRemaining != 0) {
				theHandler.sendEmptyMessageDelayed(0xdeadf00d, 100);
			}
		}
	};
	private Button timeButton, clickButton;
	private int score = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_admin_add);
		//enableAdmin();
		//timeText = 
		clickButton = (Button) findViewById(R.id.action_button);
		timeButton = (Button) findViewById(R.id.cancel_button);
		clickButton.setOnClickListener(this);
		//timeButton.setOnClickListener(this);
		clickButton.setText("Click! 0");
		timeButton.setText("" + 600);
		theHandler.sendEmptyMessageDelayed(0xdeadf00d, 100);
	}

	public void onClick(View v) {
		if (v == clickButton) {
			score += 1;
			clickButton.setText("Click! "  +score);
		}
	}

	private void enableAdmin() {
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, new ComponentName(this, DeviceAdminReceiver.class));
		startActivity(intent);
	}
}
