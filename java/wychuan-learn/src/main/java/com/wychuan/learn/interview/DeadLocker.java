package com.wychuan.learn.interview;

/**
 * 题目：写一个死锁程序
 * @author mfhj-dz-001-504
 *
 */
class Lock {
	static Object locka = new Object();
	static Object lockb = new Object();
}

public class DeadLocker implements Runnable {

	private boolean flag;

	public DeadLocker(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		if (flag) {
			synchronized (Lock.locka) {
				System.out.println("true locka");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (Lock.lockb) {
					System.out.println("true lockb");
				}
			}
		} else {
			synchronized (Lock.lockb) {
				System.out.println("false lockb");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (Lock.locka) {
					System.out.println("false loacka");
				}
			}
		}
	}
	
	public static void  main(String[] args){
		Thread thread1 = new Thread(new DeadLocker(true));
		Thread thread2 = new Thread(new DeadLocker(false));
		thread1.start();
		thread2.start();
	}

}
