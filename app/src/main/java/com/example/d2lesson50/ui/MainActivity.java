package com.example.d2lesson50.ui;

import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.d2lesson50.R;
import com.example.d2lesson50.models.QuestionModel;
import com.example.d2lesson50.service.QuestionModelImpl;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView txtSavol,togriJ,prosent,TjavoblarS;
    private RadioButton rad1,rad2,rad3,rad4;
    private Button button,btn_next1,btn_provois;
    private QuestionModelImpl questionModel = new QuestionModelImpl();
    private List<QuestionModel> list;
    private  int index = 0;
    private String myAnswer = "";
    private  int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        checkBox = findViewById(R.id.check1);
//        txt = findViewById(R.id.txt);
//        linearLayout = findViewById(R.id.container);
//
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    linearLayout.setBackgroundColor(Color.RED);
//                    txt.setText("Checked bo'ldi");
//                }else {
//                    txt.setText("Uncheked bo'ldi");
//                    linearLayout.setBackgroundColor(Color.BLUE);
//                }
//            }
//        })

        setUi();
        loadData();
        setTest();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id != -1){

                myAnswer = ((RadioButton)findViewById(id)).getText().toString();
                }
            }
        });
        button.setOnClickListener(view -> {
            if (myAnswer == null){
                Toast.makeText(this, "Javob tanlang!", Toast.LENGTH_SHORT).show();
            }else {
                if (myAnswer.equalsIgnoreCase(list.get(index).getAnswer())){
                    Toast.makeText(this, "Barakalla", Toast.LENGTH_SHORT).show();
                    count++;
                    TjavoblarS.setText(String.valueOf(count));
                }else {
                    Toast.makeText(this, "Xato", Toast.LENGTH_SHORT).show();
                }
                if (index<list.size()-1){

                index++;
                radioGroup.clearCheck();
                setTest();
                }else{
                    txtSavol.setText("Savollar tugadi");
                    radioGroup.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    btn_next1.setVisibility(View.GONE);
                    btn_provois.setVisibility(View.GONE);
                    togriJ.setVisibility(View.VISIBLE);
                    TjavoblarS.setVisibility(View.VISIBLE);
                    prosent.setVisibility(View.VISIBLE);



                }
            }
            btn_next1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index == list.size() - 1){
                        Toast.makeText(MainActivity.this, "Savollar tugadi tasdiqlashni bosing", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "1+", Toast.LENGTH_SHORT).show();
                        index++;
                        setTest();
                    }
                }
            });
            btn_provois.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index == 0){
                        Toast.makeText(MainActivity.this, "Siz birinchi savoldasiz", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "1-", Toast.LENGTH_SHORT).show();
                        index--;
                        setTest();

                    }

                }
            });

        });




    }

    private void setTest() {
        list = questionModel.getList();
        QuestionModel questionModel1 = list.get(index);
        txtSavol.setText(questionModel1.getQuestion());
        rad1.setText(questionModel1.getVar1());
        rad2.setText(questionModel1.getVar2());
        rad3.setText(questionModel1.getVar3());
        rad4.setText(questionModel1.getVar4());
        myAnswer = null;
    }

    private void loadData() {
        questionModel.add();
    }

    private void setUi() {
        radioGroup = findViewById(R.id.radG);
        rad1 = findViewById(R.id.rad1);
        rad2 = findViewById(R.id.rad2);
        rad3 = findViewById(R.id.rad3);
        rad4 = findViewById(R.id.rad4);
        txtSavol = findViewById(R.id.txtSavol);
        button = findViewById(R.id.btnNext);
        btn_next1 = findViewById(R.id.btn_next1);
        btn_provois =findViewById(R.id.btn_previous);
        togriJ = findViewById(R.id.correct_answers);
        TjavoblarS = findViewById(R.id.error_no);
        prosent = findViewById(R.id.prosent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int itemId = item.getItemId();
            switch (itemId){
                case R.id.restart:
                    Toast.makeText(this, "Testni bosidan boshlang", Toast.LENGTH_SHORT).show();
                    index = 0;
                    radioGroup.clearCheck();
                    radioGroup.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    btn_next1.setVisibility(View.VISIBLE);
                    btn_provois.setVisibility(View.VISIBLE);
                    togriJ.setVisibility(View.GONE);
                    TjavoblarS.setVisibility(View.GONE);
                    prosent.setVisibility(View.GONE);
                    break;
            }
        return super.onOptionsItemSelected(item);
    }
}