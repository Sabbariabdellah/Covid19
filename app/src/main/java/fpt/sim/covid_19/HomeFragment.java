package fpt.sim.covid_19;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    ImageView flags;
    Spinner spinner;
    Button samu;
    Button yakada;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        spinner = view.findViewById(R.id.spinner);
        flags = view.findViewById(R.id.flag);
        samu = view.findViewById(R.id.allo_samu);
        yakada = view.findViewById(R.id.allo_yakada);

        final String yakada_phone = "tel:" + "0801004747".trim();
        final String samu_phone = "tel:" + "141".trim();
        samu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(samu_phone));
                startActivity(intent);
            }
        });

        yakada.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(yakada_phone));
                startActivity(intent);
            }
        });


        spinner.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,countryData.countryNames));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flags.setImageResource(countryData.countryFlags[spinner.getSelectedItemPosition()]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

}
