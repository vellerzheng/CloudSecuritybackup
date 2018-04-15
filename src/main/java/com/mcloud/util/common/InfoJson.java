package com.mcloud.util.common;


import java.io.Serializable;

public class InfoJson implements Serializable {
//		public static final String ERROR="系统繁忙，请稍后重试";

    /**
     * 2000：成功状态码
     * 5000：系统请求失败
     * 5001：请求参数有误
     * 5002：操作失败
     * 4000：签名参数未传
     * 4003：签名错误
     * 5003：请求超时返回
     * 2004：查询的数据不存在
     */
    private Integer resultCode = 2000;

    /**
     * 2000：成功状态码
     */
    public static final int INFO_SUCCESS = 2000;
    /**
     * 5000：系统请求失败
     */
    public static final int INFO_SYSTEMFAIL = 5000;
    /**
     * 5001：请求参数有误
     */
    public static final int INFO_WRONGPARAM = 5001;
    /**
     * 5002：操作失败
     */
    public static final int INFO_OPFAILED = 5002;
    /**
     * 4000：签名参数未传
     */
    public static final int INFO_NOSIGNPARAM = 4000;
    /**
     * 4003：签名错误
     */
    public static final int INFO_SIGNERROR = 4003;
    /**
     * 5003：请求超时返回
     */
    public static final int INFO_REQTIMEOUT = 5003;
    /**
     * 2004：查询的数据不存在
     */
    public static final int INFO_DATANOEXIST = 2004;

    /**
     * 返回信息
     */
    private String resultMsg;
    private Object data;

    /**
     * goback =-1时，返回上一页
     */
        /*private int goback;*/
    public InfoJson() {
    }

    public InfoJson(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public static InfoJson getSucc(Object data) {
        InfoJson info = new InfoJson();
        info.setResultCode(InfoJson.INFO_SUCCESS);
        info.setResultMsg("成功");
        info.setData(data);
        return info;
    }
    //data与getSucc用相同字符串的失败获取方法
    public static InfoJson getFail(Object data) {
        InfoJson info = new InfoJson();
        info.setResultCode(InfoJson.INFO_SYSTEMFAIL);
        info.setResultMsg("失败");
        info.setData(data);
        return info;
    }

    public static InfoJson getInfo(Integer resultCode, String resultMsg, Object data) {
        InfoJson info = new InfoJson();
        info.resultCode = resultCode;
        info.resultMsg = resultMsg;
        info.data = data;
        return info;
    }

    /*public static InfoJson setExceptionReturn(String e,Object data){
        InfoJson info= new InfoJson();
        info.setStatus(1);
        if(e!=null){
            info.getInfo(e);
        }else{
            info.getInfo("系统繁忙，请稍后重试");
        }
        if(null!=data) info.setData(data);
        return info;
    }*/
    public static InfoJson setFaile() {
        InfoJson info = new InfoJson();
        info.setResultCode(InfoJson.INFO_SYSTEMFAIL);
        info.setResultMsg("系统繁忙，请稍后重试");
        return info;
    }

    public static InfoJson setFaile(String errMsg) {
        InfoJson info = new InfoJson();
        info.setResultCode(InfoJson.INFO_SYSTEMFAIL);
        info.setResultMsg(errMsg);
        return info;
    }

    /*public int getGoback() {
        return goback;
    }
    public void setGoback(int goback) {
        this.goback = goback;
    }*/
    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}



