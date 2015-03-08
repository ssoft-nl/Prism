package nl.ssoft.prism;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by erispre on 3/8/15.
 */
public class ProviderOpenHelper extends SQLiteOpenHelper {

    private static final String LOG_KEY = "ProviderOpenHelper";

    public static final String DATABASE_NAME = "Prism";
    private static final int DATABASE_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String KEY_SENDER_ID = "sender_id";
    public static final String KEY_REGISTRATION_ID = "registration_id";
    public static final String KEY_NAME = "name";

    public static final String PROVIDERS_TABLE_NAME = "Providers";
    private static final String PROVIDERS_TABLE_CREATE =
            "CREATE TABLE " + PROVIDERS_TABLE_NAME + " (" +
                    KEY_ID + " INT PRIMARY KEY AUTOINCREMENT, " +
                    KEY_SENDER_ID + " TEXT NOT NULL, " +
                    KEY_REGISTRATION_ID + " TEXT, " +
                    KEY_NAME + " TEXT NOT NULL);";

    ProviderOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROVIDERS_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOG_KEY, "Database was upgraded!");
    }
}
