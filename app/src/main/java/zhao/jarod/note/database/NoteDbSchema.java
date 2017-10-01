package zhao.jarod.note.database;

/**
 * @Description:
 * @Author:zhaoht
 * @Date: Created in 22:40 2017/10/1
 * @Modified By:
 */
public class NoteDbSchema {
    public static final class NoteTable {
        public static final String NAME = "notes"; //表名

        public static final class Cols {
            public static final String UUID = "uuid";   //字段名
            public static final String TITLE = "title";
            public static final String CONTENT = "content";
            public static final String DATE = "date";
            public static final String FAVORITED = "favorited";

        }
    }
}
