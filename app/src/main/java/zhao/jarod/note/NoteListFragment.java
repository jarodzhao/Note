package zhao.jarod.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    private boolean mSubtitleVisible;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";    //旋转设备是保存子标题状态

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //Fragment 激活菜单
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        mNoteRecyclerView = (RecyclerView) view.findViewById(R.id.note_recyler_view);

        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            //旋转设备是保存子标题状态
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_note_list, menu);

        //标识子标题菜单项
        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //工具栏
        switch (item.getItemId()) {
            //添加新日记
            case R.id.menu_item_new_note:
                Note note = new Note();
                NoteLab.get(getActivity()).addNote(note);
                Intent intent = NotePagerActivity.newIntent(getActivity(), note.getId());
                startActivity(intent);
                return true;
            //显示/隐藏工具栏子标题
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubTitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //旋转设备是保存子标题状态
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    /**
     * 显示子标题
     */
    private void updateSubTitle() {
        NoteLab noteLab = NoteLab.get(getActivity());
        int noteCount = noteLab.getNotes().size();
        String subtitle = getString(R.string.subtitle_format, noteCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle); //格式：%1$s notes
    }

    private void updateUI() {
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getNotes(); //方法名称和书中不一致，是否需要修改？

        if (mAdapter == null) {
            mAdapter = new NoteAdapter(notes);
            mNoteRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setNotes(notes);   //到底有没有起作用？参考教材 246 页和 github 上前一版本说明
            mAdapter.notifyDataSetChanged();
        }
        updateSubTitle();
    }

    /**
     * 内部类 Holder
     */
    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mContentTextView;
        private CheckBox mFavorited;

        private Note mNote;

        public NoteHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_note_title_text_view);
            mContentTextView = (TextView) itemView.findViewById(R.id.list_item_note_content_text_view);
            mFavorited = (CheckBox) itemView.findViewById(R.id.list_item_note_favorited_check_box);
        }

        /**
         * 服务于 onBindViewHolder 的方法
         *
         * @param note
         */
        public void bindNote(Note note) {
            mNote = note;

            mTitleTextView.setText(mNote.getTitle());
            mContentTextView.setText(mNote.getContent());
            mFavorited.setChecked(mNote.isFavorited()); //没有初始化该值，所以会返回错误
        }

        /**
         * 明细页面点击后，启动详情页动作
         * @param view
         */
        @Override
        public void onClick(View view) {
            Intent intent = NotePagerActivity.newIntent(getActivity(), mNote.getId());
            startActivity(intent);
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
            View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);

            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note note = mNotes.get(position);
            holder.bindNote(note);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        //列表视图中编辑对象后,到明细页面同步更新
        public void setNotes(List<Note> notes) {
            mNotes = notes;     //参考教材 246 页，bug？！
        }
    }
}
