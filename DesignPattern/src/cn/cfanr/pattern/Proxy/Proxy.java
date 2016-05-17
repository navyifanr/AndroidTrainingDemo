package cn.cfanr.pattern.Proxy;


public class Proxy implements Sourceable {
	
	private Source source;
	public Proxy(){
		super();
		this.source=new Source();
	}
	
	@Override
	public void method() {
		before();
		source.method();
		after();
	}

	private void before() {
		System.out.println("before proxy...");
	}

	private void after() {
		System.out.println("after proxy...");
	}

}
