package com.Service1.vo;

import lombok.Data;

@Data
public class RequestConcat {

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