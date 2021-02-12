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
	
	public void press(char inputChar) {
		input.buttonPress(inputChar);
		text.setText(input.inputExpression);
	}
	
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(333, 388);
		shell.setText("Calculator");
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		text.setBounds(10, 10, 298, 40);
		
		CalculatorApp inputter = new CalculatorApp();
		
		Button button1 = new Button(shell, SWT.NONE);
		button1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button1.getText().charAt(0));
			}
		});
		button1.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button1.setBounds(10, 66, 70, 50);
		button1.setText("(");
		
		Button button2 = new Button(shell, SWT.NONE);
		button2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button2.getText().charAt(0));
			}
		});
		button2.setText("7");
		button2.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button2.setBounds(10, 122, 70, 50);
		
		Button button3 = new Button(shell, SWT.NONE);
		button3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button3.getText().charAt(0));
			}
		});
		button3.setText("4");
		button3.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button3.setBounds(10, 178, 70, 50);
		
		Button button4 = new Button(shell, SWT.NONE);
		button4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button4.getText().charAt(0));
			}
		});
		button4.setText("1");
		button4.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button4.setBounds(10, 234, 70, 50);
		
		Button button5 = new Button(shell, SWT.NONE);
		button5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button5.getText().charAt(0));
			}
		});
		button5.setText("0");
		button5.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button5.setBounds(10, 290, 70, 50);
		
		Button button6 = new Button(shell, SWT.NONE);
		button6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button6.getText().charAt(0));
			}
		});
		button6.setText(")");
		button6.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button6.setBounds(86, 66, 70, 50);
		
		Button button7 = new Button(shell, SWT.NONE);
		button7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button7.getText().charAt(0));
			}
		});
		button7.setText("8");
		button7.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button7.setBounds(86, 122, 70, 50);
		
		Button button8 = new Button(shell, SWT.NONE);
		button8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button8.getText().charAt(0));
			}
		});
		button8.setText("5");
		button8.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button8.setBounds(86, 178, 70, 50);
		
		Button button9 = new Button(shell, SWT.NONE);
		button9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button9.getText().charAt(0));
			}
		});
		button9.setText("2");
		button9.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button9.setBounds(86, 234, 70, 50);
		
		Button button10 = new Button(shell, SWT.NONE);
		button10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button10.getText().charAt(0));
			}
		});
		button10.setText(".");
		button10.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button10.setBounds(86, 290, 70, 50);
		
		Button button11 = new Button(shell, SWT.NONE);
		button11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.input.clear();
				text.setText(inputter.input.inputExpression);
			}
		});
		button11.setText("C");
		button11.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button11.setBounds(162, 66, 70, 50);
		
		Button button12 = new Button(shell, SWT.NONE);
		button12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button12.getText().charAt(0));
			}
		});
		button12.setText("9");
		button12.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button12.setBounds(162, 122, 70, 50);
		
		Button button13 = new Button(shell, SWT.NONE);
		button13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button13.getText().charAt(0));
			}
		});
		button13.setText("6");
		button13.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button13.setBounds(162, 178, 70, 50);
		
		Button button14 = new Button(shell, SWT.NONE);
		button14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button14.getText().charAt(0));
			}
		});
		button14.setText("3");
		button14.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button14.setBounds(162, 234, 70, 50);
		
		Button button15 = new Button(shell, SWT.NONE);
		button15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				text.setText(inputter.input.evaluate());
				inputter.input.clear();
			}
		});
		button15.setText("=");
		button15.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button15.setBounds(162, 290, 70, 50);
		
		Button button16 = new Button(shell, SWT.NONE);
		button16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.input.backspace();
				text.setText(inputter.input.inputExpression);
			}
		});
		button16.setText("BCK");
		button16.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button16.setBounds(238, 66, 70, 50);
		
		Button button17 = new Button(shell, SWT.NONE);
		button17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button17.getText().charAt(0));
			}
		});
		button17.setText("/");
		button17.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button17.setBounds(238, 122, 70, 50);
		
		Button button18 = new Button(shell, SWT.NONE);
		button18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button18.getText().charAt(0));
			}
		});
		button18.setText("*");
		button18.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button18.setBounds(238, 178, 70, 50);
		
		Button button19 = new Button(shell, SWT.NONE);
		button19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button19.getText().charAt(0));
			}
		});
		button19.setText("-");
		button19.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button19.setBounds(238, 234, 70, 50);
		
		Button button20 = new Button(shell, SWT.NONE);
		button20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				inputter.press(button20.getText().charAt(0));
			}
		});
		button20.setText("+");
		button20.setFont(SWTResourceManager.getFont("Arial", 18, SWT.BOLD));
		button20.setBounds(238, 290, 70, 50);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
