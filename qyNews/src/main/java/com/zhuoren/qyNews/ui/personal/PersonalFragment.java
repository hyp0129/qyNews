package com.zhuoren.qyNews.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.component.ApplicationComponent;
import com.zhuoren.qyNews.ui.base.BaseFragment;
import com.zhuoren.qyNews.ui.personal.view.CircleImageView;

import java.io.ByteArrayOutputStream;

import static com.zhuoren.qyNews.ui.personal.util.FileUtil.getRealFilePathFromUri;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private LinearLayout settingsLayout;
    private LinearLayout headImageLayout;
    private CircleImageView headImage;
    private TextView usernameTextView;
    private static final int REQUEST_HEAD_IMAGE=107;

    private Bitmap bitmap;
    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        //如果打开PersonalFragment时需要传入参数，可以在mainActivity中new Instance时传入参数
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        headImageLayout= getActivity().findViewById(R.id.headImageLayout);
        headImageLayout.setOnClickListener(this);
        headImage= getActivity().findViewById(R.id.headImage);
        usernameTextView= getActivity().findViewById(R.id.usernameTextView);




        settingsLayout= getActivity().findViewById(R.id.settingsLayout);
        settingsLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.headImageLayout:{
                Intent intent=new Intent(getActivity(),SettingPersonalInfoActivity.class);
//                到这里
                if (bitmap==null) {
                    Log.d("bitmap","bitmap==null");
                    drawableToBitamp(headImage.getDrawable());
                    intent.putExtra("headImage",bitmap2Bytes(bitmap));
                }else {
                    Log.d("bitmap","bitmap!=null");
                    intent.putExtra("headImage",bitmap2Bytes(bitmap));
                }

                intent.putExtra("oldUsername",usernameTextView.getText().toString());
                startActivityForResult(intent,REQUEST_HEAD_IMAGE);
                break;
            }
            case R.id.settingsLayout:{
                startActivity(new Intent(getActivity(),SettingsActivity.class));
                break;
            }
            default:break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_HEAD_IMAGE:{
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    Bundle name=data.getExtras();
                    if (uri == null && name==null) {
                        Log.d("PersonalFragment","personalIntent=null");
                        return;
                    }
                    if(uri!=null){
                        String cropImagePath = getRealFilePathFromUri(getContext(), uri);
                        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);

                        headImage.setImageBitmap(bitMap);
                        bitmap=bitMap;
                        Log.d("PersonalFragment","uri!=null");
                    }
                    if (name!=null){
                        usernameTextView.setText(name.getString("newUsername"));
                        Log.d("PersonalFragment","name！=null");
                    }
                }else {
                    Log.d("PersonalFragment","code!=ok");
                }
                break;
            }
        }
        Log.d("PersonalFragment","onActivity");
    }


    //drawable转化成bitmap的方法
    private void drawableToBitamp(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(w,h,config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
    }


    private byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


}

