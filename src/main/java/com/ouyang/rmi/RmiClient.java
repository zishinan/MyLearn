package com.ouyang.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiClient {
	public static void main(String[] args) {
		try {
			IHello hello = (IHello)Naming.lookup(IHello.RMI_PATH);
			String hw = hello.getHelloWorld();
			System.out.println(hw);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
