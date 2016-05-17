package cn.cfanr.pattern.Command;

/*
 * ����ģʽ�ܺ���⣬�ٸ����ӣ�˾��Ա������ʿ��ȥ�ɼ����飬����������ĽǶ������ǣ�˾��Ա�������ǣ����������������ݣ�
 * ������ʿ�������ʿ��ȥִ�С�������̺��ڣ������໥����κ�һ��������ȥ���������ˣ�ֻ��Ҫ�����Լ����¶����У�˾��Ա
 * Ҫ���ǽ��������ȥ��ע����ʿ������ôʵ�ֵ�
 * 
 * Invoker�ǵ����ߣ�˾��Ա����Receiver�Ǳ������ߣ�ʿ������MyCommand�����ʵ����Command�ӿڣ����н��ն���
 * 
 * ����ģʽ��Ŀ�ľ��Ǵﵽ����ķ����ߺ�ִ����֮����ʵ�������ִ�зֿ�����ϤStruts��ͬѧӦ��֪����Struts��ʵ����һ�ֽ�
 * ����ͳ��ַ���ļ��������б�Ȼ�漰����ģʽ��˼�룡
 */
public class Test {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command cmd = new MyCommand(receiver);
		Invoker invoker = new Invoker(cmd);
		invoker.action();
	}
}
