package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author heshouyou
 * @since 2019-01-13
 */
@Data
public class TStorage implements Serializable {
    private Integer id;
    private String commodityCode;
    private String name;
    private Integer count;
}
