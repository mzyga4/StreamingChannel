package aa.mzyga4.aastreamingchannel.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

public class AppStarter {
    public static void startApp(Activity activity, String packageName) {
        Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent != null) { //null pointer check in case package name was not found
            activity.finish();
            activity.startActivity(launchIntent);
        }
    }

    public static void startApp(Activity activity, String packageName, String activityName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(packageName, activityName));

        activity.finish();
        activity.startActivity(intent);
    }
}