package aa.mzyga4.aastreamingchannel;

import static aa.mzyga4.aastreamingchannel.Data.p_DISNEY;
import static aa.mzyga4.aastreamingchannel.Data.p_HBO;
import static aa.mzyga4.aastreamingchannel.Data.p_NETFLIX;
import static aa.mzyga4.aastreamingchannel.Data.p_POLSAT;
import static aa.mzyga4.aastreamingchannel.Data.p_PRIME;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_ACTIVITY_NAME;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_CHANNELID;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_PACKAGE_NAME;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_PROGRAMID_DISNEY;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_PROGRAMID_HBO;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_PROGRAMID_NETFLIX;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_PROGRAMID_POLSAT;
import static aa.mzyga4.aastreamingchannel.model.KeyValues.KEY_PROGRAMID_PRIME;
import static aa.mzyga4.aastreamingchannel.task.ProgramController.addProgram;
import static aa.mzyga4.aastreamingchannel.task.ProgramController.deleteProgram;
import static aa.mzyga4.aastreamingchannel.task.ProgramController.updateProgram;
import static aa.mzyga4.aastreamingchannel.util.SaveUtils.saveProgramId;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.tvprovider.media.tv.TvContractCompat;

import aa.mzyga4.aastreamingchannel.databinding.ActivityMainBinding;
import aa.mzyga4.aastreamingchannel.task.ChannelController;
import aa.mzyga4.aastreamingchannel.util.AppStarter;

public class MainActivity extends AppCompatActivity {
    public static long channelId = -1L;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getExtras() != null) {
            String packageName = getIntent().getStringExtra(KEY_PACKAGE_NAME);
            String activityName = getIntent().getStringExtra(KEY_ACTIVITY_NAME);

            if(packageName != null && !packageName.isEmpty()){
                try{
                    updatePrograms(this);
                    startApp(packageName, activityName);
                }catch (Exception e){
                    startApp(packageName, activityName);
                }
            }
        }else{
            continueOnCreate();
        }
    }

    private void startApp(String packageName, String activityName){
        if(activityName != null && !activityName.isEmpty())
            AppStarter.startApp(this, packageName, activityName);
        else
            AppStarter.startApp(this, packageName);
    }

    private void continueOnCreate(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            ChannelController channelController = new ChannelController(this);
            channelController.addChannelRow(() -> preparePrograms(this));
        });

        binding.button2.setOnClickListener(v -> deletePrograms(this));
        binding.button3.setOnClickListener(v -> preparePrograms(this));
    }

    public static void preparePrograms(Context ctx) {
        deletePrograms(ctx);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);
        channelId = pref.getLong(KEY_CHANNELID, -1L);

        if(channelId != -1L){
            saveProgramId(ctx, KEY_PROGRAMID_NETFLIX, addProgram(ctx, channelId, p_NETFLIX));
            saveProgramId(ctx, KEY_PROGRAMID_HBO, addProgram(ctx, channelId, p_HBO));
            saveProgramId(ctx, KEY_PROGRAMID_PRIME, addProgram(ctx, channelId, p_PRIME));
            saveProgramId(ctx, KEY_PROGRAMID_DISNEY, addProgram(ctx, channelId, p_DISNEY));
            saveProgramId(ctx, KEY_PROGRAMID_POLSAT, addProgram(ctx, channelId, p_POLSAT));
        }
    }

    public static void updatePrograms(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        channelId = pref.getLong(KEY_CHANNELID, -1L);

        if(channelId != -1L){
            updateProgram(context, channelId, KEY_PROGRAMID_NETFLIX,  p_NETFLIX);
            updateProgram(context, channelId, KEY_PROGRAMID_HBO, p_HBO);
            updateProgram(context, channelId, KEY_PROGRAMID_PRIME, p_PRIME);
            updateProgram(context, channelId, KEY_PROGRAMID_DISNEY, p_DISNEY);
            updateProgram(context, channelId, KEY_PROGRAMID_POLSAT, p_POLSAT);
        }
    }

    private static void deletePrograms(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        long netflixChannel = pref.getLong(KEY_PROGRAMID_NETFLIX, -1L);
        long primeChannel = pref.getLong(KEY_PROGRAMID_PRIME, -1L);
        long disneyChannel = pref.getLong(KEY_PROGRAMID_DISNEY, -1L);
        long hboChannel = pref.getLong(KEY_PROGRAMID_HBO, -1L);
        long polsatChannel = pref.getLong(KEY_PROGRAMID_POLSAT, -1L);

        deleteProgram(context, netflixChannel);
        deleteProgram(context, hboChannel);
        deleteProgram(context, primeChannel);
        deleteProgram(context, disneyChannel);
        deleteProgram(context, polsatChannel);
    }
}