package com.example.gfgbasics.advanceview.diprecycleview.nested.demo;

import java.util.List;

public class DemoParent {

    private String header;
    private List<DemoChild> list;

    public DemoParent(String header, List<DemoChild> list) {
        this.header = header;
        this.list = list;
    }

    public String getHeader() {
        return header;
    }

    public List<DemoChild> getList() {
        return list;
    }
}
