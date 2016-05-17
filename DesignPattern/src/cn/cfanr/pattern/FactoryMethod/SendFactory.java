package cn.cfanr.pattern.FactoryMethod;

/*
 * 多个方法模式
 */
public class SendFactory {
	public Sender produceMail() {
		return new MailSender();
	}

	public Sender produceSms() {
		return new SmsSender();
	}
}
