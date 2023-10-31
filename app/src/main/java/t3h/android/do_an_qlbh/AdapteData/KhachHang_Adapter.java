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

import t3h.android.do_an_qlbh.Entity.HoaDon;
import t3h.android.do_an_qlbh.Entity.KhachHang;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class KhachHang_Adapter extends BaseAdapter {
    ArrayList<KhachHang> khachHangs;
    KhachHang khachHang1;

    HoaDon hoaDon;

    MessageError messageError;
    String error;
    Context contextKH;
    TextView txtRowMaKH,txtRowTenKH,txtRowGioiTinh,txtRowPhoneKH;

    CircleImageView circleImageView ;

    TextView txtNgaySinh,txtGioiTinh;
    EditText edTenKH_show,edDiaChiKH_show,edNumberPhoneKH_show;
    Button btnSua,btnXoa,btnHuy,btnChonNgay,btnNam,btnNu;
    public KhachHang_Adapter(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kh,parent,false);
        messageError = new MessageError();
        contextKH = parent.getContext();
        findViewId(view);
        KhachHang khachHang = khachHangs.get(position);
        int MAKH = khachHang.getMaKH();
        String TENKH = khachHang.getTenKH();
        int NumberPhone = khachHang.getNumberPhone();
        String DiaChi = khachHang.getDiaChi();
        String GioiTinh = khachHang.getGioiTinh();
        String NgaySinh = khachHang.getNgaySinh();
        int IMGKH = khachHang.getImageKH();
        txtRowMaKH.setText(""+MAKH+"");
        txtRowTenKH.setText(""+TENKH);
        txtRowGioiTinh.setText(""+GioiTinh);
        txtRowPhoneKH.setText(""+NumberPhone);
        circleImageView.setImageResource(IMGKH);
        LinearLayout linearLayout = view.findViewById(R.id.linear_rowkh);
        linearLayout.setOnClickListener(v -> {
            AlertDialog.Builder  builder = new AlertDialog.Builder(parent.getContext());
            View showKH = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_kh,parent,false);
            builder.setView(showKH);
            Dialog dialog= builder.create();
            dialog.show();
            findTextViewId(showKH);
            findButtonId(showKH);

            edTenKH_show.setText(TENKH);
            edDiaChiKH_show.setText(DiaChi);
            edNumberPhoneKH_show.setText(NumberPhone+"");
            txtGioiTinh.setText(GioiTinh);
            txtNgaySinh.setText(NgaySinh);
            btnChonNgay.setOnClickListener(v1 -> {
                messageError.DialogDatePicker(v1, txtNgaySinh);
            });
            btnNam.setOnClickListener(v12 -> txtGioiTinh.setText("Nam"));
            btnNu.setOnClickListener(v13 -> txtGioiTinh.setText("Nữ"));
            btnHuy.setOnClickListener(v14 -> dialog.dismiss());
            btnSua.setOnClickListener(v15 -> {
                if(messageError.checkFilledKhachHang(contextKH,edTenKH_show,txtNgaySinh,edNumberPhoneKH_show,edDiaChiKH_show)){
                    int MAKH1 = MAKH;
                    String TENKH1 = edTenKH_show.getText().toString();
                    int NumberPhone1 = Integer.parseInt(edNumberPhoneKH_show.getText().toString());
                    String DiaChi1 = edDiaChiKH_show.getText().toString();
                    String NgaySinh1 = txtNgaySinh.getText().toString();
                    String GioiTinh1 = txtGioiTinh.getText().toString();
                    khachHang1 = new KhachHang(MAKH1,TENKH1,NumberPhone1,DiaChi1,NgaySinh1,GioiTinh1,IMGKH);
                    SQLife sqLife = new SQLife(v15.getContext());
                    sqLife.UpdateKH(khachHang1);
                    khachHangs.clear();
                    khachHangs.addAll(sqLife.getALLKH());
                    notifyDataSetChanged();
                    error="Sửa Khách Hàng Thành Công";
                    messageError.OpenDialog(contextKH,error);
                    dialog.dismiss();
                }

            });
            btnXoa.setOnClickListener(v16 -> {
                int MAKH1 = MAKH;
                SQLife sqLife = new SQLife(v16.getContext());
                hoaDon = sqLife.find_maKH_HoaDon(MAKH1);
                if (hoaDon.getMaKH() == MAKH1) {
                    error = "Bạn không thể xóa Khách Hàng này vì ở bảng hóa đơn đang có tên của khách hàng này";
                    messageError.OpenDialog(contextKH, error);
                    dialog.dismiss();
                } else {
                    sqLife.DeleteKH(MAKH1+"");
                    khachHangs.clear();
                    khachHangs.addAll(sqLife.getALLKH());
                    notifyDataSetChanged();
                    error="Xóa Khách Hàng Thành Công";
                    messageError.OpenDialog(contextKH,error);
                    dialog.dismiss();
                }

            });
        });
        return view;
    }

    @Override
    public int getCount() {
        return khachHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void findViewId(View view){
        txtRowMaKH = view.findViewById(R.id.txtRowMaKH);
        txtRowTenKH = view.findViewById(R.id.txtRowTenKH);
        txtRowGioiTinh = view.findViewById(R.id.txtRowGioiTinhKH);
        txtRowPhoneKH = view.findViewById(R.id.txtRowPhoneKH);
        circleImageView = view.findViewById(R.id.RowImageKH);
    }

    private void findTextViewId(View view){
        txtNgaySinh = view.findViewById(R.id.txtNS_KH_Show);
        txtGioiTinh = view.findViewById(R.id.txtGT_KH_Show);
        edTenKH_show = view.findViewById(R.id.edHT_KH_Show);
        edDiaChiKH_show = view.findViewById(R.id.edDC_KH_Show);
        edNumberPhoneKH_show = view.findViewById(R.id.edSDT_KH_Show);
        messageError.setInputTypeNumber(edNumberPhoneKH_show);
    }

    private void findButtonId(View view){
        btnSua = view.findViewById(R.id.btnSuaKH);
        btnXoa = view.findViewById(R.id.btnXoaKH);
        btnHuy = view.findViewById(R.id.btnHuyShowKH);
        btnChonNgay = view.findViewById(R.id.btnChonNS_KH_Show);
        btnNam = view.findViewById(R.id.btnGT_KH_Nam_Show);
        btnNu = view.findViewById(R.id.btnGT_KH_Nu_Show);
    }
}
