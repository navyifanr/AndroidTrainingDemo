package cn.cfanr.pattern.Builder;

public class MailSender implements Sender {

	@Override
	public void send() {
		System.out.println("this is mail sender");
	}

}
