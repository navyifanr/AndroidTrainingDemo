package cn.cfanr.pattern.FactoryMethod;

/*
 * ��ͨ��������ģʽ
 */
public class SendFactory1 {
	public Sender produce(String type) {
		if (type.equals("mail")) {
			return new MailSender();
		} else if (type.equals("sms")) {
			return new SmsSender();
		} else {
			System.out.println("��������ȷ������");
			return null;
		}
	}
}
