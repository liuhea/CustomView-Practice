## JNI 开发流程

1. 现在java代码里面声明一个native的方法
	 	
	 	public native String helloFromC();
	 	
2. 在工程目录下创建一个文件夹 名称jni
3. 编写c代码，按照规范编写

		jstring Java_com_itheima_hellojni_MainActivity_helloFromC(JNIEnv* env,jobject obj){
			char* str = "hello from c !!";
			//在jni规范里面 在jni环境的结构体里面
			//jstring     (*NewStringUTF)(JNIEnv*, const char*);
			//转化成了java的字符串
			return (*(*env)).NewStringUTF(env,str);
		}
		Java_包名_类型_方法名（jvm虚拟机的指针，调用者对象
		
4. ndk-build.cmd指令编译c代码
5. 配置Android.mk文件

		#指定编译的文件夹  指定当前文件目录
		LOCAL_PATH := $(call my-dir)
		#编译器会定义很多的临时变量，中间变量，最好清空所有的中间变量。
		include $(CLEAR_VARS)
		#编译出来模块的名称
		LOCAL_MODULE    := hello
		# 编译的源代码的名称
		LOCAL_SRC_FILES := hello.c
		
		#编译一个动态库，静态库
		#静态库 文件名.a   包含所有的函数并且函数运行的依赖，体积大，包含所有的API
		#动态库 文件名.so  包含函数，不包含函数运行的依赖，体积小，运行的时候，去操作系统寻找需要的API
		include $(BUILD_SHARED_LIBRARY)
6. 生成一个.so的动态链接库
7. 在java代码里面，把动态链接库加载到jvm虚拟机
    lib库名称.so
		
		static{
			System.loadLibrary("库文件名称不带前缀，后缀名");
		}
		
8. 像调用一般java代码一样调用native的方法

## JNI常见问题

