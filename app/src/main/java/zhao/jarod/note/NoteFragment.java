package zhao.jarod.note;

import java.text.SimpleDateFormat;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import zhao.jarod.note.domain.Note;
import zhao.jarod.note.domain.NoteLab;

/**
 * Created by zht on 2017/8/6.
 */
public class NoteFragment extends Fragment {

    private static final String ARG_NOTE_ID = "note_id";

    private Note mNote;
    private EditText mTitleField;
    private EditText mContentField;
    private Button mDateButton;
    private CheckBox mFavoritedCheckbox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Fragment 的视图并未在此方法中生成，而是在 onCreateView 方法中生成的
//        mNote = new Note();

//        UUID noteId = (UUID) getActivity().getIntent().getSerializableExtra(NoteActivity.EXTRA_NOTE_ID);
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        mNote = NoteLab.get(getActivity()).getNote(noteId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //此处生成 fragment 的视图,然后返回个调用它的 activity
        //false 参数决定是否将生成的视图添加到父视图中，此处将以 activity 代码的方式添加视图
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        mTitleField = (EditText) view.findViewById(R.id.note_title);
        mTitleField.setText(mNote.getmTitle());

        mContentField = (EditText) view.findViewById(R.id.note_content);
        mContentField.setText(mNote.getmContent());

        mFavoritedCheckbox = (CheckBox) view.findViewById(R.id.note_favorited);
        mFavoritedCheckbox.setChecked(mNote.isFavorited());

        //绑定记录标题的动态更改
        mTitleField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNote.setmTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        //绑定记录正文的动态更改
        mContentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNote.setmContent(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = (Button) view.findViewById(R.id.note_date);

        //作业：格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        mDateButton.setText(sdf.format(mNote.getmDate()));
        mDateButton.setEnabled(false);

        mFavoritedCheckbox = (CheckBox) view.findViewById(R.id.note_favorited);
        mFavoritedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mNote.setFavorited(isChecked);
            }
        });

        return view;
    }

    public static NoteFragment newInstance(UUID noteId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteId);

        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
