package com.mcloud.model.common;

public class Pager {

    private int firstPageNo=1;

    private int nowPageNo = 1;

    private int sizePerPage = 5;

    private int totalCount = 0;

    private int pageNum = 0;

    private String idList = "";

    private String actType = "list";

    private String selectHql = "  from ";
    //在业务翻页实体中定制
    //private String modelHql = " agentw.buzi.user.model.User User ";
    private String whereHql = " where 1=1 ";
    private String orderHql = " order by ";
    private String whereString = "";

    private String orderKey = "id";

    private String ascDesc = " DESC ";

    protected String modelHql="";


    //对前一页修正
    public int getBeforPageNo(){
        if( nowPageNo <=1 ){
            return 1;
        }else{
            return nowPageNo-1;
        }
    }
    //对下一页修正
    public int getNextPageNo(){

        int LastPageNo = this.getLastPageNo();

        if( nowPageNo < LastPageNo){
            return nowPageNo + 1;
        }else{
            return nowPageNo;
        }
    }
    //对最后一页修正
    public int getLastPageNo(){

        int LastPageNo  = totalCount/sizePerPage;
        int LastPageCount = totalCount%sizePerPage;

        if( LastPageCount > 0 ){
            LastPageNo++;
        }
        pageNum = LastPageNo;
        return LastPageNo;
    }

    public int getFirstPageNo() {
        return firstPageNo;
    }

    public void setFirstPageNo(int firstPageNo) {
        this.firstPageNo = firstPageNo;
    }

    //对第几页修正
    public int getNowPageNo() {
        int lastPageNo = this.getLastPageNo();
        if(lastPageNo < nowPageNo){
            return lastPageNo;
        }else{
            return nowPageNo;
        }
    }

    public void setNowPageNo(int nowPageNo) {
        this.nowPageNo = nowPageNo;
    }

    public int getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public String getOrderKey() {
        return orderKey;
    }


    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }


    public String getAscDesc() {
        return ascDesc;
    }


    public void setAscDesc(String ascDesc) {
        this.ascDesc = ascDesc;
    }


    public String getSelectHql() {
        return selectHql;
    }


    public void setSelectHql(String selectHql) {
        this.selectHql = selectHql;
    }


    public String getWhereHql() {
        return whereHql;
    }


    public void setWhereHql(String whereHql) {
        this.whereHql = whereHql;
    }


    public String getOrderHql() {
        return orderHql;
    }


    public void setOrderHql(String orderHql) {
        this.orderHql = orderHql;
    }


    public String getWhereString() {
        return whereString;
    }


    public void setWhereString(String whereString) {
        this.whereString = whereString;
    }


    public String getIdList() {
        return idList;
    }


    public void setIdList(String idList) {
        this.idList = idList;
    }


    public String getActType() {
        return actType;
    }


    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getModelHql() {
        return modelHql;
    }


    public void setModelHql(String modelHql) {
        this.modelHql = modelHql;
    }


    public String getCountByHql() {
        return this.getModelHql()+this.getWhereHql();
    }
}
