package com.zhuoren.qyNews.ui.personal;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhuoren.qyNews.R;

public class EditUsernameActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText usernameEditText;
    private Button saveButton;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_username);

        usernameEditText=findViewById(R.id.usernameEditText);
        usernameEditText.setText(getIntent().getExtras().getString("preUsername"));
        //将光标定位到文本最后
        usernameEditText.setSelection(usernameEditText.getText().length());

        saveButton= findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:{
                String newUsername= usernameEditText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("newUsername",newUsername);
                setResult(RESULT_OK,intent);
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
}
