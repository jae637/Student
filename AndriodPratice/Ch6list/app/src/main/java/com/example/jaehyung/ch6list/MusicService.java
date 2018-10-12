package com.example.jaehyung.ch6list;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by Jaehyung on 2017-06-12.
 */

public class MusicService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();

        player=MediaPlayer.create(this,R.raw.music);
        player.setLooping(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }
}
