package com.mcloud.util.common;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: vellerzheng
 * @Description: 布隆过滤器
 * @Date:Created in 15:31 2018/5/23
 * @Modify By:
 */
public class BloomFilterUtils {

    private List<String> list =new ArrayList<>();
    private static final int insertions = 1024*1024*32; // 初始化插入值
    // 制定fpp(false positive probability)
    private static final double fpp = 0.0000001d;

    private static BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),insertions,fpp);

    public static void create(List<String> lists){
            for(String str : lists){
                bf.put(str);
            }
    }

    public static void create(String str){
        if(str != null)
            bf.put(str);
    }

    public static  boolean contains(String str){
        if(str == null){
            return  false;
        }

        return bf.mightContain(str);
    }

}
