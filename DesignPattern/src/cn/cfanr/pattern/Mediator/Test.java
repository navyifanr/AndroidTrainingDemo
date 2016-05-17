package cn.cfanr.pattern.Mediator;
/*
 * �н���ģʽҲ��������������֮�����ϵģ���Ϊ�������֮����������ϵ�Ļ��������ڹ��ܵ���չ��ά������ΪֻҪ�޸�һ����������
 * �����Ķ��󶼵ý����޸ġ����ʹ���н���ģʽ��ֻ����ĺ�Mediator��Ĺ�ϵ����������֮��Ĺ�ϵ�����Ƚ���Mediator���У����е�
 * ��spring����������
 * 
 * 
 */
public class Test {
	public static void main(String[] args) {
		Mediator mediator=new MyMediator();
		mediator.createMediator();
		mediator.workAll();
	}
}
