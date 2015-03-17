package com.ouyang.mail;

public class TimingTask implements Runnable
{
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(10000L);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
