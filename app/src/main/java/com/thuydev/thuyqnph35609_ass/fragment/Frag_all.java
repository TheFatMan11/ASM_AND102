package com.thuydev.thuyqnph35609_ass.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.thuyqnph35609_ass.Adapter.Adapter_task;
import com.thuydev.thuyqnph35609_ass.DAO.DAO_task;
import com.thuydev.thuyqnph35609_ass.DTO.DTO_task;
import com.thuydev.thuyqnph35609_ass.MainActivity;
import com.thuydev.thuyqnph35609_ass.R;

import java.util.Calendar;
import java.util.List;

public class Frag_all extends Fragment {
    RecyclerView lv_list;
    List<DTO_task> list;
    SearchView sv_;
    ImageButton add;

    DTO_task dto_task;
    DAO_task dao_task;
    Adapter_task adapter_task;
    Calendar lich = Calendar.getInstance();
    int ngay_check,thang_check,nam_check;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quancongviec, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_list = view.findViewById(R.id.rc_all);
        sv_ = view.findViewById(R.id.sv_);
        add = view.findViewById(R.id.ibtn_add);
        dao_task = new DAO_task(getContext());
        dto_task = new DTO_task();
        list = dao_task.getData();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lv_list.setLayoutManager(manager);
        adapter_task = new Adapter_task(getContext(), list);
        lv_list.setAdapter(adapter_task);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_add_cv, null, false);
                builder.setView(view1);
                EditText tieuDe = view1.findViewById(R.id.edt_tencongviec);
                EditText noiDung = view1.findViewById(R.id.edt_noidung);
                EditText start = view1.findViewById(R.id.edt_start);
                EditText end = view1.findViewById(R.id.edt_end);
                Button them = view1.findViewById(R.id.btn_add);
                Button cancel = view1.findViewById(R.id.btn_Cance);
                Dialog dialog = builder.create();


                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int ngay = lich.get(Calendar.DAY_OF_MONTH);
                        int thang = lich.get(Calendar.MONTH);
                        int nam = lich.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                start.setText(String.format("%d-%d-%d", dayOfMonth, month, year));
                                ngay_check = dayOfMonth;
                                thang_check = month;
                                nam_check = year;
                            }
                        }, nam, thang, ngay);
                        datePickerDialog.show();
                    }
                });

                end.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {



                        int ngay = lich.get(Calendar.DAY_OF_MONTH);
                        int thang = lich.get(Calendar.MONTH);
                        int nam = lich.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                    end.setText(String.format("%d-%d-%d", dayOfMonth, month, year));

                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });
                //ẩn background trắng của dialog
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dto_task.setName(tieuDe.getText().toString());
                        dto_task.setConten(noiDung.getText().toString());
                        dto_task.setStart(start.getText().toString());
                        dto_task.setEnd(end.getText().toString());
                        dto_task.setStatus(0);
                        if (dao_task.AddRow(dto_task) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                        list.clear();
                        list.addAll(dao_task.getData());
                        adapter_task.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });


    }
}
