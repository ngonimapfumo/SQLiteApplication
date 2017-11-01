package ngonim.xyz.sqliteapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mName, mSurname, mAge, mId;
    private Button mButton, mViewButton, mEdit, mDelete;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("SQLite");
        databaseHelper = new DatabaseHelper(this);
        mButton = (Button) findViewById(R.id.create_button);
        mDelete = (Button)findViewById(R.id.delete_button);
        mViewButton = (Button) findViewById(R.id.view_button);
        mEdit = (Button) findViewById(R.id.view_button);
        mName = (EditText) findViewById(R.id.nameField);
        mId = (EditText)findViewById(R.id.idField);
        mSurname = (EditText) findViewById(R.id.surnameField);
        mAge = (EditText) findViewById(R.id.ageField);
        addData();
        viewData();
        deleteData();
    }

    public void addData() {

        mButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = databaseHelper.insertData(mName.getText().toString(),
                                mSurname.getText().toString(),
                                mAge.getText().toString());

                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "data is not inserted", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void viewData() {
        mViewButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor result = databaseHelper.getAllData();
                        if (result.getCount() == 0) {

                            displayMsg("Error", "No data found");
                            return;
                        }

                        StringBuilder builder = new StringBuilder();
                        while (result.moveToNext()) {
                            builder.append("id: " + result.getString(0) + "\n");
                            builder.append("name: " + result.getString(1) + "\n");
                            builder.append("surname: " + result.getString(2) + "\n");
                            builder.append("age: " + result.getString(3) + "\n\n");

                        }

                        displayMsg("data", builder.toString());
                    }
                });
    }

    public void updateData() {
        mEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO: update saved data
                    }
                }
        );
    }


    public void deleteData(){
        mDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = databaseHelper.deleteData(mId.getText().toString());

                        if(deletedRows>0){
                            Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(MainActivity.this, "data not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    public void displayMsg(String title, String message) {
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        dbuilder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();
    }
}
