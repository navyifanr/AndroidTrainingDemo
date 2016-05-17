package cn.cfanr.pattern.Adapter;
/*
 * ����ת����Ĳ���������
 */
public class TwoPlugAdapter implements ThreePlugIf {
	
	private GBTwoPlug plug;
	
	public TwoPlugAdapter(GBTwoPlug plug){
		this.plug=plug;
	}
	
	@Override
	public void powerWithThree() {
		System.out.println("ͨ��ת��");
		plug.powerWithTwo();
	}

}
