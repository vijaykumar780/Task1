package com.Service3.vo;

import lombok.Data;

@Data
public class RequestVo {

    private String Name;
    private String Surname;

    @Override
    public String toString() {
        return "RequestVo{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                '}';
    }
}
