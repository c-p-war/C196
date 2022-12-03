package com.example.c196;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c196.Entity.Note;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteItemView;

        private NoteViewHolder(View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.textView);
//            Below controls what happens when the item is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Note current = mNotes.get(position);
                    // Now that we have the position of the clicked product in the list, we can do all types of cool stuff
                    // Like go to another screen
                    Intent intent = new Intent(context, NotesDetails.class);
                    // We can add information and key values on the intent
                    intent.putExtra("noteID", current.getNoteID());
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("noteTitle", current.getNoteTitle());
                    intent.putExtra("noteText", current.getNoteText() );
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Note> mNotes;
    private final Context context;
    private final LayoutInflater mInflater;

    public NoteAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    // This is where we put things on the textview
    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteAdapter.NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            String title = current.getNoteTitle();
            // TODO: NullPointerException
            holder.noteItemView.setText(title);
        } else {
            holder.noteItemView.setText("No Title");
        }
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // Check for null
        if (mNotes != null) {
            return mNotes.size();
        } else {
            return 0;
        }
    }
}
