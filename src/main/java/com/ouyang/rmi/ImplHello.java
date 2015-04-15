package com.ouyang.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplHello extends UnicastRemoteObject implements IHello {

	private static final long serialVersionUID = 5638534684282024442L;

	protected ImplHello() throws RemoteException {
		super();
	}

	@Override
	public String getHelloWorld() {
		return "Hello !!!";
	}

}
