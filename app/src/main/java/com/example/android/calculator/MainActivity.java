package com.example.android.calculator;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btMult, btMin, btC, btDiv, btSqrt, btPM, btDel, btPlus, btDot, btEq;
    TextView tvOperation, tvResult;
    static Double result;
    static String operation = "";
    static boolean isLastEqual = false;
    static boolean hasDot = false;


    Expressions res = new Expressions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        btSqrt = (Button) findViewById(R.id.btSqrt);
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
                if(operation.length()!=0) {
                    if (operation.substring(operation.length() - 1, operation.length()).equals("."))
                        hasDot = false;
                    operation = operation.substring(0, operation.length() - 1);
                }
                tvOperation.setText(operation);
                isLastEqual=false;
            }
        });
        btDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                operation = "";
                tvOperation.setText("");
                isLastEqual=false;
                hasDot=false;
                return true;
            }
        });

        btDiv.setOnClickListener(divide);
        btPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvOperation.getText().toString().equals("")) {
                    if (!tvResult.getText().toString().equals("")) {
                        operation = tvResult.getText().toString();

                        if (!operation.substring(0, 1).equalsIgnoreCase("-")) {
                            operation = "-" + operation;
                            if(operation.substring(operation.length() - 1, operation.length()).equals("."))
                                hasDot=false;
                            tvOperation.setText(operation);
                            tvResult.setText(operation);
                        } else {
                            operation = operation.substring(1, operation.length());
                            if(operation.substring(operation.length() - 1, operation.length()).equals("."))
                                hasDot=false;
                            tvOperation.setText(operation);
                            tvResult.setText(operation);
                        }
                    } else {
                        operation = tvOperation.getText().toString();
                        if (isLastOperator(operation))
                            operation = operation.substring(0, operation.length() - 1);
                        if(operation.substring(operation.length() - 1, operation.length()).equals("."))
                            hasDot=false;
                        try {
                            result = res.Parse(operation);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        operation = result.toString();
                        if (operation.substring(operation.length() - 2, operation.length()).equals(".0"))
                            operation = operation.substring(0, operation.length() - 2);
                        if (!operation.substring(0, 1).equalsIgnoreCase("-")) {
                            operation = "-" + operation;
                            tvOperation.setText(operation);
                            tvResult.setText(operation);
                        } else {
                            operation = operation.substring(1, operation.length());
                            tvOperation.setText(operation);
                            tvResult.setText(operation);
                        }
                    }

                }
            }


        });
        btSqrt.setOnClickListener(sqrt);

        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    operation = "";
                    tvOperation.setText("");
                    tvResult.setText("");
                hasDot=false;

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
                    if(isLastEqual)
                        operation="";
                    operation += "1";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt2:
                    if(isLastEqual)
                        operation="";
                    operation += "2";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt3:
                    if(isLastEqual)
                        operation="";
                    operation += "3";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt4:
                    if(isLastEqual)
                        operation="";
                    operation += "4";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt5:
                    operation += "5";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt6:
                    if(isLastEqual)
                        operation="";
                    operation += "6";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt7:
                    if(isLastEqual)
                        operation="";
                    operation +="7";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt8:
                    if(isLastEqual)
                        operation="";
                    operation += "8";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt9:
                    if(isLastEqual)
                        operation="";
                    operation += "9";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;
                case R.id.bt0:
                    if(isLastEqual)
                        operation="";
                    if (tvOperation.getText() == "")
                        break;
                    operation += "0";
                    tvOperation.setText(operation);
                    isLastEqual=false;
                    break;

                case R.id.btDot:
                    if(!hasDot) {
                        if(!tvOperation.getText().toString().equals("")){
                        if(!isLastOperator(tvOperation.getText().toString())){
                        if (isLastEqual)
                            operation = "";
                        if (tvOperation.getText().toString().equals("")||operation.equals("")) {
                            tvOperation.setText("0.");
                            operation = tvOperation.getText().toString();
                            hasDot=true;
                        }
                        if (!tvOperation.getText().toString().substring(tvOperation.getText().toString().length() - 1, tvOperation.getText().toString().length()).equals(".")) {
                            operation += ".";
                            tvOperation.setText(operation);
                            hasDot=true;
                        }
                        isLastEqual = false;
                        break;

                    }
                            else{
                            operation=operation+("0.");
                            tvOperation.setText(operation);
                            hasDot=true;
                        }

                        }
                        else {
                            if (isLastEqual)
                                operation = "";
                            if (tvOperation.getText().toString().equals("")||operation.equals("")) {
                                tvOperation.setText("0.");
                                operation = tvOperation.getText().toString();
                                hasDot=true;
                            }
                            if (!tvOperation.getText().toString().substring(tvOperation.getText().toString().length() - 1, tvOperation.getText().toString().length()).equals(".")) {
                                operation += ".";
                                tvOperation.setText(operation);
                                hasDot=true;
                            }
                            isLastEqual = false;
                            break;
                        }
                    }
            }
        }
    };


    View.OnClickListener equal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!tvOperation.getText().toString().equals("")) {
                operation = tvOperation.getText().toString();
                cutLast();
                try {
                    result = res.Parse(operation);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                operation = result.toString();

                if (operation.substring(operation.length() - 2, operation.length()).equals(".0"))
                    operation = operation.substring(0, operation.length() - 2);

                tvResult.setText(operation);
                isLastEqual=true;
                hasDot=false;
            }


        }
    };
    View.OnClickListener plus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!tvOperation.getText().toString().equals("")&&
                    !tvOperation.getText().toString().substring(tvOperation.getText().toString().length()-1, tvOperation.getText().toString().length()).equals("+")) {
                if(isLastEqual)
                    operation = tvResult.getText().toString();
                else
                    operation = tvOperation.getText().toString();
                cutLast();
                operation = operation + "+";
                tvOperation.setText(operation);
                isLastEqual=false;
                hasDot=false;
            }


        }
    };
    View.OnClickListener minus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!tvOperation.getText().toString().equals("")) {
                if (!tvOperation.getText().toString().substring(tvOperation.getText().toString().length() - 1, tvOperation.getText().toString().length()).equals("-")) {
                    if (isLastEqual) {
                        operation = tvResult.getText().toString();
                        operation = operation + "-";
                        tvOperation.setText(operation);
                        isLastEqual = false;
                        hasDot=false;
                    }
                    else {
                        operation = tvOperation.getText().toString();
                        operation = operation + "-";
                        tvOperation.setText(operation);
                        isLastEqual = false;
                        hasDot=false;
                    }

                }}
            else {
                operation=tvOperation.getText().toString();
                operation = operation + "-";
                tvOperation.setText(operation);
                isLastEqual = false;
            }





        }
    };
    View.OnClickListener mult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!tvOperation.getText().toString().equals("")&&
                    !tvOperation.getText().toString().substring(tvOperation.getText().toString().length()-1, tvOperation.getText().toString().length()).equals("*")) {
                if(isLastEqual)
                    operation = tvResult.getText().toString();
                else
                    operation = tvOperation.getText().toString();

                cutLast();
                operation = operation + "*";
                tvOperation.setText(operation);
                isLastEqual=false;
                hasDot=false;
            }


        }
    };
    View.OnClickListener divide = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!tvOperation.getText().toString().equals("")&&
                    !tvOperation.getText().toString().substring(tvOperation.getText().toString().length()-1, tvOperation.getText().toString().length()).equals("/")) {
                if(isLastEqual)
                    operation = tvResult.getText().toString();

                else
                    operation = tvOperation.getText().toString();

                cutLast();
                operation = operation + "/";
                tvOperation.setText(operation);
                isLastEqual=false;
                hasDot=false;
            }


        }
    };
    View.OnClickListener sqrt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!tvOperation.getText().toString().equals("")) {

                if (!tvResult.getText().toString().equals("")) {
                    operation = tvResult.getText().toString();
                    Double sq = Math.sqrt(Double.parseDouble(operation));
                    operation = sq.toString();
                    if (operation.substring(operation.length() - 2, operation.length()).equals(".0"))
                        operation = operation.substring(0, operation.length() - 2);
                    tvResult.setText(operation);
                    tvOperation.setText(operation);
                } else {
                    operation = tvOperation.getText().toString();
                    cutLast();
                    try {
                        result = res.Parse(operation);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    operation = result.toString();
                    Double sq = Math.sqrt(Double.parseDouble(operation));
                    operation = sq.toString();
                    if (operation.substring(operation.length() - 2, operation.length()).equals(".0"))
                        operation = operation.substring(0, operation.length() - 2);
                    tvResult.setText(operation);
                    tvOperation.setText(operation);
                    tvOperation.setText(operation);

                }
            }



        }
    };
    public static boolean isLastOperator(String s){
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
    public void cutLast (){
        if (isLastOperator(operation))
            operation = operation.substring(0, operation.length() - 1);
    }

}


