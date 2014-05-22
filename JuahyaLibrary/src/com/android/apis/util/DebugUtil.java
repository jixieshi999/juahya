package com.android.apis.util;

import java.util.Map;

import org.json.JSONObject;
import org.json.JSONStringer;

import android.util.Log;
import android.widget.EditText;

/**
 * <p>for debug test
 * <p>dLog(*)受debug变量控制
 * <p>log(*)不受debug变量控制
 * 便于移植
 * */
public class DebugUtil {
    
    static String TAG = "DebugTools";

    public static  boolean DEBUG = true; 
    public static void dLog(String tag,String str){
        if(!DEBUG){
            return;
        }

        Log.v(tag, str);
        
    }
    public static void log(String tag,String str){
        
        Log.v(tag, str);
        
    }
    public static void dLog(String str){
        if(str.equals("箱")){
            dLog("error ");
        }
        dLog(TAG, str);
    }
    public static void dLog(Object o){
        dLog(TAG, o.toString());
    }
    public static void dLog(Object[] os){
    	if(!DEBUG){
    		return;
    	}
        for(Object o:os){
            dLog(TAG,o==null?"null":o.toString());
        }
    }
    public static void dLog(String tag,Object... os){
    	if(!DEBUG){
    		return;
    	}
        for(Object o:os){
            dLog(tag,o==null?"null":o.toString());
        }
    }
    public static void dLog(String tag,String msg,Object... os){
    	if(!DEBUG){
    		return;
    	}
    	
        for(Object o:os){
        	msg+=","+(o==null?"null":o.toString());
        }
        dLog(tag,msg);
    }
    public static void dLog(Throwable t){
        if(!DEBUG){
            return;
        }
        
        Log.e(TAG, "",t);
        
    }
    public static void log(String str){
        log(TAG, str);
    }
    
    /**
     * init user for debug 
     * */
    public static void initUserForTest(EditText user,EditText pass){
        if(!DEBUG){
            return;
        }
        if(user!=null&&pass!=null){
//            pass.setText(AppSetting.PASSWORD);
//            user.setText(AppSetting.USER);
//            dLog("p : "+AppSetting.PASSWORD+" u: "+AppSetting.USER);
        }
        
    }
    public static void dLog(String tag,String str,Exception e){
        if(!DEBUG){
            return;
        }
        
        Log.e(tag, str,e);
        
    }
    public static void eLog(String tag,String str,Exception e){
        if(!DEBUG){
            return;
        }
        
        Log.e(tag, str,e);
        
    }
    public static void eLog(String tag,String str){
        if(!DEBUG){
            return;
        }
        Log.e(tag, str);
        
    }
    
    
    /**
     * easy to modify control
     * */
    public static void log(String tag,String str,Exception e){
        
        Log.e(tag, str,e);
        
    }
    
    public static void dLog(String tag,Exception e){
        dLog(tag, "",e);
    }
    public static void dLog(Exception e){
        dLog(TAG, "",e);
    }
    public static void dLogCursor(Exception e){
        dLog("sfa_cursor", "cursor exception ",e);
    }
    
    public static void log( Exception e){
        log(TAG, "",e);
    }
    public static void log(String tag,Exception e){
        log(tag, "",e);
    }
    private static final String OUT_PATTERN = "%s.%s()  Line:%d  (%s)"; 
    private static final String OUT_PATTERN2 = "%s()  Line:%d  (%s)"; 
    
    public static void trace() {  
        if (DEBUG) {  
            StackTraceElement traceElement = Thread.currentThread()  
                    .getStackTrace()[3];//从堆栈信息中获取当前被调用的方法信息  
            String logText = String.format(OUT_PATTERN,  
                    traceElement.getClassName(), traceElement.getMethodName(),  
                    traceElement.getLineNumber(), traceElement.getFileName());  
            Log.d(TAG, logText);//打印Log  
        }  
    }
    public static void trace(String tag) {  
        if (DEBUG) {  
            StackTraceElement traceElement = Thread.currentThread()  
                    .getStackTrace()[3];//从堆栈信息中获取当前被调用的方法信息  
            String logText = String.format(OUT_PATTERN2,  
                    traceElement.getMethodName(),  
                    traceElement.getLineNumber(), traceElement.getFileName());  
            Log.d(tag, logText);//打印Log  
        }  
    }  
}
