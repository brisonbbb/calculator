import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RedScientificCalculator extends JFrame implements ActionListener {

    JTextField display;

    double num1=0,num2=0,result=0;
    String operator="";

    RedScientificCalculator(){

        setTitle("Red Scientific Calculator");
        setSize(420,500);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial",Font.BOLD,22));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display,BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,4,10,10));
        panel.setBackground(Color.BLACK);

        String buttons[]={
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","+","=",
                "sin","cos","tan","√",
                "x²","diff","int","C"
        };

        for(String text:buttons){

            JButton b = new JButton(text);

            b.setFont(new Font("Arial",Font.BOLD,16));
            b.setBackground(Color.RED);
            b.setForeground(Color.WHITE);

            b.addActionListener(this);

            panel.add(b);
        }

        add(panel,BorderLayout.CENTER);

        JMenuBar bar = new JMenuBar();

        JMenu matrixMenu = new JMenu("Matrix");

        JMenuItem addMatrix = new JMenuItem("Matrix Addition");

        addMatrix.addActionListener(e->matrixAdd());

        matrixMenu.add(addMatrix);

        bar.add(matrixMenu);

        setJMenuBar(bar);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){

        String s=e.getActionCommand();

        if((s.charAt(0)>='0' && s.charAt(0)<='9') || s.equals(".")){
            display.setText(display.getText()+s);
        }

        else if(s.equals("C")){
            display.setText("");
        }

        else if(s.equals("=")){

            num2=Double.parseDouble(display.getText());

            switch(operator){

                case "+": result=num1+num2; break;
                case "-": result=num1-num2; break;
                case "*": result=num1*num2; break;
                case "/": result=num1/num2; break;
            }

            display.setText(""+result);
        }

        else if(s.equals("sin")){
            num1=Double.parseDouble(display.getText());
            display.setText(""+Math.sin(Math.toRadians(num1)));
        }

        else if(s.equals("cos")){
            num1=Double.parseDouble(display.getText());
            display.setText(""+Math.cos(Math.toRadians(num1)));
        }

        else if(s.equals("tan")){
            num1=Double.parseDouble(display.getText());
            display.setText(""+Math.tan(Math.toRadians(num1)));
        }

        else if(s.equals("√")){
            num1=Double.parseDouble(display.getText());
            display.setText(""+Math.sqrt(num1));
        }

        else if(s.equals("x²")){
            num1=Double.parseDouble(display.getText());
            display.setText(""+(num1*num1));
        }

        else if(s.equals("diff")){

            double x=Double.parseDouble(display.getText());
            double h=0.0001;

            double derivative=((Math.pow(x+h,2)-Math.pow(x,2))/h);

            display.setText(""+derivative);
        }

        else if(s.equals("int")){

            double a=0;
            double b=Double.parseDouble(display.getText());

            int n=1000;

            double h=(b-a)/n;
            double sum=0;

            for(int i=0;i<n;i++){

                double x=a+i*h;
                sum+=Math.pow(x,2)*h;
            }

            display.setText(""+sum);
        }

        else{

            num1=Double.parseDouble(display.getText());
            operator=s;
            display.setText("");
        }
    }

    void matrixAdd(){

        int m=Integer.parseInt(JOptionPane.showInputDialog("Rows"));
        int n=Integer.parseInt(JOptionPane.showInputDialog("Columns"));

        int a[][]=new int[m][n];
        int b[][]=new int[m][n];

        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                a[i][j]=Integer.parseInt(JOptionPane.showInputDialog("A["+i+"]["+j+"]"));

        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                b[i][j]=Integer.parseInt(JOptionPane.showInputDialog("B["+i+"]["+j+"]"));

        String result="Result:\n";

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++)
                result+=(a[i][j]+b[i][j])+" ";

            result+="\n";
        }

        JOptionPane.showMessageDialog(this,result);
    }

    public static void main(String[] args){

        new RedScientificCalculator();
    }
}