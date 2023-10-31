package t3h.android.do_an_qlbh.FragmentQL;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment_ThongKe extends Fragment {
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    MessageError messageError;

    String error;

    Date DateTN, DateDN;

    public Fragment_ThongKe() {
    }

    public static Fragment_ThongKe newInstance(String param1, String param2) {
        Fragment_ThongKe fragment = new Fragment_ThongKe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongkedoanhthu, container, false);
    }

    SQLife sqLife;
    TextView ed_tungay, ed_dengay;
    ImageView img_tungay, img_dengay;
    AppCompatButton btn_xn;
    TextView tv_doanhthu;

    Context context;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messageError = new MessageError();
        context = getContext();
        findIdView(view);
        img_tungay.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view1, year, month, dayOfMonth) -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                calendar.set(year, month, dayOfMonth);

                ed_tungay.setText(simpleDateFormat.format(calendar.getTime()));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        });
        img_dengay.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view12, year, month, dayOfMonth) -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                calendar.set(year, month, dayOfMonth);

                ed_dengay.setText(simpleDateFormat.format(calendar.getTime()));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        });
        btn_xn.setOnClickListener(v -> {
            sqLife = new SQLife(getActivity());
            String tuNgay = ed_tungay.getText().toString();
            String denNgay = ed_dengay.getText().toString();
            try {
                if (messageError.checkThongKe(context, ed_tungay, ed_dengay)) {
                    tv_doanhthu.setText("Doanh Thu: " + sqLife.getlaygiatritheongay(tuNgay, denNgay) + " VND");
                }
            } catch (ParseException e) {
                error="Vui lòng nhập đúng định dạng ngày " +
                        "EX:18-12-2002";
                messageError.OpenDialog(context,error);
            }

        });
    }

    private void findIdView(View view){
        ed_tungay = view.findViewById(R.id.ed_tungay);
        ed_dengay = view.findViewById(R.id.ed_denngay);
        ed_dengay.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
        ed_tungay.setInputType(InputType.TYPE_CLASS_DATETIME| InputType.TYPE_DATETIME_VARIATION_DATE);
        img_tungay = view.findViewById(R.id.img_tungay);
        img_dengay = view.findViewById(R.id.img_denngay);
        btn_xn = view.findViewById(R.id.btn_check);
        tv_doanhthu = view.findViewById(R.id.tv_hienthikq);
    }
}
