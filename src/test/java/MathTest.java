import com.google.common.collect.Maps;
import com.mpobjects.bdparsii.eval.Expression;
import com.mpobjects.bdparsii.eval.Parser;
import com.mpobjects.bdparsii.eval.Scope;
import com.mpobjects.bdparsii.eval.Variable;
import com.mpobjects.bdparsii.tokenizer.ParseException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * 解析数学表达式
 *
 * @author gaoyanzhen
 * @since 2022-05-20
 */
public class MathTest {
    @Test
    public void decimalTest() {
        BigDecimal decimal = new BigDecimal("-1646.1205580000");
        BigDecimal intPart = decimal.setScale(2, RoundingMode.DOWN);
        BigDecimal frac = decimal.subtract(intPart).abs();
        System.out.println("decimal=" + decimal);
        System.out.println("intPart=" + intPart);
        System.out.println("frac=" + frac.toPlainString());
        System.out.println("精度丢失：" + (frac.compareTo(BigDecimal.ZERO) > 0));
    }

    @Test
    public void add() throws ParseException {
//        String exp = "2 + (7-5) * 3.14159 * num + sin(0)";
        String exp = "2 +  num ";
        // compile
        Scope scope = new Scope();
        Expression expression = Parser.parse(exp, scope);
        Variable var = scope.getVariable("num");
        var.setValue(2);


        // evaluate
//        double result = parsiiExpr.evaluate();
        //OR
        BigDecimal result = expression.evaluate();


        System.out.println(result);//-> 2.0
    }

    @Test
    public void addByParam() throws ParseException {
//        String exp = "2 + (7-5) * 3.14159 * num + sin(0)";
        String exp = "busIncome +  totalassets *5 ";

        Map<String, String> map = Maps.newHashMap();
        map.put("busIncome", "1.5788");
        map.put("totalassets", "11");
        map.put("haha", "44");
        // compile
        Scope scope = new Scope();
        Expression expression = Parser.parse(exp, scope);

        map.keySet().stream().forEach(key -> {
            Variable var = scope.getVariable(key);
            var.setValue(new BigDecimal(map.get(key)));
        });
        BigDecimal result = expression.evaluate();
        System.out.println(result);//-> 2.0
    }

    @Test
    public void cellTest(){
        int len = 8366;
        int threadDataSize = (int)Math.ceil(len / 5L);
        double threadDataSize2 = Math.ceil(len / 5L);
        double threadDataSize3 = Math.ceil((double) len / 5);
        System.out.println("threadDataSize:" +threadDataSize);
        System.out.println("threadDataSize:" +threadDataSize2);
        System.out.println("threadDataSize:" +threadDataSize3);
    }

}
