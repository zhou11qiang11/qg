package com.qg.utils;

public enum TaskType {
    //当前枚举类的全部实例
    ADD_TEMPSTOCK("11"),UPDATE_TEMPSTOCK("12"),DEL_TEMPSTOCK("13"),ADD_ORDER("21"),UPDATE_ORDER("22"),DEL_ORDER("23"),ADD_TRADE("31"),UPDATE_STOCK("41");

    private String value;//枚举类型的属性不要有setter和getter

    private TaskType(String value){
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public static TaskType get(String type){
        switch(type){
            case "11": return ADD_TEMPSTOCK;
            case "12": return UPDATE_TEMPSTOCK;
            case "13": return DEL_TEMPSTOCK;
            case "21": return ADD_ORDER;
            case "22": return UPDATE_ORDER;
            case "23": return DEL_ORDER;
            case "31": return ADD_TRADE;
            case "41": return UPDATE_STOCK;
        }
        return null;
    }
}
