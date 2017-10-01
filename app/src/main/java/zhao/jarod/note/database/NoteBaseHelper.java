package zhao.jarod.note.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static zhao.jarod.note.database.NoteDbSchema.*;

/**
 * @Description:
 * @Author:zhaoht
 * @Date: Created in 22:47 2017/10/1
 * @Modified By:
 */
public class NoteBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION =1;
    private static final String DATABASE_NAME = "noteBase.db";

    public NoteBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NoteTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                NoteTable.Cols.UUID + ", " +
                NoteTable.Cols.TITLE + ", " +
                NoteTable.Cols.CONTENT + ", " +
                NoteTable.Cols.DATE + ", " +
                NoteTable.Cols.FAVORITED + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oleVersion, int newVersion) {

    }
}
