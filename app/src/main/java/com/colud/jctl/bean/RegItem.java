package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/4/14.
 */

public class RegItem implements Serializable {

    private static final long serialVersionUID = -4863858273024276850L;

    private String mName;
    private int mSex;
    private String mAge;
    private String mAddress;
    private String companyName;  //公司
    private String mMobile;
    private String password;
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    private String mVerCode;  //验证码

    private String id;
    private String flag;
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //	public RegItem(){};
    //
    //
    //	protected RegItem(Parcel in) {
    //		mName = in.readString();
    //		mSex = in.readInt();
    //		mAge = in.readString();
    //		mAddress = in.readString();
    //		companyName = in.readString();
    //		mMobile = in.readString();
    //		mPass = in.readString();
    //		password = in.readString();
    //		mVerCode = in.readString();
    //	}

    //	@Override
    //	public void writeToParcel(Parcel dest, int flags) {
    //		dest.writeString(mName);
    //		dest.writeInt(mSex);
    //		dest.writeString(mAge);
    //		dest.writeString(mAddress);
    //		dest.writeString(companyName);
    //		dest.writeString(mMobile);
    //		dest.writeString(mPass);
    //		dest.writeString(password);
    //		dest.writeString(mVerCode);
    //	}
    //
    //	@Override
    //	public int describeContents() {
    //		return 0;
    //	}
    //
    //	public static final Creator<RegItem> CREATOR = new Creator<RegItem>() {
    //		@Override
    //		public RegItem createFromParcel(Parcel in) {
    //			RegItem regItem = new RegItem();
    //			regItem.mName = in.readString();
    //			regItem.mSex =in.readInt();
    //			regItem.mAge=in.readString();
    //			regItem.mAddress = in.readString();
    //			regItem.companyName=in.readString();
    //			regItem.mMobile=in.readString();
    //			regItem.mPass=in.readString();
    //			regItem.password =in.readString();
    //			regItem.mVerCode=in.readString();
    //			return regItem;
    //
    //
    //		}
    //
    //		@Override
    //		public RegItem[] newArray(int size) {
    //			return new RegItem[size];
    //		}
    //	};

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmSex() {
        return mSex;
    }

    public void setmSex(int mSex) {
        this.mSex = mSex;
    }

    public String getmAge() {
        return mAge;
    }

    public void setmAge(String mAge) {
        this.mAge = mAge;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getmVerCode() {
        return mVerCode;
    }

    public void setmVerCode(String mVerCode) {
        this.mVerCode = mVerCode;
    }


}
