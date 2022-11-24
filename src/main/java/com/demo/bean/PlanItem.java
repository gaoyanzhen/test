package com.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-08-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanItem {
    private int id;
    private String currNum;
    private BigDecimal price;
}
