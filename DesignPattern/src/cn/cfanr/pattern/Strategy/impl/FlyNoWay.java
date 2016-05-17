package cn.cfanr.pattern.Strategy.impl;

import cn.cfanr.pattern.Strategy.FlyingStragety;


public class FlyNoWay implements FlyingStragety{

	@Override
	public void performFly() {
		System.out.println("�������");
	}

}
