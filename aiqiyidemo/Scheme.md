 * String url="https://blog.csdn.net:443/sinat_31057219/article/details/78362326?time=22";
 
 * url =            protocol + authority(host + port) + path + query
 * 协议protocol=    https
 * 域名authority=   blog.csdn.net:443
 * 页面path=        /sinat_31057219/article/details/78362326
 * 参数query=      time=22
 * authority =      host + port
 * 主机host=        blog.csdn.net:
 * 端口port=        443



 * 客户端应用可以向操作系统注册一个 URL scheme，该 scheme 用于从浏览器或其他应用中启动本应用。
 * (1)服务器下发跳转路径，客户端根据服务器下发跳转路径跳转相应的页面
 * (2)H5页面点击锚点，根据锚点具体跳转路径APP端跳转具体的页面
 * (3)APP端收到服务器端下发的PUSH通知栏消息，根据消息的点击跳转路径跳转相关页面
 * (3)APP根据URL跳转到另外一个APP指定页面
 
 
 
     <activity
             android:name=".launchmode.AActivity">
 
             <!--要想在别的App上能成功调起App，必须添加intent过滤器-->
             <intent-filter>
                 <!--协议部分，随便设置
                     lh://com.liuhe.aiqiyidemo:8888/AActivity?time=10011002
                  -->
                 <data
                     android:host="${applicationId}"
                     android:path="/AActivity"
                     android:port="8888"
                     android:scheme="lh" />
 
                 <!--下面这几行也必须得设置-->
                 <category android:name="android.intent.category.DEFAULT" />
                 <action android:name="android.intent.action.VIEW" />
                 <category android:name="android.intent.category.BROWSABLE" />
             </intent-filter>
 
     </activity>


public abstract ResolveInfo resolveActivity(Intent intent, int flags)
参数：  
　　* intent 查寻条件，Activity所配置的action和category
　　* flags： 
            MATCH_DEFAULT_ONLY     ：Category必须带有CATEGORY_DEFAULT的Activity，才匹配
　　　　　　 GET_INTENT_FILTERS     ：匹配Intent条件即可
　　　　　　 GET_RESOLVED_FILTER    ：匹配Intent条件即可`
