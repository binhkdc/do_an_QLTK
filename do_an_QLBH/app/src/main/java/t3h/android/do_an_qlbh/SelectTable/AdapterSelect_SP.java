package t3h.android.do_an_qlbh.SelectTable;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import t3h.android.do_an_qlbh.Entity.SanPham;
import t3h.android.do_an_qlbh.R;
import t3h.android.do_an_qlbh.Controller.SelectTableController;
import java.util.ArrayList;

public class AdapterSelect_SP extends BaseAdapter {

    SelectTableController selectTableController;
    ArrayList<SanPham> sanPhams;

    public AdapterSelect_SP(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sanpham, parent, false);
        selectTableController = new SelectTableController();
        selectTableController.findIdRowSP(view);
        selectTableController.getDataSanPham(position,sanPhams);
        selectTableController.setDataRowSP();
        return view;
    }

    @Override
    public int getCount() {
        return sanPhams.size();
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