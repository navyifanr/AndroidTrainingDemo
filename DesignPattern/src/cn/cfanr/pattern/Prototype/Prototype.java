package cn.cfanr.pattern.Prototype;
/*
 * ��ģʽ��˼����ǽ�һ��������Ϊԭ�ͣ�������и��ơ���¡������һ����ԭ�������Ƶ��¶���.
 * һ��ԭ���ֻ࣬��Ҫʵ��Cloneable�ӿڣ���дclone�������˴�clone�������Ըĳ���������ƣ�
 * ��ΪCloneable�ӿ��Ǹ��սӿڣ���������ⶨ��ʵ����ķ�����
 */
public class Prototype implements Cloneable {
	public Object clone() throws CloneNotSupportedException {
		Prototype proto = (Prototype) super.clone(); //�˴����õ���Object�ķ���
		return proto;
	}
}
