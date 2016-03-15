package androidlearning.sweethome.notetoself;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sweethome on 13/03/16.
 */
class JSONSerializer {
    private String mFileName;
    private Context mContext;

    public JSONSerializer(final String mFileName, final Context mContext) {
        this.mFileName = mFileName;
        this.mContext = mContext;
    }

    void save(final List<Note> notes) throws IOException, JSONException {

        //Make an array in JSON format
        JSONArray jsonArray = new JSONArray();

        //And load it with the notes
        for (Note note : notes) {
            jsonArray.put(note.convertToJSON());
        }

        //Now write it to the private diskspace of our app
        Writer writer = null;
        try {
            OutputStream outputStream = mContext.openFileOutput(mFileName, mContext.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(jsonArray.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    List<Note> load() throws JSONException, IOException {
        List<Note> noteList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream inputStream = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonStringBuilder.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                noteList.add(new Note(jsonArray.getJSONObject(i)));
            }
        } catch (final FileNotFoundException ex) {
            Log.e("Exception", "Exception occured while trying to read file", ex);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return noteList;
    }
}
