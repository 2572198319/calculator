package com.example.calculator;

import android.support.v4.app.INotificationSideChannel;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Calculator {
    public static String Calculate(String input){
        while(input.matches("^.*\\(−?[0-9.]\\).*$")) {
            String reg = "\\((−?[0-9.])\\)";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(input);
            while(m.find()) {
                input=input.replace(m.group(0),m.group(1));
            }
        }
        if(input.matches("^.*\\(.*\\).*$")){
            String reg = "\\(([^()]*)\\)";//匹配最里边的括号
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(input);
            while(m.find()) {
                input=input.replace(m.group(0),Calculate(m.group(1)));
            }
        }
        while(input.contains("!")){
            String reg = "([0-9]+)!";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                int a=Integer.parseInt(str1);
                double b=1;
                for(int i=1;i<=a;i++){
                    b=b*i;
                }
                input = input.replace(str, b + "");
            }
        }
        while(input.contains("sinh")) {
            System.out.println("sinh");
            String reg = "sinh\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.sinh(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.sinh(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("cosh")) {
            String reg = "cosh\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1,str1.length()));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.cosh(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.cosh(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("tanh")) {
            System.out.println(input);
            String reg = "tanh\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.tanh(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.tanh(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("sin")) {
            String reg = "sin\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.sin(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.sin(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("cos")) {
            String reg = "cos\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.cos(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.cos(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("tan")) {
            System.out.println("tan");
            String reg = "tan\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.tan(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.tan(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("ln")) {
            String reg = "ln\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.log(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.log(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("lg")) {
            String reg = "lg\\(?(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                    input = input.replace(str, Math.log10(a) + "");
                }
                else {
                    input = input.replace(str,"×" + Math.log10(a));
                }
            }
            if(input.startsWith("×")){
                input=input.substring(1);
            }
            input=input.replaceAll("-","−");
        }
        while(input.contains("e")) {
            String reg = "e\\^(−?[0-9.]+)";
            Pattern p=Pattern.compile(reg);
            Matcher m=p.matcher(input);
            while(m.find()) {
                String str=m.group(0),str1=m.group(1);
                String str2=m.group(2);
                System.out.println(str1);
                double a;
                if(str1.contains("−")){
                    a=-Double.parseDouble(str1.substring(1));
                } else {
                    a=Double.parseDouble(str1);
                }
                double b = Double.parseDouble( str2 );
            

            input=input.replace(m.group(1),Math.pow(a,b)+"");
        }}
        input=input.replaceAll("-","−");

        while(input.contains("√")) {
        String reg = "√\\(?([0-9.]+)";
        Pattern p=Pattern.compile(reg);
        Matcher m=p.matcher(input);
        while(m.find()) {
            String str=m.group(0),str1=m.group(1);
            double a;
            a=Double.parseDouble(str1);
            if(input.contains("+"+str)||input.contains("−"+str)||input.contains("×"+str)||input.contains("÷"+str)){
                input = input.replace(str, Math.sqrt(a) + "");
            }
            else {
                input = input.replace(str,"×" + Math.sqrt(a));
            }
        }
        if(input.startsWith("×")){
            input=input.substring(1);
        }
        input=input.replaceAll("-","−");
    }
        while(input.contains("%")){
        String reg = "((\\d*\\.*\\d+)%)";
        Pattern p=Pattern.compile(reg);
        Matcher m = p.matcher(input);
        while(m.find()){
            double a=0.01*Double.parseDouble(m.group(2));
            input=input.replace(m.group(1),a+"");
        }
    }
        while(input.contains("×")||input.contains("÷")) {
        String reg = "((−?\\d*\\.*\\d+)[×÷](−?\\d*\\.*\\d+))";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(input);
        while (m.find()){
            double a,b;
            String str=m.group(0),str1=m.group(2).toString(),str2=m.group(3).toString();
            if(str1.contains("−")){
                a=-Double.parseDouble(str1.substring(1,str1.length()));
            } else {
                a=Double.parseDouble(str1);
            }
            if(str2.contains("−")){
                b=-Double.parseDouble(str2.substring(1,str2.length()));
            } else {
                b=Double.parseDouble(str2);
            }
            if (str.contains("×")){
                input=input.replace(str,a*b+"");
            } else {
                if(Math.abs(b)<1e-5) {return "error";}
                input=input.replace(str,a/b+"");
            }
        }
    }
        if(input.contains("E")){
        input="error";
    }
    input=input.replace("-","−");
        while(input.contains("+")||input.contains("−")){
        if(input.matches("^−\\d*\\.*\\d+$")) {
            break;
        };
        String reg = "((−?\\d*\\.*\\d+)[+−](−?\\d*\\.*\\d+))";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(input);
        while (m.find()){
            double a,b;
            String str=m.group(0),str1=m.group(2).toString(),str2=m.group(3).toString();
            if(str1.contains("−")){
                a=-Double.parseDouble(str1.substring(1,str1.length()));
            } else {
                a=Double.parseDouble(str1);
            }
            if(str2.contains("−")){
                b=-Double.parseDouble(str2.substring(1,str2.length()));
            } else {
                b=Double.parseDouble(str2);
            }
            if (str.contains("+")){
                input=input.replace(str,a+b+"");
            } else {
                input=input.replace(str,a-b+"");
            }
        }
        input=input.replaceAll("-","−");
    }
        if(!input.matches("−?\\d*\\.*\\d+")){
        input="error!";
    }
        if(input.matches("^.*\\.0$")){
        int len=input.length();
        input=input.substring(0,len-2);
    }
        return input;
}
}
