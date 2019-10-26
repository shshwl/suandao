package com.she.suandao.entity.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description :
 * 佛像实体类
 *
 * @author : mrj
 * Date : 2019/10/25
 * Time : 11:59
 */
@Entity
public class Buddha {
    @Id
    private Long id;
    private String Code;
    private int Category;
    private String Name;
    private String Describe;
    private String Type;
    private String SumDescribe;
    private String Url;
    @Generated(hash = 757082150)
    public Buddha(Long id, String Code, int Category, String Name, String Describe,
            String Type, String SumDescribe, String Url) {
        this.id = id;
        this.Code = Code;
        this.Category = Category;
        this.Name = Name;
        this.Describe = Describe;
        this.Type = Type;
        this.SumDescribe = SumDescribe;
        this.Url = Url;
    }
    @Generated(hash = 273860964)
    public Buddha() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return this.Code;
    }
    public void setCode(String Code) {
        this.Code = Code;
    }
    public int getCategory() {
        return this.Category;
    }
    public void setCategory(int Category) {
        this.Category = Category;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getDescribe() {
        return this.Describe;
    }
    public void setDescribe(String Describe) {
        this.Describe = Describe;
    }
    public String getType() {
        return this.Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }
    public String getSumDescribe() {
        return this.SumDescribe;
    }
    public void setSumDescribe(String SumDescribe) {
        this.SumDescribe = SumDescribe;
    }
    public String getUrl() {
        return this.Url;
    }
    public void setUrl(String Url) {
        this.Url = Url;
    }
}
