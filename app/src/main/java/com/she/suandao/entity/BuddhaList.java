package com.she.suandao.entity;

import java.util.List;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/25
 * Time : 14:15
 */
public class BuddhaList {

    /**
     * Status : 1
     * InnerData : [{"Describe":"紫微大帝是道教四御之一，全称为\u201c中天紫微北极太皇大帝\u201d，紫微又叫紫微垣、紫宫、紫微星，位处三垣之中的中垣。地位仅次于玉皇大帝。道教认为北极星是永远不动的星，位于上天的最中间，位置最高，最为尊贵，是\u201c众星之主\u201d，因此对他极为尊崇。紫微大帝的职能是：执掌天经地纬，以率日月星辰和山川诸神及四时节气等自然现象，能呼风唤雨，役使雷电鬼神。如《九天应元雷声普化天尊玉枢宝经集注》卷上曰：\u201c北极紫微大帝掌握五雷也。\u201d紫微大帝的神诞日为农历的四月十八日。","Type":"事业","Category":1,"SumDescribe":"供奉此仙能佑您职场如鱼得水，事业平步青云。","Code":"1001","Url":"http://i1-suand.she-tech.cn/qifu/shenxiang/qifu_daxian_00.png","Name":"紫微大帝"}]
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
         * Describe : 紫微大帝是道教四御之一，全称为“中天紫微北极太皇大帝”，紫微又叫紫微垣、紫宫、紫微星，位处三垣之中的中垣。地位仅次于玉皇大帝。道教认为北极星是永远不动的星，位于上天的最中间，位置最高，最为尊贵，是“众星之主”，因此对他极为尊崇。紫微大帝的职能是：执掌天经地纬，以率日月星辰和山川诸神及四时节气等自然现象，能呼风唤雨，役使雷电鬼神。如《九天应元雷声普化天尊玉枢宝经集注》卷上曰：“北极紫微大帝掌握五雷也。”紫微大帝的神诞日为农历的四月十八日。
         * Type : 事业
         * Category : 1
         * SumDescribe : 供奉此仙能佑您职场如鱼得水，事业平步青云。
         * Code : 1001
         * Url : http://i1-suand.she-tech.cn/qifu/shenxiang/qifu_daxian_00.png
         * Name : 紫微大帝
         */
        private String Describe;
        private String Type;
        private int Category;
        private String SumDescribe;
        private String Code;
        private String Url;
        private String Name;

        public void setDescribe(String Describe) {
            this.Describe = Describe;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public void setCategory(int Category) {
            this.Category = Category;
        }

        public void setSumDescribe(String SumDescribe) {
            this.SumDescribe = SumDescribe;
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

        public String getType() {
            return Type;
        }

        public int getCategory() {
            return Category;
        }

        public String getSumDescribe() {
            return SumDescribe;
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
