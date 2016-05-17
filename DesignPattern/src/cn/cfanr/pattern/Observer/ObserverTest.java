package cn.cfanr.pattern.Observer;
/*
 * �����֮��Ĺ�ϵ�����漰���̳�
 * �۲���ģʽ�ܺ���⣬�������ʼ����ĺ�RSS���ģ����������һЩ���ͻ�wikiʱ�������ῴ��RSSͼ�꣬�������˼�ǣ����㶩��
 * �˸����£���������и��£��ἰʱ֪ͨ�㡣��ʵ����������һ�仰����һ������仯ʱ�����������ö���Ķ��󶼻��յ�֪ͨ��
 * �������ű仯������֮����һ��һ�Զ�Ĺ�ϵ
 * 
 * MySubject��������ǵ�������Observer1��Observer2��������MySubject�Ķ��󣬵�MySubject�仯ʱ��Observer1��Observer2
 * ��Ȼ�仯��AbstractSubject���ж�������Ҫ��صĶ����б����Զ�������޸ģ����ӻ�ɾ������ض����ҵ�MySubject�仯ʱ��
 * ����֪ͨ���б��ڴ��ڵĶ���
 * 
 * ���磬��ͼ�����õ��۲���ģʽ���ػ�ʱ�����߳�ˢ��
 */
public class ObserverTest {
	public static void main(String[] args) {
		Subject sub=new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());
		sub.operation();
	}
}
