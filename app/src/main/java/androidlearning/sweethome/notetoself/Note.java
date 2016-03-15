package androidlearning.sweethome.notetoself;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sweethome on 25/02/16.
 */
public final class Note {
    private String mTitle;
    private String mDescription;
    private boolean mIdea;
    private boolean mTodo;
    private boolean mImportant;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea";
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";

    public Note(final String mTitle, final String mDescription, final boolean mIdea, final boolean mTodo, final boolean mImportant) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mIdea = mIdea;
        this.mTodo = mTodo;
        this.mImportant = mImportant;
    }

    public Note() {
    }

    public Note(JSONObject jsonObject) throws JSONException {
        mTitle = jsonObject.getString(JSON_TITLE);
        mDescription = jsonObject.getString(JSON_DESCRIPTION);
        mIdea = jsonObject.getBoolean(JSON_IDEA);
        mTodo = jsonObject.getBoolean(JSON_TODO);
        mImportant = jsonObject.getBoolean(JSON_IMPORTANT);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isIdea() {
        return mIdea;
    }

    public boolean isTodo() {
        return mTodo;
    }

    public boolean isImportant() {
        return mImportant;
    }

    public JSONObject convertToJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_DESCRIPTION, mDescription);
        jsonObject.put(JSON_IDEA, mIdea);
        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_IMPORTANT, mImportant);

        return jsonObject;
    }

    @Override
    public String toString() {
        return "Note{" +
                "mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mIdea=" + mIdea +
                ", mTodo=" + mTodo +
                ", mImportant=" + mImportant +
                '}';
    }
}
