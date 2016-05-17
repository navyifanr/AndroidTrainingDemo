package cn.cfanr.pattern.Strategy;

import cn.cfanr.pattern.Strategy.impl.FlyWithWin;


public class MallardDuck extends Duck{

	public MallardDuck(){
		super();
		super.setFlyingStragety(new FlyWithWin());
	}
	@Override
	public void display() {
		System.out.println("�ҵĲ�������ɫ��");
	}
	
}
