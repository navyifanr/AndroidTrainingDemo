package cn.cfanr.pattern.Strategy;
/*
 * ���࣬���е�Ѽ�Ӷ�Ҫ�̳д���
 * ������Ѽ�ӵ���Ϊ����ʾ������
 */
public abstract class Duck {
	/*
	 * Ѽ�ӷ�������
	 * ͨ����Ϊ���ɳ���ʵ��
	 */
	public void quack(){
		System.out.println("�¸¸�");
	}
	/*
	 * ��ʾѼ�ӵ����
	 * Ѽ�ӵ���۸�����ͬ������Ϊabstract��������ʵ��(����)
	 */
	public abstract void display();
	
	private FlyingStragety flyingStragety;
	
	public void setFlyingStragety(FlyingStragety flyingStragety){
		this.flyingStragety=flyingStragety;
	}
	
	public void fly(){
		flyingStragety.performFly();
	}
}
