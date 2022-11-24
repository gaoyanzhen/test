package com.demo.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-07-22
 */
@Setter
@Getter
public class Car {
    private String brand;
    private String color;

    public void getDesc() {
        System.out.println("brand:" + brand + ", color:" + color);
    }
}
