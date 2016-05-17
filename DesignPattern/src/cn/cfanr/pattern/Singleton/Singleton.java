package cn.cfanr.pattern.Singleton;

public class Singleton {
	// ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ�����ӳټ���
	private static Singleton instance = null;

	// ˽�й��캯������ֹ��ʵ����
	private Singleton() {
	}

	/*
	 *��ͨ�����࣬��̬���̷���������ʵ�����������������̰߳�ȫ�������࣬
	 *������ǰ���������̵߳Ļ����£��϶��ͻ��������
	 */
	/*
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}*/
	
	/*
	 * ��Javaָ���д�������͸�ֵ�����Ƿֿ����еģ�Ҳ����˵instance = new Singleton();����Ƿ�����ִ�еġ�
	 * ����JVM������֤�������������Ⱥ�˳��Ҳ����˵�п���JVM��Ϊ�µ�Singletonʵ������ռ䣬
	 * Ȼ��ֱ�Ӹ�ֵ��instance��Ա��Ȼ����ȥ��ʼ�����Singletonʵ������������������ķ����Ϳ��ܳ�����
	 */
	public static Singleton getInstance(){
		if(instance==null){
			synchronized(instance){    //ֻ���ڵ�һ�δ��������ʱ����Ҫ������֮��Ͳ���Ҫ��
				if(instance==null){
					instance=new Singleton();
				}
			}
		}
		return instance;
	}

	// ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ��
	public Object readResolve() {
		return instance;
	}
}
