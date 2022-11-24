package channel;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.base.IChannelFunction;
import com.demo.channel.kx.KxChannelFunction;
import com.demo.channel.kx.KxFunctionChoice;
import com.demo.channel.kx.proxy.KxChannelFunctionProxy;
import com.demo.channel.result.R;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class ChannelTest {
    @Test
    public void test() {
        KxFunctionChoice functionChoice = new KxFunctionChoice();

        R r1 = functionChoice.goToFunction("querySettleStatus", "v1");
        System.out.println("querySettleStatus.v1，返回结果:" + JSONObject.toJSONString(r1));
        R r2 = functionChoice.goToFunction("queryAcceptResult", "v1");
        System.out.println("queryAcceptResult.v1，返回结果:" + JSONObject.toJSONString(r2));
        R r3 = functionChoice.goToFunction("revokeOfApply", "v1");
        System.out.println("revokeOfApply.v1，返回结果:" + JSONObject.toJSONString(r3));
        R r4 = functionChoice.goToFunction("revokeOfApplyError", "v1");
        System.out.println("revokeOfApplyError.v1，返回结果:" + JSONObject.toJSONString(r4));
    }
}
