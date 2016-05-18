朋友圈的实现：（主要控件）
0. recyclerview控件 compile 'com.android.support:recyclerview-v7:23.3.0' 

1. 下拉刷新 compile 'cn.bingoogolapple:bga-refreshlayout:1.1.4' 

2. 刷新状态 compile project(':multiStateView') 
   maven { url 'https://jitpack.io' } 

3. 数据绑定 DataBinding 
    dataBinding {
        enabled true
    }
    item_topic 布局生命 会自动生成Binding类 

4.GridView 
  ImagesAdapter compile(name: 'cyphotosview', ext: 'aar') 
  
5.单个头像加载 glide compile 'com.github.bumptech.glide:glide:3.7.0' 

6.标签 taggroup  compile 'me.gujun.android.taggroup:library:1.4@aar' 
