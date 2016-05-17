package cn.cfanr.pattern.Singleton;

public class Singleton0 {

	/* 私有构造方法，防止被实例化 */
	private Singleton0() {
	}

	/* 此处使用一个内部类来维护单例 */
	private static class SingletonFactory {
		private static Singleton0 instance = new Singleton0();
	}

	/* 获取实例 */
	public static Singleton0 getInstance() {
		return SingletonFactory.instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}
}