package com.bawei.zuoyeohe.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bawei.zuoyeohe.R;
import com.bawei.zuoyeohe.base.BaseActivity;
import com.bawei.zuoyeohe.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {


    @BindView(R.id.second_one)
    TextView secondOne;
    @BindView(R.id.second_bt1)
    Button secondBt1;
    @BindView(R.id.second_bt2)
    TextView secondBt2;
    @BindView(R.id.second_image)
    ImageView secondImage;
    @BindView(R.id.bt3)
    Button bt3;


    @Override
    protected void initData() {
        ButterKnife.bind(this);
        CodeUtils.init(this);
        secondImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(secondImage, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, "成功" + result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter provitePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.second_one, R.id.second_bt1, R.id.second_bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.second_one:
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.one);
                Bitmap bitmap = CodeUtils.createImage("看看这是啥", 400, 400, bitmap1);
                secondImage.setImageBitmap(bitmap);
                break;
            case R.id.second_bt1:
                CodeUtils.analyzeByCamera(this);
                break;
            case R.id.second_bt2:
                CodeUtils.analyzeByPhotos(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(SecondActivity.this, "成功" + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.bt3)
    public void onViewClicked() {
         EventBus.getDefault().post("哈哈哈");
    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEvent(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
}
