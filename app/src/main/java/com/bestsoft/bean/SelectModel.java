package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/13
 * @description:
 **/
public class SelectModel {
    private String selectname;
    private boolean isSelected;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SelectModel( String id,String selectname, boolean isSelected) {
        this.selectname = selectname;
        this.isSelected = isSelected;
        this.id = id;
    }

    public String getSelectname() {
        return selectname;
    }

    public void setSelectname(String selectname) {
        this.selectname = selectname;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
