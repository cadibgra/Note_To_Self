package androidlearning.sweethome.notetoself;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sweethome on 05/03/16.
 */
public class NoteAdapter extends BaseAdapter {

    private JSONSerializer jsonSerializer;
    private static final String FILE_NAME = "NoteToSelf.json";

    private List<Note> noteList;
    private Activity mContext;

    public NoteAdapter(final Activity context) {
        mContext = context;
        jsonSerializer = new JSONSerializer(FILE_NAME, mContext.getApplicationContext());
        try {
            noteList = jsonSerializer.load();
        } catch (final Exception ex) {
            noteList = new ArrayList<>();
            Log.e("Error loading notes: ", "", ex);
        }
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Has view been inflated already
        if (convertView == null) {
            //if not, do so here
            //First create a LayoutInflater
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            //The false parameter is necessary because of the way we want to use listitem.
        } //end if

        //Grab a reference to all our TextView and ImageView widgets
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
        ImageView imageViewImportant = (ImageView) convertView.findViewById(R.id.imageViewImportant);
        ImageView imageViewTodo = (ImageView) convertView.findViewById(R.id.imageViewTodo);
        ImageView imageViewIdea = (ImageView) convertView.findViewById(R.id.imageViewIdea);


        //Hide any ImageView widgets that are not relevant
        Note tempNote = noteList.get(position);
        Log.d("Note information", tempNote.toString());

        if (!tempNote.isImportant()) {
            imageViewImportant.setVisibility(View.GONE);
        } else {
            imageViewImportant.setVisibility(View.VISIBLE);
        }

        if (!tempNote.isIdea()) {
            imageViewIdea.setVisibility(View.GONE);
        } else {
            imageViewIdea.setVisibility(View.VISIBLE);
        }

        if (!tempNote.isTodo()) {
            imageViewTodo.setVisibility(View.GONE);
        } else {
            imageViewTodo.setVisibility(View.VISIBLE);
        }

        //Add the text to the heading and description
        txtTitle.setText(tempNote.getTitle());
        txtDescription.setText(tempNote.getDescription());

        return convertView;
    }

    public void addNote(final Note newNote) {
        noteList.add(newNote);
        notifyDataSetChanged();
    }

    public void saveNotes() {
        try {
            if (noteList != null && noteList.size() > 0) {
                jsonSerializer.save(noteList);
            }
        } catch (final Exception ex) {
            Log.e("Error Saving Notes","", ex);
        }
    }
}
