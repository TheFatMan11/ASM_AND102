package com.thuydev.thuyqnph35609_ass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.thuydev.thuyqnph35609_ass.DAO.DAO_phien;
import com.thuydev.thuyqnph35609_ass.DTO.DTO_user;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_begin;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_dangKy;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_dangnhap;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_quen_pass_doipass;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_quen_pass_xacthuc;

public class MainActivity extends AppCompatActivity implements Frag_quen_pass_xacthuc.IChuyendata {
    Frag_begin frag_begin;
    Frag_dangKy frag_dangKy;
    Frag_dangnhap frag_dangnhap;
    Frag_quen_pass_doipass frag_quen_pass_doipass;

    FragmentManager manager;
    DAO_phien dao_phien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frag_begin = new Frag_begin();
        frag_dangKy = new Frag_dangKy();
        frag_dangnhap = new Frag_dangnhap();
        manager = getSupportFragmentManager();
        dao_phien = new DAO_phien(this);
        int check = dao_phien.so();

        new Thread() {
            @Override
            public void run() {
                super.run();
                manager.beginTransaction().add(R.id.frag_, frag_begin).commit();
                try {
                    sleep(3000);
                    if (check == 0) {
                        manager.beginTransaction().replace(R.id.frag_, frag_dangKy).commit();
                        dao_phien.soPhien();
                    } else {
                        manager.beginTransaction().replace(R.id.frag_, frag_dangnhap).commit();
                    }
                } catch (Exception e) {

                }
            }
        }.start();


    }

    @Override
    public void chuyenData(DTO_user user) {
        frag_quen_pass_doipass = (Frag_quen_pass_doipass) getSupportFragmentManager().findFragmentById(R.id.frag_);
        manager.beginTransaction().replace(R.id.frag_,frag_quen_pass_doipass).commit();
        frag_quen_pass_doipass.nhanDuLieu(user);
    }
}