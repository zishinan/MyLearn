package com.ouyang.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHello extends Remote {
	public static final String RMI_PATH = "rmi://127.0.0.1:8888/RmiHello";
	public static final String RMI_PATH_1 = "//localhost:8888/RmiHello";
	public String getHelloWorld() throws RemoteException;
}
