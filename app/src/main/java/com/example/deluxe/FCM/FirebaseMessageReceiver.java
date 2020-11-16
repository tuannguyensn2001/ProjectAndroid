package com.example.deluxe.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.deluxe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class FirebaseMessageReceiver extends FirebaseMessagingService {

	@Override
	public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
		super.onMessageReceived(remoteMessage);


		if (remoteMessage.getData().isEmpty()) {
			showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
		} else {
			showNotification(remoteMessage.getData());
		}


		FirebaseMessaging.getInstance().getToken()
				.addOnCompleteListener(new OnCompleteListener<String>() {
					@Override
					public void onComplete(@NonNull Task<String> task) {
						String token = task.getResult();
					}
				});
	}


	private void showNotification(Map<String, String> data) {
		String title = data.get("title");
		String body = data.get("body");

		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		String NOTIFICAATION_CHANNEL_ID = "example.deluxe.FCM.test";

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel notificationChannel = new NotificationChannel(NOTIFICAATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);
			notificationChannel.setDescription("Tuan Nhokvip");
			notificationChannel.enableLights(true);
			notificationChannel.setLightColor(Color.BLUE);
			notificationChannel.enableLights(true);
			notificationManager.createNotificationChannel(notificationChannel);

		}
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICAATION_CHANNEL_ID);

		notificationBuilder.setAutoCancel(true)
				.setDefaults(Notification.DEFAULT_ALL)
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.drawable.ic_launcher_background)
				.setContentTitle(title)
				.setContentText(body)
				.setContentInfo("Info");

		notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
	}

	private void showNotification(String title, String body) {
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		String NOTIFICAATION_CHANNEL_ID = "example.deluxe.FCM.test";

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel notificationChannel = new NotificationChannel(NOTIFICAATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);
			notificationChannel.setDescription("Tuan Nhokvip");
			notificationChannel.enableLights(true);
			notificationChannel.setLightColor(Color.BLUE);
			notificationChannel.enableLights(true);
			notificationManager.createNotificationChannel(notificationChannel);

		}
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICAATION_CHANNEL_ID);

		notificationBuilder.setAutoCancel(true)
				.setDefaults(Notification.DEFAULT_ALL)
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.drawable.ic_launcher_background)
				.setContentTitle(title)
				.setContentText(body)
				.setContentInfo("Info");

		notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
	}

	@Override
	public void onNewToken(@NonNull String s) {
		super.onNewToken(s);
	}
}