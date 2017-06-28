package sk.upjs.ics.diagnostika5.ActivityAFragmenty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import sk.upjs.ics.diagnostika5.R;
import sk.upjs.ics.diagnostika5.Zaznam;

public class PriemeryMapaFragment extends Fragment {

    private Zaznam zaznam;

    TextView stredovaLiniaTextView;
    TextView hfTextView;
    TextView lrTextView;
    TextView jinJangTextView;

    GridView mapaEnergetickejAsimetrieGridView;

    private ArrayAdapter<String> adapterMapaGridView;

    public PriemeryMapaFragment() {
        // Required empty public constructor
    }

    public static PriemeryMapaFragment newInstance(Zaznam zaznam) {

        Bundle args = new Bundle();
        args.putSerializable("zaznam", zaznam);

        PriemeryMapaFragment fragment = new PriemeryMapaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_priemery_mapa, container, false);

        if (savedInstanceState != null){
            zaznam = (Zaznam) savedInstanceState.getSerializable("zaznam");
        }else{
            zaznam = new Zaznam();
        }

        stredovaLiniaTextView = (TextView) view.findViewById(R.id.stredovaLiniaTextView);
        hfTextView = (TextView) view.findViewById(R.id.hfTextView);
        lrTextView = (TextView) view.findViewById(R.id.lrTextView);
        jinJangTextView = (TextView) view.findViewById(R.id.jinJangTextView);

        mapaEnergetickejAsimetrieGridView = (GridView) view.findViewById(R.id.mapaEnergetickejAsimetrieGridView);

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
        stredovaLiniaTextView.setText(String.valueOf(zaznam.getStredovaLinia()));
        hfTextView.setText(String.valueOf(zaznam.getHf()));
        lrTextView.setText(String.valueOf(zaznam.getLr()));
        jinJangTextView.setText(String.valueOf(zaznam.getJinJang()));

        adapterMapaGridView = new ArrayAdapter<String>(getContext(), R.layout.grid_item,zaznam.getMapaPreGridView()){
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
    }
}
