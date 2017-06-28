package sk.upjs.ics.diagnostika5.ActivityAFragmenty;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import sk.upjs.ics.diagnostika5.R;
import sk.upjs.ics.diagnostika5.Zaznam;


public class HodnotyFragment extends Fragment {

    private Zaznam zaznam;

    private TextView menoDetailTextView;
    private TextView datumDetailTextView;
    private TextView poznamkaDetailTextView;

    private GridView hodnotyDetailGridView;

    private ArrayAdapter<String> adapterHodnotyGridView;

    public HodnotyFragment() {
        // Required empty public constructor
    }

    public static HodnotyFragment newInstance(Zaznam zaznam) {

        Bundle args = new Bundle();
        args.putSerializable("zaznam", zaznam);

        HodnotyFragment fragment = new HodnotyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hodnoty, container, false);

        if (savedInstanceState != null){
            zaznam = (Zaznam) savedInstanceState.getSerializable("zaznam");
        }else{
            zaznam = new Zaznam();
        }

        menoDetailTextView = (TextView) view.findViewById(R.id.menoDetailTextView);
        datumDetailTextView = (TextView) view.findViewById(R.id.datumDetailTextView);
        poznamkaDetailTextView = (TextView) view.findViewById(R.id.poznamkaDetailTextView);

        hodnotyDetailGridView = (GridView) view.findViewById(R.id.hodnotyDetailGridView);

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
        menoDetailTextView.setText(zaznam.getMeno());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd.MM.yyyy HH:mm", Locale.getDefault());
        datumDetailTextView.setText(dateFormat.format(zaznam.getDatumACas()));
        poznamkaDetailTextView.setText(zaznam.getPoznamka());

        adapterHodnotyGridView = new ArrayAdapter<>(getContext(), R.layout.grid_item,zaznam.getHodnotyPreGridView());
        hodnotyDetailGridView.setAdapter(adapterHodnotyGridView);
    }
}
