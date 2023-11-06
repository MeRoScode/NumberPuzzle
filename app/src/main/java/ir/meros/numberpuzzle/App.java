package ir.meros.numberpuzzle;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("Numbers", "پازل اعداد", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("نوتیفیکشن بازی پازل اعداد");
            notificationChannel.enableLights(true);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }

        }

    }
}
