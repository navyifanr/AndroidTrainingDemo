package cn.cfanr.pattern.FactoryMethod;

/*
 * �������ģʽ
 */
public class SendFactory {
	public Sender produceMail() {
		return new MailSender();
	}

	public Sender produceSms() {
		return new SmsSender();
	}
}
