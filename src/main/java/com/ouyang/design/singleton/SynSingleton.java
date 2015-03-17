package com.ouyang.design.singleton;

/**
 * 加锁单例模式
 * 
 * @author YangXi
 */
public class SynSingleton {  
  
    private static SynSingleton instance = null;  
  
    private SynSingleton() {  
    }  
  
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new SynSingleton();  
        }  
    }  
  
    public static SynSingleton getInstance() {  
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }  
}  
