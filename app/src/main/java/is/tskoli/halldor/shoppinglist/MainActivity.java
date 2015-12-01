package is.tskoli.halldor.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Button addButton;
    public ListView shoppingList;
    public Button removeButton;

    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        addButton = (Button)findViewById(R.id.addButton);
        removeButton = (Button)findViewById(R.id.removeButton);
        shoppingList = (ListView)findViewById(R.id.shoppingList);

        shoppingList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        shoppingList.setSelection(0);
        shoppingList.setItemChecked(0, true);

        List<ShoppingList> shoppingLists = db.getAllItems();


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        shoppingList.setAdapter(adapter);
        for (ShoppingList al : shoppingLists) {
            String log = "Id: "+al.GetID()+" ,Name: " + al.getText();
            Log.d("", log);

            list.add(al.getText());

            adapter.notifyDataSetChanged();
        }



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTextActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
