package sk.upjs.ics.diagnostika5.databaza;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ZaznamyContentProvider extends ContentProvider {

    private DatabaseOpenHelper openHelper;

    public ZaznamyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

                long id = ContentUris.parseId(uri);
                int affectedRows = openHelper.getWritableDatabase()
                        .delete(Provider.Zaznam.TABLE_NAME, Provider.Zaznam._ID + " = " + id, null);
                getContext().getContentResolver().notifyChange(Provider.CONTENT_URI, null);
                return affectedRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = this.openHelper.getWritableDatabase();
        long id = db.insert(Provider.Zaznam.TABLE_NAME, null, values);
        getContext().getContentResolver().notifyChange(Provider.CONTENT_URI, null);
        return ContentUris.withAppendedId(Provider.CONTENT_URI, id);

    }

    @Override
    public boolean onCreate() {
        openHelper = new DatabaseOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.query(Provider.Zaznam.TABLE_NAME,
                null,null,null,null,null,null);

        // aby pocuvalo na zmeny
        cursor.setNotificationUri(getContext().getContentResolver(), Provider.CONTENT_URI);

        return cursor;
    }

    private Cursor findById(long id) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        String selection = Provider.Zaznam._ID + "=" + id;
        return db.query(Provider.Zaznam.TABLE_NAME,
                null, selection, null, null, null, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
