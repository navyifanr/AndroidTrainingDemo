package cn.cfanr.pattern.FactoryMethod;

public class FactoryTest {
	public static void main(String[] args) {
		/*
		 * ����ģʽ�ʺϣ����ǳ����˴����Ĳ�Ʒ��Ҫ���������Ҿ��й�ͬ�Ľӿ�ʱ������ͨ����������ģʽ���д�����
		 * �����ϵ�����ģʽ�У���һ�����������ַ������󣬲�����ȷ�������󣬵���������ڵڶ��֣�����Ҫʵ
		 * ���������࣬���ԣ����������£����ǻ�ѡ�õ����֡�����̬��������ģʽ��
		 * 
		 * ���ǣ������Ҫ��չ���򣬱���Թ���������޸ģ���Υ���˱հ�ԭ�����Ҫ�õ����󹤳�����ģʽ
		 */
		//��ͨ��������ģʽ
		SendFactory1 factory=new SendFactory1();
		Sender sender=factory.produce("sms");
		sender.send();
		
		//�����������ģʽ
		SendFactory factory2=new SendFactory();
		Sender sender2=factory2.produceMail();
		sender2.send();
		
		//��̬��������ģʽ������ѡ�ã�
		Sender sender3=SendFactory3.produceSms();
		sender3.send();
	}
}
