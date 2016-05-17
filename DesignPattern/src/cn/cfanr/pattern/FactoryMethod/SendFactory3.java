package cn.cfanr.pattern.FactoryMethod;

/*
 * ��̬����ģʽ
 */
public class SendFactory3 {
	public static Sender prodecuMail(){
		return new MailSender();
	}
	
	public static Sender produceSms(){
		return new SmsSender();
	}
}
