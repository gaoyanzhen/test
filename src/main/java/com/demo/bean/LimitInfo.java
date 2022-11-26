package com.demo.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 额度信息实体类
 *
 * @author gaoyanzhen
 * @since 2022-11-25
 */
@Data
public class LimitInfo {
    private int id;
    private BigDecimal limitAmount;
    private BigDecimal usedAmount;
    private BigDecimal leftAmount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
