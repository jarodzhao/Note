package zhao.jarod.note.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import zhao.jarod.note.domain.Note;

import static zhao.jarod.note.database.NoteDbSchema.*;

/**
 * @Description:处理游标对象 Cursor
 * @Author:zhaoht
 * @Date: Created in 23:28 2017/10/1
 * @Modified By:
 */
public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {
        String uuidString = getString(getColumnIndex(NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteTable.Cols.TITLE));
        String content = getString(getColumnIndex(NoteTable.Cols.CONTENT));
        long date = getLong(getColumnIndex(NoteTable.Cols.DATE));
        int isFavorited = getInt(getColumnIndex(NoteTable.Cols.FAVORITED));

        Note note = new Note(UUID.fromString(uuidString));
        note.setTitle(title);
        note.setContent(content);
        note.setDate(new Date(date));
        note.setFavorited(isFavorited != 0);    //经典方法，值得记住

        return note;
    }
}
