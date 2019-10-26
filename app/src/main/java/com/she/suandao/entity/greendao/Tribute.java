package com.she.suandao.entity.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description :
 * 贡品实体类
 *
 * @author : mrj
 * Date : 2019/10/25
 * Time : 13:20
 */
@Entity
public class Tribute {
    @Id
    private Long id;
    private String Code;
    private int Category;
    private String Name;
    private String Describe;
    private String Url;
    private double Price;
    private int Quantity;
    private int Sort;
    @Generated(hash = 1632124259)
    public Tribute(Long id, String Code, int Category, String Name, String Describe,
            String Url, double Price, int Quantity, int Sort) {
        this.id = id;
        this.Code = Code;
        this.Category = Category;
        this.Name = Name;
        this.Describe = Describe;
        this.Url = Url;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Sort = Sort;
    }
    @Generated(hash = 448354283)
    public Tribute() {
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
    public String getUrl() {
        return this.Url;
    }
    public void setUrl(String Url) {
        this.Url = Url;
    }
    public double getPrice() {
        return this.Price;
    }
    public void setPrice(double Price) {
        this.Price = Price;
    }
    public int getQuantity() {
        return this.Quantity;
    }
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    public int getSort() {
        return this.Sort;
    }
    public void setSort(int Sort) {
        this.Sort = Sort;
    }
}
