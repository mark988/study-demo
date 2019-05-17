package com.example.demo.entity;


import lombok.Data;

/**
 *
 * @author
 * @since 2019-01-13
 */
@Data
public class TAccount   {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userId;
    private Double amount;
}
