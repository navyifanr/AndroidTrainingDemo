package cn.cfanr.pattern.FactoryMethod;

public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("this is sms sender.");
	}

}
