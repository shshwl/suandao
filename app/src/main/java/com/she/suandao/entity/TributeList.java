package com.she.suandao.entity;

import java.util.List;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/25
 * Time : 14:10
 */
public class TributeList {

    /**
     * Status : 1
     * InnerData : [{"Describe":"松子有\u201c送子\u201d之意。","Category":2,"Price":10,"Quantity":10,"Sort":4,"Code":"2027","Url":"http://i1-suand.she-tech.cn/upload//qifu/gongpin/pray%2Flingji_qifutai_fruit3.png","Name":"松子"},{"Describe":"人法地，地法天，天法道，道法自然。以自己为法，实际不是特意地去效法某一个东西，而只是顺乎一切事物和世界的本性，顺乎规律，遂其自身固有的性质和变化历程。兰生于幽谷，不与桃李争艳，不因霜雪变色，幽香清远，发乎自然，无矫揉造作之态，也没有趋势求媚之容，\u201c兰在幽林亦自芳\u201d，这和师法自然的道理完全一致的。?","Category":1,"Price":60,"Quantity":10,"Sort":9,"Code":"2002","Url":"http://i1-suand.she-tech.cn/upload//qifu/gongpin/flower_2.png","Name":"兰花"}]
     * Message : 获取成功
     * Result : true
     */
    private int Status;
    private List<InnerDataEntity> InnerData;
    private String Message;
    private boolean Result;

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setInnerData(List<InnerDataEntity> InnerData) {
        this.InnerData = InnerData;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public void setResult(boolean Result) {
        this.Result = Result;
    }

    public int getStatus() {
        return Status;
    }

    public List<InnerDataEntity> getInnerData() {
        return InnerData;
    }

    public String getMessage() {
        return Message;
    }

    public boolean isResult() {
        return Result;
    }

    public class InnerDataEntity {
        /**
         * Describe : 松子有“送子”之意。
         * Category : 2
         * Price : 10.0
         * Quantity : 10
         * Sort : 4
         * Code : 2027
         * Url : http://i1-suand.she-tech.cn/upload//qifu/gongpin/pray%2Flingji_qifutai_fruit3.png
         * Name : 松子
         */
        private String Describe;
        private int Category;
        private double Price;
        private int Quantity;
        private int Sort;
        private String Code;
        private String Url;
        private String Name;

        public void setDescribe(String Describe) {
            this.Describe = Describe;
        }

        public void setCategory(int Category) {
            this.Category = Category;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public void setQuantity(int Quantity) {
            this.Quantity = Quantity;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDescribe() {
            return Describe;
        }

        public int getCategory() {
            return Category;
        }

        public double getPrice() {
            return Price;
        }

        public int getQuantity() {
            return Quantity;
        }

        public int getSort() {
            return Sort;
        }

        public String getCode() {
            return Code;
        }

        public String getUrl() {
            return Url;
        }

        public String getName() {
            return Name;
        }
    }
}
