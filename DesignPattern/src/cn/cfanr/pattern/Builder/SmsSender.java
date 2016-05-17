package cn.cfanr.pattern.Builder;

public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("this is sms sender.");
	}

}
