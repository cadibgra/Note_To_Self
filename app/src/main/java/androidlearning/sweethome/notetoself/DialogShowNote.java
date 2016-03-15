package androidlearning.sweethome.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sweethome on 28/02/16.
 */
public class DialogShowNote extends DialogFragment {
    private Note mNote;
    @Override
    public Dialog onCreateDialog(final Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_show_note, null);
        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);
        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());
        ImageView imageViewImportant = (ImageView) dialogView.findViewById(R.id.imageViewImportant);
        ImageView imageViewTodo = (ImageView) dialogView.findViewById(R.id.imageViewTodo);
        ImageView imageViewIdea = (ImageView) dialogView.findViewById(R.id.imageViewIdea);
        if (!mNote.isImportant()) {
            imageViewImportant.setVisibility(View.GONE);
        } else {
            imageViewImportant.setVisibility(View.VISIBLE);
        }
        if (!mNote.isImportant()) {
            imageViewTodo.setVisibility(View.GONE);
        } else {
            imageViewTodo.setVisibility(View.VISIBLE);
        }
        if (!mNote.isIdea()) {
            imageViewIdea.setVisibility(View.GONE);
        } else {
            imageViewIdea.setVisibility(View.VISIBLE);
        }

        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);
        builder.setView(dialogView).setMessage("Your Note");
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return builder.create();
    }

    public void sendNoteSelected(final Note noteSelected) {
        mNote = noteSelected;
    }
}
