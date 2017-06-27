package sk.upjs.ics.diagnostika5.databaza;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public interface Provider {

    public static final String DATABASE_NAME = "diagnostika5";

    public static final Uri CONTENT_URI = new Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority("sk.upjs.ics.diagnostika5")
            .appendPath(Zaznam.TABLE_NAME)
            .build();

    public interface Zaznam extends BaseColumns{

        public static final String TABLE_NAME = "zaznam";
        public static final String MENO = "meno";
        public static final String DATUM_A_CAS = "datumACas";
        public static final String POZNAMKA = "poznamka";

        public static final String HL1 = "hl1";
        public static final String HL2 = "hl2";
        public static final String HL3 = "hl3";
        public static final String HL4 = "hl4";
        public static final String HL5 = "hl5";
        public static final String HL6 = "hl6";

        public static final String HR1 = "hr1";
        public static final String HR2 = "hr2";
        public static final String HR3 = "hr3";
        public static final String HR4 = "hr4";
        public static final String HR5 = "hr5";
        public static final String HR6 = "hr6";

        public static final String FL1 = "fl1";
        public static final String FL2 = "fl2";
        public static final String FL3 = "fl3";
        public static final String FL4 = "fl4";
        public static final String FL5 = "fl5";
        public static final String FL6 = "fl6";

        public static final String FR1 = "fr1";
        public static final String FR2 = "fr2";
        public static final String FR3 = "fr3";
        public static final String FR4 = "fr4";
        public static final String FR5 = "fr5";
        public static final String FR6 = "fr6";

    }

}
