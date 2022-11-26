package com.demo.lock;

import com.alibaba.fastjson.JSONObject;
import com.demo.bean.LimitInfo;
import com.demo.jdbc.DbUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 额度同步控制
 *
 * @author gaoyanzhen
 * @since 2022-11-25
 */
@Slf4j
public class LimitInfoDao {
    private String SELECT_BY_ID_SQL = "SELECT * FROM limit_info where id=:id";
    private ReentrantLock limitLock = new ReentrantLock();
    private NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(DbUtil.getDataSource());

    @Transactional(rollbackFor = Exception.class)
    public synchronized boolean reduceLimitSync(int id, BigDecimal amount) {
        log.info("进入同步扣减额度方法，等待5秒...");
//        Thread.sleep(5000);
        boolean flag = reduceLimit(id, amount);
        log.info("离开同步扣减额度方法...");
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public synchronized boolean recoverLimitSync(int id, BigDecimal amount) {
        log.info("进入同步恢复额度方法，等待5秒...");
//        Thread.sleep(5000);
        boolean flag = recoverLimit(id, amount);
        log.info("离开同步恢复额度方法...");
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean reduceLimit(int id, BigDecimal amount){
        log.info("额度扣减开始，额度ID：{}，扣减金额：{}", id, amount);
        limitLock.lock();
        try {
            Map<String, Object> paramMap = Maps.newHashMap();
            paramMap.put("id", id);
            LimitInfo limitInfo = jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, paramMap, new BeanPropertyRowMapper<>(LimitInfo.class));
            log.info("额度信息:{}", JSONObject.toJSONString(limitInfo));
            if(limitInfo.getLeftAmount().compareTo(amount) <0){
                log.info("额度可用额度不足，可用额度：{}", limitInfo.getLeftAmount());
                return false;
            }
            BigDecimal usedAmount = limitInfo.getUsedAmount().add(amount);
            BigDecimal leftAmount = limitInfo.getLeftAmount().subtract(amount);
            String updateSql = "update limit_info set used_amount=:usedAmount, left_amount=:leftAmount, update_time=:updateTime where id=:id";
            paramMap.put("usedAmount", usedAmount);
            paramMap.put("leftAmount", leftAmount);
            paramMap.put("updateTime", LocalDateTime.now());
            jdbcTemplate.update(updateSql, paramMap);
            log.info("额度--结束，扣减金额：{}，额度总额：{}，已用额度：{}，可用额度：{}", amount, limitInfo.getLimitAmount(), usedAmount, leftAmount);
        } catch (Exception e) {
            log.error("扣减额度异常，回滚事务，{}", e.getMessage());
        } finally {
            limitLock.unlock();
        }
        return true;
    }

    public boolean recoverLimit(int id, BigDecimal amount){
        log.info("额度恢复开始，额度ID：{}，恢复金额：{}", id, amount);
        limitLock.lock();
        try {
            Map<String, Object> paramMap = Maps.newHashMap();
            paramMap.put("id", id);
            LimitInfo limitInfo = jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, paramMap, new BeanPropertyRowMapper<>(LimitInfo.class));
            log.info("额度信息:{}", JSONObject.toJSONString(limitInfo));
            if(limitInfo.getUsedAmount().compareTo(BigDecimal.ZERO) <=0){
                log.info("已用额度无需恢复，可用额度：{}", limitInfo.getLeftAmount());
                return false;
            }
            BigDecimal usedAmount = limitInfo.getUsedAmount().subtract(amount);
            BigDecimal leftAmount = limitInfo.getLeftAmount().add(amount);
            String updateSql = "update limit_info set used_amount=:usedAmount, left_amount=:leftAmount, update_time=:updateTime where id=:id";
            paramMap.put("usedAmount", usedAmount);
            paramMap.put("leftAmount", leftAmount);
            paramMap.put("updateTime", LocalDateTime.now());
            jdbcTemplate.update(updateSql, paramMap);
            log.info("额度++结束，扣减金额：{}，额度总额：{}，已用额度：{}，可用额度：{}", amount, limitInfo.getLimitAmount(), usedAmount, leftAmount);
        } catch (Exception e) {
            log.error("恢复额度异常，回滚事务，{}", e.getMessage());
        } finally {
            limitLock.unlock();
        }
        return true;
    }
}
