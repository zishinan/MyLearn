package com.ouyang.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

public class ThreadLocalTest {

	public static void main(String[] args) {
		Exchanger<List<Integer>> exchanger = new Exchanger<>();
		new Producer(exchanger).start();
		new Producer(exchanger).start();
	}

}

class Producer extends Thread {
	List<Integer> list = new ArrayList<>();
	Exchanger<List<Integer>> exchanger = null;

	public Producer(Exchanger<List<Integer>> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			list.clear();
			list.add(rand.nextInt(10000));
			list.add(rand.nextInt(10000));
			list.add(rand.nextInt(10000));
			list.add(rand.nextInt(10000));
			list.add(rand.nextInt(10000));
			
			for (Integer integer : list) {
				System.out.print(integer + ",");
			}
			
			try {
				list = exchanger.exchange(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("\n==============");
		}
	}
}

class Consumer extends Thread {
	List<Integer> list = new ArrayList<>();
	Exchanger<List<Integer>> exchanger = null;

	public Consumer(Exchanger<List<Integer>> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				list = exchanger.exchange(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}
}