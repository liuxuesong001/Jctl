package com.colud.jctl.utils;

import android.app.Activity;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Jcty on 2017/4/20.
 */

public class EditTextUtil {


    /**
     * EditText获取焦点并显示软键盘
     * //
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().toString().length());
        //		activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //	public static  void colseSoftInputFromWindow(Activity activity, EditText editText) {
    //		//关闭软键盘
    //		InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    //		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    //	}
    //
    //	//输入监听
    //	public void  voidsetEditTextListener(final  EditText editText, final  int tailLength) {
    //
    //		//输入类型  浮点double型
    //
    //		editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    //
    //		editText.addTextChangedListener(new TextWatcher() {
    //			@Override
    //			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //			}
    //
    //			@Override
    //			public void onTextChanged(CharSequence s, int start, int before, int count) {
    //				//				//当包含运算符。的时候
    //				//				if (s.toString().contains(".")) {
    //				//					//如果长度大于你设定的长度
    //				//					if (s.length() - tailLength - s.toString().indexOf(".") > 1) {
    //				//						//窃取小数点前的数字加上小数点后你要限定的位数
    //				//						s = s.toString().subSequence(0,
    //				//						s.toString().indexOf(".") + 1) + "" + s.toString().subSequence(s.toString().indexOf(".") + 1, s.toString().indexOf(".") + tailLength + 1);
    //				//						//如果是.开头的加上0
    //				//						if (s.toString().trim().substring(0, 1).equals(".")) {
    //				//							s = "0" + s;
    //				//						}
    //				//						editText.setText(s);
    //				//						editText.setSelection(s.length());
    //				//						return;
    //				//					} else {
    //				//						//当长度没有大于限定位数的时候  如果是.开头的加上0
    //				//						if (s.toString().trim().substring(0, 1).equals(".")) {
    //				//							s = "0" + s;
    //				//							editText.setText(s);
    //				//							editText.setSelection(s.length());
    //				//							return;
    //				//						}
    //				//					}
    //				//				}
    //				//				//当以0开头并且长度大于1的时候
    //				//				if (s.toString().startsWith("0")
    //				//						&& s.toString().trim().length() > 1) {
    //				//					//如果第二位不是运算符.就截取第二位到S字符串末尾
    //				//					if (!s.toString().substring(1, 2).equals(".")) {
    //				//						editText.setText(s.subSequence(1, s.length()));
    //				//						editText.setSelection(editText.getText().toString().length());
    //				//						return;
    //				//					}
    //				//				}
    //			}
    //			@Override
    //			public void afterTextChanged(Editable s) {
    //
    //			}
    //		});
    //	}
    //
    //	/**
    //	 * 设置获取焦点状态
    //	 * @param value
    //	 * @param editText
    //	 */
    public static void isEdit(boolean value, EditText editText) {
        if (value) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    return null;
                }
            }});
        } else {
            //设置不可获取焦点
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
            //输入框无法输入新的内容
            editText.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    return source.length() < 1 ? dest.subSequence(dstart, dend) : "";
                }
            }});
        }
    }
}
