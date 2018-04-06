#include<stdio.h>
#include <jni.h>
#include <malloc.h>
#include "com_liuhe_jnidemo_DataProvider.h"

JNIEXPORT jint JNICALL Java_com_liuhe_jnidemo_DataProvider_login(JNIEnv * env, jobject obj, jint name, jint password){
    if (name==123 && password==123) {
        return 1;
    } else {
        return -1;
    }

}