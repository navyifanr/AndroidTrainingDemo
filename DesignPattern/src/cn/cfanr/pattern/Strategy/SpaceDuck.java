package cn.cfanr.pattern.Strategy;

import cn.cfanr.pattern.Strategy.impl.FlyWithRocket;

public class SpaceDuck extends Duck{

	public SpaceDuck(){
		super();
		super.setFlyingStragety(new FlyWithRocket());
	}
	@Override
	public void display() {
		System.out.println("��ͷ��ͷ��");
	}
	
	public void quack(){
		System.out.println("ͨ�����ߵ�ͨѶ");
	}

}
