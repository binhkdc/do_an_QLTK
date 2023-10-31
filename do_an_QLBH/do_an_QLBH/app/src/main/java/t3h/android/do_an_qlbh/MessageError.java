package t3h.android.do_an_qlbh;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import t3h.android.do_an_qlbh.LoginAccount.DangKi;
import t3h.android.do_an_qlbh.LoginAccount.DangNhap;
import t3h.android.do_an_qlbh.LoginAccount.StepDangKi;

public class MessageError {
    String error = "";

    Date DateTN, DateDN;

    public void OpenDialog(Context context, String errorText) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.error_message);

        TextView error = dialog.findViewById(R.id.error_message);

        error.setText(errorText);

        Button btn_close_dialog = dialog.findViewById(R.id.btn_close_dialog);

        btn_close_dialog.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    public boolean checkFillStepDangKi(Context context, EditText edHoTen, TextView txtNgaySinh, TextView txtGioiTinh, EditText edDiaChi, EditText edSDT) {

        if (edHoTen.length() == 0) {
            error = "Họ tên bắt buộc nhập";
            OpenDialog(context, error);
            return false;
        }

        if (edHoTen.length() <5) {
            error = "Họ tên tối thiểu 5 kí tự";
            OpenDialog(context, error);
            return false;
        }

        String text = edHoTen.getText().toString();


        if (txtNgaySinh.length() == 0) {
            error = "Vui lòng chọn ngày sinh";
            OpenDialog(context, error);
            return false;
        }

        if (txtGioiTinh.length() == 0) {
            error = "Vui lòng chọn giới tính";
            OpenDialog(context, error);
            return false;
        }

        if (edDiaChi.length() == 0) {
            error = "Địa chỉ bắt buộc nhập";
            OpenDialog(context, error);
            return false;
        }

        if (edSDT.length() == 0) {
            error = "Số điện thoại bắt buộc nhập";
            OpenDialog(context, error);
            return false;
        }

        // after all validation return true.
        return true;
    }

    public int checkKhoangTrang(String text) {
        return text.replaceAll("[^ ]", "").length();
    }

    public boolean checkFillDangKi(Context context, EditText edTaiKhoan, EditText edMatKhau, EditText edMatKhau2) {

        if (edTaiKhoan.length() == 0) {
            error = "Tên đăng nhập bắt buộc nhập";
            OpenDialog(context, error);
            return false;
        }
        String text = edTaiKhoan.getText().toString();
        String pass = edMatKhau.getText().toString();
        String repass = edMatKhau2.getText().toString();
        if(checkKhoangTrang(text)>0){
            error = "Tên đăng nhập không được nhập kí tự khoảng trắng";
            OpenDialog(context, error);
            return false;
        }

        if(checkKhoangTrang(pass)>0){
            error = "Mật khẩu không được nhập kí tự khoảng trắng";
            OpenDialog(context, error);
            return false;
        }

        if (text.equals(pass)) {
            error = "Tên đăng nhập không được giống mật khẩu";
            OpenDialog(context, error);
            return false;
        }

        if (edTaiKhoan.length() < 5) {
            error = "Tên đăng nhập phải dài hơn 5 kí tự";
            OpenDialog(context, error);
            return false;
        }


        if (edMatKhau.length() == 0) {
            error = "Mật khẩu bắt buộc nhập";
            OpenDialog(context, error);
            return false;
        }

        if (edMatKhau.length() < 8) {
            error = "Mật khẩu phải dài hơn 8 kí tự";
            OpenDialog(context, error);
            return false;
        }

        if (!pass.equals(repass)) {
            error = "Mật khẩu không trùng khớp";
            OpenDialog(context, error);
            return false;
        }

        // after all validation return true.
        return true;
    }

    public boolean checkFillDangNhap(Context context, EditText edTaiKhoan, EditText edMatKhau) {

        if (edTaiKhoan.length() == 0) {
            error = "Tên đăng nhập bắt buộc nhập";
            OpenDialog(context, error);
            return false;
        }

        if (edMatKhau.length() == 0) {
            error = "Mật khẩu bắt buộc nhập";
            OpenDialog(context, error);
            return false;
        }

        if (edMatKhau.length() < 8) {
            error = "Mật khẩu phải dài hơn 8 kí tự";
            OpenDialog(context, error);
            return false;
        }

        // after all validation return true.
        return true;
    }

    public boolean checkFilledHoaDon(Context context, TextView tvMAKH, TextView tvMASP, TextView tvNgayMua) {
        if (tvMAKH.length() == 0) {
            error = "Bạn chưa chọn khách hàng";
            OpenDialog(context, error);
            return false;
        }

        if (tvMASP.length() == 0) {
            error = "Bạn chưa chọn mã sản phẩm";
            OpenDialog(context, error);
            return false;
        }

        if (tvNgayMua.length() == 0) {
            error = "Bạn chưa chọn ngày mua";
            OpenDialog(context, error);
            return false;
        }

        return true;
    }

    public boolean checkFilledKhachHang(Context context, TextView view, TextView view1, TextView view2, TextView view3) {
        if (view.length() == 0) {
            error = "Tên không được để trống";
            OpenDialog(context, error);
            return false;
        }

        if (view1.length() == 0) {
            error = "Bạn chưa chọn ngày Sinh";
            OpenDialog(context, error);
            return false;
        }

        if (view2.length() == 0) {
            error = "Bạn nhập số điện thoại";
            OpenDialog(context, error);
            return false;
        }

        if (view2.length() > 9) {
            error = "Số điện thoại chỉ bao gồm 9 số";
            OpenDialog(context, error);
            return false;
        }

        if (view3.length() == 0) {
            error = "Bạn chưa nhập địa chỉ";
            OpenDialog(context, error);
            return false;
        }

        return true;
    }

    public boolean checkFilledLoaiSanPham(Context context, EditText tvLoaisp) {

        if (tvLoaisp.length() == 0) {
            error = "Tên loại không được để trống";
            OpenDialog(context, error);
            return false;
        }
        return true;
    }

    public void DialogDatePicker(View view, TextView textView) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog dialog1 = new DatePickerDialog(
                view.getContext(),
                (datePicker, i, i1, i2) -> {

                    int nam = i;
                    int thang = i1;
                    int ngay = i2;

                    textView.setText(ngay + " - " + (thang + 1) + " - " + nam);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );
        dialog1.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        dialog1.show();
    }


    public void setInputTypeNumber(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
    }

    public boolean checkFilledSanPham(Context context, EditText e1, EditText e2, TextView e3, EditText e4, TextView e5) {
        if (e1.length() == 0) {
            error = "Tên sản phẩm không được để trống";
            OpenDialog(context, error);
            return false;
        }

        if (e2.length() == 0) {
            error = "Giá nhập không được để trống";
            OpenDialog(context, error);
            return false;
        }

        if (e2.length() >= 10) {
            error = "Tối đa 9 kí tự trong tường giá nhập";
            OpenDialog(context, error);
            return false;
        }

        if (e3.length() == 0) {
            error = "bạn chưa chọn ngày nhập";
            OpenDialog(context, error);
            return false;
        }

        if (e4.length() == 0) {
            error = "Bạn chưa nhập số lượng";
            OpenDialog(context, error);
            return false;
        }

        if (e4.length() >= 10) {
            error = "Tối đa 9 kí tự trong tường số lượng nhập";
            OpenDialog(context, error);
            return false;
        }

        if (e5.length() == 0) {
            error = "Bạn chưa chọn loại sản phẩm";
            OpenDialog(context, error);
            return false;
        }
        return true;
    }

    ;

    public boolean checkFilledNhanVien(Context context, EditText tennv, TextView ngaysinh, EditText sodienthoai, EditText diachi) {
        if (tennv.length() == 0) {
            error = "Tên Nhân Viên không được để trống";
            OpenDialog(context, error);
            return false;
        }

        if (ngaysinh.length() == 0) {
            error = "Ngày sinh không được để trống";
            OpenDialog(context, error);
            return false;
        }

        if (sodienthoai.length() == 0) {
            error = "Bạn chưa nhập số điện thoại";
            OpenDialog(context, error);
            return false;
        }

        if (sodienthoai.length() > 9) {
            error = "Số điện thoại chỉ bao gồm 9 số";
            OpenDialog(context, error);
            return false;
        }

        if (diachi.length() == 0) {
            error = "Địa chỉ không được để trống";
            OpenDialog(context, error);
            return false;
        }
        return true;
    }

    public boolean checkThongKe(Context context, TextView tungay, TextView denngay) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        if (tungay.length() == 0) {
            error = "Từ ngày không được để trống";
            OpenDialog(context, error);
            return false;
        }

        if (denngay.length() == 0) {
            error = "Đến ngày không được để trống";
            OpenDialog(context, error);
            return false;
        }

        if (tungay.length() == 0) {
            error = "Từ ngày không được để trống";
            OpenDialog(context, error);
            return false;
        } else {
            String tuNgay = tungay.getText().toString().trim();
            String denNgay = denngay.getText().toString().trim();
            DateTN = sdf.parse(tuNgay);
            DateDN = sdf.parse(denNgay);
            if (DateDN.before(DateTN)) {
                error = "Ngày kết thúc không được nhỏ hơn ngày bắt đầu";
                OpenDialog(context, error);
                return false;
            }
        }

        return true;
    }
}
