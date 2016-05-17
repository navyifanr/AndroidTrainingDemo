package cn.cfanr.pattern.Adapter;
/*
 * 采用继承方式的插座的适配器
 */
public class TwoPlugAdapterExtends extends GBTwoPlug implements ThreePlugIf {

	@Override
	public void powerWithThree() {
		System.out.println("借助继承适配器");
		this.powerWithTwo();
	}

}
