#include <jni.h>
#include <string>

//调用 Java 公有方法
extern "C"
JNIEXPORT void JNICALL
Java_cn_cfanr_jnisample_MethodJni_accessPublicMethod(JNIEnv *env, jobject jobj) {
    //1.获取对应 class 的实体类
    jclass jclazz = env->GetObjectClass(jobj);
    //2.获取方法的 id
    jmethodID mid = env->GetMethodID(jclazz, "setSex", "(Ljava/lang/String;)V");
    //3.字符数组转换为字符串
    char c[10] = "male";
    jstring jsex = env->NewStringUTF(c);
    //4.通过该 class 调用对应的 public 方法
    env->CallVoidMethod(jobj, mid, jsex);
}

//调用 Java 的静态方法
extern "C"
JNIEXPORT jint JNICALL
Java_cn_cfanr_jnisample_MethodJni_accessStaticMethod(JNIEnv *env, jobject jobj) {
    //1.获取对应 class 实体类
    jclass jclazz = env->GetObjectClass(jobj);
    //2.通过 class 类找到对应的方法 id
    jmethodID mid = env->GetStaticMethodID(jclazz, "getHeight", "()I");  //注意静态方法是调用GetStaticMethodID, 不是GetMethodID
    //3.通过 class 调用对应的静态方法
    return env->CallStaticIntMethod(jclazz, mid);
}

//调用 Java 的父类方法
extern "C"
JNIEXPORT jstring JNICALL
Java_cn_cfanr_jnisample_MethodJni_accessSuperMethod(JNIEnv *env, jobject jobj) {
    //1.通过反射获取 class 实体类
    jclass jclazz = env-> FindClass("cn/cfanr/jnisample/SuperJni");  //注意 FindClass 不要 L和;
    if(jclazz == NULL) {
        char c[10] = "error";
        return env->NewStringUTF(c);
    }
    //通过 class 找到对应的方法 id
    jmethodID mid = env->GetMethodID(jclazz, "hello", "(Ljava/lang/String;)Ljava/lang/String;");
    char ch[10] = "cfanr";
    jstring jstr = env->NewStringUTF(ch);
    return (jstring) env->CallNonvirtualObjectMethod(jobj, jclazz, mid, jstr);
}