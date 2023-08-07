package com.thuydev.thuyqnph35609_ass;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class cauhinh extends Application {
    public static final String ID="a";

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }

    private void config() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "Thông báo xóa";

            String mota = "Đã xóa một công việc";
            int do_uu_tien = NotificationManager.IMPORTANCE_DEFAULT;

            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            AudioAttributes audioAttributes  = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
            NotificationChannel channel = new NotificationChannel(ID,name,do_uu_tien);
            channel.setDescription(mota);
            channel.setSound(uri,audioAttributes);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
