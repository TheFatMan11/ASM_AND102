package com.thuydev.thuyqnph35609_ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.thuydev.thuyqnph35609_ass.DTO.DTO_user;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_all;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_chuahoanthanh;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_hoanthanh;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_moi;

public class QuanLyCongViec extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    View header;
    DTO_user user;

    FragmentManager manager;
    Frag_all frag_all;
    Frag_moi frag_moi;
    Frag_hoanthanh frag_hoanthanh;
    Frag_chuahoanthanh frag_chuahoanthanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_cong_viec);
        toolbar = findViewById(R.id.tb_);
        drawerLayout = findViewById(R.id.layout_chinh);
        navigationView = findViewById(R.id.navi);
        frag_all = new Frag_all();
        frag_moi = new Frag_moi();
        frag_hoanthanh = new Frag_hoanthanh();
        frag_chuahoanthanh = new Frag_chuahoanthanh();
        manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.frag_02,frag_all).commit();

        //hiển thị dữ liệu lên header
        Intent intent = getIntent();
        user = new DTO_user();
        user = (DTO_user) intent.getSerializableExtra("data");

        header = navigationView.getHeaderView(0);
        ImageView avatar = header.findViewById(R.id.imv_avatar);
        TextView name = header.findViewById(R.id.tv_username_head);
        name.setText(user.getFullname());


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản lý công việc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.it_quanlycongviec) {
                    toolbar.setTitle("Quản lý công việc");
                    manager.beginTransaction().replace(R.id.frag_02,frag_all).commit();
                } else if (item.getItemId() == R.id.it_cv_moi) {
                    toolbar.setTitle("Công việc mới tạo");
                    manager.beginTransaction().replace(R.id.frag_02,frag_moi).commit();
                } else if (item.getItemId() == R.id.it_cv_dalam) {
                    toolbar.setTitle("Công việc đã làm");
                    manager.beginTransaction().replace(R.id.frag_02,frag_hoanthanh).commit();
                }else if (item.getItemId() == R.id.it_cv_chualam) {
                    toolbar.setTitle("Công việc chưa làm");
                    manager.beginTransaction().replace(R.id.frag_02,frag_chuahoanthanh).commit();
                }else if (item.getItemId() == R.id.it_nguoidung) {
                    toolbar.setTitle("Quản lý tài khoản");
                }else if (item.getItemId() == R.id.it_vechungtoi) {
                    toolbar.setTitle("Thông tin ứng dụng");
                }else if (item.getItemId() == R.id.it_dangxuat) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyCongViec.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn đăng xuất ?");
                    builder.setIcon(R.drawable.baseline_warning_amber_24);

                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }
                drawerLayout.close();
                return false;
            }
        });
    }
}