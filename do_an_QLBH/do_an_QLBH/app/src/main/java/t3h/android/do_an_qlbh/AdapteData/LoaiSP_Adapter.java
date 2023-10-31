package t3h.android.do_an_qlbh.AdapteData;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import t3h.android.do_an_qlbh.Entity.LoaiSP;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.Entity.SanPham;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.util.ArrayList;

public class LoaiSP_Adapter extends BaseAdapter {

    MessageError messageError;
    ArrayList<LoaiSP> loaiSPS;

    String error;
    TextView txtMaSP_show;
    EditText edTenSP_show;
    Context contextLSP;

    TextView txtRowMaLoaiSP, txtRowTenLoaiSP;
    int MALOAISP, MALSP1;
    String TENLOAISP;

    Button btnSua, btnXoa;

    LoaiSP_Adapter adapter;

    SanPham sanPham;

    public LoaiSP_Adapter(ArrayList<LoaiSP> loaiSPS) {
        this.loaiSPS = loaiSPS;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loaisp, parent, false);
        contextLSP = parent.getContext();
        messageError = new MessageError();
        findIdRowLSP(view);
        getLSP(position, loaiSPS);
        LinearLayout linearLayout = view.findViewById(R.id.linear_LoaiSanPham);
        linearLayout.setOnClickListener(v -> {
            messageError = new MessageError();
            AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
            View showSP = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_lsp, parent, false);
            builder.setView(showSP);
            Dialog dialog = builder.create();
            dialog.show();
            findIdShowLSP(showSP);
            getLSP(position, loaiSPS);
            setShowLSP();
            btnSua = showSP.findViewById(R.id.btnSuaLoaiSP);
            btnXoa = showSP.findViewById(R.id.btnXoaLoaiSP);
            findIdShowLSP(showSP);
            btnSua.setOnClickListener(v1 -> {
                findIdShowLSP(showSP);
                if (messageError.checkFilledLoaiSanPham(contextLSP, edTenSP_show)) {
                    int MALSP1 = Integer.parseInt(txtMaSP_show.getText().toString());
                    String TENLSP = edTenSP_show.getText().toString();
                    LoaiSP loaiSP1 = new LoaiSP(MALSP1, TENLSP);
                    SQLife sqLife = new SQLife(v1.getContext());
                    sqLife.UpdateLSP(loaiSP1);
                    loaiSPS.clear();
                    loaiSPS.addAll(sqLife.getALL_LSP());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    error = "Sửa Loại sản phẩm thành công";
                    messageError.OpenDialog(contextLSP, error);
                }
            });
            btnXoa.setOnClickListener(v12 -> {
                int MALSP1 = Integer.parseInt(txtMaSP_show.getText().toString());
                SQLife sqLife = new SQLife(v12.getContext());
                sanPham = sqLife.findSanPham(MALSP1);
                if (sanPham.getMALOAISP() == MALSP1) {
                    error = "Bạn không thể xóa Loại Sản Phẩm này vì có một sản phẩm đang sử dụng Loại sản phẩm này";
                    messageError.OpenDialog(contextLSP, error);
                    dialog.dismiss();
                } else {
                    String TENLSP = edTenSP_show.getText().toString();
                    LoaiSP loaiSP1 = new LoaiSP(MALSP1, TENLSP);

                    sqLife.DeleteLSP(loaiSP1);
                    loaiSPS.clear();
                    loaiSPS.addAll(sqLife.getALL_LSP());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    error = "Xóa Loại sản phẩm thành công";
                    messageError.OpenDialog(contextLSP, error);
                }

            });
        });
        setLSP();
        return view;
    }

    public void getLSP(int position, ArrayList<LoaiSP> loaiSPS) {
        LoaiSP loaiSP = loaiSPS.get(position);
        MALOAISP = loaiSP.getMaLoai();
        TENLOAISP = loaiSP.getTenLoai();
    }

    public void setLSP() {
        txtRowMaLoaiSP.setText(MALOAISP + "");
        txtRowTenLoaiSP.setText(TENLOAISP);
    }

    public void findIdShowLSP(View showSP) {
        txtMaSP_show = showSP.findViewById(R.id.txtMaLoaiSP_Show);
        edTenSP_show = showSP.findViewById(R.id.ed_NameLoaiSP_Show);
    }

    public void setShowLSP() {
        txtMaSP_show.setText(MALOAISP + "");
        edTenSP_show.setText(TENLOAISP);
    }

    public void findIdRowLSP(View view) {
        txtRowMaLoaiSP = view.findViewById(R.id.txtRowMaLoaiSP);
        txtRowTenLoaiSP = view.findViewById(R.id.txtRowNameLoaiSP);
    }

    @Override
    public int getCount() {
        return loaiSPS.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
