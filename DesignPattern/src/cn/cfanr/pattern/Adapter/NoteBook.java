package cn.cfanr.pattern.Adapter;

public class NoteBook {
	
	private ThreePlugIf plug;
	public NoteBook(ThreePlugIf plug){
		this.plug=plug;
	}
	//ʹ�ò������
	public void charge(){
		plug.powerWithThree();
	}
	
	public static void main(String[] args){
		GBTwoPlug two=new GBTwoPlug();
		ThreePlugIf three=new TwoPlugAdapter(two);
		NoteBook nb=new NoteBook(three);
		nb.charge();
		
		three=new TwoPlugAdapterExtends();
		nb=new NoteBook(three);
		nb.charge();
	}
}
