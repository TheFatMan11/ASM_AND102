package com.thuydev.thuyqnph35609_ass.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.thuydev.thuyqnph35609_ass.DAO.DAO_task;
import com.thuydev.thuyqnph35609_ass.DTO.DTO_task;
import com.thuydev.thuyqnph35609_ass.QuanLyCongViec;
import com.thuydev.thuyqnph35609_ass.R;
import com.thuydev.thuyqnph35609_ass.cauhinh;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_all;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_bixoa;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_chuahoanthanh;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_hoanthanh;
import com.thuydev.thuyqnph35609_ass.fragment.Frag_moi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Adapter_task extends RecyclerView.Adapter<Adapter_task.ViewHolder> implements Filterable {
    private static final int NOTIFYCATION_ID = 1;
    Context context;
    List<DTO_task> list;
    List<DTO_task> listOld;
    Frag_all frag_all;
    Frag_moi frag_moi;
    Frag_bixoa frag_bixoa;
    Frag_hoanthanh frag_hoanthanh;
    Frag_chuahoanthanh frag_chuahoanthanh;

    DAO_task dao_task;
    DTO_task dto_task;
    Calendar lich = Calendar.getInstance();
    int frag_status = 0;
    int ngay_check, thang_check, nam_check;
    int idUser = 0;


    public Adapter_task(Context context, List<DTO_task> list, int sta, int id_user) {
        this.context = context;
        this.list = list;
        listOld = list;
        frag_status = sta;
        idUser = id_user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_cv, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).getName());
        holder.status.setText(status(list.get(position).getStatus()));
        dto_task = list.get(position);
        dao_task = new DAO_task(context);
        frag_all = new Frag_all();
        frag_chuahoanthanh = new Frag_chuahoanthanh();
        frag_hoanthanh = new Frag_hoanthanh();
        frag_bixoa = new Frag_bixoa();
        frag_moi = new Frag_moi();
        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dto_task = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Trạng thái");
                String[] st = new String[]{
                        "Mới tạo", "Đang làm", "Hoàn thành"
                };
                builder.setItems(st, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            dto_task.setStatus(0);
                        } else if (which == 1) {
                            dto_task.setStatus(1);
                        } else if (which == 2) {
                            dto_task.setStatus(2);
                        }
                        if (dao_task.UpdateRow(dto_task) > 0) {
                            Toast.makeText(context, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            checkFrag(frag_status);
                            dialog.dismiss();

                        } else {
                            Toast.makeText(context, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                Dialog dialog1 = builder.create();
                dialog1.show();
            }
        });
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_more, null, false);
                builder.setView(view);
                Button sua = view.findViewById(R.id.btn_update_task);
                Button xoa = view.findViewById(R.id.btn_xoa_task);
                Button chitiet = view.findViewById(R.id.btn_xemthem_task);
                Dialog dialog = builder.create();
                dialog.show();
                if (frag_status == -1) {
                    xoa.setVisibility(View.GONE);
                }

                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.dialog_add_cv, null, false);
                        builder.setView(view1);
                        TextView tenbang = view1.findViewById(R.id.tv_title_ns);
                        EditText tieuDe = view1.findViewById(R.id.edt_tencongviec);
                        EditText noiDung = view1.findViewById(R.id.edt_noidung);
                        EditText start = view1.findViewById(R.id.edt_start);
                        EditText end = view1.findViewById(R.id.edt_end);
                        Button them = view1.findViewById(R.id.btn_add);
                        Dialog dialog1 = builder.create();
                        dialog1.show();

                        tenbang.setText("Sửa công việc");
                        tieuDe.setText(dto_task.getName());
                        noiDung.setText(dto_task.getConten());
                        start.setText(dto_task.getStart());
                        end.setText(dto_task.getEnd());
                        them.setText("Sửa");

dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        start.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int ngay = lich.get(Calendar.DAY_OF_MONTH);
                                int thang = lich.get(Calendar.MONTH);
                                int nam = lich.get(Calendar.YEAR);

                                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        start.setText(String.format("%d-%d-%d", dayOfMonth, month + 1, year));
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

                                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                        if (ngay_check <= dayOfMonth && thang_check <= month && nam_check <= year) {
                                            end.setText(String.format("%d-%d-%d", dayOfMonth, month + 1, year));

                                        } else {
                                            Toast.makeText(context, "Ngày kết thúc phải nhiều hơn ngày bắt đầu", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }, nam, thang, ngay);
                                datePickerDialog.show();
                            }
                        });
                        //ẩn background trắng của dialog
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        them.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!tieuDe.getText().toString().isEmpty() && !noiDung.getText().toString().isEmpty() && !start.getText().toString().isEmpty() && !end.getText().toString().isEmpty()) {
                                    dto_task.setName(tieuDe.getText().toString());
                                    dto_task.setConten(noiDung.getText().toString());
                                    dto_task.setStart(start.getText().toString());
                                    dto_task.setEnd(end.getText().toString());

                                    QuanLyCongViec quanLyCongViec = (QuanLyCongViec) context;
                                    dto_task.setId_user(quanLyCongViec.Guidata().getId());
                                    if (dao_task.UpdateRow(dto_task) > 0) {
                                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        dialog1.dismiss();

                                    } else {
                                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                    checkFrag(frag_status);
                                } else {
                                    Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
                xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                        View view1 = inflater1.inflate(R.layout.dialog_xac_nhan, null, false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();

                        ImageButton xacNhan = view1.findViewById(R.id.ibtn_dongy);
                        ImageButton khong = view1.findViewById(R.id.ibtn_khong);

                        xacNhan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dto_task.setStatus(-1);
                                if (dao_task.UpdateRow(dto_task) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    thongbao();
                                    dialog.dismiss();


                                    checkFrag(frag_status);
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                                dialog1.dismiss();
                            }
                        });

                        khong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });

                    }
                });

                chitiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                        View view1 = inflater1.inflate(R.layout.dialog_ctcv, null, false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        TextView tenCV = view1.findViewById(R.id.tv_tencv_ct);
                        TextView noidungCV = view1.findViewById(R.id.tv_ndcv_ct);
                        TextView trangthaiCV = view1.findViewById(R.id.tv_trangthaicv_ct);
                        TextView startCV = view1.findViewById(R.id.tv_startcv_ct);
                        TextView endCV = view1.findViewById(R.id.tv_endcv_ct);
                        Button dong = view1.findViewById(R.id.btn_close);

                        tenCV.setText(dto_task.getName());
                        noidungCV.setText(dto_task.getConten());
                        trangthaiCV.setText(status(dto_task.getStatus()));
                        startCV.setText(dto_task.getStart());
                        endCV.setText(dto_task.getEnd());

                        dong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });
                    }
                });
            }


        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint.toString().isEmpty()) {
                    list = listOld;
                } else {
                    List<DTO_task> listnew = new ArrayList<>();
                    for (DTO_task task : listOld
                    ) {
                        if (task.getName().toLowerCase().contains(constraint.toString())) {
                            listnew.add(task);
                        }
                    }
                    list = listnew;

                }
                FilterResults results = new FilterResults();
                results.values = list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<DTO_task>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, status;
        ImageButton menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_tencv);
            status = itemView.findViewById(R.id.tv_trangthai);
            menu = itemView.findViewById(R.id.ibtn_more);

        }
    }

    public String status(int st) {
        String status = "Đang làm";
        if (st == 0) {
            status = "Mới";
        } else if (st == 2) {
            status = "Hoàn thành";
        } else if (st == -1) {
            status = "Xóa";
        }
        return status;
    }

    public void checkFrag(int a) {
        if (a == -1) {
            list.clear();
            list.addAll(frag_bixoa.loc(dao_task.getData(idUser)));
            notifyDataSetChanged();
        } else if (a == 0) {
            list.clear();
            list.addAll(frag_moi.loc(dao_task.getData(idUser)));
            notifyDataSetChanged();
        } else if (a == 1) {
            list.clear();
            list.addAll(frag_chuahoanthanh.loc(dao_task.getData(idUser)));
            notifyDataSetChanged();
        } else if (a == 2) {
            list.clear();
            list.addAll(frag_hoanthanh.loc(dao_task.getData(idUser)));
            notifyDataSetChanged();
        } else {
            list.clear();
            list.addAll(frag_all.loc(dao_task.getData(idUser)));
            notifyDataSetChanged();
        }
    }

public void thongbao(){
        Notification notification = new NotificationCompat.Builder(context,cauhinh.ID)
                .setContentTitle("Thông báo !!")
                .setContentText("Bạn vừa xóa một công việc")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(Color.RED).build();

    NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    manager.notify(NOTIFYCATION_ID,notification);
}


}

