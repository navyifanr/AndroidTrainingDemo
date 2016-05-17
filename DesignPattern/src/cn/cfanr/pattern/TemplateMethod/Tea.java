package cn.cfanr.pattern.TemplateMethod;
/*
 * �������࣬�ṩ���Ʊ���ľ���ʵ��
 */
public class Tea extends RefreshBeverage {

	@Override
	protected void brew() {
		System.out.println("��80�ȵ���ˮ���ݲ�Ҷ5����");
	}

	@Override
	protected void addCondiments() {
		System.out.println("��������");
	}
	/*
	 *����ͨ�����ǵ���ʽѡ����ع��Ӻ���
	 * @see cn.cfanr.pattern.template.RefreshBeverage#isCustomerWantsCondiments()
	 */
	@Override
	protected boolean isCustomerWantsCondiments() {
		return false;
	}
}
