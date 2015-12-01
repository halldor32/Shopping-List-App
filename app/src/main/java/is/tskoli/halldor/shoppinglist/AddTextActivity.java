package is.tskoli.halldor.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by halldor32 on 14.9.2015.
 */
public class AddTextActivity extends Activity {

    public Button CancelButton;
    public Button OkButton;
    public EditText editText;

    DatabaseHandler db = new DatabaseHandler(this);

    public void enableSubmitIfReady() {

        boolean isReady =editText.getText().toString().length()>0;
        OkButton.setEnabled(isReady);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

//        DatabaseHandler db = new DatabaseHandler(this);

        CancelButton = (Button)findViewById(R.id.CancelButton);
        OkButton = (Button)findViewById(R.id.OkButton);
        editText = (EditText)findViewById(R.id.editText);
        OkButton.setEnabled(false);


        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        OkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {

                    db.addToList(editText.getText().toString());

                    finish();
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                enableSubmitIfReady();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });



    }



}
