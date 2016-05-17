package cn.cfanr.pattern.Singleton;

public class Singleton {
	// 持有私有静态实例，防止被引用，此处赋值为null，目的是延迟加载
	private static Singleton instance = null;

	// 私有构造函数，防止被实例化
	private Singleton() {
	}

	/*
	 *普通单例类，静态工程方法，创建实例，但像这样毫无线程安全保护的类，
	 *如果我们把它放入多线程的环境下，肯定就会出现问题
	 */
	/*
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}*/

	/*
	 * 在Java指令中创建对象和赋值操作是分开进行的，也就是说instance = new Singleton();语句是分两步执行的。
	 * 但是JVM并不保证这两个操作的先后顺序，也就是说有可能JVM会为新的Singleton实例分配空间，
	 * 然后直接赋值给instance成员，然后再去初始化这个Singleton实例。这样，所以下面的方法就可能出错了
	 */
	public static Singleton getInstance(){
		if(instance==null){
			synchronized(instance){    //只有在第一次创建对象的时候需要加锁，之后就不需要了
				if(instance==null){
					instance=new Singleton();
				}
			}
		}
		return instance;
	}

	// 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	public Object readResolve() {
		return instance;
	}
}
