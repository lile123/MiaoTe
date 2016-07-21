package com.qianfeng.laosiji.miaote.views;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lenovo11 on 2016/7/21.
 */
public class StretchUtil {

    private TextView textView;
    private int lines;
    private Button arrowButton;

    private StretchUtil(TextView textView , int lines ,Button arrowButton ){
        this.textView = textView;
        this.lines = lines;
        this.arrowButton = arrowButton;

    }

    /**
     * @Title: getInstance
     * @Description:得到收缩工具的实例
     * @param textView 要收缩的文本控件
     * @param lines 指定收缩的行数
     * @param Button 控制收缩的箭头控件
     * @param arrowUpId 箭头向上的图标
     * @param arrowDownId 箭头向下的图标
     * @return StretchUtil 返回本类对象
     */
    public static StretchUtil getInstance(TextView textView , int lines ,Button arrowButton ){
        return new StretchUtil(textView ,lines ,arrowButton );
    }

    /**
     * @Title: initStretch
     * @Description:初始化文本收缩
     */
    public void initStretch(){
        //设置默认状态
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            boolean flag = false;
            @Override
            public void onGlobalLayout(){
                if(!flag){
                    flag = true;
                    initStretchStatus();
                }
            }
        });
        //当文本内容改变时初始化状态
        textView.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                initStretchStatus();
            }



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void afterTextChanged(Editable s){}
        });
        //当箭头点击时，改变状态
        arrowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Object o = v.getTag();
                if(o != null){
                    int status = (Integer)o;
                    if(status == 0){
                        setStretch(1);
                    } else if(status == 1){
                        setStretch(0);
                    }
                }
            }
        });
    }

    /**
     * @Title: initStretchStatus
     * @Description:初始化文本的状态
     */
    public void initStretchStatus(){
        if(isMoreLines()){
            //超过了两行
            setStretch(1);
        }else{
            setStretch(-1);
        }
    }

    /**
     * @Title: isMoreLines
     * @Description:判断文本的内容是否超过指定的行数
     * @return boolean
     */
    public boolean isMoreLines(){
        float allTextPx = textView.getPaint().measureText(textView.getText().toString());
        float showViewPx = (textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight()) * lines;
        return allTextPx > showViewPx;
    }

    /**
     * @Title: setStretch
     * @Description:设置箭头的显示状态与文本的伸缩状态
     * @param status -1:不显示，0：向上箭头，1：向下箭头
     */
    public void setStretch(int status){
        if(status == -1){
            arrowButton.setVisibility(View.GONE);
        }else if(status == 0){
            arrowButton.setText("点击收起");
            textView.setSingleLine(false);
            textView.setEllipsize(null);
        }else if(status == 1){
            arrowButton.setText("显示更多");
            textView.setLines(lines);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        arrowButton.setTag(status);
    }
}
