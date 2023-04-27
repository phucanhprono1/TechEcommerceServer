package com.example.techecommerceserver.dto;

public class CommentDTO {
    private Integer comment_id;
    private String comment;
    private Integer rate;
    private String resp_comment;

    private Integer productId;

    private Integer cId;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getResp_comment() {
        return resp_comment;
    }

    public void setResp_comment(String resp_comment) {
        this.resp_comment = resp_comment;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public CommentDTO(Integer comment_id, String comment, Integer rate, String resp_comment, Integer productId, Integer cId) {
        this.comment_id = comment_id;
        this.comment = comment;
        this.rate = rate;
        this.resp_comment = resp_comment;
        this.productId = productId;
        this.cId = cId;
    }

    public CommentDTO() {
    }
}
