package com.example.alltyperecycleview.nested.demo;

import java.util.List;

public class MovieGroup {
    private String groupName;
    private List<Movie> list;

    public MovieGroup(String groupName, List<Movie> list) {
        this.groupName = groupName;
        this.list = list;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Movie> getList() {
        return list;
    }
}
