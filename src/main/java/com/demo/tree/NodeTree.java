package com.demo.tree;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-07-01
 */
public class NodeTree {
    /**
     * 递归获取某个父节点下面的所有子节点
     *
     * @param childMenu 要返回的结果
     * @param menuList  数据库查询出来的所有机构集合
     * @param pid       父id
     *                  注:本身的机构节点不会添加进去
     */
    public static void getChildNode(List<Menu> childMenu, List<Menu> menuList, String pid) {
        menuList.stream()
                //过滤出父id等于参数的id
                .filter(menu -> StringUtils.isNotBlank(menu.getPid()) && menu.getPid().equals(pid))
                .forEach(menu -> {
                    //递归遍历下一级
                    getChildNode(childMenu, menuList, menu.getId());
                    //添加
                    childMenu.add(menu);
                });
    }

    /**
     * 递归获取某个父节点下面的所有子节点
     *
     * @param childMenu 要返回的结果
     * @param menuList  数据库查询出来的所有机构集合
     * @param pids      父id
     *                  注:本身的机构节点不会添加进去
     */
    public static void getChildNode2(List<Menu> childMenu, List<Menu> menuList, List<String> pids) {
        menuList.stream()
                //过滤出父id等于参数的id
                .filter(menu -> StringUtils.isNotBlank(menu.getPid()) && pids.contains(menu.getPid()))
                .forEach(menu -> {
                    //递归遍历下一级
                    getChildNode2(childMenu, menuList, Lists.newArrayList(menu.getId()));
                    //添加
                    childMenu.add(menu);
                });
    }

    private static List<Menu> streamToTree(Menu tree, List<Menu> list) {
        List<Menu> treeList = list.stream()
                // 过滤父节点
                .filter(parent -> parent.getPid().equals(tree.getId()))
                // 把父节点children递归赋值成为子节点
                .map(child -> {
                    child.setChildMenu(streamToTree(child, list));
                    return child;
                }).collect(Collectors.toList());
        return treeList;
    }


    /**
     * list 转 tree
     *
     * @param list
     * @return
     */
    public List<Menu> listToTree2(List<Menu> list) {
        //最终树
        List<Menu> treeList = new ArrayList<>();
        //筛选根节点
        List<Menu> rootList = list.stream().filter(vo -> "0".equals(vo.getPid()))
                .sorted(Comparator.comparing(Menu::getId))
                .collect(Collectors.toList());
        //寻找子节点
        rootList.forEach(tree -> treeList.add(findChildren(tree, list)));
        return treeList;
    }

    /**
     * 寻找子节点
     *
     * @param tree
     * @param list
     * @return
     */
    private Menu findChildren(Menu tree, List<Menu> list) {

        List<Menu> temList = list.stream().filter(node -> tree.getPid().equals(node.getPid())).collect(Collectors.toList());
        if (temList != null) {
            temList.forEach(node -> {
                if (tree.getChildMenu() == null) {
                    tree.setChildMenu(new ArrayList<Menu>());
                }
                tree.getChildMenu().add(findChildren(node, list));
            });
        }
        return tree;
    }

    public static void main(String args[]) {
        List<Menu> menuList = new ArrayList<Menu>();
        Menu mu = new Menu();
        mu.setId("1");
        mu.setName("阿里巴巴");
        mu.setPid("0");

        Menu mu1 = new Menu();
        mu1.setId("2");
        mu1.setName("开发部");
        mu1.setPid("1");

        Menu mu2 = new Menu();
        mu2.setId("3");
        mu2.setName("业务部");
        mu2.setPid("1");

        Menu mu3 = new Menu();
        mu3.setId("4");
        mu3.setName("销售部");
        mu3.setPid("1");

        Menu mu4 = new Menu();
        mu4.setId("5");
        mu4.setName("业务一部");
        mu4.setPid("3");

        Menu mu5 = new Menu();
        mu5.setId("6");
        mu5.setName("业务二部");
        mu5.setPid("3");

        Menu mu6 = new Menu();
        mu6.setId("7");
        mu6.setName("业1-1");
        mu6.setPid("5");

        Menu mu7 = new Menu();
        mu7.setId("8");
        mu7.setName("业1-2");
        mu7.setPid("5");

        Menu mu8 = new Menu();
        mu8.setId("9");
        mu8.setName("业1-1-1");
        mu8.setPid("7");

        Menu mu9 = new Menu();
        mu9.setId("9");
        mu9.setName("开-1");
        mu9.setPid("2");

        Menu mu10 = new Menu();
        mu10.setId("10");
        mu10.setName("开-1");
        mu10.setPid("2");

        Menu mu11 = new Menu();
        mu11.setId("11");
        mu11.setName("开-1-1");
        mu11.setPid("9");

        menuList.add(mu);
        menuList.add(mu1);
        menuList.add(mu2);
        menuList.add(mu3);
        menuList.add(mu4);
        menuList.add(mu5);
        menuList.add(mu6);
        menuList.add(mu7);
        menuList.add(mu8);
        menuList.add(mu9);
        menuList.add(mu10);
        menuList.add(mu11);

        List<Menu> menus2 = new ArrayList<>();
        getChildNode2(menus2, menuList, Lists.newArrayList("3", "2"));
        menus2.stream()
                .map(menu -> menu.getId() + "   " + menu.getName())
                .forEach(System.out::println);

        System.out.println();

        List<Menu> menus = new ArrayList<>();
        getChildNode(menus, menuList, "2");
        menus.stream()
                .map(menu -> menu.getId() + "   " + menu.getName())
                .forEach(System.out::println);

//        menus = streamToTree( mu2, menuList);

    }
}
