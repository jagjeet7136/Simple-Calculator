package calculator;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Calculator {

	private JFrame frame;
	private JTextField editor;
	private JTextField result;
	JTextArea textArea;
	String memory="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					Calculator window = new Calculator();
					window.editor.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent ke) {
							if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
								window.editor.setEditable(true);

							} else {
								window.editor.setEditable(false);
							}
						}
					});
					window.frame.setTitle("Calculator");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 740, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		editor = new JTextField();
		editor.setHorizontalAlignment(SwingConstants.RIGHT);
		editor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		editor.setColumns(10);
		editor.setBorder(null);
		editor.setBounds(20, 60, 378, 46);
		frame.getContentPane().add(editor);

		result = new JTextField();
		result.setHorizontalAlignment(SwingConstants.RIGHT);
		result.setBackground(Color.WHITE);
		result.setEditable(false);
		result.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		result.setColumns(10);
		result.setBorder(null);
		result.setBounds(207, 22, 191, 39);
		frame.getContentPane().add(result);

		JButton modulo = new JButton("%");
		modulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0) {
					return;
				}
				if(result.getText().length()==0 && editor.getText().length()!=0) { 
					result.setText(editor.getText()+"%");
					editor.setText("");
				}
				if(result.getText().length()!=0 && editor.getText().length()!=0) {
					String string = result.getText();
					char character = string.charAt(string.length()-1);
					if(character=='+' || character=='-' || character=='/' || character=='*' || character=='%') {
						return;
					}
					else {
						result.setText(editor.getText()+"%");
						editor.setText("");
					}
				}

			}
		});
		modulo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		modulo.setBounds(10, 133, 102, 53);
		frame.getContentPane().add(modulo);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBorder(new LineBorder(Color.GRAY, 2));
		editorPane.setBounds(10, 11, 398, 104);
		frame.getContentPane().add(editorPane);

		JButton squareRoot = new JButton("Sqrt");
		squareRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0 && result.getText().length()==0) {
					return;
				}
				if(editor.getText().length()==0 && result.getText().length()!=0) {
					return;
				}
				double res;
				if(editor.getText().length()!=0 && result.getText().length()!=0) {
					String x = result.getText();
					char character  = x.charAt(x.length()-1);

					if(	character=='+' || character=='/' || character=='-' || character=='*' || character=='%') {
						return;
					}
					else {
						String string = editor.getText();
						res = Math.sqrt(Double.parseDouble(string));
						result.setText(String.valueOf(res));

					}

					if(memory.length()==0) {
						memory = "Sqrt("+editor.getText()+") = "+String.valueOf(res);
					}
					else {
						memory = memory + "Sqrt("+editor.getText()+") = "+String.valueOf(res);
					}
					editor.setText("");
					textArea.setText(memory);
				}
				else if(editor.getText().length()!=0 && result.getText().length()==0) {
					String string = editor.getText();
					res = Math.sqrt(Double.parseDouble(string));
					result.setText(String.valueOf(res));

					if(memory.length()==0) {
						memory = "Sqrt("+editor.getText()+") = "+String.valueOf(res)+"\n";
					}
					else {
						memory = memory + "Sqrt("+editor.getText()+") = "+String.valueOf(res)+"\n";
					}
					editor.setText("");
					textArea.setText(memory);
				}
			}
		});

		squareRoot.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		squareRoot.setBounds(110, 133, 102, 53);
		frame.getContentPane().add(squareRoot);

		JButton clear = new JButton("C");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText("");
				result.setText("");
			}
		});
		clear.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		clear.setBounds(207, 133, 102, 53);
		frame.getContentPane().add(clear);

		JButton cross = new JButton("X");
		cross.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0) {
					return;
				}
				String string = editor.getText();
				char array[] = new char[string.length()-1];
				for(int i=0; i<array.length; i++) {
					array[i] = string.charAt(i);
				}
				string = new String(array);
				editor.setText(string);
			}
		});
		cross.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		cross.setIcon(null);
		cross.setBounds(306, 133, 102, 53);
		frame.getContentPane().add(cross);

		JButton seven = new JButton("7");
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+7);
			}
		});
		seven.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		seven.setBounds(10, 197, 102, 53);
		frame.getContentPane().add(seven);

		JButton eight = new JButton("8");
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+8);
			}
		});
		eight.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		eight.setBounds(110, 197, 102, 53);
		frame.getContentPane().add(eight);

		JButton nine = new JButton("9");
		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+9);
			}
		});
		nine.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		nine.setBounds(207, 197, 102, 53);
		frame.getContentPane().add(nine);

		JButton divide = new JButton("/");
		divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0) {
					return;
				}
				if(result.getText().length()==0 && editor.getText().length()!=0) { 
					result.setText(editor.getText()+"/");
					editor.setText("");
				}
				if(result.getText().length()!=0 && editor.getText().length()!=0) {
					String string = result.getText();
					char character = string.charAt(string.length()-1);
					if(character=='+' || character=='-' || character=='/' || character=='*' || character=='%') {
						return;
					}
					else {
						result.setText(editor.getText()+"/");
						editor.setText("");
					}
				}
			}
		});

		divide.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		divide.setBounds(306, 197, 102, 53);
		frame.getContentPane().add(divide);

		JButton four = new JButton("4");
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+4);
			}
		});
		four.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		four.setBounds(10, 261, 102, 53);
		frame.getContentPane().add(four);

		JButton five = new JButton("5");
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+5);
			}
		});
		five.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		five.setBounds(110, 261, 101, 53);
		frame.getContentPane().add(five);

		JButton six = new JButton("6");
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+6);
			}
		});
		six.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		six.setBounds(207, 261, 102, 53);
		frame.getContentPane().add(six);

		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0) {
					return;
				}
				if(result.getText().length()==0 && editor.getText().length()!=0) { 
					result.setText(editor.getText()+"-");
					editor.setText("");
				}
				if(result.getText().length()!=0 && editor.getText().length()!=0) {
					String string = result.getText();
					char character = string.charAt(string.length()-1);
					if(character=='+' || character=='-' || character=='/' || character=='*' || character=='%') {
						return;
					}
					else {
						result.setText(editor.getText()+"-");
						editor.setText("");
					}
				}

			}
		});
		minus.setFont(new Font("Segoe UI Light", Font.PLAIN, 45));
		minus.setBounds(306, 261, 102, 53);
		frame.getContentPane().add(minus);

		JButton one = new JButton("1");
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+1);
			}
		});
		one.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		one.setBounds(10, 325, 102, 53);
		frame.getContentPane().add(one);

		JButton two = new JButton("2");
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+2);
			}
		});
		two.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		two.setBounds(110, 325, 102, 53);
		frame.getContentPane().add(two);

		JButton three = new JButton("3");
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+3);
			}
		});
		three.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		three.setBounds(207, 325, 102, 53);
		frame.getContentPane().add(three);

		JButton plus = new JButton("+");
		plus.setBackground(Color.WHITE);
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0) {
					return;
				}
				if(result.getText().length()==0 && editor.getText().length()!=0) { 
					result.setText(editor.getText() + "+");
					editor.setText("");
				}
				if(result.getText().length()!=0 && editor.getText().length()!=0) {
					String string = result.getText();
					char character = string.charAt(string.length()-1);
					if(character=='+' || character=='-' || character=='/' || character=='*' || character=='%') {
						return;
					}
					else {
						result.setText(editor.getText()+"+");
						editor.setText("");
					}
				}
			}
		});
		plus.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 25));
		plus.setBounds(306, 325, 102, 53);
		frame.getContentPane().add(plus);

		JButton decimal = new JButton(".");
		decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().contains(".")) {
					return;
				}
				editor.setText(editor.getText()+".");
			}
		});
		decimal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		decimal.setBounds(10, 389, 102, 53);
		frame.getContentPane().add(decimal);

		JButton zero = new JButton("0");
		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.setText(editor.getText()+0);
			}
		});
		zero.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		zero.setBounds(110, 389, 102, 53);
		frame.getContentPane().add(zero);

		JButton multiply = new JButton("*");
		multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0) {
					return;
				}
				if(result.getText().length()==0 && editor.getText().length()!=0) { 
					result.setText(editor.getText() + "*");
					editor.setText("");
				}
				if(result.getText().length()!=0 && editor.getText().length()!=0) {
					String string = result.getText();
					char character = string.charAt(string.length()-1);
					if(character=='+' || character=='-' || character=='/' || character=='*' || character=='%') {
						return;
					}
					else {
						result.setText(editor.getText()+"*");
						editor.setText("");
					}
				}
			}
		});
		multiply.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 30));
		multiply.setBounds(307, 389, 102, 53);
		frame.getContentPane().add(multiply);

		JButton eualTo = new JButton("=");
		eualTo.setForeground(new Color(0, 0, 0));
		eualTo.setBackground(Color.WHITE);
		eualTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editor.getText().length()==0 && result.getText().length()==0) {
					return;
				}
				if(editor.getText().length()!=0 && result.getText().length()==0) {
					result.setText(editor.getText()+"=");
					editor.setText("");
				}
				if(editor.getText().length()!=0 && result.getText().length()!=0) {
					if((result.getText().charAt(result.getText().length()-1))=='+' ||
							(result.getText().charAt(result.getText().length()-1))=='-' ||
							(result.getText().charAt(result.getText().length()-1))=='/' || 
							(result.getText().charAt(result.getText().length()-1))=='*' ||
							(result.getText().charAt(result.getText().length()-1))=='%' ) {

						char operator = result.getText().charAt(result.getText().length()-1);
						if(operator=='-') {
							if(memory.length()==0) {
								memory = result.getText()+editor.getText()+"=";
							}
							else {
								memory = memory + result.getText()+editor.getText()+"=";
							}
							double editorNum = Double.parseDouble(editor.getText());
							byte array[] = result.getText().getBytes();
							byte array2[] = new byte[array.length-1];
							for(int i=0; i<array2.length; i++) {
								array2[i] = array[i];
							}
							String string = new String(array2);
							double resultNum = Double.parseDouble(string);
							double sum = resultNum - editorNum;
							String finalSum = String.valueOf(sum);
							roundOff(finalSum);

						}

						if(operator=='+') {
							if(memory.length()==0) {
								memory = result.getText()+editor.getText()+" = ";
							}
							else {
								memory = memory + result.getText()+editor.getText()+" = ";
							}
							double editorNum = Double.parseDouble(editor.getText());
							byte array[] = result.getText().getBytes();
							byte array2[] = new byte[array.length-1];
							for(int i=0; i<array2.length; i++) {
								array2[i] = array[i];
							}
							String string = new String(array2);
							double resultNum = Double.parseDouble(string);
							double sum = resultNum + editorNum;
							String finalSum = String.valueOf(sum);
							roundOff(finalSum);

						}
						if(operator=='*') {
							if(memory.length()==0) {
								memory = result.getText()+editor.getText()+" = ";
							}
							else {
								memory = memory + result.getText()+editor.getText()+" = ";
							}
							double editorNum = Double.parseDouble(editor.getText());
							byte array[] = result.getText().getBytes();
							byte array2[] = new byte[array.length-1];
							for(int i=0; i<array2.length; i++) {
								array2[i] = array[i];
							}
							String string = new String(array2);
							double resultNum = Double.parseDouble(string);
							double sum = resultNum * editorNum;
							String finalSum = String.valueOf(sum);
							roundOff(finalSum);

						}
						if(operator=='/') {
							if(memory.length()==0) {
								memory = result.getText()+editor.getText()+" = ";
							}
							else {
								memory = memory + result.getText()+editor.getText()+"=";
							}
							double editorNum = Double.parseDouble(editor.getText());
							if(editorNum==0) {
								result.setText("Error");
								editor.setText("");
								return;
							}
							byte array[] = result.getText().getBytes();
							byte array2[] = new byte[array.length-1];
							for(int i=0; i<array2.length; i++) {
								array2[i] = array[i];
							}
							String string = new String(array2);
							double resultNum = Double.parseDouble(string);
							double sum = resultNum / editorNum;
							String finalSum = String.valueOf(sum);
							roundOff(finalSum);

						}
						if(operator=='%') {
							if(memory.length()==0) {
								memory = result.getText()+editor.getText()+" = ";
							}
							else {
								memory = memory + result.getText()+editor.getText()+" = ";
							}
							double editorNum = Double.parseDouble(editor.getText());
							byte array[] = result.getText().getBytes();
							byte array2[] = new byte[array.length-1];
							for(int i=0; i<array2.length; i++) {
								array2[i] = array[i];
							}
							String string = new String(array2);
							double resultNum = Double.parseDouble(string);
							double sum = resultNum % editorNum;
							String finalSum = String.valueOf(sum);
							roundOff(finalSum);

						}
					}
					else {
						result.setText(editor.getText());
						editor.setText("");
					}
				}

			}
		});
		eualTo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		eualTo.setBounds(207, 389, 103, 53);
		frame.getContentPane().add(eualTo);

		JLabel lblNewLabel = new JLabel("Memory");
		lblNewLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblNewLabel.setBounds(638, 11, 76, 33);
		frame.getContentPane().add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(418, 44, 296, 2);
		frame.getContentPane().add(separator);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(418, 60, 296, 382);
		frame.getContentPane().add(scrollPane_1);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		scrollPane_1.setViewportView(textArea);

		JButton btnNewButton = new JButton("Clear Memory");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memory = "";
				textArea.setText(memory);
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(612, 444, 102, 22);
		frame.getContentPane().add(btnNewButton);

	}

	public void roundOff(String finalSum) {

		int zero = 0;
		int nonZero = 0;
		int index = finalSum.indexOf(".");
		int count = 0;
		for(int i=0; i<index; i++) {
			count++;
		}
		if(count>10) {
			return;
		}
		if(index+1<finalSum.length()) {
			if(finalSum.charAt(index+1)=='0') {
				zero++;
			}
			else {
				nonZero++;
			}
		}
		if(index+2<finalSum.length()) {
			if(finalSum.charAt(index+2)=='0') {
				zero++;
			}
			else {
				nonZero++;
			}
		}

		if(zero>0 && nonZero==0) {

			result.setText(finalSum.substring(0, index));
			editor.setText("");
			memory = memory + finalSum.substring(0, index) + "\n";
			textArea.setText(memory);
		}
		else {
			String x = finalSum.substring(0,index);
			int j = index;
			for(int i=0; i<5; i++) {
				if(j<finalSum.length()) {
					x= x+ finalSum.charAt(j);
					j++;
				}
			}
			result.setText(x);
			editor.setText("");
			memory = memory + x + "\n";
			textArea.setText(memory);
		}
	}
}
