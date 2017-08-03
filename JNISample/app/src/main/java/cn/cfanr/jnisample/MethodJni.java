package cn.cfanr.jnisample;

/**
 * Created by Pipi on 2017/8/1.
 */

public class MethodJni extends SuperJni{
    /**
     * JNI 调用 Java 对象的 public 方法
     */
    private String sex = "female";
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex(){
        return sex;
    }
    public native void accessPublicMethod();

    /**
     * JNI 调用 Java 的静态方法
     */
    private static int height = 170;
    public static int getHeight() {
        return height;
    }
    public native int accessStaticMethod();

    /**
     * JNI 访问 Java 类的父类方法
     */
    public native String accessSuperMethod();
}
