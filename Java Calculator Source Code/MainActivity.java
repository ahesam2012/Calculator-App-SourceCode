package com.BOSS.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
  private static int countOP;
  
  private String Display;
  
  private TextView _screen;
  
  private int count = 0;
  
  private String currentop = "";
  
  private String result = "";
  
  private void clear() {
    this.Display = "";
    this.currentop = "";
    this.result = "";
    Updatescreen();
  }
  
  private String operate(String paramString1, String paramString2, String paramString3) {
    if (paramString3.equals("/") && Double.valueOf(paramString2).doubleValue() == 0.0D)
      return "Infinity"; 
    if (paramString3.equals("x") && Double.valueOf(paramString2).doubleValue() == 0.0D)
      return "0"; 
    if (paramString1.equals("Too big!") && (paramString3.equals("+") || paramString3.equals("-") || paramString3.equals("/")))
      return "Too big!"; 
    byte b = -1;
    int i = paramString3.hashCode();
    if (i != 43) {
      if (i != 45) {
        if (i != 47) {
          if (i == 120 && paramString3.equals("x"))
            b = 2; 
        } else if (paramString3.equals("/")) {
          b = 3;
        } 
      } else if (paramString3.equals("-")) {
        b = 1;
      } 
    } else if (paramString3.equals("+")) {
      b = 0;
    } 
    if (b != 0) {
      if (b != 1) {
        if (b != 2) {
          if (b != 3)
            return "0"; 
          try {
            double d = Double.valueOf(paramString1).doubleValue() / Double.valueOf(paramString2).doubleValue();
            return String.valueOf(d);
          } catch (Exception exception) {
            Log.d("Calc", exception.getMessage());
            return "0";
          } 
        } 
        return String.valueOf(Double.valueOf((String)exception).doubleValue() * Double.valueOf(paramString2).doubleValue());
      } 
      return String.valueOf(Double.valueOf((String)exception).doubleValue() - Double.valueOf(paramString2).doubleValue());
    } 
    return String.valueOf(Double.valueOf((String)exception).doubleValue() + Double.valueOf(paramString2).doubleValue());
  }
  
  private String operatefac(String paramString1, String paramString2) {
    byte b;
    null = "";
    try {
      boolean bool = paramString1.equals("Infinity");
      if (!bool && !paramString1.equals("infinity")) {
        if (Double.valueOf(paramString1).doubleValue() > 100000.0D)
          return "Too big!"; 
        if (String.valueOf(paramString1).equals("Too big"))
          return "Too big!"; 
        if (!paramString1.equals("Too big!")) {
          b = -1;
          if (paramString2.hashCode() == 33 && paramString2.equals("!"))
            b = 0; 
        } else {
          return null;
        } 
      } else {
        return "Too big!";
      } 
    } catch (Exception exception) {
      clear();
      this._screen.setText("error :(");
      return this.result;
    } 
    return (b != 0) ? "" : String.valueOf(fac((String)exception));
  }
  
  public void Updatescreen() {
    this._screen.setText(this.Display);
  }
  
  public double fac(String paramString) {
    if (Double.valueOf(paramString).doubleValue() == 0.0D)
      return 0.0D; 
    if (Double.valueOf(paramString).doubleValue() == 1.0D)
      return 1.0D; 
    double d1 = Double.valueOf(paramString).doubleValue();
    double d2 = 1.0D;
    while (d1 > 0.0D) {
      d2 *= d1;
      d1--;
    } 
    return d2;
  }
  
  public void onClickClear(View paramView) {
    clear();
    Updatescreen();
    reset();
  }
  
  public void onClickEqual(View paramView) {
    if (countOP > 1) {
      clear();
      this._screen.setText("Cannot have more than one operation at a time! ");
      reset();
      return;
    } 
    String[] arrayOfString = this.Display.split(Pattern.quote(this.currentop));
    try {
      if (arrayOfString.length < 2) {
        String str = this.currentop;
        byte b = -1;
        if (str.hashCode() == 33 && str.equals("!"))
          b = 0; 
        if (b != 0)
          return; 
        this.result = String.valueOf(operatefac(arrayOfString[0], this.currentop));
        TextView textView = this._screen;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.Display);
        stringBuilder.append("\n");
        stringBuilder.append(this.result);
        textView.setText(stringBuilder.toString());
        reset();
        return;
      } 
    } catch (Exception exception) {
      clear();
      reset();
      this._screen.setText("Cannot do that");
    } 
    try {
      this.result = String.valueOf(operate(arrayOfString[0], arrayOfString[1], this.currentop));
      TextView textView = this._screen;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.Display);
      stringBuilder.append("\n");
      stringBuilder.append(String.valueOf(this.result));
      textView.setText(stringBuilder.toString());
      reset();
      return;
    } catch (Exception exception) {
      clear();
      this._screen.setText("error :(");
      reset();
    } 
  }
  
  public void onClickNumber(View paramView) {
    if (this.result != "") {
      clear();
      Updatescreen();
    } 
    if (this.count == 0)
      clear(); 
    Button button = (Button)paramView;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.Display);
    stringBuilder.append(button.getText().toString());
    this.Display = stringBuilder.toString();
    this.count++;
    Updatescreen();
  }
  
  public void onClickOperator(View paramView) {
    countOP++;
    String str = this.result;
    if (str != "") {
      this.Display = str;
      this.result = "";
    } 
    Button button = (Button)paramView;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.Display);
    stringBuilder.append(button.getText());
    this.Display = stringBuilder.toString();
    this.currentop = button.getText().toString();
    Updatescreen();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131296284);
    this._screen = (TextView)findViewById(2131165186);
    this._screen.setText(this.Display);
  }
  
  public void reset() {
    countOP = 0;
  }
}
