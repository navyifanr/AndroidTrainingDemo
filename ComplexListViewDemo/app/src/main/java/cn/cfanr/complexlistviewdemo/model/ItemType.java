package cn.cfanr.complexlistviewdemo.model;

/**
 * Created by cfanr on 2015/12/4.
 */
public enum ItemType{
    SIGN_MALL(0),
    TAG(1),
    SPECIAL(2),
    AD(3),
    MENU(4),
    MEAL_SHOW(5),
    TALENT_SHOW(6);

    public int getValue(){
        return value;
    }

    private int value;
    ItemType(int value){
        this.value = value;
    }

}
