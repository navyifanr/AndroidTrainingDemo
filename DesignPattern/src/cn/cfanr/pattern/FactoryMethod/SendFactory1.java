package cn.cfanr.pattern.FactoryMethod;

/*
 * 普通工厂方法模式
 */
public class SendFactory1 {
	public Sender produce(String type) {
		if (type.equals("mail")) {
			return new MailSender();
		} else if (type.equals("sms")) {
			return new SmsSender();
		} else {
			System.out.println("请输入正确的类型");
			return null;
		}
	}
}
