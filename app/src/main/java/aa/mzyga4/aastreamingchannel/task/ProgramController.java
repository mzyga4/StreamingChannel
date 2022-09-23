package aa.mzyga4.aastreamingchannel.task;

import static aa.mzyga4.aastreamingchannel.model.KeyValues.ACTIVITY_NAME;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_ACTIVITY_NAME;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_PACKAGE_NAME;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.PACKAGE_NAME;
import static aa.mzyga4.aastreamingchannel.util.DrawableUtils.getServiceDrawable;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;

import aa.mzyga4.aastreamingchannel.model.Program;

public class ProgramController {
    private static final String TAG = "ProgramController";

    public static long addProgram(Context context, long channelId, Program program) {
        int drawableRes = getServiceDrawable(context,
                program.getPrefix(), program.getLastElementIndex());

        Uri logoUri = Uri.parse("android.resource://" + "aa.mzyga4.aastreamingchannel"
                + "/" + drawableRes);

        Intent intent = program.getActivityName().isEmpty()
                ? setIntent(program.getPackageName())
                : setIntent(program.getPackageName(), program.getActivityName());

        @SuppressLint("RestrictedApi") PreviewProgram content = new PreviewProgram.Builder()
                .setChannelId(channelId)
                .setType(TvContractCompat.PreviewPrograms.TYPE_TV_SERIES)
                .setTitle(program.getTitle())
                .setDescription(program.getTitle())
                .setAuthor("Mateusz")
                .setIntent(intent)
                .setPosterArtUri(logoUri)
                .build();

        Uri contentUri = context.getContentResolver().insert(
                TvContractCompat.PreviewPrograms.CONTENT_URI,
                content.toContentValues());

        long programId = ContentUris.parseId(contentUri);
        Log.d(TAG, "AddChannelContent:" + content + " with id [" + programId + "]");
        return programId;
    }

    public static void updateProgram(Context context, long channelId, String key, Program program) {
        int drawableRes = getServiceDrawable(context,
                program.getPrefix(), program.getLastElementIndex());

        Uri logoUri = Uri.parse("android.resource://" + "aa.mzyga4.aastreamingchannel"
                + "/" + drawableRes);

        Intent intent = program.getActivityName().isEmpty()
                ? setIntent(program.getPackageName())
                : setIntent(program.getPackageName(), program.getActivityName());

        @SuppressLint("RestrictedApi") PreviewProgram content = new PreviewProgram.Builder()
                .setChannelId(channelId)
                .setType(TvContractCompat.PreviewPrograms.TYPE_TV_SERIES)
                .setTitle(program.getTitle())
                .setDescription(program.getTitle())
                .setAuthor("Mateusz")
                .setIntent(intent)
                .setPosterArtUri(logoUri)
                .build();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        long programId = prefs.getLong(key, -1);

        if(programId != -1){
            context.getContentResolver().update(
                    TvContractCompat.buildPreviewProgramUri(programId),
                    content.toContentValues(), null, null);
        }
    }

    public static void deleteProgram(Context context, long programId){
        if(programId != -1L)
            context.getContentResolver().delete(TvContractCompat.buildPreviewProgramUri(programId),
                    null, null);
    }

    private static Intent setIntent(String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(PACKAGE_NAME, ACTIVITY_NAME));
        intent.putExtra(KEY_PACKAGE_NAME, packageName);
        return intent;
    }

    private static Intent setIntent(String packageName, String activityName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(PACKAGE_NAME, ACTIVITY_NAME));
        intent.putExtra(KEY_PACKAGE_NAME, packageName);
        intent.putExtra(KEY_ACTIVITY_NAME, activityName);
        return intent;
    }
}
