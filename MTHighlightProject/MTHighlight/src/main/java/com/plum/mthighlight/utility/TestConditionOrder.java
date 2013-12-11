package com.plum.mthighlight.utility;

import com.plum.mthighlight.activity.FirstStageActivity;
import com.plum.mthighlight.activity.SecondStageActivity;
import com.plum.mthighlight.activity.ThirdStageActivity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by plum on 12/10/13.
 */
public class TestConditionOrder {


    public static List<List<Class<?>>> TESTCASE = new ArrayList<List<Class<?>>>();
    public static List<int[]> ORDER = new ArrayList<int[]>();
    static{
        TESTCASE.add(Arrays.<Class<?>>asList(FirstStageActivity.class, SecondStageActivity.class, ThirdStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(FirstStageActivity.class, ThirdStageActivity.class, SecondStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(SecondStageActivity.class, FirstStageActivity.class, ThirdStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(SecondStageActivity.class, ThirdStageActivity.class, FirstStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(ThirdStageActivity.class, FirstStageActivity.class, SecondStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(ThirdStageActivity.class, SecondStageActivity.class, FirstStageActivity.class));

        ORDER.add(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        ORDER.add(new int[]{0, 2, 1, 3, 4, 5, 6, 7, 8});
        ORDER.add(new int[]{0, 1, 2, 3, 4, 5, 6, 8, 7});
        ORDER.add(new int[]{0, 2, 1, 3, 5, 4, 6, 7, 8});
        ORDER.add(new int[]{0, 1, 2, 3, 5, 4, 6, 8, 7});
        ORDER.add(new int[]{0, 2, 1, 3, 5, 4, 6, 8, 7});

        ORDER.add(new int[]{7, 6, 8, 1, 0, 2, 4, 3, 5});
        ORDER.add(new int[]{7, 8, 6, 1, 0, 2, 4, 3, 5});
        ORDER.add(new int[]{7, 6, 8, 1, 0, 2, 4, 5, 3});
        ORDER.add(new int[]{7, 8, 6, 1, 2, 0, 4, 3, 5});
        ORDER.add(new int[]{7, 6, 8, 1, 2, 0, 4, 5, 3});
        ORDER.add(new int[]{7, 8, 6, 1, 2, 0, 4, 5, 3});

        ORDER.add(new int[]{5, 3, 4, 8, 6, 7, 2, 1, 0});
        ORDER.add(new int[]{5, 4, 3, 8, 6, 7, 2, 1, 0});
        ORDER.add(new int[]{5, 3, 4, 8, 6, 7, 2, 0, 1});
        ORDER.add(new int[]{5, 4, 3, 8, 7, 6, 2, 0, 1});
        ORDER.add(new int[]{5, 3, 4, 8, 7, 6, 2, 0, 1});
        ORDER.add(new int[]{5, 4, 3, 8, 7, 6, 2, 1, 0});


    }





}
