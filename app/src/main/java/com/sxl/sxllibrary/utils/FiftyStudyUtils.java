package com.sxl.sxllibrary.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName Pkusky
 * @ClassDescribe
 * @Author
 * @Date 2017/12/28 15:21
 * @Copyright 未名天
 * 五十音图学习工具类
 */

public class FiftyStudyUtils {
    //ist: 要拆分的列表
//pageSize: 每个子列表条数
    public static List<List<List<String>>>  splitdataS(List list, int pageSize) {
        int total = list.size();
        //总页数
        int pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        List<List<List<String>>> result = new ArrayList<>();
        for(int i = 0; i < pageCount; i++) {
            int start = i * pageSize;
            //最后一条可能超出总数
            int end = start + pageSize > total ? total : start + pageSize;
            List subList = list.subList(start, end);
//            result[i] = subList;
            result.add(subList);
        }
        return result;
    }
}
