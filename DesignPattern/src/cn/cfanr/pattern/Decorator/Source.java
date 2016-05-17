package cn.cfanr.pattern.Decorator;

public class Source implements Sourceable{

	@Override
	public void method() {
		System.out.println("this is an original method");
	}

}
