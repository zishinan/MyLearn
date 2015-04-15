package com.ouyang.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
	public static void main(String[] args) {
		try {
			IHello hello = new ImplHello();
			LocateRegistry.createRegistry(8888);
			Naming.bind(IHello.RMI_PATH, hello);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
