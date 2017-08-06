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
        for(int i=0;i<20;i++) {
            Note note = new Note();
            note.setmTitle("测试数据标题 #" + i);
            note.setmContent("测试数据正文内容 #" + i);
            note.setFavorited(i % 2 == 0);
            mNotes.add(note);
        }
    }

    public List<Note> getmNotes() {
        return mNotes;
    }

    public Note getNote(UUID uuid) {
        for (Note note : mNotes) {
            if (note.getmId().equals(uuid)) {
                return note;
            }
        }
        return null;
    }
}
