import com.demo.bean.Car;
import com.demo.bean.Truck;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-07-22
 */
public class ExtendsTest {
    @Test
    public void testFunc() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Truck truck = new Truck();
        Car car = new Car();
        Class clazz = Truck.class;
        Method[] methods = clazz.getMethods();
        System.out.println("Truck类所有方法：");
        Arrays.stream(methods).forEach(method -> {
            System.out.println(method + " ->class: " + method.getDeclaringClass());
        });
        System.out.println("Truck类声明方法：");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
            System.out.println(method + " ->class: " + method.getDeclaringClass());
        });
        System.out.println("truck.getDesc调用结果：");
        Method getDesc = clazz.getMethod("getDesc", null);
        getDesc.invoke(truck, null);
        System.out.println("car.getDesc调用结果：");
        Method getDesc2 = Car.class.getMethod("getDesc", null);
        getDesc2.invoke(car, null);
    }

}
