package com.demo.algorithm.recursion;

import com.demo.bean.PlanItem;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 打包代扣
 *
 * @author gaoyanzhen
 * @since 2021-11-18
 */
public class PackDeduct {
    private static BigDecimal balance = new BigDecimal("1");

    public static void main(String[] args) {
        String[] amountArr = {"100", "100"};
//        String[] amountArr = {"100" };
        List<PlanItem> list = genList(amountArr);

        PackDeduct packDeduct = new PackDeduct();
        list.sort(Comparator.comparing(PlanItem::getPrice).reversed());
        System.out.println("代扣计划列表：");
        printList(list);
        packDeduct.packDeduct(list, list);
    }

    public void packDeduct(List<PlanItem> list, List<PlanItem> allList) {
        BigDecimal sum = list.stream().map(PlanItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        int packSize = list.size();
        boolean success = deduct(sum);
        // 单笔数据，扣完结束
        if (packSize == 1 && allList.size() == 1) {
            System.out.println("执行单笔扣款");
            this.processResult(success, packSize);
            if (success) {
                System.out.println("单笔扣款success，扣款金额：" + sum);
            } else {
                System.out.println("单笔扣款失败，扣款金额：" + sum);
            }
            // 递归到单笔
        } else if (packSize == 1) {
            this.processResult(success, packSize);
            if (success) {
                System.out.println("打包扣款success（单笔），包大小：" + packSize + "，包总额：" + sum);
                // 拆掉扣款成功的
                List<PlanItem> subList = allList.stream().filter(p -> !list.contains(p)).collect(Collectors.toList());
                // 递归
                packDeduct(subList, subList);
            } else {
                //递归结束
                System.out.println("打包单笔扣款失败（单笔），包大小：" + packSize + "，包总额：" + sum
                        + "，剩下" + (allList.size() - packSize) + "全部执行单笔扣款");
                List<PlanItem> subList = allList.stream().filter(p -> !list.contains(p)).collect(Collectors.toList());
                System.out.println("剩余全部业务：");
                printList(subList);
                subList.stream().forEach(planItem -> {
                    packDeduct(Lists.newArrayList(planItem), Lists.newArrayList(planItem));
                });
            }
        } else {
            if (success) {
                System.out.println("打包扣款success，包大小：" + packSize + "，包总额：" + sum);
                this.processResult(success, packSize);
                if (packSize != allList.size()) {
                    // 拆掉扣款成功的
                    List<PlanItem> subList = allList.stream().filter(p -> !list.contains(p)).collect(Collectors.toList());
                    // 递归
                    packDeduct(subList, subList);
                }
            } else {
                System.out.println("打包扣款失败，包大小：" + packSize + "，包总额：" + sum);
                // 每次拆一笔，拆掉金额最大的
                List<PlanItem> subList = list.subList(1, packSize);
                // 递归
                packDeduct(subList, allList);
            }
        }
    }

    public boolean deduct(BigDecimal amount) {
        synchronized (balance) {
            if (balance.compareTo(amount) >= 0) {
                balance = balance.subtract(amount);
                return true;
            } else {
                return false;
            }
        }
    }

    private void processResult(boolean success, int packSize) {
        if (success) {
            System.out.println("处理还款结果成功，" + packSize + " 笔数据状态");
        } else {
            System.out.println("处理还款结果失败，" + packSize + " 笔数据状态");
        }
    }

    private static void printList(List<PlanItem> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("序号：" + (i + 1) + "," + list.get(i));
        }
    }

    private static List<PlanItem> genList(String[] amountArr) {
        List<PlanItem> list = new ArrayList<>(amountArr.length);
        for (int i = 0; i < amountArr.length; i++) {
            PlanItem planItem = new PlanItem(i, "1", new BigDecimal(amountArr[i]));
            list.add(planItem);
        }
        return list;
    }
}
