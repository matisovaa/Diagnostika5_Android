package sk.upjs.ics.diagnostika5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sk.upjs.ics.diagnostika5.databaza.Provider;

public class ZaznamCursorAdapter extends SimpleCursorAdapter {

    public ZaznamCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.zaznam_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView menoText = (TextView) view.findViewById(R.id.menoTextView);
        TextView datumText = (TextView) view.findViewById(R.id.datumTextView);

        String meno = cursor.getString(cursor.getColumnIndex(Provider.Zaznam.MENO));
        menoText.setText(meno);

        long datum = cursor.getInt(cursor.getColumnIndex(Provider.Zaznam.DATUM_A_CAS));
        Date date = new Date(datum*1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd.MM.yyyy HH:mm", Locale.getDefault());

        datumText.setText(dateFormat.format(date));
    }
}
