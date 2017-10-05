package zhao.jarod.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;
import java.util.UUID;

import zhao.jarod.note.domain.Note;
import zhao.jarod.note.domain.NoteLab;

public class NotePagerActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE_ID = "zhao.jarod.note.note_id";

    private ViewPager mViewPager;
    private List<Note> mNotes;
    private UUID noteId;
    private NoteLab noteLab;

    public static Intent newIntent(Context packageContext, UUID noteId) {
        Intent intent = new Intent(packageContext, NotePagerActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pager);

        noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_note_pager_view_pager);

        noteLab = NoteLab.get(this);
        mNotes = noteLab.getNotes();

        //viewpager 启动，设置 adapter
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Note note = mNotes.get(position);   //每个 pager 对应的对象
                return NoteFragment.newInstance(note.getId());
            }
            @Override
            public int getCount() {
                return mNotes.size();
            }
        });

        //循环所有对象比对id,和传进来的id相符,则设置为viewpager的当前值
        for (int i = 0; i < mNotes.size(); i++) {
            if (mNotes.get(i).getId().equals(noteId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete:
                //删除操作，提交操作申请
                noteLab.deleteNote(noteId.toString());
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
