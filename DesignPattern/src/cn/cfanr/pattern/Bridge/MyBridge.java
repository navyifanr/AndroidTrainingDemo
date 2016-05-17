package cn.cfanr.pattern.Bridge;

public class MyBridge extends Bridge {
	public void method(){
		getSource().method();
	}
}
