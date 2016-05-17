package cn.cfanr.pattern.FactoryMethod;


/*
 * 静态方法模式
 */
public class SendFactory3 {
	public static Sender prodecuMail(){
		return new MailSender();
	}

	public static Sender produceSms(){
		return new SmsSender();
	}
}
