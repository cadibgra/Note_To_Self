package androidlearning.sweethome.notetoself;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter mNoteAdapter;
    private ListView mListNote;
    private boolean mSound;
    private AnimationOption mAnimOption;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoteAdapter = new NoteAdapter(this);
        mListNote = (ListView) findViewById(R.id.listView);
        mListNote.setAdapter(mNoteAdapter);
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
        mListNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create a temporary note which is a reference to the note that has been just clicked.
                Note tempNote = (Note) mNoteAdapter.getItem(position);

                //create a new dialog window.
                DialogShowNote dialogShowNote = new DialogShowNote();

                //Send a reference to the note the be shown.
                dialogShowNote.sendNoteSelected(tempNote);

                //Show the dialog with the note in it.
                dialogShowNote.show(getFragmentManager(), "");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_add) {
            DialogNewNote dialogNewNote = new DialogNewNote();
            dialogNewNote.show(getFragmentManager(), "");
            return Boolean.TRUE;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createNewNote(final Note note) {
        mNoteAdapter.addNote(note);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPrefs = getSharedPreferences(SettingsProperties.APP_NAME, MODE_PRIVATE);
        mSound = mPrefs.getBoolean(SettingsProperties.SOUND, Boolean.TRUE);
        mAnimOption = AnimationOption.valueOf(mPrefs.getInt(SettingsProperties.ANIM_OPTION, AnimationOption.FAST.value()));
    }

    @Override
    protected void onPause() {
        super.onPause();

        mNoteAdapter.saveNotes();
    }
}
