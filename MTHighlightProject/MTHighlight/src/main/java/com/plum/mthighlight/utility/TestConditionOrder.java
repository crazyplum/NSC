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
    static{
        TESTCASE.add(Arrays.<Class<?>>asList(FirstStageActivity.class, SecondStageActivity.class, ThirdStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(FirstStageActivity.class, ThirdStageActivity.class, SecondStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(SecondStageActivity.class, FirstStageActivity.class, ThirdStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(SecondStageActivity.class, ThirdStageActivity.class, FirstStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(ThirdStageActivity.class, FirstStageActivity.class, SecondStageActivity.class));
        TESTCASE.add(Arrays.<Class<?>>asList(ThirdStageActivity.class, SecondStageActivity.class, FirstStageActivity.class));
    }




}
