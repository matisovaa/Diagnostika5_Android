package sk.upjs.ics.diagnostika5;

import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import sk.upjs.ics.diagnostika5.databaza.Provider;

public class ZaznamDetailActivity extends AppCompatActivity {

    private Zaznam zaznam;
    @BindView(R.id.menoDetailTextView)
    TextView menoDetailTextView;
    @BindView(R.id.datumDetailTextView)
    TextView datumDetailTextView;
    @BindView(R.id.poznamkaDetailTextView)
    TextView poznamkaDetailTextView;
    @BindView(R.id.hodnotyDetailGridView)
    GridView hodnotyDetailGridView;
    private ArrayAdapter<String> adapterGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaznam_detail);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState != null){
            zaznam = (Zaznam) savedInstanceState.getSerializable("zaznam");
        }else if ((bundle != null) && (zaznam == null)){
            zaznam = (Zaznam) bundle.getSerializable("zaznam");
        }else{
            zaznam = new Zaznam();
        }

        vykreslenieUdajov();

    }

    private void vykreslenieUdajov() {
        menoDetailTextView.setText(zaznam.getMeno());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd.MM.yyyy HH:mm", Locale.getDefault());
        datumDetailTextView.setText(dateFormat.format(zaznam.getDatumACas()));
        poznamkaDetailTextView.setText(zaznam.getPoznamka());

        adapterGridView = new ArrayAdapter<>(this, R.layout.grid_item,zaznam.getHodnotyPreGridView());
        hodnotyDetailGridView.setAdapter(adapterGridView);

        //TODO tabulku vykreslit
        //TODO graf
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("zaznam",zaznam);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zaznam_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.odstranZaznamMenu){

            new AlertDialog.Builder(this)
                    .setMessage("Chcete odstrániť tento záznam?")
                    .setTitle("Odstránenie záznamu")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            odstranZaznam(zaznam.get_id());
                        }
                    })
                    .setNegativeButton("Close", null)
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void odstranZaznam(Integer id) {
        AsyncQueryHandler deleteHandler = new AsyncQueryHandler(getContentResolver()) {
            @Override
            protected void onDeleteComplete(int token, Object cookie, int result) {
                Toast.makeText(ZaznamDetailActivity.this, "Záznam bol zmazany", Toast.LENGTH_SHORT)
                        .show();
            }
        };
        Uri selectedNoteUri = ContentUris.withAppendedId(Provider.CONTENT_URI, id);
        deleteHandler.startDelete(0, null, selectedNoteUri,
                null, null);

        Intent intent = new Intent(getApplicationContext(),ZaznamListActivity.class);
        startActivity(intent);
    }
}
