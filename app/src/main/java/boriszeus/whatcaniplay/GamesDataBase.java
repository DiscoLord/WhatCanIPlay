package boriszeus.whatcaniplay;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Bo on 08.06.2016.
 */
public class GamesDataBase extends SQLiteAssetHelper {


    private static final String DATABASE_NAME = "gamesBase.db";
    private static final int DATABASE_VERSION = 1;

    public GamesDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
