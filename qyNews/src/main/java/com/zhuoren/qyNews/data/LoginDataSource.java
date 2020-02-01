package com.zhuoren.qyNews.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhuoren.qyNews.MyApp;
import com.zhuoren.qyNews.data.model.DBHelper;
import com.zhuoren.qyNews.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        if (MyApp.getInstance().getFlag() ==0){
            return register(username,password,0);

        }else{
            return login(username,password,1);
        }
    }

    public Result<LoggedInUser> login(String username, String password,int code) {
        //将登录数据发送给服务器的网络交互操作写在下面
        boolean success=false;

        DBHelper dbHelper;
        SQLiteDatabase db= MyApp.getInstance().getDbHelper().getReadableDatabase();
        String sql = "select * from user where username=? and password = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{username,password});

        if(cursor.moveToFirst()){
            String uname= cursor.getString(cursor.getColumnIndex("username"));
            String pswd= cursor.getString(cursor.getColumnIndex("password"));
            success=true;
        }

        cursor.close();
        if(success){
            try {
                // TODO: handle loggedInUser authentication

                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                "Jane Doe");
                return new Result.Success<>(fakeUser);
            } catch (Exception e) {
                return new Result.Error(new IOException("Error logging in", e));
            }
        }else {
            return new Result.Error(new IOException("Error logging in"));
        }

    }

    public Result<LoggedInUser> register(String username, String password,int code) {

        //将注册数据发送给服务器的网络交互操作下面
        try {
            // TODO: handle loggedInUser authentication
            SQLiteDatabase db= MyApp.getInstance().getDbHelper().getWritableDatabase();
            String sql ="insert into user values('"+username+"','"+password+"')";
            db.execSQL(sql);
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }



    public void logout() {
        // TODO: revoke authentication
    }
}
