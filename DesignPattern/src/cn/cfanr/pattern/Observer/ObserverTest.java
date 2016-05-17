package cn.cfanr.pattern.Observer;
/*
 * 类和类之间的关系，不涉及到继承
 * 观察者模式很好理解，类似于邮件订阅和RSS订阅，当我们浏览一些博客或wiki时，经常会看到RSS图标，就这的意思是，当你订阅
 * 了该文章，如果后续有更新，会及时通知你。其实，简单来讲就一句话：当一个对象变化时，其它依赖该对象的对象都会收到通知，
 * 并且随着变化！对象之间是一种一对多的关系
 *
 * MySubject类就是我们的主对象，Observer1和Observer2是依赖于MySubject的对象，当MySubject变化时，Observer1和Observer2
 * 必然变化。AbstractSubject类中定义着需要监控的对象列表，可以对其进行修改：增加或删除被监控对象，且当MySubject变化时，
 * 负责通知在列表内存在的对象。
 *
 * 比如，绘图可能用到观察者模式，重绘时提醒线程刷新
 */
public class ObserverTest {
	public static void main(String[] args) {
		Subject sub=new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());
		sub.operation();
	}
}
