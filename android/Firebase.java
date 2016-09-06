package org.godotengine.godot;

import android.app.Activity;
import android.content.Intent;
import javax.microedition.khronos.opengles.GL10;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class Firebase extends Godot.SingletonBase {
    Activity activity;
    static public Godot.SingletonBase initialize(Activity activity) {
        return new Firebase(activity);
    }

    public Firebase(Activity activity) {
        //register class name and functions to bind
        registerClass("Firebase", new String[]{"initializeApp", "addOnValueEventListener"});

        activity.runOnUiThread(new Runnable() {
                public void run() {}
        });
        this.activity = activity;
    }

    public void initializeApp(String appId, String apiKey, String databaseUrl, String storageBucket) {
      FirebaseOptions opts = new FirebaseOptions.Builder()
              .setApplicationId(appId)
              .setApiKey(apiKey)
              .setDatabaseUrl(databaseUrl)
              .setStorageBucket(storageBucket)
              .build();
      FirebaseApp app = FirebaseApp.initializeApp(activity, opts);
    }

    public void addOnValueEventListener(final int id, String path) {
      DatabaseReference ref = FirebaseDatabase.getInstance().getReference(path);
      ref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            String data = dataSnapshot.getValue(String.class);
            System.out.println(data);
            GodotLib.calldeferred(id, "_firebase_on_value", new Object[]{data});
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });
    }

    protected void onMainActivityResult(int requestCode, int resultCode, Intent data) {}

    protected void onMainPause() {}
    protected void onMainResume() {}
    protected void onMainDestroy() {}

    protected void onGLDrawFrame(GL10 gl) {}
    protected void onGLSurfaceChanged(GL10 gl, int width, int height) {}

}
