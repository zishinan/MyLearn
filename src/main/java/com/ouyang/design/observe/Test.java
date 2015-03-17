package com.ouyang.design.observe;

/**
 * 观察者模式
 * @author yangxi
 *
 */
public class Test
{
	public static void main(String[] args)
	{
		Watched girl = new ConcreteWatched();

		Watcher boy1 = new ConcreteWatcher();
		Watcher boy2 = new ConcreteWatcher();
		Watcher boy3 = new ConcreteWatcher();

		girl.addWatcher(boy1);
		girl.addWatcher(boy2);
		girl.addWatcher(boy3);

		girl.notifyWatchers("first notify");

		girl.removeWatcher(boy1);

		girl.notifyWatchers("second notify");

		girl.addWatcher(boy1);

		girl.notifyWatchers("third notify");
	}
}
