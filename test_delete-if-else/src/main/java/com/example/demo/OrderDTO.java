package com.example.demo;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.math.BigDecimal;

/***
 * @author : 马晓光
 * @date   : 2019/5/24
 * @description :
 **/
@Data
public class OrderDTO {
    private String code;
    private BigDecimal price;
    private String type;
}
