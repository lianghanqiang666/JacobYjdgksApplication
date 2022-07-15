package com.lhq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 问题类
 *
 * @author lianghanqiang
 * @CLass Problem
 * @create 2022/7/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    public  Problem(String title,String a,String b, String c, String d, String answer){
        this.title = title;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }
    /**
     * 标题
     */
    private String title;
    /**
     * A选项
     */
    private String a;
    /**
     * B选项
     */
    private String b;
    /**
     * C选项
     */
    private  String c;
    /**
     * D选项
     */
    private String d;
    /**
     * 答案
     */
    private String answer;
    /**
     * 我的答案
     */
    private String myAnswer;
}
