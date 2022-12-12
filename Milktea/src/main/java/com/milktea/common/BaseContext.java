package com.milktea.common;

public class BaseContext {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Integer id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Integer getCurrentId(){
        return threadLocal.get();
    }
}
