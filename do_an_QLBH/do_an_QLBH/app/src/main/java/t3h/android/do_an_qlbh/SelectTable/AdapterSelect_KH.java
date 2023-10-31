package t3h.android.do_an_qlbh.SelectTable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import t3h.android.do_an_qlbh.Entity.KhachHang;
import t3h.android.do_an_qlbh.R;
import t3h.android.do_an_qlbh.Controller.SelectTableController;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterSelect_KH extends BaseAdapter{

    SelectTableController selectTableController;
    ArrayList<KhachHang> khachHangs;
    public AdapterSelect_KH(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_kh, parent, false);
        selectTableController = new SelectTableController();
        selectTableController.findIdSelectKH(view);
        selectTableController.getDataKhachHang(position,khachHangs);
        selectTableController.setDataRowKH();
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

}