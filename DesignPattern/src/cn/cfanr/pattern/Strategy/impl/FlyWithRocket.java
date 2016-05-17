package cn.cfanr.pattern.Strategy.impl;

import cn.cfanr.pattern.Strategy.FlyingStragety;


public class FlyWithRocket implements FlyingStragety{

	@Override
	public void performFly() {
		System.out.println("用火箭在太空遨游");
	}

}
