package com.QuestionBank.entity;



import java.io.Serializable;

public class ConcretePermissionEntity implements Serializable {
    /**
     * @Author ZhangFuGui
     * @Description  具体权限的名称
     * @Date 下午 5:01 2018/9/12 0012
     * @Param
     * @return
     **/
    private  String  permissionName;
    public  static final  String PERMISSION_NAME="permissionName";

    /**
     * @Author ZhangFuGui
     * @Description 权限的状态码 1 拥有  2 未拥有
     * @Date 下午 5:01 2018/9/12 0012
     * @Param
     * @return
     **/
    private  String state;
    public  static  final  String STATE="state";


    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ConcretePermissionEntity{" +
                "permissionName='" + permissionName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
