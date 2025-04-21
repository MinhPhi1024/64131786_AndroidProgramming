package ntu.leminhphi.example.fragmentex_replace;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FooterFragment extends Fragment {

    public FooterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_footer, container, false);
        //tìm các nút bấm
        Button btnFragment1 = view.findViewById(R.id.btnFragment1);
        Button btnFragment2 = view.findViewById(R.id.btnFragment2);
        Button btnFragment3 = view.findViewById(R.id.btnFragment3);

        //Tìm kiếm frame
        FragmentManager fragmentManager = getParentFragmentManager();
        //Lắng nghe
        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new Fragment1())
                        .commit();
            }
        });
        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new Fragment1())
                        .commit();
            }
        });
        btnFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new Fragment1())
                        .commit();
            }
        });



        return view;
    }
}