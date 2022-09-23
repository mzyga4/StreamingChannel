package aa.mzyga4.aastreamingchannel.util;

import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_CHANNELID;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveUtils {
    public static void saveChannelId(Context context, long id){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(KEY_CHANNELID, id);
        editor.apply();
    }

    public static void saveProgramId(Context context, String key, long id){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, id);
        editor.apply();
    }
}
