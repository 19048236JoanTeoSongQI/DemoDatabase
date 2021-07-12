package sg.edu.rp.c346.id19048236.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert,btnGetTask;
    TextView tvResults;
    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;
    EditText edDesc, edDate;

    int clickcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert=findViewById(R.id.btnInsert);
        btnGetTask=findViewById(R.id.btnGetTasks);
        tvResults=findViewById(R.id.tvResults);
        lv=findViewById(R.id.lv);
        edDesc=findViewById(R.id.editTextDesc);
        edDate=findViewById(R.id.editTextDate);

        al=new ArrayList<>();
        aa=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db= new DBHelper(MainActivity.this);
                //db.insertTask("Submit RJ","25 Apr 2021");
                db.insertTask(edDesc.getText().toString(), edDate.getText().toString());
            }
        });

        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db= new DBHelper(MainActivity.this);
                ArrayList<String> data= db.getTaskContent();
                db.close();

                String txt="";
                for (int i=0; i<data.size();i++){
                    Log.d("Database Content",i+". "+data.get(i));
                    txt+= i+". "+data.get(i)+"\n";
                }
                tvResults.setText(txt);

                clickcount=clickcount+1;

                al.clear();
                al.addAll(db.getTask(clickcount));
                aa.notifyDataSetChanged();
            }
        });
    }
}

