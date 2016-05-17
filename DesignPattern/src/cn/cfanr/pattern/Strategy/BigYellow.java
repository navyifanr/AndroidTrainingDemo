package cn.cfanr.pattern.Strategy;

import cn.cfanr.pattern.Strategy.impl.FlyNoWay;


public class BigYellow extends Duck{
	
	public BigYellow(){
		super();
		super.setFlyingStragety(new FlyNoWay());
	}
	@Override
	public void display() {
		System.out.println("����ܴ�ȫ��ƻ�");
	}
	
	public void quack(){
		System.out.println("��~~~");
	}

}
