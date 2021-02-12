import java.util.ArrayList;

enum InputType { //types of inputs
	OPERATOR,
	DIGIT,
	OPENINGBRACKET,
	CLOSINGBRACKET,
	SPACE
}

public class CalculatorLogic {
	String inputExpression = ""; //string that stores the expression that the user inputs
	
	public void buttonPress(char value) {
		inputExpression += value; //concatenate the digit with the expression
	}
	
	public void backspace() {
		if (inputExpression.length() > 0) {
			inputExpression = inputExpression.substring(0, inputExpression.length()-1);
		}
	}
	
	public void clear() {
		inputExpression = "";
	}
	
	public InputType type(char value) { //determine the type of character
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
	
	public String evaluate() {		
		inputExpression = "0+" + inputExpression + " "; //add a space to the end of the input expression to prevent an outofbounds error
		
		ArrayList<Double> values = new ArrayList<Double>(3); //store all the numbers
		ArrayList<Character> operators = new ArrayList<Character>(1); //store the operators
		ArrayList<Integer> operatorPriorities = new ArrayList<Integer>(1); //parallel arrayList that stores the priority of each operator (order of operations)
		
		String tempValue = ""; //temporary value used for concatenating the digits of numbers together
		int bracketLevel = 0; //amount of brackets used for determining priorities (order of operations)
		int maxPriority = 0; //highest priority in the whole expression
		
		for (int i = 0; i < inputExpression.length(); i++) { //loop through the whole expression
			char digitOrLetter = inputExpression.charAt(i); //extract the individual character
			
			switch (type(digitOrLetter)) {
			case DIGIT:
				tempValue += digitOrLetter; //add the digit to the temporary value
				
				if (type(inputExpression.charAt(i+1)) != InputType.DIGIT) { //if the next value isn't a digit, add the number to the arrayList
					values.add(Double.parseDouble(tempValue));
					tempValue = "";
				}
				break;
			case OPERATOR:
				if (type(inputExpression.charAt(i-1)) != InputType.DIGIT) {
					return "Improper syntax";
				}
				
				operators.add(digitOrLetter); //add the operator to the arrayList
				
				if (digitOrLetter == '*' || digitOrLetter == '/') { //add the priority of the operator to the parallel arrayList
					operatorPriorities.add(bracketLevel * 3 + 1);
					
					if (bracketLevel * 3 + 1 > maxPriority) {
						maxPriority++;
					}
				} else if (digitOrLetter == '^'){
					operatorPriorities.add(bracketLevel * 3 + 2);
					
					if (bracketLevel * 3 + 1 > maxPriority) {
						maxPriority += 2;
					}
				} else {
					operatorPriorities.add(bracketLevel * 3);
				}
				break;
			case OPENINGBRACKET:
				if (type(inputExpression.charAt(i-1)) != InputType.OPERATOR) {
					operators.add('*');
					
					operatorPriorities.add(bracketLevel * 3 + 1);
					
					if (bracketLevel * 3 + 1 > maxPriority) {
						maxPriority++;
					}
				}
				
				bracketLevel++;
				
				if (bracketLevel * 3 > maxPriority) {
					maxPriority = bracketLevel * 3;
				}
				break;
			case CLOSINGBRACKET:
				if (type(inputExpression.charAt(i-1)) != InputType.DIGIT) {
					return "Improper syntax";
				}
				
				bracketLevel--;
				
				if (type(inputExpression.charAt(i+1)) == InputType.DIGIT) {
					operators.add('*');
					
					operatorPriorities.add(bracketLevel * 3 + 1);
					
					if (bracketLevel * 3 + 1 > maxPriority) {
						maxPriority++;
					}
					
					System.out.println("check");
				}
				
				break;
			case SPACE:
				break;
			}	
		}
		
		for (int i = maxPriority; i >= 0; i--) { //iterate through the expression in order of priority
			boolean loopedThrough = false;
			
			while (loopedThrough == false) { //iterate through all the operators of a certain priority
				try {
					int index = operatorPriorities.indexOf(i); //determine the index of the operator being processed
					
					double value1 = values.get(index);
					double value2 = values.get(index + 1);
					
					double simpleValue; //temporary value to store new value
					
					switch (operators.get(index)) { //determine type of operator
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
						if (value2 != 0) {
							simpleValue = value1 / value2;
						} else {
							return "Cannot divide by 0";
						}
						break;
					case '^':
						simpleValue = Math.pow(value1, value2);
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
		
		return Double.toString(values.get(0));
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