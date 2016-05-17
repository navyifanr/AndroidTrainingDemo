package cn.cfanr.pattern.AbstractFactory;

public class MailSender implements Sender {

	@Override
	public void send() {
		System.out.println("this is mail sender");
	}

}
