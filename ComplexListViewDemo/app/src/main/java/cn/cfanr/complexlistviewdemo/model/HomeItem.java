package cn.cfanr.complexlistviewdemo.model;

import java.util.List;

/**
 * Created by cfanr on 2015/12/4.
 */
public class HomeItem{
    private ItemType itemType;
    private String tagName;
    private Special special;
    private Ad ad;
    private MenuPo[] menuPos;
    private List<MealShow> mealShowList;
    private List<TalentShow> talentShowList;

    public ItemType getItemType(){
        return itemType;
    }

    public void setItemType(ItemType itemType){
        this.itemType=itemType;
    }

    public Special getSpecial(){
        return special;
    }

    public void setSpecial(Special special){
        this.special=special;
    }

    public Ad getAd(){
        return ad;
    }

    public void setAd(Ad ad){
        this.ad=ad;
    }

    public List<MealShow> getMealShowList(){
        return mealShowList;
    }

    public void setMealShowList(List<MealShow> mealShowList){
        this.mealShowList=mealShowList;
    }

    public List<TalentShow> getTalentShowList(){
        return talentShowList;
    }

    public void setTalentShowList(List<TalentShow> talentShowList){
        this.talentShowList=talentShowList;
    }

    public MenuPo[] getMenuPos(){
        return menuPos;
    }

    public void setMenuPos(MenuPo[] menuPos){
        this.menuPos=menuPos;
    }

    public String getTagName(){
        return tagName;
    }

    public void setTagName(String tagName){
        this.tagName=tagName;
    }
}
