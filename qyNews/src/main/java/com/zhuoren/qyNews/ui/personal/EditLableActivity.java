package com.zhuoren.qyNews.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.zhuoren.qyNews.MyApp;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.ui.personal.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class EditLableActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView saveButton;
    private ImageView iv_back;

    private FlowLayout flowLayout;
    private List<String> labels;
    private String[] arr={"皮皮虾,我们走","你有freestyle吗?",
            "锦鲤","双11","中二","宅男","佛系","b站","店小二","工匠精神"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lable);
        init();




        saveButton= findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:{
                setSelected();
                finish();
                break;

            }
            case R.id.iv_back:{
                finish();
                break;
            }
            default:break;
        }

    }

    public void init(){
        flowLayout= findViewById(R.id.fl);
        labels= new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            labels.add(arr[i]);
        }
        flowLayout.setLabels(labels);
        flowLayout.initSelectedLables();
    }

    public void setSelected(){
        MyApp.getInstance().getSelectedLables().clear();
        MyApp.getInstance().getSelectedLables().addAll(flowLayout.getSelected());
        Log.d("Hashset:",""+MyApp.getInstance().getSelectedLables().size());
//        flowLayout.getSelected().clear();
        Log.d("Hashset:",""+flowLayout.getSelected().size());
    }
}
