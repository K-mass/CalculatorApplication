import java.util.ArrayList;

enum InputType {
	OPERATOR,
	DIGIT,
	OPENINGBRACKET,
	CLOSINGBRACKET,
	SPACE
}

public class CalculatorLogic {
	String inputExpression = "";
	
	public void buttonPress(char value) {
		inputExpression += value;
	}
	
	public InputType type(char value) {
		if (Character.isDigit(value) || value == '.') {
			return InputType.DIGIT;
		} else if (value == '(') {
			return InputType.OPENINGBRACKET;
		} else if (value == ')') {
			return InputType.CLOSINGBRACKET;
		} else if (value == ' ') {
			return InputType.SPACE;
		} else {
			return InputType.OPERATOR;
		}
	}
	
	public double evaluate() {		
		inputExpression += " ";
		
		ArrayList<Double> values = new ArrayList<Double>(3);
		ArrayList<Character> operators = new ArrayList<Character>(1);
		ArrayList<Integer> operatorPriorities = new ArrayList<Integer>(1);
		
		String tempValue = "";
		int bracketLevel = 0;
		int maxPriority = 0;
		
		for (int i = 0; i < inputExpression.length(); i++) {
			char digitOrLetter = inputExpression.charAt(i);
			
			switch (type(digitOrLetter)) {
			case DIGIT:
				tempValue += digitOrLetter;
				
				if (type(inputExpression.charAt(i+1)) != InputType.DIGIT) {
					values.add(Double.parseDouble(tempValue));
					tempValue = "";
				}
				break;
			case OPERATOR:
				operators.add(digitOrLetter);
				
				if (digitOrLetter == '*' || digitOrLetter == '/') {
					operatorPriorities.add(bracketLevel * 3 + 1);
					
					if (bracketLevel * 3 + 1 > maxPriority) {
						maxPriority++;
					}
				} else {
					operatorPriorities.add(bracketLevel * 3);
				}
				break;
			case OPENINGBRACKET:
				bracketLevel++;
				
				if (bracketLevel * 3 > maxPriority) {
					maxPriority = bracketLevel * 3;
				}
				break;
			case CLOSINGBRACKET:
				bracketLevel--;
				break;
			case SPACE:
				break;
			}	
		}
		
		for (int i = maxPriority; i >= 0; i--) {
			boolean loopedThrough = false;
			
			while (loopedThrough == false) {
				try {
					int index = operatorPriorities.indexOf(i);
					
					double value1 = values.get(index);
					double value2 = values.get(index + 1);
					
					double simpleValue;
					
					switch (operators.get(index)) {
					case '+':
						simpleValue = value1 + value2;
						break;
					case '-':
						simpleValue = value1 - value2;
						break;
					case '*':
						simpleValue = value1 * value2;
						break;
					case '/':
						simpleValue = value1 / value2;
						break;
					default:
						simpleValue = 0;
						break;
					}
					
					values.remove(index + 1);
					operators.remove(index);
					
					values.set(index, simpleValue);
				} catch (Exception e) {
					loopedThrough = true;
				}
			}
		}
		
		return values.get(0);
	}
}

/* enum inputType {
	operator,
	digit,
	backspace
}

public class CalculatorLogic {
	float num1 = "";
	float num2 = "";
	char operator = 0;
	float answer = "";
	boolean operatorOn = false;
	boolean answerOn = false;
	boolean decimalOn = false;
	
	public boolean SixDigitCheck(float value) {
		if (String.valueOf(value).length() >= 6) {
			return true;
		}
		return false;
	}
	
	public int decimalDigits(float value) {
		return String.valueOf(value - Math.floor(value)).length();
	}
	
	public int concatenate(int value1, char value2) { //concatenate two values
		if (SixDigitCheck(value1)) {
			if (!decimalOn) {
				return value1 * 10 + value2;
			} else {
				return value1 + value2 / 10
			}
		}
	}
	
	public boolean undefinedCheck(int value) {
			if (value != 0) {
				return false;
			}
		return true;
	}
	
	public int removeChar(int value) {
		
	}
	
	public void buttonPress(inputType type, char value) { //code to be executed when any button is pressed
		switch (type) { //determine which type of button was pressed (operator (0), digit (1), backspace (2), equals (3)
		case operator: //operator is pressed
			operator = value;
			operatorOn = true;
			break;
		case digit: //digit is pressed
			if (operatorOn == false) { //determine whether to modify first or second number
				num1 = concatenate(num1, value); //concatenate the button value with num1
			} else {
				if (answerOn == true) {
					num1 = 0;
					num2 = 0;
					operator = 0;
					concatenate(num1, value); //start a new expression and reset all variables
				} else {
					num2 = concatenate(num2, value); //concatenate the button value with num2
				}
			}
			break;
		case backspace:
			if (operatorOn == false) {
				num1 = removeChar(num1);
			} else if (operatorOn == true && answerOn == false && undefinedCheck(num2) == true) {
				num1 = removeChar(num1);
			} else if (operatorOn == true && answerOn == false && undefinedCheck(num2) == false) {
				num2 = removeChar(num2);
			}
			break;
		}
	}
}
*/