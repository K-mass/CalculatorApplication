import java.math.BigDecimal;
import java.math.RoundingMode;

enum InputType { //types of inputs
	OPERATOR,
	DIGIT,
	DECIMAL,
	OPENINGBRACKET,
	CLOSINGBRACKET,
	OPENINGABS,
	CLOSINGABS,
	MODIFIER
}

public class CalculatorLogic {
	
	String inputExpression = ""; //string that stores the expression that the user inputs
	int currentIndex = 0;
	boolean absOn = false;
	
	public void buttonPress(String value) {
		inputExpression += value; //concatenate the digit with the expression
	}
	
	public void backspace() {
		if (inputExpression.length() > 0) {
			inputExpression = inputExpression.substring(0, inputExpression.length()-1);
		}
	}
	
	public void clear() {
		inputExpression = "";
		currentIndex = 0;
		absOn = false;
	}
	
	private InputType type(char value) { //determine the type of character
		if (Character.isDigit(value)) {
			return InputType.DIGIT;
		} else if (value == '.') {
			return InputType.DECIMAL;
		} else if (value == '(') {
			return InputType.OPENINGBRACKET;
		} else if (value == ')') {
			return InputType.CLOSINGBRACKET;
		} else if (absOn == false && value == '|') {
			return InputType.OPENINGABS;
		} else if (absOn == true && value == '|') {
			return InputType.CLOSINGABS;
		} else if (value == '-' ||  value == 's' || value == '+' || value == 'l' || value == 'c' || value == 't'){
			return InputType.MODIFIER;
		} else {
			return InputType.OPERATOR;
		}
	}
	
	public double evaluate() { return evaluateExpression(); }
	
	private void operatorCheck() {
		if (currentIndex < inputExpression.length()) {
			if (type(inputExpression.charAt(currentIndex)) == InputType.OPERATOR) {
				throw new InvalidExpressionException("Improper syntax2");
			}
		} else {
			throw new InvalidExpressionException("Improper syntax3");
		}
	}
	
	/*
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
				
				if (type(inputExpression.charAt(i+1)) != InputType.DIGIT && type(inputExpression.charAt(i+1)) != InputType.DECIMAL) { //if the next value isn't a digit, add the number to the arrayList
					values.add(Double.parseDouble(tempValue));
					tempValue = "";
				}
				break;
			case DECIMAL:
				tempValue += digitOrLetter; //add the digit to the temporary value
				
				if (type(inputExpression.charAt(i+1)) != InputType.DIGIT) { //if the next value isn't a digit, add the number to the arrayList
					return "Improper syntax";
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
			
			int index = 0;
			
			while (loopedThrough == false) { //iterate through all the operators of a certain priority
				try {
					for (; operatorPriorities.get(index) != i; index++); //determine the index of the operator being processed
								
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
					operatorPriorities.remove(index);
					
					values.set(index, simpleValue);
				} catch (Exception e) {
					loopedThrough = true;
				}
			}
		}
		
		return Double.toString(values.get(0));
	}
	*/

/*** Recursive algorithm for evaluating an expression ***/
/*
 * This algorithm uses recursive descent
 * 
 * We divide an string representing a mathematical expression into 3 different components, ranked in order of priority.
 * 1) Term: A component representing the highest priority of operation (brackets, exponent, sin, cos, tan etc)
 * 		Term = Number|+Term|-Term|Term^Term|(Expression)
 * 2) Factor: Consists of products or quotients of Terms or Factors
 * 		Factor = Factor|Term *|/ Factor|Term
 * 3) Expression: Consists of the sum or difference of Factors or Expressions
 * 		Expression = Expression|Factor +|- Expression|Factor
 * 
 * Based on these 3 tiers of components, we can create a recursive algorithm involves 3 functions.
 * One function for evaluating a Term, one for evaluating a Factor, and one for evaluating an Expression.
 * These functions will recursively call each other in order to first evaluate components of higher priority.
 * The evaluate Expression function will call the evaluate Factor function, which will call the evaluate Term function, which may in turn call the evaluate Expression function again.
 * This recursive calling of functions will continue until the base case is reached, which is simply the evaluation of a single integer or exponent.
 * Then the results are propagated up the stack of recursive function calls to build up the overall solution.
 * 
 * 
 */
	// First function in the recursive algorithm. This function gets called first, starting the recursive chain, and will in turn call the second.
	// Calling this function first ensures it in at the bottom of the recursive function stack, so that the addition/subtraction will be performed last
	private double evaluateExpression() {
		// evaluate the first Factor
		double result = evaluateFactor();
		
		// Lowest priority operations, addition and subtraction
		while (currentIndex < inputExpression.length()) {
			// accumulate the sum/difference of Factors while there are still +/- operators
			if (inputExpression.charAt(currentIndex) == '+') {
				currentIndex++;
				
				operatorCheck();
				
				result = result + evaluateFactor();
			}
			else if (inputExpression.charAt(currentIndex) == '-') {
				currentIndex++;
				
				operatorCheck();
				
				result = result - evaluateFactor();
			}
			else {
				// return the result when there are no more operators
				if (String.valueOf(result) == "Infinity") {
					throw new InvalidExpressionException("Result too large");
				} else if (String.valueOf(result) == "NaN") {
					return result;
				} else {
					return BigDecimal.valueOf(result).setScale(15, RoundingMode.HALF_UP).doubleValue();
				}
			}
		}
		
		if (String.valueOf(result) == "Infinity") {
			throw new InvalidExpressionException("Result too large");
		} else if (String.valueOf(result) == "NaN") {
			return result;
		} else {
			return BigDecimal.valueOf(result).setScale(15, RoundingMode.HALF_UP).doubleValue();
		}
	}
	
	// Second function in the recursive algorithm. This function gets called by the first function and will in turn call the third function.
	// Calling this function second in the recursive chain puts it above the first function in the stack, ensuring the multiplication/division will be performed before subtraction/division in the first function
	private double evaluateFactor() {
		double result = evaluateTerm();
		
		// Medium priority operations, multiplication and division
		while (currentIndex < inputExpression.length()) {
			// accumulate the product/quotient of Terms while there are still * / operators
			if (inputExpression.charAt(currentIndex) == '*') {
				currentIndex++;
				
				operatorCheck();
				
				result = result * evaluateTerm();
			}
			else if (inputExpression.charAt(currentIndex) == '/') {
				currentIndex++;
				
				operatorCheck();
				
				if (inputExpression.charAt(currentIndex) != '0') {
					result = result / evaluateTerm();
				} else {
					throw new InvalidExpressionException("Cannot divide by zero");
				}
			}
			else {
				//return the result when there are no more operators
				return result;
			}
		}
		
		return result;
	}
	
	// Third function in the recursive algorithm. This function gets called by the second function and will either call itself or the first function (starting the evaluation of an expression within the expression)
	// Calling this function last in the recursive chain puts it above the other functions in the stack, ensuring the highest priority operations are performed first
	// This function also handles the base case, where the part of the expression is simply a number or signed number
	private double evaluateTerm() {
		double result;
		
		// positive number
		if (inputExpression.charAt(currentIndex) == '+') {
			currentIndex++;
			
			operatorCheck();
			
			result = evaluateTerm();
		}
		// negative number
		else if (inputExpression.charAt(currentIndex) == '-') {
			currentIndex++;
			
			operatorCheck();
			
			result = -evaluateTerm();
		}
		else if (currentIndex + 3 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 3).equals("sin")) {
			currentIndex += 3;
			
			if (currentIndex + 2 < inputExpression.length() && (inputExpression.substring(currentIndex, currentIndex + 2).equals("-1"))) {
				currentIndex += 2;
				
				if (currentIndex + 1 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 1).equals("'")) {
					currentIndex += 1;
					
					operatorCheck();
					
					result = Math.toDegrees(Math.asin((evaluateTerm())));
				} else {
					operatorCheck();
					
					result = Math.asin(evaluateTerm());
				}
			} else {
				if (currentIndex + 1 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 1).equals("'")) {
					currentIndex += 1;
					
					operatorCheck();
					
					result = Math.sin(Math.toRadians(evaluateTerm()));
				} else {
					operatorCheck();
					
					result = Math.sin(evaluateTerm());
				}
			}
		}
		else if (currentIndex + 3 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 3).equals("cos")) {
			currentIndex += 3;
			
			if (currentIndex + 2 < inputExpression.length() && (inputExpression.substring(currentIndex, currentIndex + 2).equals("-1"))) {
				currentIndex += 2;
				
				if (currentIndex + 1 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 1).equals("'")) {
					currentIndex += 1;
					
					operatorCheck();
					
					result = Math.toDegrees(Math.acos((evaluateTerm())));
				} else {
					operatorCheck();
					
					result = Math.acos(evaluateTerm());
				}
			} else {
				if (currentIndex + 1 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 1).equals("'")) {
					currentIndex += 1;
					
					operatorCheck();
					
					result = Math.cos(Math.toRadians(evaluateTerm()));
				} else {
					operatorCheck();
					
					result = Math.cos(evaluateTerm());
				}
			}
		}
		else if (currentIndex + 3 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 3).equals("tan")) {
			currentIndex += 3;
			
			if (currentIndex + 2 < inputExpression.length() && (inputExpression.substring(currentIndex, currentIndex + 2).equals("-1"))) {
				currentIndex += 2;
				
				if (currentIndex + 1 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 1).equals("'")) {
					currentIndex += 1;
					
					operatorCheck();
					
					result = Math.toDegrees(Math.atan((evaluateTerm())));
				} else {
					operatorCheck();
					
					result = Math.atan(evaluateTerm());
				}
			} else {
				if (currentIndex + 1 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 1).equals("'")) {
					currentIndex += 1;
					
					operatorCheck();
					
					result = Math.tan(Math.toRadians(evaluateTerm()));
				} else {
					operatorCheck();
					
					result = Math.tan(evaluateTerm());
				}
			}
		}
		else if (currentIndex + 3 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 3).equals("log")) {
			currentIndex += 3;
			
			operatorCheck();
			
			result = Math.log10(evaluateTerm());
		}
		else if (currentIndex + 2 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 2).equals("ln")) {
			currentIndex += 2;
			
			operatorCheck();
			
			result = Math.log(evaluateTerm());
		}
		else if (currentIndex + 4 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 4).equals("sqrt")) {
			currentIndex += 4;
			
			operatorCheck();
			
			result = Math.sqrt(evaluateTerm());
		}
		// opening bracket, starts the evaluation of a new expression (calls the first function again)
		else if (type(inputExpression.charAt(currentIndex)) == InputType.OPENINGBRACKET || type(inputExpression.charAt(currentIndex)) == InputType.OPENINGABS) {
			InputType startingBrckt = type(inputExpression.charAt(currentIndex));
			
			currentIndex++;
			
			operatorCheck();
			
			boolean brcktDeclaredInAbs = false;
			
			if (startingBrckt == InputType.OPENINGBRACKET) {
				if (absOn) {
					brcktDeclaredInAbs = true;
				}
				
				result = evaluateExpression();
			} else {
				absOn = true;
				
				result = Math.abs(evaluateExpression());
			}
			
			// handle the closing bracket and throw exception if no closing bracket found
			if (currentIndex < inputExpression.length() &&
				(startingBrckt == InputType.OPENINGBRACKET && type(inputExpression.charAt(currentIndex)) == InputType.CLOSINGBRACKET
				|| startingBrckt == InputType.OPENINGABS && type(inputExpression.charAt(currentIndex)) == InputType.CLOSINGABS)) {
				
				currentIndex++;
				
				if (startingBrckt == InputType.OPENINGBRACKET) {
					if (brcktDeclaredInAbs != absOn) {
						brcktDeclaredInAbs = false;
						throw new InvalidExpressionException("Invalid brackets");
					}
					
					brcktDeclaredInAbs = false;
				} else {
					absOn = false;
				}
				
				System.out.println("abs");
				
				if (currentIndex < inputExpression.length()) {
					if (type(inputExpression.charAt(currentIndex)) == InputType.OPENINGBRACKET
					|| type(inputExpression.charAt(currentIndex)) == InputType.OPENINGABS
					|| type(inputExpression.charAt(currentIndex)) == InputType.DIGIT
					|| type(inputExpression.charAt(currentIndex)) == InputType.MODIFIER
					&& !(inputExpression.charAt(currentIndex) == '-' || inputExpression.charAt(currentIndex) == '+')) {
						inputExpression = inputExpression.substring(0, currentIndex) + "*" + inputExpression.substring(currentIndex);
					}
				}
				
				return result;
			}
			else {
				throw new InvalidExpressionException("No closing bracket found");
			}
		}
		// number
		else if (type(inputExpression.charAt(currentIndex)) == InputType.DIGIT || type(inputExpression.charAt(currentIndex)) == InputType.DECIMAL) {
			int startIndex = currentIndex;
			
			// get the number by using the starting and ending index of the number in the expression string
			try {
				while (type(inputExpression.charAt(currentIndex)) == InputType.DIGIT || type(inputExpression.charAt(currentIndex)) == InputType.DECIMAL) {
					if (currentIndex + 1 < inputExpression.length()) {
						currentIndex++;
					} else {
						currentIndex++;
						
						result = Double.parseDouble(inputExpression.substring(startIndex, currentIndex));
						return result;
					}
				}
				result = Double.parseDouble(inputExpression.substring(startIndex, currentIndex));
			} catch (NumberFormatException e) {
				throw new InvalidExpressionException("Improper number format");
			}
			if (type(inputExpression.charAt(currentIndex)) == InputType.OPENINGBRACKET
			|| type(inputExpression.charAt(currentIndex)) == InputType.OPENINGABS
			|| type(inputExpression.charAt(currentIndex)) == InputType.DIGIT
			|| type(inputExpression.charAt(currentIndex)) == InputType.MODIFIER
			&& !(inputExpression.charAt(currentIndex) == '-' || inputExpression.charAt(currentIndex) == '+')) {
				inputExpression = inputExpression.substring(0, currentIndex) + "*" + inputExpression.substring(currentIndex);
			}
		}
		else {
			System.out.println("huge");
			
			throw new InvalidExpressionException("Improper syntax1");
		}
		
		// handle exponent case
		if (currentIndex + 1 < inputExpression.length() && inputExpression.charAt(currentIndex) == '^') {
			currentIndex++;
			
			operatorCheck();
			
			result = Math.pow(result, evaluateTerm());
		}
		else if (currentIndex + 4 < inputExpression.length() && inputExpression.substring(currentIndex, currentIndex + 4).equals("root")) {
			currentIndex += 4;
			
			operatorCheck();
			
			result = Math.pow(evaluateTerm(), 1.0/result);
		}
		
		return result;
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