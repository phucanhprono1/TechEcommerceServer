package com.example.techecommerceserver.implementation;


import com.example.techecommerceserver.exception.CommentException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Comment;
import com.example.techecommerceserver.model.Product;
import com.example.techecommerceserver.repository.CommentRepo;
import com.example.techecommerceserver.repository.ProductRepo;
import com.example.techecommerceserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentImpl implements CommentService {
    @Autowired
    private CommentRepo cRepo;
    @Autowired
    private ProductRepo pRepo;
    @Override
    public List<Comment> viewCommentbyProduct(Integer productId) throws CommentException {
        Optional<Product> product = pRepo.findById(productId);
        if (product.isPresent()) {
            return product.get().getCommentList();
        } else {
            throw new CommentException("Product not found with category id - " + productId);
        }

    }

    @Override
    public Comment addComment(Comment comment) throws CommentException {
        Comment comm = cRepo.save(comment);
        if (comm != null) {
            return comm;
        } else {
            throw new CommentException("Comment not added");
        }

    }


}
