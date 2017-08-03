#include <jni.h>
#include <string>

//访问某个属性，并在 JNI 层通过某个方法对其处理后返回
extern "C"
JNIEXPORT jint JNICALL
Java_cn_cfanr_jnisample_FieldJni_addNum(JNIEnv *env, jobject jobj) {
    //获取实例对应的 class
    jclass jclazz = env->GetObjectClass(jobj);
    //通过class获取相应的变量的 field id
    jfieldID fid = env->GetFieldID(jclazz, "num", "I");
    //通过 field id 获取对应的值
    jint num = env->GetIntField(jobj, fid);  //注意，不是用 jclazz, 使用 jobj

    num++;

    return num;
}

//
extern "C"
JNIEXPORT void JNICALL
Java_cn_cfanr_jnisample_FieldJni_accessStaticField(JNIEnv *env, jobject jobj) {
    jclass jclazz = env->GetObjectClass(jobj);

    jfieldID fid = env->GetStaticFieldID(jclazz, "name", "Ljava/lang/String;");  //注意是用GetStaticFieldID，不是GetFieldID

    jstring name = (jstring) env->GetStaticObjectField(jclazz, fid);

    const char* str = env->GetStringUTFChars(name, JNI_FALSE);

    /*
     * 不要用 == 比较字符串
     * name == (jstring) "cfanr"
     * 或用 = 直接赋值
     * name = (jstring) "navy"
     * 警告：warning: result of comparison against a string literal is unspecified (use strncmp instead) [-Wstring-compare]
     */

    char ch[30] = "hello, ";
    strcat(ch, str);
    jstring new_str = env->NewStringUTF(ch);
    // 将jstring类型的变量，设置到java
    env->SetStaticObjectField(jclazz, fid, new_str);
}


extern "C"
JNIEXPORT void JNICALL
Java_cn_cfanr_jnisample_FieldJni_accessPrivateField(JNIEnv *env, jobject jobj) {
    jclass clazz = env->GetObjectClass(jobj);

    jfieldID fid = env->GetFieldID(clazz, "age", "I");

    jint age = env->GetIntField(jobj, fid);

    if(age > 18) {
        age = 18;
    } else {
        age--;
    }
    env->SetIntField(jobj, fid, age);
}
