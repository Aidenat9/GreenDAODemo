package com.tmgg.greendao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tmgg.greendao.R;
import com.tmgg.greendao.app.MyApp;
import com.tmgg.greendao.bean.Note;
import com.tmgg.greendao.bean.User;
import com.tmgg.greendao.gen.DaoSession;
import com.tmgg.greendao.gen.NoteDao;
import com.tmgg.greendao.gen.UserDao;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 增
     */
    private Button mBtnMainAdd;
    /**
     * 删
     */
    private Button mBtnMainDelete;
    /**
     * 改
     */
    private Button mBtnMainUpdate;
    /**
     * 查
     */
    private Button mBtnMainQuery;
    /**
     * 删库
     */
    private Button mBtnMainDrop;
    private UserDao userDao;
    private EditText mEditMainDelete;
    private List<User> dataLists;
    private MainActivity mContext;
    private MyAdapter mAdapter;
    private NoteDao noteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initRecyclerView();
        initDaoMaster();
    }

    private void initRecyclerView() {
        dataLists = new ArrayList<>(1000);

        RecyclerView recyclerView = findViewById(R.id.rv_main_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_rv_db, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(inflate);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            User user = dataLists.get(position);
            List<Note> lists = user.getLists();
            holder.tvUserId.setText("" + user.getId());
                holder.tvUserName.setText("" + user.getName());
                holder.tvSex.setText("" + user.getSex());
                holder.tvAge.setText("" + user.getAge());
                holder.tvSalary.setText("" + user.getSalary());
                StringBuilder sbContent = new StringBuilder();
                StringBuilder sbId = new StringBuilder();
                StringBuilder sbCustomId = new StringBuilder();
            for (Note note:
                 lists) {
                sbContent.append(note.getContent()+"~");
                sbId.append(note.getId()+"~");
                sbCustomId.append(note.getCustomId()+"~");
            }
            holder.tvNoteContent.setText(sbContent);
            holder.tvNoteId.setText(sbId);
            holder.tvNoteCustomId.setText(sbCustomId);
        }

        @Override
        public int getItemCount() {
            return dataLists.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView tvUserId;
            private TextView tvUserName;
            private TextView tvSex;
            private TextView tvAge;
            private TextView tvSalary;
            private TextView tvNoteId;
            private TextView tvNoteContent;
//            private TextView tvCreateDate;
//            private TextView tvEditDate;
            private TextView tvNoteCustomId;

            public MyViewHolder(View itemView) {
                super(itemView);
                getViews(itemView);
            }

            private void getViews(View view) {
                tvUserId = view.findViewById(R.id.tv_item_userId);
                tvUserName = view.findViewById(R.id.tv_item_userName);
                tvSex = view.findViewById(R.id.tv_item_sex);
                tvAge = view.findViewById(R.id.tv_item_age);
                tvSalary = view.findViewById(R.id.tv_item_salary);
                tvNoteId = view.findViewById(R.id.tv_item_noteId);
                tvNoteContent = view.findViewById(R.id.tv_item_content);
//                tvCreateDate = view.findViewById(R.id.tv_item_createDate);
//                tvEditDate = view.findViewById(R.id.tv_item_editDate);
                tvNoteCustomId = view.findViewById(R.id.tv_item_customId);
            }
        }
    }

    private void initDaoMaster() {
        DaoSession daoSession = MyApp.getDaoSession();
        userDao = daoSession.getUserDao();
        noteDao = daoSession.getNoteDao();
    }

    private void initView() {
        mBtnMainAdd = (Button) findViewById(R.id.btn_main_add);
        mBtnMainAdd.setOnClickListener(this);
        mBtnMainDelete = (Button) findViewById(R.id.btn_main_delete);
        mBtnMainDelete.setOnClickListener(this);
        mBtnMainUpdate = (Button) findViewById(R.id.btn_main_update);
        mBtnMainUpdate.setOnClickListener(this);
        mBtnMainQuery = (Button) findViewById(R.id.btn_main_query);
        mBtnMainQuery.setOnClickListener(this);
        mBtnMainDrop = (Button) findViewById(R.id.btn_main_drop);
        mBtnMainDrop.setOnClickListener(this);
        mEditMainDelete = (EditText) findViewById(R.id.et_main_delete);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_main_add:
                addUser();
                break;
            case R.id.btn_main_delete:
                deleteUser();
                break;
            case R.id.btn_main_update:
                updateUser();
                break;
            case R.id.btn_main_query:
                queryUser();
                break;
            case R.id.btn_main_drop:
                dropDb();
                queryUser();
                break;
        }
    }

    private void dropDb() {
        userDao.deleteAll();
    }

    private void queryUser() {
        if (null != dataLists && dataLists.size() > 0) {
            dataLists.clear();
            mAdapter.notifyDataSetChanged();
        }
        Query<User> query = userDao.queryBuilder()
//                .where(UserDao.Properties.Name.like("%张三%"))
//                .orderDesc(UserDao.Properties.Age)
                .build();
        List<User> list = query.list();

        dataLists.addAll(list);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(mContext,"数据size : "+dataLists.size()+"",Toast.LENGTH_SHORT).show();
    }

    private void updateUser() {
        String userId = mEditMainDelete.getText().toString().trim();
        long id = TextUtils.isEmpty(userId) ? 0 : Long.parseLong(userId);
        User user = userDao.load(id);
        if (null != user) {
            user.setName("李四");
            user.setSex("未知");
            user.setSalary(18000L);
            user.setAge(21);
            userDao.update(user);
        }

    }

    private void deleteUser() {
        String userId = mEditMainDelete.getText().toString().trim();
        long id = TextUtils.isEmpty(userId) ? 0 : Long.parseLong(userId);
        if (0 != id) userDao.deleteByKey(id);
    }

    private void addUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0,len = 1000; i <len ; i++) {
                    User user = null;
                    if(i%2==0){
                        user = new User(null, "张三" + Calendar.MILLISECOND, "男", 19, i);
                    }else{
                        user = new User(null, "莉莉" + Calendar.MILLISECOND, "女", 16, i);
                    }

                    long insertId = userDao.insert(user);
                    //返回当前的id ,作为note的外键
                    addNotes(insertId,i);
                }

            }

            private void addNotes(long insertId,int position) {
                Note note1 = new Note(null, "content" + position, "" + Calendar.MINUTE, "" + Calendar.MINUTE, insertId);
                Note note2 = new Note(null, "内容" + position, "" + Calendar.MINUTE, "" + Calendar.MINUTE, insertId);
                noteDao.insert(note1);
                noteDao.insert(note2);
            }
        }).start();
    }
}
