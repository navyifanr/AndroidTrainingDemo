package cn.cfanr.jnisample;

/**
 * Created by Pipi on 2017/8/1.
 */

public class FieldJni {
    /**
     * 访问某个变量，并通过某个方法对其处理后返回
     */
    public int num = 10;
    public native int addNum();

    /**
     * 访问一个 static 变量，并对其修改
     */
    public static String name = "cfanr";
    public native void accessStaticField();

    /**
     * 访问一个 private 变量，并对其修改
     */
    private int age = 21;
    public native void accessPrivateField();
    public int getAge() {
        return age;
    }

}