package sk.upjs.ics.diagnostika5.ActivityAFragmenty;

/*
* Zdroje:
* http://www.android-graphview.org/download-getting-started/
*
* */

import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import sk.upjs.ics.diagnostika5.R;
import sk.upjs.ics.diagnostika5.Zaznam;
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

    @BindView(R.id.stredovaLiniaTextView)
    TextView stredovaLiniaTextView;
    @BindView(R.id.hfTextView)
    TextView hfTextView;
    @BindView(R.id.lrTextView)
    TextView lrTextView;
    @BindView(R.id.jinJangTextView)
    TextView jinJangTextView;

    @BindView(R.id.mapaEnergetickejAsimetrieGridView)
    GridView mapaEnergetickejAsimetrieGridView;

    @BindView(R.id.grafView)
    GraphView grafView;

    private ArrayAdapter<String> adapterHodnotyGridView;
    private ArrayAdapter<String> adapterMapaGridView;

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

        adapterHodnotyGridView = new ArrayAdapter<>(this, R.layout.grid_item,zaznam.getHodnotyPreGridView());
        hodnotyDetailGridView.setAdapter(adapterHodnotyGridView);

        stredovaLiniaTextView.setText(String.valueOf(zaznam.getStredovaLinia()));
        hfTextView.setText(String.valueOf(zaznam.getHf()));
        lrTextView.setText(String.valueOf(zaznam.getLr()));
        jinJangTextView.setText(String.valueOf(zaznam.getJinJang()));

        adapterMapaGridView = new ArrayAdapter<String>(this, R.layout.grid_item,zaznam.getMapaPreGridView()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view;

                try{
                    int cislo = Integer.parseInt(text.getText().toString());
                    if(cislo<0){
                        text.setTextColor(Color.RED);
                    }
                } catch (NumberFormatException e) {
                    // nezmeni sa obsah policka
                }
                return text;
            }
        };
        mapaEnergetickejAsimetrieGridView.setAdapter(adapterMapaGridView);

        LineGraphSeries<DataPoint> seriesSL = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, zaznam.getStredovaLinia()),
                new DataPoint(24, zaznam.getStredovaLinia())
        });
        seriesSL.setColor(Color.RED);
        grafView.addSeries(seriesSL);

        LineGraphSeries<DataPoint> seriesCiaraHore = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, zaznam.getStredovaLinia()+5),
                new DataPoint(24, zaznam.getStredovaLinia()+5)
        });
        seriesCiaraHore.setColor(Color.BLACK);
        grafView.addSeries(seriesCiaraHore);

        LineGraphSeries<DataPoint> seriesCiaraDole = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, zaznam.getStredovaLinia()-5),
                new DataPoint(24, zaznam.getStredovaLinia()-5)
        });
        seriesCiaraDole.setColor(Color.BLACK);
        grafView.addSeries(seriesCiaraDole);

        int[] hodnoty = zaznam.getHodnoty();

        LineGraphSeries<DataPoint> seriesH1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, hodnoty[1]),
                new DataPoint(2, hodnoty[7])
        });
        grafView.addSeries(seriesH1);

        LineGraphSeries<DataPoint> seriesH2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(2, hodnoty[2]),
                new DataPoint(4, hodnoty[8])
        });
        grafView.addSeries(seriesH2);

        LineGraphSeries<DataPoint> seriesH3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(4, hodnoty[3]),
                new DataPoint(6, hodnoty[9])
        });
        grafView.addSeries(seriesH3);

        LineGraphSeries<DataPoint> seriesH4 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(6, hodnoty[4]),
                new DataPoint(8, hodnoty[10])
        });
        grafView.addSeries(seriesH4);

        LineGraphSeries<DataPoint> seriesH5 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(8, hodnoty[5]),
                new DataPoint(10, hodnoty[11])
        });
        grafView.addSeries(seriesH5);

        LineGraphSeries<DataPoint> seriesH6 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(10, hodnoty[6]),
                new DataPoint(12, hodnoty[12])
        });
        grafView.addSeries(seriesH6);

        LineGraphSeries<DataPoint> seriesF1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(12, hodnoty[13]),
                new DataPoint(14, hodnoty[19])
        });
        grafView.addSeries(seriesF1);

        LineGraphSeries<DataPoint> seriesF2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(14, hodnoty[14]),
                new DataPoint(16, hodnoty[20])
        });
        grafView.addSeries(seriesF2);

        LineGraphSeries<DataPoint> seriesF3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(16, hodnoty[15]),
                new DataPoint(18, hodnoty[21])
        });
        grafView.addSeries(seriesF3);

        LineGraphSeries<DataPoint> seriesF4 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(18, hodnoty[16]),
                new DataPoint(20, hodnoty[22])
        });
        grafView.addSeries(seriesF4);

        LineGraphSeries<DataPoint> seriesF5 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(20, hodnoty[17]),
                new DataPoint(22, hodnoty[23])
        });
        grafView.addSeries(seriesF5);

        LineGraphSeries<DataPoint> seriesF6 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(22, hodnoty[18]),
                new DataPoint(24, hodnoty[24])
        });
        grafView.addSeries(seriesF6);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(grafView);
        staticLabelsFormatter.setHorizontalLabels(new String[]
                {"","H1","","H2","","H3","","H4","","H5","","H6"
                        ,"","F1","","F2","","F3","","F4","","F5","","F6",""});
        grafView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        grafView.getViewport().setScrollable(true);
        grafView.getViewport().setScrollableY(true);
        grafView.getViewport().setScalable(true);
        grafView.getViewport().setScalableY(true);

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
