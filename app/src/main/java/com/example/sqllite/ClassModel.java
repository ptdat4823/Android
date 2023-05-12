package com.example.sqllite;

import java.io.Serializable;

public class ClassModel implements Serializable {
    private String id;
    private String name;
    private int stt;
    public ClassModel(String id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}
