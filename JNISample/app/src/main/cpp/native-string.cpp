#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_cn_cfanr_jnisample_StringJni_handlerString(JNIEnv *env, jobject jobj, jstring str_) {
    //utf-8 字符
    const char *str = env->GetStringUTFChars(str_, JNI_FALSE);
    //unicode 字符
    const jchar *str1 = env->GetStringChars(str_, 0);  //注意这里是 jchar，不是 char

    jsize jlen = env->GetStringUTFLength(str_);
    jsize jlen1 = env->GetStringLength(str_);

    char ch[20] = "你好哇，";
    strcat(ch, str);

    //让 JVM 释放 String 的对象空间
    env->ReleaseStringUTFChars(str_, str);
    env->ReleaseStringChars(str_, str1);

    return env->NewStringUTF(ch);
}

char* jstringToChar(JNIEnv* env, jstring jstr) {
    char* rtn = NULL;
    //获取 String 的 class
    jclass jcls = env->FindClass("java/lang/String");
    //定义 String 的编码格式
    jstring strcode = env->NewStringUTF("utf-8");
    //获取  String 的 getBytes 方法的 id
    jmethodID mid = env->GetMethodID(jcls, "getBytes", "(Ljava/lang/String;)[B");
    //调用 getBytes 方法，返回一个 jbyteArray
    jbyteArray byteArr = (jbyteArray) env->CallObjectMethod(jstr, mid, strcode);

    jsize arrLen = env->GetArrayLength(byteArr);
    //数组指向一个 byte 指针
    jbyte* pbyte = env->GetByteArrayElements(byteArr, 0);
    if(arrLen > 0) {
        //申请数组长度+1个内存空间
        rtn = (char*) malloc(arrLen + 1);
        //从源 pbyte 所指的内存地址的起始位置开始拷贝 arrLen 个字节到目标 rtn 所指的内存地址的起始位置中
        memcpy(rtn, pbyte, arrLen);
        rtn[arrLen] = 0;
    }
    //释放内存
    env->ReleaseByteArrayElements(byteArr, pbyte, 0);
    return rtn;
}

//字符串转成字节数组
extern "C"
JNIEXPORT jbyteArray JNICALL
Java_cn_cfanr_jnisample_StringJni_handlerStrToByte(JNIEnv *env, jobject jobj, jstring str_) {
//    const char *str = env->GetStringUTFChars(str_, 0);
    char * str = NULL;
    str =jstringToChar(env, str_);

    jbyteArray RtnArr = NULL;  //下面一系列操作把chr转成jbyteArray 返回出去
    RtnArr = env->NewByteArray(strlen(str));

    env->SetByteArrayRegion(RtnArr, 0, strlen(str), (jbyte*)str );


    env->ReleaseStringUTFChars(str_, str);

    return RtnArr;
}



