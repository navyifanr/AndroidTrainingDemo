package cn.cfanr.jnisample;

import java.util.ArrayList;

import cn.cfanr.jnisample.model.Person;

/**
 * Created by Pipi on 2017/8/2.
 */

public class ParamsJni {

    public native int intMethod(int n);

    public native String stringMethod(String name);

    public native int intArrayMethod(int[] arr);

    //传递复杂对象person，再jni函数中新构造一个person传回java层输出
    public native Person objectMethod(Person person);

    public native ArrayList<Person> personArrayListMethod(ArrayList<Person> persons);


}
