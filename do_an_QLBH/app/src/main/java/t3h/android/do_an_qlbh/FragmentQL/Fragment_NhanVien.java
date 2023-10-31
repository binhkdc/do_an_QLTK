package t3h.android.do_an_qlbh.FragmentQL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import t3h.android.do_an_qlbh.AdapteData.NhanVien_Adapter;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.R;

public class Fragment_NhanVien extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_nv,container,false);
        ListView listView = view.findViewById(R.id.lvNV);
        SQLife sqLife = new SQLife(view.getContext());
        NhanVien_Adapter nhanVien_adapter = new NhanVien_Adapter(sqLife.getALLNV());
        listView.setAdapter(nhanVien_adapter);
        return view;
    }
}
