package androidlearning.sweethome.notetoself;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private boolean mSound;
    private AnimationOption mAnimOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mPrefs = getSharedPreferences(SettingsProperties.APP_NAME, MODE_PRIVATE);
        mEditor = mPrefs.edit();

        mSound = mPrefs.getBoolean(SettingsProperties.SOUND, Boolean.TRUE);

        CheckBox checkBoxSound = (CheckBox) findViewById(R.id.checkBoxSound);
        if (mSound) {
            checkBoxSound.setChecked(Boolean.TRUE);
        } else {
            checkBoxSound.setChecked(Boolean.FALSE);
        }

        checkBoxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("sound = ", "" + mSound);
                Log.i("isChecked = ", "" + isChecked);
                mSound = !mSound;
                mEditor.putBoolean(SettingsProperties.SOUND, mSound);
            }
        });

        mAnimOption = AnimationOption.valueOf(mPrefs.getInt(SettingsProperties.ANIM_OPTION, AnimationOption.FAST.value()));
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        switch (mAnimOption) {
            case FAST:
                radioGroup.check(R.id.radioFast);
                break;
            case SLOW:
                radioGroup.check(R.id.radioSlow);
                break;
            case NONE:
                radioGroup.check(R.id.radioNone);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (rb != null && checkedId >-1) {
                    switch (rb.getId()) {
                        case R.id.radioFast:
                            mAnimOption = AnimationOption.FAST;
                            break;
                        case R.id.radioSlow:
                            mAnimOption = AnimationOption.SLOW;
                            break;
                        case R.id.radioNone:
                            mAnimOption = AnimationOption.NONE;
                            break;
                    }
                    mEditor.putInt(SettingsProperties.ANIM_OPTION, mAnimOption.value());
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Save the settings here
        mEditor.commit();
    }
}
