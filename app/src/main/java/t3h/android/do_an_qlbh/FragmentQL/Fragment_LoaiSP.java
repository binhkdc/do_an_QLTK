package t3h.android.do_an_qlbh.FragmentQL;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import t3h.android.do_an_qlbh.AdapteData.LoaiSP_Adapter;
import t3h.android.do_an_qlbh.Entity.LoaiSP;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.util.ArrayList;

public class Fragment_LoaiSP extends Fragment {

    MessageError messageError;

    String error;
    EditText edTenLSP_ADD;

    Context contextLsp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_loaisp,container,false);
        contextLsp = container.getContext();
        ListView listView = view.findViewById(R.id.lvLoaiSP);
        SQLife sqLife = new SQLife(container.getContext());
        ArrayList<LoaiSP> loaiSPS = sqLife.getALL_LSP();
        LoaiSP_Adapter adapter = new LoaiSP_Adapter(loaiSPS);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Button btnADD = view.findViewById(R.id.btnAddLoaiSP);
        btnADD.setOnClickListener(v -> {
            messageError = new MessageError();
            AlertDialog.Builder  builder = new AlertDialog.Builder(container.getContext());
            View showAdd_LSP = LayoutInflater.from(container.getContext()).inflate(R.layout.show_add_lsp,container,false);
            builder.setView(showAdd_LSP);
            Dialog dialog= builder.create();
            dialog.show();
            edTenLSP_ADD = showAdd_LSP.findViewById(R.id.edTenLSP_ADD);
            Button btnSAVE;
            btnSAVE = showAdd_LSP.findViewById(R.id.btnAddLoaiSP_);
            btnSAVE.setOnClickListener(v1 -> {
                if(messageError.checkFilledLoaiSanPham(contextLsp,edTenLSP_ADD)){
                    String NAME_LSP = edTenLSP_ADD.getText().toString();
                    LoaiSP loaiSP = new LoaiSP(0,NAME_LSP);
                    sqLife.AddLoaiSP(loaiSP);
                    ArrayList<LoaiSP> loaiSPS1 = sqLife.getALL_LSP();
                    LoaiSP_Adapter adapter1 = new LoaiSP_Adapter(loaiSPS1);
                    listView.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
                    error="Thêm Loại Sản Phẩm thành công!";
                    messageError.OpenDialog(contextLsp,error);
                    dialog.cancel();
                }
            });
        });
        return view;
    }
}
