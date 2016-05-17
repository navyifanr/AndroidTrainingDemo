package cn.cfanr.pattern.AbstractFactory;

public class Test {
	public static void main(String[] args) {
		/*
		 * ���󹤳�����ģʽ�ô������������������һ�����ܣ�����ʱ��Ϣ����ֻ����һ��ʵ���࣬ʵ��Sender�ӿڣ�
		 * ͬʱ��һ�������࣬ʵ��Provider�ӿڣ���OK�ˣ�����ȥ�Ķ��ֳɵĴ��롣����������չ�ԽϺã�
		 */
		Provider provider=new SendMailFactory();
		Sender sender=provider.produce();
		sender.send();
	}
}
