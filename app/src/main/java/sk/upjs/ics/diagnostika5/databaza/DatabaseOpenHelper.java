package sk.upjs.ics.diagnostika5.databaza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper{
    public DatabaseOpenHelper(Context context) {
        super(context, Provider.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE %s ( " +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT, " +
                "%s DATETIME, " +
                "%s TEXT, " +

                "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " +
                "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " +
                "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " +
                "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " +
                "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " +
                "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER " +

                ")";

        sql = String.format(sql,
                Provider.Zaznam.TABLE_NAME,
                Provider.Zaznam._ID,
                Provider.Zaznam.MENO,
                Provider.Zaznam.DATUM_A_CAS,
                Provider.Zaznam.POZNAMKA,

                Provider.Zaznam.HL1,
                Provider.Zaznam.HL2,
                Provider.Zaznam.HL3,
                Provider.Zaznam.HL4,
                Provider.Zaznam.HL5,
                Provider.Zaznam.HL6,

                Provider.Zaznam.HR1,
                Provider.Zaznam.HR2,
                Provider.Zaznam.HR3,
                Provider.Zaznam.HR4,
                Provider.Zaznam.HR5,
                Provider.Zaznam.HR6,

                Provider.Zaznam.FL1,
                Provider.Zaznam.FL2,
                Provider.Zaznam.FL3,
                Provider.Zaznam.FL4,
                Provider.Zaznam.FL5,
                Provider.Zaznam.FL6,

                Provider.Zaznam.FR1,
                Provider.Zaznam.FR2,
                Provider.Zaznam.FR3,
                Provider.Zaznam.FR4,
                Provider.Zaznam.FR5,
                Provider.Zaznam.FR6

                );
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // prazne
    }
}
