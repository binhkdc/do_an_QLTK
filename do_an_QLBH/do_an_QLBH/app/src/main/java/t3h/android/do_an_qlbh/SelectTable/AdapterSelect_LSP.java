package t3h.android.do_an_qlbh.SelectTable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import t3h.android.do_an_qlbh.Entity.LoaiSP;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;
import t3h.android.do_an_qlbh.Controller.SelectTableController;

import java.util.ArrayList;

public class AdapterSelect_LSP extends BaseAdapter {



    SelectTableController selectTableController;

    ArrayList<LoaiSP> loaiSPS;
    int MaLoaiSelect;


    public AdapterSelect_LSP(ArrayList<LoaiSP> loaiSPS) {
        this.loaiSPS = loaiSPS;
    }

    public int getMaLoaiSelect() {
        return MaLoaiSelect;
    }

    public void setMaLoaiSelect(int maLoaiSelect) {
        MaLoaiSelect = maLoaiSelect;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_loaisp, parent, false);
        selectTableController = new SelectTableController();
        selectTableController.findIdRowLSP(view);
        selectTableController.getDataLSP(position,loaiSPS);
        selectTableController.setDataLSP();
        return view;
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
