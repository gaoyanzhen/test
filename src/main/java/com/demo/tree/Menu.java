package com.demo.tree;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private String id;
    private String name;
    private String pid;
    private List<Menu> childMenu;
}
