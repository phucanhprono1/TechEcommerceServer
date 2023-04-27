package com.example.techecommerceserver.service;


import com.example.techecommerceserver.exception.CommentException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Comment;
import com.example.techecommerceserver.model.Product;

import java.util.List;

public interface CommentService {
    public List<Comment> viewCommentbyProduct(Integer productId) throws CommentException;

    public Comment addComment(Comment com) throws CommentException;

}
