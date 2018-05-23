package com.test.UtisTest;

import com.mcloud.util.common.BloomFilterUtils;
import org.junit.Test;

import java.util.*;

/**
 * @Author: vellerzheng
 * @Description:
 * @Date:Created in 15:51 2018/5/23
 * @Modify By:
 */
public class BloomFilterTest {

    private final int insertions = 1000000;
    Set<String> sets = new HashSet<>(insertions);
    List<String> lists = new ArrayList<>(insertions);

    @Test
   public void bloomFilterTest(){
        for(int i =0; i< insertions; i++){
            String uuid = UUID.randomUUID().toString();
            sets.add(uuid);
            lists.add(uuid);
        }
        BloomFilterUtils.create(lists);

        int wrong = 0;  //bloomfilter 错误判断次数
        int right = 0; //bloomfilter 正确判断次数

        for(int i = 0; i< 10000; i++){
            String test = i%100 == 0 ? lists.get(i/100) : UUID.randomUUID().toString();
            if(BloomFilterUtils.contains(test)){
                if(sets.contains(test)){
                    right ++;
                }else {
                    wrong ++;
                }
            }
        }

        System.out.println("-------right ---10000--: " + right);
        System.out.println("*******wrong ---10000--: " + wrong);
    }


}
