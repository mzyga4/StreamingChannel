package aa.mzyga4.aastreamingchannel.task;

import static aa.mzyga4.aastreamingchannel.MainActivity.channelId;
import static aa.mzyga4.aastreamingchannel.util.SaveUtils.saveChannelId;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.ChannelLogoUtils;
import androidx.tvprovider.media.tv.TvContractCompat;

import aa.mzyga4.aastreamingchannel.R;

public class ChannelController {
    private static final String TAG = "ChannelController";
    private final Context context;

    public ChannelController(Context context) {
        this.context = context;
    }

    public void addChannelRow(AddChannelCallback callback) {
        Channel channel = new Channel.Builder()
                .setDisplayName("Streaming")
                .setDescription("Streaming")
                .setDisplayNumber("Streaming_001")
                .setType(TvContractCompat.Channels.TYPE_PREVIEW)
                .setInputId("Streaming")
                .setOriginalNetworkId(1)
                .build();

        Uri channelUri = context.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI,
                channel.toContentValues());

        Log.d(TAG, "AddChannelContent:" + channel
                + " channelUri [" + channelUri.toString() + "]");

        channelId = ContentUris.parseId(channelUri);
        saveChannelId(context, channelId);
        Log.d("ChannelID", "id: " + channelId);

        addChannelLogo();
        callback.taskComplete();
    }

    private void addChannelLogo() {
        Bitmap bm = BitmapFactory.decodeResource( context.getResources(), R.drawable.logo );
        ChannelLogoUtils.storeChannelLogo(context, channelId, bm);
    }
}
