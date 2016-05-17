package cn.cfanr.pattern.Memento;
/*
 * ��ҪĿ���Ǳ���һ�������ĳ��״̬���Ա����ʵ���ʱ��ָ�����
 */
public class Test {
	public static void main(String[] args) {
		//����ԭʼ��
		Original origi=new Original("egg");
		
		//����������
		Storage storage=new Storage(origi.createMemento());
		
		//�޸�ԭʼ���״̬
		System.out.println("��ʼ��״̬Ϊ��"+origi.getValue());
		origi.setValue("niu");
		System.out.println("�޸ĺ��״̬Ϊ��"+origi.getValue());
		
		//�ظ�ԭʼ���״̬
		origi.restoreMemento(storage.getMemento());
		System.out.println("�ָ����״̬Ϊ��"+origi.getValue());
	}
}
