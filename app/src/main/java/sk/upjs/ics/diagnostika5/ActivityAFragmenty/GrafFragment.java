package sk.upjs.ics.diagnostika5.ActivityAFragmenty;

/*
* Zdroje:
* http://www.android-graphview.org/download-getting-started/
*
* */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import sk.upjs.ics.diagnostika5.R;
import sk.upjs.ics.diagnostika5.Zaznam;


public class GrafFragment extends Fragment {

    private Zaznam zaznam;

    private GraphView grafView;

    public GrafFragment() {
        // Required empty public constructor
    }

    public static GrafFragment newInstance(Zaznam zaznam) {

        Bundle args = new Bundle();
        args.putSerializable("zaznam", zaznam);

        GrafFragment fragment = new GrafFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_graf, container, false);

        if (savedInstanceState != null){
            zaznam = (Zaznam) savedInstanceState.getSerializable("zaznam");
        }else{
            zaznam = new Zaznam();
        }

        grafView = (GraphView) view.findViewById(R.id.grafView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getArguments() != null){
            this.zaznam = (Zaznam) getArguments().getSerializable("zaznam");
            inicializujKomponenty();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("zaznam",zaznam);
    }

    private void inicializujKomponenty(){
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
}
