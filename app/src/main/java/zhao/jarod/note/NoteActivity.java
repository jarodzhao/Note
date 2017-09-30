package zhao.jarod.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

/**
 * Created by zht on 2017/8/6.
 */
public class NoteActivity extends SingleFragmentActivity {

    //最终被废弃的类，有 NotePagerActivity 替代(使用了 viewpager)

    private static final String EXTRA_NOTE_ID = "zhao.jarod.note.note_id";

    public static Intent newIntent(Context packageContext, UUID noteId) {

        Intent intent = new Intent(packageContext, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);

        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        return NoteFragment.newInstance(noteId);
    }

    //没有删除,导致在 List 中点击项目打开明细时闪退!!
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.dialog_date.activity_fragment);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment == null) {
//            fragment = new NoteFragment();
//            fm.beginTransaction()
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//        }
//    }

}
