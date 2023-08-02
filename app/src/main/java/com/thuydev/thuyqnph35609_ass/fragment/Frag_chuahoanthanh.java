package com.thuydev.thuyqnph35609_ass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.thuyqnph35609_ass.Adapter.Adapter_task;
import com.thuydev.thuyqnph35609_ass.DAO.DAO_task;
import com.thuydev.thuyqnph35609_ass.DTO.DTO_task;
import com.thuydev.thuyqnph35609_ass.QuanLyCongViec;
import com.thuydev.thuyqnph35609_ass.R;

import java.util.ArrayList;
import java.util.List;

public class Frag_chuahoanthanh extends Fragment {
    RecyclerView lv_list;
    List<DTO_task> list;
    List<DTO_task> listCheck;
    SearchView sv_;
    DTO_task dto_task;
    DAO_task dao_task;
    Adapter_task adapter_task;
    QuanLyCongViec quanLyCongViec ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_cv_chuahoanthanh,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_list = view.findViewById(R.id.rc_chua);
        sv_ = view.findViewById(R.id.sv_chua);
        quanLyCongViec = (QuanLyCongViec) getContext();
        dao_task = new DAO_task(getContext());
        dto_task = new DTO_task();
        list = dao_task.getData(quanLyCongViec.Guidata().getId());

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lv_list.setLayoutManager(manager);
        listCheck = loc(list);
        adapter_task = new Adapter_task(getContext(), listCheck,1,quanLyCongViec.Guidata().getId());
        lv_list.setAdapter(adapter_task);

        sv_.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_task.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_task.getFilter().filter(newText);
                return true;
            }
        });
    }
    public List<DTO_task> loc(List<DTO_task> list){
        List<DTO_task> listCheck = new ArrayList<>();
        for (DTO_task task:list) {
            if(task.getStatus()==1){
                listCheck.add(task);
            }
        }
        return listCheck;
    }
}
