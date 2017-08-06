package zhao.jarod.note;

import android.support.v4.app.Fragment;

/**
 * Created by zht on 2017/8/7.
 */
public class NoteListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NoteListFragment();
    }
}
