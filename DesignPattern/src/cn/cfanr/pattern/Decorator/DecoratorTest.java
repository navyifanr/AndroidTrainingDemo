package cn.cfanr.pattern.Decorator;
/*
 * װ��ģʽ���Ǹ�һ����������һЩ�µĹ��ܣ������Ƕ�̬�ģ�Ҫ��װ�ζ���ͱ�װ�ζ���ʵ��ͬһ���ӿڣ�
 * װ�ζ�����б�װ�ζ����ʵ��;
 * Source���Ǳ�װ���࣬Decorator����һ��װ���࣬����ΪSource�ද̬�����һЩ����;
 * 
 * װ����ģʽ��Ӧ�ó�����
	1����Ҫ��չһ����Ĺ��ܡ�
	2����̬��Ϊһ���������ӹ��ܣ����һ��ܶ�̬���������̳в���������һ�㣬�̳еĹ����Ǿ�̬�ģ����ܶ�̬��ɾ����
	ȱ�㣺�����������ƵĶ��󣬲����Ŵ�
 */
public class DecoratorTest {
	public static void main(String[] args) {
		Sourceable source=new Source();
		Sourceable obj=new Decorator(source);
		obj.method();
	}
}
