package com.example.detectarinternet;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

public class InternetService extends Service{
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "MyServiceChannel";
    private static final String NOTIFICATION_CHANNEL_NAME = "My Service Channel";

    private NetworkChangeReceiver mNetworkChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(NOTIFICATION_ID, createNotification());
        registerNetworkChangeReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChangeReceiver();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        return new Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Mi Servicio")
                .setContentText("El servicio está en ejecución")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();
    }

    private void registerNetworkChangeReceiver() {
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkChangeReceiver, intentFilter);
    }

    private void unregisterNetworkChangeReceiver() {
        if (mNetworkChangeReceiver != null) {
            unregisterReceiver(mNetworkChangeReceiver);
            mNetworkChangeReceiver = null;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            Network network = connectivityManager.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    //showToast("¡Hay conexión a Internet!");
                    //MainActivity mainActivity = new MainActivity();
                    //mainActivity.create();
                    showToast("Sincronizando informacion");
                } else {
                    showToast("Conexión de red sin acceso a Internet.");
                }
            } else {
                //MainActivity mainActivity2 = new MainActivity();
                //mainActivity2.removeAll();
                showToast("No hay conexión de red.");
            }
        }
    }
}
