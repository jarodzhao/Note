package zhao.jarod.note.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import zhao.jarod.note.database.NoteBaseHelper;
import zhao.jarod.note.database.NoteCursorWrapper;
import zhao.jarod.note.database.NoteDbSchema;

import static zhao.jarod.note.database.NoteDbSchema.*;

/**
 * 单例模式
 * Created by zht on 2017/8/7.
 */
public class NoteLab {

    private static NoteLab sNoteLab;        //静态变量 s 开头

    private List<Note> mNotes;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
        mNotes = new ArrayList<>();
    }

    public List<Note> getNotes() {

        List<Note> notes = new ArrayList<>();

        NoteCursorWrapper cursor = queryNotes(null, null);  //无条件参数，查询所有记录放在单例中

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return notes;
    }

    public Note getNote(UUID id) {

        NoteCursorWrapper cursor = queryNotes(
                NoteTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getNote();
        } finally {
            cursor.close();
        }
    }

    public void addNote(Note note) {
        ContentValues values = getContentValues(note);
        mDatabase.insert(NoteTable.NAME, null, values);
    }

    public void updateNote(Note note) {
        String uuidString = note.getId().toString();
        ContentValues values = getContentValues(note);
        mDatabase.update(NoteTable.NAME, values, NoteTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs) {

        Cursor cursor = mDatabase.query(
                NoteTable.NAME,
                null,       //要查询的列, null 值标识所有列
                whereClause,
                whereArgs,
                null,       //groupBy
                null,       //having
                null        //orderBy
        );

        return new NoteCursorWrapper(cursor);   //通过 NoteCursorWrapper 封装 cursor
    }

    private static ContentValues getContentValues(Note note) {

        ContentValues values = new ContentValues();

        values.put(NoteTable.Cols.UUID, note.getId().toString());
        values.put(NoteTable.Cols.TITLE, note.getTitle());
        values.put(NoteTable.Cols.CONTENT, note.getContent());
        values.put(NoteTable.Cols.FAVORITED, note.isFavorited() ? 1 : 0);
        values.put(NoteTable.Cols.DATE, note.getDate().getTime());

        return values;
    }
}
