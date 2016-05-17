package cn.cfanr.pattern.ResponsibilityChain;
/*
 * �ж������ÿ��������ж���һ����������ã������ͻ��γ�һ�������������������ϴ��ݣ�ֱ��ĳһ����������������
 * ���Ƿ����߲���������������Ǹ�����ᴦ����������ԣ�������ģʽ����ʵ�֣��������ͻ��˵�����£���ϵͳ���ж�̬�ĵ�����
 * 
 * �����ϵ����������һ������������һ��������������һ������ģʽ����Լ���������Ҫ�����Լ�ȥʵ�֣�ͬʱ����һ��ʱ�̣�����
 * ֻ������һ�����󴫸���һ�����󣬶����������������
 */
public class Test {
	public static void main(String[] args) {
		MyHandler h1=new MyHandler("h1");
		MyHandler h2=new MyHandler("h2");
		MyHandler h3=new MyHandler("h3");
		
		h1.setHandler(h2);
		h2.setHandler(h3);
		
		h1.operator();
	}
}
