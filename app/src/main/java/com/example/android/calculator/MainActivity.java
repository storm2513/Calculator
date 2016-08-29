package com.example.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btMult, btMin, btC, btDiv, btPirc, btPM, btDel, btPlus, btDot, btEq;
    TextView tvOperation, tvResult;
    static Integer result;
    static String operation = "";

    Expressions res = new Expressions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);
        bt0 = (Button) findViewById(R.id.bt0);
        btMult = (Button) findViewById(R.id.btMult);
        btMin = (Button) findViewById(R.id.btMin);
        btC = (Button) findViewById(R.id.btC);
        btDiv = (Button) findViewById(R.id.btDiv);
        btPirc = (Button) findViewById(R.id.btPirc);
        btPM = (Button) findViewById(R.id.btPM);
        btDel = (Button) findViewById(R.id.btDel);
        btPlus = (Button) findViewById(R.id.btPlus);
        btDot = (Button) findViewById(R.id.btDot);
        btEq = (Button) findViewById(R.id.btEq);
        tvOperation = (TextView) findViewById(R.id.oper);
        tvResult = (TextView) findViewById(R.id.result);
        tvOperation.setText("");
        tvResult.setText("");

        bt1.setOnClickListener(numListener);
        bt2.setOnClickListener(numListener);
        bt3.setOnClickListener(numListener);
        bt4.setOnClickListener(numListener);
        bt5.setOnClickListener(numListener);
        bt6.setOnClickListener(numListener);
        bt7.setOnClickListener(numListener);
        bt8.setOnClickListener(numListener);
        bt9.setOnClickListener(numListener);
        bt0.setOnClickListener(numListener);
        btDot.setOnClickListener(numListener);

        btEq.setOnClickListener(equal);
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operation.length()!=0)
                operation = operation.substring(0, operation.length() - 1);
                tvOperation.setText(operation);

            }
        });
        btDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                operation = "";
                tvOperation.setText("");
                return true;
            }
        });

        btDiv.setOnClickListener(divide);
        btPM.setOnClickListener(just); //TODO make smth
        btPirc.setOnClickListener(pircent);

        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    operation = "";
                    tvOperation.setText("");
                    tvResult.setText("");

            }
        });

        btMin.setOnClickListener(minus);
        btMult.setOnClickListener(mult);
        btPlus.setOnClickListener(plus);


    }

    View.OnClickListener numListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt1:
                    operation += "1";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt2:
                    operation += "2";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt3:
                    operation += "3";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt4:
                    operation += "4";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt5:
                    operation += "5";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt6:
                    operation += "6";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt7:
                    operation +="7";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt8:
                    operation += "8";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt9:
                    operation += "9";
                    tvOperation.setText(operation);
                    break;
                case R.id.bt0:
                    if (tvOperation.getText() == "")
                        break;
                    operation += "0";
                    tvOperation.setText(operation);
                    break;

                case R.id.btDot:
                    operation += ".";
                    tvOperation.setText(operation);
                    break;

            }
        }
    };
    View.OnClickListener just = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Works", Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener equal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            operation=tvOperation.getText().toString();
            if(isLastOperator(operation))
                operation=operation.substring(0, operation.length()-1);
            result = res.eval(operation);
            tvResult.setText(result.toString());
            operation="";

        }
    };
    View.OnClickListener plus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            operation=tvOperation.getText().toString();
            operation=operation + "+";
            tvOperation.setText(operation);


        }
    };
    View.OnClickListener minus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            operation=tvOperation.getText().toString();
            operation=operation + "-";
            tvOperation.setText(operation);


        }
    };
    View.OnClickListener mult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            operation=tvOperation.getText().toString();
            operation=operation + "*";
            tvOperation.setText(operation);


        }
    };
    View.OnClickListener divide = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            operation=tvOperation.getText().toString();
            operation=operation + "/";
            tvOperation.setText(operation);


        }
    };
    View.OnClickListener pircent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            operation=tvOperation.getText().toString();
            operation=operation + "%";
            tvOperation.setText(operation);


        }
    };
    public boolean isLastOperator(String s){
       switch(s.substring(s.length()-1, s.length())){
           case "%":
               return true;
           case "*":
               return true;
           case "/":
               return true;
           case "+":
               return true;
           case "-":
               return true;
           default:
               return false;

       }
    }

}
