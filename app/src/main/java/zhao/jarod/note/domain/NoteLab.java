package zhao.jarod.note.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 单例模式
 * Created by zht on 2017/8/7.
 */
public class NoteLab {

    private static NoteLab sNoteLab;        //静态变量 s 开头

    private List<Note> mNotes;


    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context) {
        mNotes = new ArrayList<>();
        for(int i=0;i<200;i++) {
            Note note = new Note();
            note.setTitle("测试数据标题 #" + i);
            note.setContent("测试数据正文内容 #" + i);
            note.setFavorited(i % 2 == 0);
            mNotes.add(note);
        }
    }

    public List<Note> getmNotes() {
        return mNotes;
    }

    public Note getNote(UUID uuid) {
        for (Note note : mNotes) {
            if (note.getId().equals(uuid)) {
                return note;
            }
        }
        return null;
    }
}
