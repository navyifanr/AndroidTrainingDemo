package cn.cfanr.jnisample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import cn.cfanr.jnisample.model.Person;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        /**
         * JNI 访问 Java 的变量
         */
        Log.e(TAG, "-----------JNI 访问 Java 的变量----------");
        FieldJni fieldJni = new FieldJni();
        Log.e(TAG, "调用前：num = " + fieldJni.num);
        Log.e(TAG, "调用后：" + fieldJni.addNum());

        Log.e(TAG, "调用前：name = " + fieldJni.name);
        fieldJni.accessStaticField();
        Log.e(TAG, "调用后：" + fieldJni.name);

        Log.e(TAG, "调用前：age = " + fieldJni.getAge());
        fieldJni.accessPrivateField();
        Log.e(TAG, "调用后：age = " + fieldJni.getAge());

        /**
         * JNI 访问 Java 的方法
         */
        Log.e(TAG, "-----------JNI 调用 Java 的方法----------");
        MethodJni methodJni = new MethodJni();
        Log.e(TAG, "调用前：getSex() = " + methodJni.getSex());
        methodJni.accessPublicMethod();
        Log.e(TAG, "调用后：getSex() = " + methodJni.getSex());

        Log.e(TAG, "调用静态方法：getHeight() = " + methodJni.accessStaticMethod());

        Log.e(TAG, "调用父类方法：hello(name) = " + methodJni.accessSuperMethod());

        /**
         * 传递参数给 JNI 函数
         */
        Log.e(TAG, "-----------传递参数给 JNI 函数----------");
        ParamsJni paramsJni = new ParamsJni();

        Log.e(TAG, "intMethod: " + paramsJni.intMethod(4));
        Log.e(TAG, "stringMethod: " + paramsJni.stringMethod("cfanr"));
        Log.e(TAG, "intArrayMethod: " + paramsJni.intArrayMethod(new int[]{4, 9, 10, 16}) + "");
        Log.e(TAG, "objectMethod: " + paramsJni.objectMethod(new Person()).toString() + "");

        ArrayList<Person> personList = new ArrayList<>();
        Person person;
        for (int i = 0; i < 3; i++) {
            person = new Person();
            person.setName("cfanr");
            person.setAge(10 + i);
            personList.add(person);
        }
        Log.e(TAG, "调用前：java list = " + personList.toString());
        Log.e(TAG, "调用后：jni list  = " + paramsJni.personArrayListMethod(personList).toString());

        /**
         * JNI 函数字符串处理
         */
        Log.e(TAG, "-----------JNI 函数字符串处理----------");
        StringJni stringJni = new StringJni();
        Log.e(TAG, "handlerString: " + stringJni.handlerString("cfanr"));
        byte[] bytes = stringJni.handlerStrToByte("navyifanr");
        for (byte b : bytes) {
            Log.e(TAG, "handlerStrToByte: " + (char) b);
        }

        /**
         * 动态注册 JNI
         */
        Log.e(TAG, "-----------动态注册 JNI----------");
        String hello = new DynamicRegisterJni().getStringFromCpp();
        Log.e(TAG, hello);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


}
