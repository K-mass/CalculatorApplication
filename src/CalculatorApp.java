import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CalculatorApp {
	private static Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	CalculatorLogic input = new CalculatorLogic();
	
	boolean secondOn = false;
	boolean degreeModeOn = false;
	
	public void press(String inputString) {
		input.buttonPress(inputString);
		text.setText(input.inputExpression);
	}
	
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(333, 501);
		shell.setText("Colin's Calculator");
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		text.setBounds(10, 10, 298, 40);
		
		CalculatorApp inputter = new CalculatorApp();
		
		Button button2 = new Button(shell, SWT.NONE);
		button2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					inputter.press("sqrt(");
				} else {
					inputter.press("root(");
				}
			}
		});
		button2.setText("sqrt");
		button2.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button2.setBounds(10, 122, 70, 50);
		
		Button button3 = new Button(shell, SWT.NONE);
		button3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					inputter.press("^2");
				} else {
					inputter.press("^");
				}
			}
		});
		button3.setText("x^2");
		button3.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button3.setBounds(10, 178, 70, 50);
		
		Button button4 = new Button(shell, SWT.NONE);
		button4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("7");
			}
		});
		button4.setText("7");
		button4.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button4.setBounds(10, 234, 70, 50);
		
		Button button5 = new Button(shell, SWT.NONE);
		button5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("4");
			}
		});
		button5.setText("4");
		button5.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button5.setBounds(10, 290, 70, 50);
		
		Button button6 = new Button(shell, SWT.NONE);
		button6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("1");
			}
		});
		button6.setText("1");
		button6.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button6.setBounds(10, 346, 70, 50);
		
		Button button7 = new Button(shell, SWT.NONE);
		button7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("0");
			}
		});
		button7.setText("0");
		button7.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button7.setBounds(10, 402, 70, 50);
		
		Button button8 = new Button(shell, SWT.NONE);
		button8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					inputter.press("(");
				} else {
					inputter.degreeModeOn = true;
				}
			}
		});
		button8.setText("(");
		button8.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button8.setBounds(86, 66, 70, 50);
		
		Button button9 = new Button(shell, SWT.NONE);
		button9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					inputter.press("1/");
				} else {
					inputter.press("10^");
				}
			}
		});
		button9.setText("1/x");
		button9.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button9.setBounds(86, 122, 70, 50);
		
		Button button10 = new Button(shell, SWT.NONE);
		button10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					if (inputter.degreeModeOn) {
						inputter.press("sin'(");
					} else {
						inputter.press("sin(");
					}
				} else {
					if (inputter.degreeModeOn) {
						inputter.press("sin-1'(");
					} else {
						inputter.press("sin-1(");
					}
				}
			}
		});
		button10.setText("sin");
		button10.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button10.setBounds(86, 178, 70, 50);
		
		Button button11 = new Button(shell, SWT.NONE);
		button11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("8");
			}
		});
		button11.setText("8");
		button11.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button11.setBounds(86, 234, 70, 50);
		
		Button button12 = new Button(shell, SWT.NONE);
		button12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("5");
			}
		});
		button12.setText("5");
		button12.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button12.setBounds(86, 290, 70, 50);
		
		Button button13 = new Button(shell, SWT.NONE);
		button13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("2");
			}
		});
		button13.setText("2");
		button13.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button13.setBounds(86, 346, 70, 50);
		
		Button button14 = new Button(shell, SWT.NONE);
		button14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(".");
			}
		});
		button14.setText(".");
		button14.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button14.setBounds(86, 402, 70, 50);
		
		Button button15 = new Button(shell, SWT.NONE);
		button15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					inputter.press(")");
				} else {
					inputter.degreeModeOn = false;
				}
			}
		});
		button15.setText(")");
		button15.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button15.setBounds(162, 66, 70, 50);
		
		Button button16 = new Button(shell, SWT.NONE);
		button16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					inputter.press("log(");
				} else {
					inputter.press("ln(");
				}
			}
		});
		button16.setText("log");
		button16.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button16.setBounds(162, 122, 70, 50);
		
		Button button17 = new Button(shell, SWT.NONE);
		button17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					if (inputter.degreeModeOn) {
						inputter.press("cos'(");
					} else {
						inputter.press("cos(");
					}
				} else {
					if (inputter.degreeModeOn) {
						inputter.press("cos-1'(");
					} else {
						inputter.press("cos-1(");
					}
				}
			}
		});
		button17.setText("cos");
		button17.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button17.setBounds(162, 178, 70, 50);
		
		Button button18 = new Button(shell, SWT.NONE);
		button18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("9");
			}
		});
		button18.setText("9");
		button18.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button18.setBounds(162, 234, 70, 50);
		
		Button button19 = new Button(shell, SWT.NONE);
		button19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("6");
			}
		});
		button19.setText("6");
		button19.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button19.setBounds(162, 290, 70, 50);
		
		Button button20 = new Button(shell, SWT.NONE);
		button20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("3");
			}
		});
		button20.setText("3");
		button20.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button20.setBounds(162, 346, 70, 50);
		
		Button button21 = new Button(shell, SWT.NONE);
		button21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (inputter.input.inputExpression.length() > 0) {
					try {
						text.setText(Double.toString(inputter.input.evaluate()));
						inputter.input.currentIndex = 0;
						inputter.input.clear();
					} catch (InvalidExpressionException ex) {
						text.setText((ex.toString()).substring(28));
						inputter.input.currentIndex = 0;
						inputter.input.clear();
					}
				} else {
					text.setText("No input detected");
					inputter.input.currentIndex = 0;
					inputter.input.clear();
				}
			}
		});
		button21.setText("=");
		button21.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button21.setBounds(162, 402, 70, 50);
		
		Button button22 = new Button(shell, SWT.NONE);
		button22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.input.clear();
				text.setText(inputter.input.inputExpression);
			}
		});
		button22.setText("C");
		button22.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button22.setBounds(238, 66, 70, 50);
		
		Button button23 = new Button(shell, SWT.NONE);
		button23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.input.backspace();
				text.setText(inputter.input.inputExpression);
			}
		});
		button23.setText("BCK");
		button23.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button23.setBounds(238, 122, 70, 50);
		
		Button button24 = new Button(shell, SWT.NONE);
		button24.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!inputter.secondOn) {
					if (inputter.degreeModeOn) {
						inputter.press("tan'(");
					} else {
						inputter.press("tan(");
					}
				} else {
					if (inputter.degreeModeOn) {
						inputter.press("tan-1'(");
					} else {
						inputter.press("tan-1(");
					}
				}
			}
		});
		button24.setText("tan");
		button24.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button24.setBounds(238, 178, 70, 50);
		
		Button button25 = new Button(shell, SWT.NONE);
		button25.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("/");
			}
		});
		button25.setText("/");
		button25.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button25.setBounds(238, 234, 70, 50);
		
		Button button26 = new Button(shell, SWT.NONE);
		button26.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("*");
			}
		});
		button26.setText("*");
		button26.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button26.setBounds(238, 290, 70, 50);
		
		Button button27 = new Button(shell, SWT.NONE);
		button27.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("-");
			}
		});
		button27.setText("-");
		button27.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button27.setBounds(238, 346, 70, 50);
		
		Button button28 = new Button(shell, SWT.NONE);
		button28.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press("+");
			}
		});
		button28.setText("+");
		button28.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button28.setBounds(238, 402, 70, 50);
		
		Button button1 = new Button(shell, SWT.NONE);
		button1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (inputter.secondOn == false) {
					button2.setText("nroot");
					button3.setText("y^x");
					button8.setText("Deg");
					button9.setText("10^x");
					button10.setText("sin-1");
					button15.setText("Rad");
					button16.setText("ln");
					button17.setText("cos-1");
					button24.setText("tan-1");
					
					button1.setText("1st");
				} else {
					button2.setText("sqrt");
					button3.setText("x^2");
					button8.setText("(");
					button9.setText("1/x");
					button10.setText("sin");
					button15.setText(")");
					button16.setText("log");
					button17.setText("cos");
					button24.setText("tan");
					
					button1.setText("2nd");
				}
				
				inputter.secondOn = !inputter.secondOn;
			}
		});
		button1.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button1.setBounds(10, 66, 70, 50);
		button1.setText("2nd");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
