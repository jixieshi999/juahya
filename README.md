juahya
======

Juahya is android real time xml layout inflater<br />
Juahya 是一个android的实时解析xml转化为view的解析器<br />

version 1.1 2015-05-26
###     
    support android view
         CheckBox －－》IFCheckBoxinflater
         EditText －－》IFEditTextinflater
         ImageView－－》IFImageViewinflater
         TextView－－》IFTextViewinflater
    support android layout   
         LinearLayout－－》IFLinearLayout
         RelativeLayout－－》IFRelativeLayout
         ScrollView－－》IFScrollViewLayout


## Demo
<p align="center" >
<img src="http://jixieshi999.github.io/ilife/images/juahyademo.gif" width="180" height="320"/>
</p>

what can juahya do ?
======
you can use juahya to parse  your custom layout xml stream ,where its come from internet,sdcard,sqlite ...<br />
你可以使用juahya来解析出view来，数据类型可以是string，stream，数据来源可以是web,sqlite,sdcard ...<br />
###
    how to build your custom view inflater
        1.add IFXXX on com.xml.inflate.inflater
            xxx is android view
        2.config you IFxxx to com.xml.inflate.factory.IFLalterSImpleLinearLayoutFactory
        3.change your xml layout you can  see some of the things juahya used in his magic tricks ,

###
    how to build your custom juahyaview inflater
        1.add IFJxxx on com.xml.inflate.inflater.juahya
            IFJxxx is extends IFXXX and add juahya ATTRIBUTE
        2.config you IFxxx to com.xml.inflate.factory.IFLalterSImpleLinearLayoutFactory
        3.change your xml layout you can  see some of the things juahya used in his magic tricks
        
##Usage

```
		IFlateServicePoxy service = new IFlateServicePoxy();
		service.setIJuahyaLayoutInflateListener(DemoListActivity.this);
		String result="<LinearLayout android:orientation=\"vertical\" "
					+" android:layout_width=\"fill_parent\" android:layout_height=\"fill_parent\" "
					+"</LinearLayout>";
		View view= service.inflate(result, DemoListActivity.this);
		setContentView(view);
```		
eg file [test.xml](jixieshi999.github.io/ilife/juahya/test.xml)
<br>http://jixieshi999.github.io/ilife/juahya/test.xml
###
    <TextView  android:layout_weight="1"
            android:layout_width="wrap_content" android:background="#e2e2c2"
            android:layout_height="fill_parent" android:gravity="center" 
            android:text="会员注册" />
    <com.juahya.guis.JTextView  android:layout_width="200" android:gravity="center" android:layout_gravity="center"
        android:layout_height="wrap_content" android:background="#e2eff2"
        juahya:attrKey="action_save_upload"  android:text="保存"
         android:paddingTop="10" android:paddingBottom="10" 
        juahya:attrDescription="12331" 
        android:id="12331" 
        />
    ...
    <ImageView 
        android:layout_width="wrap_content"  android:layout_gravity="center"
        android:layout_height="wrap_content"  
        android:background="@url/http://tupian.hbrc.com/joke/UpFilesnew/2012/6/23/201262303147971.jpg"
        />
###
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="fill_parent" android:layout_height="fill_parent" android:orientation="vertical" >
        <!-- ......layout...... -->
        <TextView   android:layout_width="wrap_content"  android:layout_height="wrap_content"  
            android:text="输入用户名" />
    </LinearLayout>
Acknowledgements
======

[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader) for the img lazy loader<br />


======
 

