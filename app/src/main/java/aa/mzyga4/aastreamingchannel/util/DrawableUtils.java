package aa.mzyga4.aastreamingchannel.util;

import android.content.Context;

import java.util.Random;

public class DrawableUtils {
    public static synchronized
    int getServiceDrawable(Context context, String prefix, int lastElementIndex){
        final int random = new Random().nextInt(lastElementIndex + 1);
        return getDrawableById(context, prefix + random);
    }

    public static synchronized
    int getDrawableById(Context context, String drawableName){
        return context.getResources().getIdentifier(drawableName,
                "drawable", context.getPackageName());
    }
}
