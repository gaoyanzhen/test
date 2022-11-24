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
public class Truck extends Car {
    private String mode;

    @Override
    public void getDesc() {
        System.out.println("Truck： brand:" + super.getBrand() + ", color:" + super.getColor());
    }

    public void getTruckDesc() {
        System.out.println("brand:" + super.getBrand() + ", color:" + super.getColor() + ", mode:" + mode);
    }
}
