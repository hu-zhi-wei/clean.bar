package com.bar.goods.bean;

public class ProductContents {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_contents.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_contents.product_id
     *
     * @mbg.generated
     */
    private Integer productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_contents.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_contents.id
     *
     * @return the value of product_contents.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_contents.id
     *
     * @param id the value for product_contents.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_contents.product_id
     *
     * @return the value of product_contents.product_id
     *
     * @mbg.generated
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_contents.product_id
     *
     * @param productId the value for product_contents.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_contents.remark
     *
     * @return the value of product_contents.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_contents.remark
     *
     * @param remark the value for product_contents.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}