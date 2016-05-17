package cn.cfanr.pattern.TemplateMethod;
/*
 * 抽象基类，为子类提供一个算法框架
 *
 * 提神饮料
 */
public abstract class RefreshBeverage {
	//final防止子类重写该方法
	/*
	 * 制备饮料的模板方法
	 * 封装了所有子类共同遵循的算法框架
	 */
	public final void prepareBeverageTemplate(){
		//步骤一 将水煮沸
		boilWater();
		//步骤二 炮制饮料
		brew();
		//步骤三 将饮料倒入杯中
		pourInCup();
		if(isCustomerWantsCondiments()){
			//步骤四 加入调味料
			addCondiments();
		}
	}
	/*
	 * Hook, 钩子函数，提供一个默认或空的实现
	 * 具体的子类可以自行决定是否挂钩以及如何挂钩
	 * 询问用户是否加入调料
	 */
	protected boolean isCustomerWantsCondiments() {
		return true;
	}
	/*
	 * (通用)基本方法，将水煮沸
	 */
	private void boilWater() {
		System.out.println("将水煮沸");
	}
	/*
	 * 基本方法，将饮料倒入杯中
	 */
	private void pourInCup() {
		System.out.println("将饮料倒入杯中");
	}
	//使用protected（或默认）, 并抽象，延迟到子类
	protected abstract void brew();

	protected abstract void addCondiments();

}