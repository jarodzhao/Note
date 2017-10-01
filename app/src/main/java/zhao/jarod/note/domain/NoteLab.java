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
    }

    public List<Note> getNotes() {
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

    public void addNote(Note note) {
        mNotes.add(note);
    }
}
