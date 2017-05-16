package com.eric.savingsmanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.eric.savingsmanager.R;
import com.eric.savingsmanager.manager.NotificationsManager;
import com.eric.savingsmanager.utils.Constants;
import com.eric.savingsmanager.utils.Utils;

/**
 * Created by Hector on 16/05/2017.
 */

public class SavingsManagerReceivers extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            Log.d(Constants.LOG_TAG, "Intent received: " + action);
            switch (action) {
                case Constants.ACTION_DUE_SAVINGS_ITEM_ALARM:
                    String alarmTime = Utils.formatDate(intent.getLongExtra(Constants.INTENT_EXTRA_ALARM_TIME, 0));
                    Log.d(Constants.LOG_TAG, "Alarm time: " + alarmTime);
                    // send a notification to status bar
                    NotificationsManager.sendNotification(context,
                            context.getString(R.string.notification_due_savings_title, alarmTime),
                            context.getString(R.string.notification_due_savings_message));
                    break;
                default:
                    break;
            }
        }
    }
}
