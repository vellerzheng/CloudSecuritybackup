package com.mcloud.service.cloudService.baidu.entity;

/**
 * Created by vellerzheng on 2017/8/18.
 */

/**
 * @author ydcun 获取云空间的信息 例如：
 * 				{"quota":123794882560, 空间配额，单位为字节
 *        		 "used":83573494688, 已使用空间大小 单位为字节。
 *          	 "request_id":2853739529}
 */
public class CloudInfo{
    private Double quota;
    private Double used;
    private Double request_id;
    /**
     * @return the quota  空间配额，单位为字节
     */
    public Double getQuota() {
        return quota;
    }
    /**
     * @param quota the quota to set  空间配额，单位为字节
     */
    public void setQuota(Double quota) {
        this.quota = quota;
    }
    /**
     * @return the used 已使用空间大小 单位为字节
     */
    public Double getused() {
        return used;
    }
    /**
     * @param used the used to set 已使用空间大小 单位为字节
     */
    public void setused(Double used) {
        this.used = used;
    }
    /**
     * @return the request_id
     */
    public Double getRequest_id() {
        return request_id;
    }
    /**
     * @param request_id the request_id to set
     */
    public void setRequest_id(Double request_id) {
        this.request_id = request_id;
    }
    @Override
    public String toString() {

        return new StringBuffer().append("空间容量:").append(this.getQuota()/1024/1024).append("M;  已用:").append(this.getused()/1024/1024).append("M; ").toString();
    }
}