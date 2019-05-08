
# layout文件夹分模块布局的问题毛病以及解决方法

rec文件夹layout文件夹(之后改为layouts了，复数)中是有文件夹的，但是Android Studio（之后简称AS）没有显示出来

## 方法一、本地打开

![1](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/1.png)

1. 右击layouts文件夹，点击show in Exploer快速打开所在本地位置

![2](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/2.jpg)

2.layouts中对应每个功能模块布局文件的位置

![3](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/3.jpg)

3.编辑或添加对应的布局文件
> 可以选择如图中直接拉入AS进行代码修改，也可用另外的编辑器修改保存

![4](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/4.jpg)

<hr>

## 方法二、将配置layout的代码段注释掉，重新Sync

1. 注释res/build.gradle中如图所示的代码段

![5](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/5.jpg)

2. 重新Sync

![5](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/6.jpg)

3. layouts文件夹的内容便显示出来了。
**不过在修改完之后，编译/调试/运行之前，需要在将之前代码段的注释取消掉。**

![5](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/7.jpg)
=======
# layout文件夹分模块布局的问题毛病以及解决方法

rec文件夹layout文件夹(之后改为layouts了，复数)中是有文件夹的，但是Android Studio（之后简称AS）没有显示出来

## 方法一、本地打开

![1](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/1.png)

1. 右击layouts文件夹，点击show in Exploer快速打开所在本地位置

![2](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/2.jpg)

2.layouts中对应每个功能模块布局文件的位置

![3](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/3.jpg)

3.编辑或添加对应的布局文件
> 可以选择如图中直接拉入AS进行代码修改，也可用另外的编辑器修改保存

![4](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/4.jpg)

<hr>

## 方法二、将配置layout的代码段注释掉，重新Sync

1. 注释res/build.gradle中如图所示的代码段

![5](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/5.jpg)

2. 重新Sync

![5](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/6.jpg)

3. layouts文件夹的内容便显示出来了。
**不过在修改完之后，编译/调试/运行之前，需要在将之前代码段的注释取消掉。**

![5](https://github.com/notedeveloper/PersonalNoteApp/raw/master/Helpguide/Android_layout/7.jpg)
