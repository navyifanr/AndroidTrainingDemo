package cn.cfanr.pattern.TemplateMethod;
/*
 * ������࣬Ϊ�����ṩһ���㷨���
 * 
 * ��������
 */
public abstract class RefreshBeverage {
	//final��ֹ������д�÷���
	/*
	 * �Ʊ����ϵ�ģ�巽��
	 * ��װ���������๲ͬ��ѭ���㷨���
	 */
	public final void prepareBeverageTemplate(){
		//����һ ��ˮ���
		boilWater();
		//����� ��������
		brew();
		//������ �����ϵ��뱭��
		pourInCup();
		if(isCustomerWantsCondiments()){
			//������ �����ζ��
			addCondiments();
		}
	}
	/*
	 * Hook, ���Ӻ������ṩһ��Ĭ�ϻ�յ�ʵ��
	 * ���������������о����Ƿ�ҹ��Լ���ιҹ�
	 * ѯ���û��Ƿ�������
	 */
	protected boolean isCustomerWantsCondiments() {
		return true;
	}
	/*
	 * (ͨ��)������������ˮ���
	 */
	private void boilWater() {
		System.out.println("��ˮ���");
	}
	/*
	 * ���������������ϵ��뱭��
	 */
	private void pourInCup() {
		System.out.println("�����ϵ��뱭��");
	}
	//ʹ��protected����Ĭ�ϣ�, �������ӳٵ�����
	protected abstract void brew();
	
	protected abstract void addCondiments();
	
}
