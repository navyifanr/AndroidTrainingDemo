package cn.cfanr.jnisample;

/**
 * Created by Pipi on 2017/8/2.
 */

public class StringJni {

    public native String handlerString(String str);

    public native byte[] handlerStrToByte(String str);

}
