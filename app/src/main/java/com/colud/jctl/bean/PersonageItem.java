package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/3/23.
 */

public class PersonageItem implements Serializable {
    private static final long serialVersionUID = -6471356317189785103L;

    public PersonageItem(String head, String content) {
        this.head = head;
        this.content = content;
    }

    private String head;
    private String content;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
