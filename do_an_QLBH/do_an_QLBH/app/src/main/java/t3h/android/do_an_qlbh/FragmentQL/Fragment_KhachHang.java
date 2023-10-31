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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import t3h.android.do_an_qlbh.AdapteData.KhachHang_Adapter;
import t3h.android.do_an_qlbh.Entity.KhachHang;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.util.ArrayList;

public class Fragment_KhachHang extends Fragment {

    EditText edHoTen,edSDT,edDiaChi;
    TextView tvNgaySinh,tvGioiTinh;
    Button btnChonNS,btnNam,btnNu,btnSave,btnADD;
    MessageError messageError;

    Context contextKH;

    String error;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_kh,container,false);
        btnADD = view.findViewById(R.id.btnAddKH);
        ListView listView = view.findViewById(R.id.lvKH);
        SQLife sqLife = new SQLife(view.getContext());
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        khachHangs.addAll(sqLife.getALLKH());
        KhachHang_Adapter khachHang_adapter = new KhachHang_Adapter(khachHangs);
        listView.setAdapter(khachHang_adapter);
        messageError= new MessageError();
        contextKH = container.getContext();
        btnADD.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            View view1 = inflater.inflate(R.layout.show_add_kh,container,false);
            builder.setView(view1);
            Dialog dialog = builder.create();
            dialog.show();
            findFormKHById(view1);
            btnChonNS.setOnClickListener(v1 -> {
                messageError.DialogDatePicker(v1,tvNgaySinh);
            });
            btnNam.setOnClickListener(v12 -> tvGioiTinh.setText("Nam"));
            btnNu.setOnClickListener(v13 -> tvGioiTinh.setText("Nữ"));
            btnSave.setOnClickListener(v14 -> {
                if(messageError.checkFilledKhachHang(contextKH,edHoTen,tvNgaySinh,edSDT,edDiaChi)){
                    try {
                        String TENKH = edHoTen.getText().toString();
                        int SDT = Integer.parseInt(edSDT.getText().toString());
                        String DiaChi = edDiaChi.getText().toString();
                        String NgaySinh = tvNgaySinh.getText().toString();
                        String GioiTinh = tvGioiTinh.getText().toString();
                        int IMGKH = R.drawable.img_inf4;
                        KhachHang khachHang = new KhachHang(0,TENKH,SDT,DiaChi,NgaySinh,GioiTinh,IMGKH);
                        sqLife.AddKhachhang(khachHang);
                        khachHangs.clear();
                        khachHangs.addAll(sqLife.getALLKH());
                        KhachHang_Adapter khachHang_adapter_ = new KhachHang_Adapter(khachHangs);
                        listView.setAdapter(khachHang_adapter_);
                        khachHang_adapter_.notifyDataSetChanged();
                        dialog.dismiss();

                    }catch (Exception e)
                    {
                       error= "Thêm Khách Hàng thất bại";
                       messageError.OpenDialog(contextKH,error);
                    }
                }
            });
        });
        return view;
    }

    private void findFormKHById(View view1){
        edHoTen = view1.findViewById(R.id.edHT_KH_Add);
        edSDT = view1.findViewById(R.id.edSDT_KH_Add);
        messageError.setInputTypeNumber(edSDT);
        edDiaChi = view1.findViewById(R.id.edDC_KH_Add);
        tvNgaySinh = view1.findViewById(R.id.txtNS_KH_Add);
        tvGioiTinh = view1.findViewById(R.id.txtGT_KH_Add);
        btnChonNS = view1.findViewById(R.id.btnChonNS_KH_Add);
        btnNam = view1.findViewById(R.id.btnGT_KH_Nam_Add);
        btnNu = view1.findViewById(R.id.btnGT_KH_Nu_Add);
        btnSave = view1.findViewById(R.id.btnAddKH_);
    }
}