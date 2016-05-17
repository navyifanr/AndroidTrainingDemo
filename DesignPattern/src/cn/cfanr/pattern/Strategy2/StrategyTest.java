package cn.cfanr.pattern.Strategy2;
/*
 * ����ģʽ������һϵ���㷨������ÿ���㷨��װ������ʹ���ǿ����໥�滻�����㷨�ı仯����Ӱ�쵽ʹ���㷨�Ŀͻ�����Ҫ���һ��
 * �ӿڣ�Ϊһϵ��ʵ�����ṩͳһ�ķ��������ʵ����ʵ�ָýӿڣ����һ�������ࣨ���п��ޣ����ڸ����ࣩ���ṩ��������
 */
public class StrategyTest {
	public static void main(String[] args) {
		String exp="2+8";
		ICalculator cal=new Plus();
		int result=cal.calculate(exp);
		System.out.println(result);
	}
}
