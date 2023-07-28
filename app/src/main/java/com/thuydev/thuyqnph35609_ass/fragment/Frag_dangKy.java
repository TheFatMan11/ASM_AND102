package com.thuydev.thuyqnph35609_ass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.thuydev.thuyqnph35609_ass.DAO.DAO_user;
import com.thuydev.thuyqnph35609_ass.DTO.DTO_user;
import com.thuydev.thuyqnph35609_ass.R;

public class Frag_dangKy extends Fragment {
    EditText taiKhoan,matKhau,reMatKhau,email,hoVaTen;
    Button dangKy;
    CheckBox dieuKhoan;
    TextView dangNhap;

    DTO_user dto_user;
    DAO_user daoUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_dangky,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taiKhoan = view.findViewById(R.id.edt_tentaikhoan);
        matKhau = view.findViewById(R.id.edt_matkhau);
        reMatKhau = view.findViewById(R.id.edt_rematkhau);
        email = view.findViewById(R.id.edt_email);
        hoVaTen = view.findViewById(R.id.edt_hovaten);
        dangKy = view.findViewById(R.id.btn_dangky);
        dieuKhoan = view.findViewById(R.id.cbo_dongydieukhoan);
        dangNhap = view.findViewById(R.id.tv_dangnhap);

        dangKy.setEnabled(false);

        dieuKhoan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dangKy.setEnabled(true);
                }else {
                    dangKy.setEnabled(false);
                }
            }
        });
        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDangKy();

                Toast.makeText(getContext(), ""+email.getText().toString()+"-----"+dto_user.getEmail(), Toast.LENGTH_SHORT).show();

            }
        });
        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_dangnhap frag_dangnhap = new Frag_dangnhap();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_,frag_dangnhap).commit();
            }
        });
    }
    public void setDangKy(){
        daoUser = new DAO_user(getContext());
        String checkEmail = "[aA0-zZ9]+@+[a-z]+.+[a-z]{2,3}";
        if(taiKhoan.getText().toString().isEmpty()||matKhau.getText().toString().isEmpty()||reMatKhau.getText().toString().isEmpty()||email.getText().toString().isEmpty()||hoVaTen.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Không được để trống , vui lòng thử lại", Toast.LENGTH_SHORT).show();
        } else if(!email.getText().toString().matches(checkEmail)){
            Toast.makeText(getContext(), "Định dạng email không hợp lệ", Toast.LENGTH_SHORT).show();
        } else if (!matKhau.getText().toString().equals(reMatKhau.getText().toString())) {
            Toast.makeText(getContext(), "Mật khẩu không khớp, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
        } else {
            dto_user = new DTO_user();

            dto_user.setUsername(taiKhoan.getText().toString());
            dto_user.setPassword(matKhau.getText().toString());
            dto_user.setEmail(email.getText().toString());
            dto_user.setFullname(hoVaTen.getText().toString());
            if(daoUser.AddRow(dto_user)>0){
                Frag_dangnhap frag_dangnhap = new Frag_dangnhap();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frag_,frag_dangnhap).commit();
                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
