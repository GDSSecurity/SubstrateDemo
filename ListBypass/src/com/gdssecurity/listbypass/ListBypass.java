package com.gdssecurity.listbypass;

import java.lang.reflect.Method;
import android.util.Log;
import com.saurik.substrate.*;


public class ListBypass {
	private static String TAG = "ListBypasss";
	private static String className = "com.historypeats.listlock.classes.Authenticate";
	
	 public static void initialize() {
		  Log.i(TAG, "Substrate Initialized.");
          MS.hookClassLoad(className, new MS.ClassLoadHook() {
        	  @SuppressWarnings({ "unchecked", "rawtypes" })
              public void classLoaded(Class<?> _class) {
              	Method method;
              	final String methodName = "validatePassword";
              	Log.i(TAG, "Class Loaded.");
                  try{
                  	method = _class.getMethod(methodName, String.class, String.class); // public boolean validatePassword(String password, String storedPassword)
                  }catch(NoSuchMethodException e){ 
                  	method = null;
                  	Log.i(TAG, "No such method: " + methodName);
                  }
                  
                  	
              	if (method != null) {
              		Log.i(TAG, "Method Hooked.");
              		MS.hookMethod(_class, method, new MS.MethodAlteration<Object, Boolean>() {
                		public Boolean invoked(Object _class, Object... args) throws Throwable
                		{
                			//Do Something
                			Boolean originalRetValue = invoke(_class, args);
                			Boolean newRetValue = true;
                			Log.i(TAG, "Original Return Value: " + originalRetValue);
                			Log.i(TAG, "New Return Value: " + newRetValue);
                			return newRetValue;
                		}
					});// End MS.hookMethod
              	}
              }
          }); // End MS.hookClassLoad
	   }
}
