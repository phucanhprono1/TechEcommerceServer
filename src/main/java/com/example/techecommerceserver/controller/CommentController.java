package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.exception.CommentException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Comment;
import com.example.techecommerceserver.model.Product;
import com.example.techecommerceserver.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService cService;


    @GetMapping("/viewComment/{productId}")
    public ResponseEntity<List<Comment>> viewProductByCategoryId(@PathVariable Integer productId)
            throws CommentException {
        return new ResponseEntity<List<Comment>>(cService.viewCommentbyProduct(productId), HttpStatus.OK);
    }

    @PostMapping("/addComment/{productId}")
    public ResponseEntity<Comment> addProduct(@RequestBody Comment c) throws CommentException {
        Comment comment = cService.addComment(c);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

}
