package zhao.jarod.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zhao.jarod.note.domain.Note;
import zhao.jarod.note.domain.NoteLab;

/**
 * Created by zht on 2017/8/7.
 */
public class NoteListFragment extends Fragment {

    private RecyclerView mNoteRecyclerView;
    private NoteAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        mNoteRecyclerView = (RecyclerView) view.findViewById(R.id.note_recyler_view);

        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getmNotes(); //方法名称和书中不一致，是否需要修改

        mAdapter = new NoteAdapter(notes);
        mNoteRecyclerView.setAdapter(mAdapter);

    }


    /**
     * 内部类 Holder
     */
    private class NoteHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;

        public NoteHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView;
        }
    }

    /**
     * 内部类 Adapter
     */
    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes) {
            mNotes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note note = mNotes.get(position);
            holder.mTitleTextView.setText(note.getmTitle());
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }
}
