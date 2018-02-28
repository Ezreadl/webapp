package com.qm.nms.service.impl;

public class ThreadTest {
	static int cout = 0;

	private ThreadTest(){
		
	}
	public static void main(String[] args) {
		ThreadTest tt=new ThreadTest();
		threadOne one = tt.new threadOne();
		threadTwo two = tt.new threadTwo();
//		for (int i = 0; i < 2; i++) {
			Thread tho = new Thread(one);
			Thread thoo = new Thread(one);
//			Thread tht = new Thread(two);
			tho.start();
			thoo.start();
//			tht.start();			
//		}

		System.out.println(cout);
	}
	
	public class threadOne implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 200000; i++) {
				inc();
//				cout++;
			}
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("after one:"+cout);
			
		}
		
	}
	public class threadTwo implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 1000000; i++) {
				dec();
//				cout--;
			}
			System.out.println("after two:"+cout);
		}		
	}
	public synchronized static void inc(){
		cout ++;
		System.out.println(Thread.currentThread().getName()+"-inc:"+cout);		
	}
	public synchronized static void dec(){
		cout --;
		System.out.println(Thread.currentThread().getName()+"-dec:"+cout);		
	}
}
