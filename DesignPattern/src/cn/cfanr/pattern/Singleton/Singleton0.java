package cn.cfanr.pattern.Singleton;

public class Singleton0 {

	/* ˽�й��췽������ֹ��ʵ���� */
	private Singleton0() {
	}

	/* �˴�ʹ��һ���ڲ�����ά������ */
	private static class SingletonFactory {
		private static Singleton0 instance = new Singleton0();
	}

	/* ��ȡʵ�� */
	public static Singleton0 getInstance() {
		return SingletonFactory.instance;
	}

	/* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */
	public Object readResolve() {
		return getInstance();
	}
}