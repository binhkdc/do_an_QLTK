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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import t3h.android.do_an_qlbh.AdapteData.SanPham_Adapter;
import t3h.android.do_an_qlbh.Entity.LoaiSP;
import t3h.android.do_an_qlbh.Entity.SanPham;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;
import t3h.android.do_an_qlbh.SelectTable.AdapterSelect_LSP;

import java.util.ArrayList;
import java.util.List;

public class Fragment_SanPham extends Fragment {
    int MALOAISP_;

    public int getMALOAISP_() {
        return MALOAISP_;
    }

    public void setMALOAISP_(int MALOAISP_) {
        this.MALOAISP_ = MALOAISP_;
    }

    MessageError messageError;

    String error;

    Context contextSP;

    ListView lvDanhSach;
    Button btnAddSanPham;

    EditText edTenSP, edGiaNhap, edSLN;
    TextView tvNgayNhap, tvMaLoai;
    Button btnChonNgay, btnThemMaLoai, btnSave;

    SearchView searchView;
    ArrayList<SanPham> sanPhams;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_sp, container, false);
        messageError = new MessageError();
        contextSP = container.getContext();
        lvDanhSach = view.findViewById(R.id.lvSP);
        btnAddSanPham = view.findViewById(R.id.btnAddSP);
        searchView = view.findViewById(R.id.searchView);
        SQLife sqLife = new SQLife(view.getContext());
        sanPhams = sqLife.getALLSP();
        lvDanhSach.setAdapter(new SanPham_Adapter(sanPhams));
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        btnAddSanPham.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            View viewShowSP = inflater.inflate(R.layout.show_add_sp, container, false);
            builder.setView(viewShowSP);
            Dialog dialog = builder.create();
            dialog.show();
            findViewById(viewShowSP);

            btnThemMaLoai.setOnClickListener(v1 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v1.getContext());
                View view1 = LayoutInflater.from(v1.getContext()).inflate(R.layout.select_table_maloai, container, false);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                SQLife sqLife1 = new SQLife(view1.getContext());
                ArrayList<LoaiSP> loaiSPS = sqLife1.getALL_LSP();
                if (loaiSPS.size() == 0) {
                    messageError = new MessageError();
                    error = "Bạn hiện chưa có Loại Sản Phẩm nào, vui lòng thêm loại sản phẩm để tiếp tục";
                    messageError.OpenDialog(contextSP, error);
                    dialog1.dismiss();
                }
                AdapterSelect_LSP adapterSelect_lsp = new AdapterSelect_LSP(loaiSPS);
                ListView lvShowLoaiSP = view1.findViewById(R.id.lvShowSelectLSP);
                lvShowLoaiSP.setAdapter(adapterSelect_lsp);
                lvShowLoaiSP.setOnItemClickListener((parent, view2, position, id) -> {
                    tvMaLoai.setText(loaiSPS.get(position).getMaLoai() + "");
                    dialog1.dismiss();
                });

            });
            btnChonNgay.setOnClickListener(v12 -> {
                messageError.DialogDatePicker(v12, tvNgayNhap);
            });
            btnSave.setOnClickListener(v13 -> {
                if (messageError.checkFilledSanPham(contextSP, edTenSP, edGiaNhap, tvNgayNhap, edSLN, tvMaLoai)) {
                    try {
                        String TenSP = edTenSP.getText().toString();
                        int SLN = Integer.parseInt(edSLN.getText().toString());
                        int GIANHAP = Integer.parseInt(edGiaNhap.getText().toString());
                        String NgayNhap = tvNgayNhap.getText().toString();
                        int MALOAI = Integer.parseInt(tvMaLoai.getText().toString());

                        SanPham sanPham = new SanPham(0, TenSP, GIANHAP, NgayNhap, SLN, 0, MALOAI);
                        sqLife.AddSP(sanPham);
                        sanPhams.clear();
                        sanPhams.addAll(sqLife.getALLSP());
                        lvDanhSach.setAdapter(new SanPham_Adapter(sanPhams));
                        error = "Thêm sản phẩm thành công";
                        messageError.OpenDialog(contextSP, error);
                        dialog.dismiss();
                    } catch (Exception e) {
                        error = "Thêm sản phẩm thất bại";
                        messageError.OpenDialog(contextSP, error);
                    }
                }
            });
        });
        return view;
    }

    private void findViewById(View view) {
        edTenSP = view.findViewById(R.id.ed_TenSP_Add);
        edSLN = view.findViewById(R.id.ed_SLN_Add);
        messageError.setInputTypeNumber(edSLN);
        edGiaNhap = view.findViewById(R.id.ed_GiaNhap_Add);
        messageError.setInputTypeNumber(edGiaNhap);
        tvNgayNhap = view.findViewById(R.id.tvNgayNhap_Add);
        tvMaLoai = view.findViewById(R.id.tvMaLoai_Add);
        btnChonNgay = view.findViewById(R.id.btnChonNN_SP_Add);
        btnThemMaLoai = view.findViewById(R.id.btnAddLSP_SP_Add);
        btnSave = view.findViewById(R.id.btnSaveSP);
    }

    private void filterList(String newText) {
        ArrayList<SanPham> filteredList = new ArrayList<>();

        for (SanPham sanPham : sanPhams) {
            if (sanPham.getNAMESP().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(sanPham);
            }
            if (filteredList.isEmpty()) {
                lvDanhSach.setAdapter(new SanPham_Adapter(sanPhams));
            }else {
                lvDanhSach.setAdapter(new SanPham_Adapter(filteredList));
            }
        }

    }
}
