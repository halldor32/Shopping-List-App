package is.tskoli.halldor.shoppinglist;

/**
 * Created by halldor32 on 27.9.2015.
 */
public class ShoppingList {

    int _id;
    String _text;

    public ShoppingList() {

    }

    public ShoppingList(int id, String text) {
        this._id = id;
        this._text = text;
    }

    public ShoppingList(String text) {
        this._text = text;
    }

    public int GetID() {
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getText(){
        return this._text;
    }

    public void setText(String text){
        this._text = text;
    }

}
