package fracCalc;
import java.util.*;
public class FracCalc {

	public static void main(String[] args) {
		System.out.println("Welcome to FracCalc!");
        System.out.println("- Type operands in form of 1_1/2, 3/4, or 7.");
        System.out.println("- Space operator between operands like this: 6 + 7_81/9");
        System.out.println("Get Calculating!");
		Scanner input = new Scanner(System.in);
        String answer = " ";
        while(!answer.equals("quit")) {//allows for continuous inputs from user
        	answer = input.nextLine();
        	if (answer.equals("quit")) {//ends program when user types 'quit'
        		break;
        	}else {
        		String output = produceAnswer(answer);
                System.out.println(output);
        	}
        }
        input.close();//saves resources
    }

    public static String produceAnswer(String input) { 
    	//String output = produceAnswerCP1(input);
    	//String output = produceAnswerCP2(input);
    	//String output = produceAnswerCP3(input);
    	String output = produceAnswerFinal(input);
    	return output;
    }
    
    public static String produceAnswerFinal(String input) {
    	int space1Index = input.indexOf(" ");
    	int term2Index = space1Index + 3;
    	int endIndex = input.length();
    	String termOne = input.substring(0, space1Index);
    	String termTwo = input.substring(term2Index, endIndex);
    	String operator = input.substring(space1Index+1,space1Index+2);

    	//parse first term
    	String t1Whole = "";
    	String t1Num = "";
    	String t1Den = "";
    	int end1Index = termOne.length();
    	int t1_Index = termOne.indexOf("_");
    	int t1SlashIndex = termOne.indexOf("/");
    	if(t1_Index == -1 && t1SlashIndex == -1) {//whole number
    		t1Whole = termOne;
    		t1Num = "0";
    		t1Den = "1";
    	}else if(t1_Index == -1) {//fraction
    		t1Num = termOne.substring(t1_Index+1, t1SlashIndex);
        	t1Den = termOne.substring((t1SlashIndex+1), end1Index);
        	t1Whole = "00";
    	}else {//mixed number
    		t1Whole = termOne.substring(0, t1_Index);
        	t1Num = termOne.substring(t1_Index+1, t1SlashIndex);
        	t1Den = termOne.substring((t1SlashIndex+1), end1Index);
        	if (t1Whole.indexOf("-") != -1) { //distributes negative to the fraction 
        		t1Num = "-"+t1Num;
        	}
    	}
    	//parse second term
    	String t2Whole = "";
    	String t2Num = "";
    	String t2Den = "";
    	int end2Index = termTwo.length();
    	int t2_Index = termTwo.indexOf("_");
    	int t2SlashIndex = termTwo.indexOf("/");
    	if(t2_Index == -1 && t2SlashIndex == -1) { //whole number
    		t2Whole = termTwo;
    		t2Num = "0";
    		t2Den = "1";
    	}else if(t2_Index == -1) {//a fraction
    		t2Num = termTwo.substring(t2_Index+1, t2SlashIndex);
        	t2Den = termTwo.substring((t2SlashIndex+1), end2Index);
        	t2Whole = "00";
    	}else {//mixed number
    		t2Whole = termTwo.substring(0, t2_Index);
        	t2Num = termTwo.substring(t2_Index+1, t2SlashIndex);
        	t2Den = termTwo.substring((t2SlashIndex+1), end2Index);
        	if (t2Whole.indexOf("-") != -1) { 
        		t2Num = "-"+t2Num;
        	}
    	}
    	//operators
    	String answer = "";
    	int nFinal;
    	int dFinal;
    	int numerator1 = Integer.valueOf(t1Num);
		int numerator2 = Integer.valueOf(t2Num);
		int denominator1 = Integer.valueOf(t1Den);
		int denominator2 = Integer.valueOf(t2Den);
		int whole1 = Integer.valueOf(t1Whole);
		int whole2 = Integer.valueOf(t2Whole);
    	if (operator.equals("+")) {//addition operator
    		if(numerator1 == 0 && numerator2 == 0) { //whole number added to whole number
    			int value = whole1 + whole2;
    			answer = ""+value;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) { //fraction added to fraction
    			nFinal = numerator1*denominator2 + numerator2*denominator1;
    			dFinal = denominator1*denominator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction added to mixed fraction
    				nFinal = numerator1*denominator2 + numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal + dFinal*whole2;
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction added to a whole number
    				nFinal = numerator1 + denominator1*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")){
    			if(numerator1 != 0) { //mixed fraction added to a fraction
    				nFinal = numerator1*denominator2 + numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal + dFinal*whole1;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number added to a fraction
    				nFinal = numerator2 + denominator2*whole1;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { 
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction added to whole number
    				int wholeSum = whole1 + whole2;
    				nFinal = denominator1*wholeSum + numerator1;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number added to mixed fraction
    				int wholeSum = whole1 + whole2;
    				nFinal = denominator2*wholeSum + numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction added to mixed fraction
    				int wholeSum = whole1 + whole2;
    				nFinal = (denominator1*denominator2)*wholeSum + numerator1*denominator2 + numerator2*denominator1;
    				dFinal = denominator1*denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}else if(operator.equals("-")) {//subtraction operator
    		if(numerator1 == 0 && numerator2 == 0) { //whole number minus whole number
    			int value = whole1 - whole2;
    			answer = ""+value;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) { //fraction minus fraction
    			nFinal = numerator1*denominator2 - numerator2*denominator1;
    			dFinal = denominator1*denominator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction minus mixed fraction
    				nFinal = numerator1*denominator2 - numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal - dFinal*whole2;
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction minus whole number
    				nFinal = numerator1 - denominator1*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")){
    			if(numerator1 != 0) { //mixed fraction minus fraction
    				nFinal = numerator1*denominator2 - numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal + dFinal*whole1;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number minus fraction
    				nFinal = denominator2*whole1 - numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { 
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction minus whole number 
    				int wholeSum = whole1 - whole2;
    				nFinal = denominator1*wholeSum + numerator1;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number minus mixed fraction
    				int wholeSum = whole1 - whole2;
    				nFinal = denominator2*wholeSum - numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction minus mixed fraction
    				nFinal = (whole1*denominator1+numerator1)*denominator2-(whole2*denominator2+numerator2)*denominator1;
    				dFinal = denominator1*denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}else if(operator.equals("/")) {//division operator
    		if(numerator1 == 0 && numerator2 == 0) { //whole number / whole number
    			answer = whole1+"/"+whole2;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) { //fraction / fraction
    			nFinal = numerator1*denominator2;
    			dFinal = denominator1*numerator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction / mixed fraction 
    				nFinal = numerator1*denominator2;
        			dFinal = denominator1*(whole2*denominator2+numerator2);
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction / whole number
    				nFinal = numerator1;
    				dFinal = denominator1*whole2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")) {
    			if(numerator1 != 0) { //mixed fraction / fraction
    				nFinal = denominator2*(whole1*denominator1+numerator1);
        			dFinal = numerator2*denominator1;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number / fraction
    				nFinal = denominator2*whole1;
    				dFinal = numerator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { //mixed fraction / mixed fraction
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction / whole number 
    				nFinal = whole1*denominator1*denominator2;
    				dFinal = denominator1*numerator2;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number / mixed fraction
    				nFinal = whole1*denominator2;
    				dFinal = denominator2*whole2+numerator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction / mixed fraction
    				nFinal = (whole1*denominator1+numerator1)*(denominator2);
    				dFinal = (whole2*denominator2+numerator2)*(denominator1);
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}else if(operator.equals("*")) {//multiplication operator
    		if(numerator1 == 0 && numerator2 == 0) {
    			answer = ""+whole1*whole2;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) {
    			nFinal = numerator1*numerator2;
    			dFinal = denominator1*denominator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction * mixed fraction 
    				nFinal = numerator1*numerator2*denominator2;
        			dFinal = denominator1*denominator2;
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction * whole number
    				nFinal = numerator1*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")) {
    			if(numerator1 != 0) { //mixed fraction * fraction
    				nFinal = numerator1*whole2*numerator2;
        			dFinal = denominator1*denominator2;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number * fraction
    				nFinal = whole1*numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { 
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction * whole number 
    				nFinal = (whole1*denominator1+numerator1)*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number * mixed fraction
    				nFinal = whole1*(whole2*denominator2+numerator2);
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction / mixed fraction
    				nFinal = (whole1*denominator1+numerator1)*(whole2*denominator2+numerator2);
    				dFinal = denominator1*denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}
    	//negatives and simplification of improper fractions
    	if(answer.indexOf("/") != -1) {
    		int indexSlash = answer.indexOf("/");
    		int answerEnd = answer.length();
    		String num = answer.substring(0, indexSlash);
    		String den = answer.substring(indexSlash+1, answerEnd);
    		int numInt = Integer.valueOf(num);
    		int denInt = Integer.valueOf(den);
    		int whole;
    		if(denInt < 0) {//transfers negative to numerator
    			numInt = numInt*(-1);
    			denInt = Math.abs(denInt);
    		}
    		//simplification
    		if(numInt == denInt) {
    			answer = 1+"";
    		}else if(Math.abs(numInt) < denInt) { //simplify a fraction
    			for(int i = 2; i<=denInt && i<=Math.abs(numInt); i++) {
    				while(Math.abs(numInt)%i == 0 && denInt%i==0) {
    					numInt = numInt/i;
    	    			denInt = denInt/i;
    				}
    			}
    			answer = numInt + "/" + denInt;
    			if (numInt == 0) {
    				answer = "0";
    			}
    		}else if(Math.abs(numInt) > denInt) { //simplify an improper fraction
    			whole = numInt/denInt;
    			numInt = numInt%denInt;
    			numInt = Math.abs(numInt);
    			for(int i = 2; i<=denInt && i<=numInt; i++) {
    				while(numInt%i == 0 && denInt%i==0) {
    					numInt = numInt/i;
    	    			denInt = denInt/i;
    				}
    				
    			}
    			answer = whole + "_" + numInt + "/" + denInt;
    			if (numInt == 0) {
    				answer = ""+whole;
    			}
    		}
    	}
    	return answer;
    }

    //CHECKPOINTS METHODS (not called for final method)
    public static String produceAnswerCP1(String input) {
    	int spaceIndex = input.indexOf(" ");
    	String termOne = input.substring(0, spaceIndex);
    	int term2Index = spaceIndex + 3;
    	int endIndex = input.length();
    	String termTwo = input.substring(term2Index, endIndex);
    	return termTwo;
    }
    public static String produceAnswerCP2(String input) {
    	int space1Index = input.indexOf(" ");
    	int term2Index = space1Index + 3;
    	int endIndex = input.length();
    	String termOne = input.substring(0, space1Index);
    	String termTwo = input.substring(term2Index, endIndex);
    	String operator = input.substring(space1Index+1);
    	//parse the first term
    	String t1Whole = "";
    	String t1Num = "";
    	String t1Den = "";
    	String output1 = "";
    	int end1Index = termOne.length();
    	int t1_Index = termOne.indexOf("_");
    	int t1SlashIndex = termOne.indexOf("/");
    	if(t1_Index == -1 && t1SlashIndex == -1) {
    		t1Whole = termOne;
    		output1 = ("whole:" + t1Whole + " numerator:0" + " denominator:1");
    	}else if(t1_Index == -1) {
    		t1Num = termOne.substring(t1_Index+1, t1SlashIndex);
        	t1Den = termOne.substring((t1SlashIndex+1), end1Index);
        	output1 = ("whole:0" + " numerator:" + t1Num + " denominator:" + t1Den);
    	}else {
    		t1Whole = termOne.substring(0, t1_Index);
        	t1Num = termOne.substring(t1_Index+1, t1SlashIndex);
        	t1Den = termOne.substring((t1SlashIndex+1), end1Index);
        	output1 = ("whole:" + t1Whole + " numerator:" + t1Num + " denominator:" + t1Den);
    	}
    	//parse the second term
    	String t2Whole = "";
    	String t2Num = "";
    	String t2Den = "";
    	String output2 = "";
    	int end2Index = termTwo.length();
    	int t2_Index = termTwo.indexOf("_");
    	int t2SlashIndex = termTwo.indexOf("/");
    	if(t2_Index == -1 && t2SlashIndex == -1) {
    		t2Whole = termTwo;
    		output2 = ("whole:" + t2Whole + " numerator:0" + " denominator:1");
    	}else if(t2_Index == -1) {
    		t2Num = termTwo.substring(t2_Index+1, t2SlashIndex);
        	t2Den = termTwo.substring((t2SlashIndex+1), end2Index);
        	output2 = ("whole:0" + " numerator:" + t2Num + " denominator:" + t2Den);
    	}else {
    		t2Whole = termTwo.substring(0, t2_Index);
        	t2Num = termTwo.substring(t2_Index+1, t2SlashIndex);
        	t2Den = termTwo.substring((t2SlashIndex+1), end2Index);
        	output2 = ("whole:" + t2Whole + " numerator:" + t2Num + " denominator:" + t2Den);
    	}
    	return output2;
    }
    public static String produceAnswerCP3(String input) {
    	int space1Index = input.indexOf(" ");
    	int term2Index = space1Index + 3;
    	int endIndex = input.length();
    	String termOne = input.substring(0, space1Index);
    	String termTwo = input.substring(term2Index, endIndex);
    	String operator = input.substring(space1Index+1,space1Index+2);

    	//parse first term
    	String t1Whole = "";
    	String t1Num = "";
    	String t1Den = "";
    	int end1Index = termOne.length();
    	int t1_Index = termOne.indexOf("_");
    	int t1SlashIndex = termOne.indexOf("/");
    	if(t1_Index == -1 && t1SlashIndex == -1) {//whole number
    		t1Whole = termOne;
    		t1Num = "0";
    		t1Den = "1";
    	}else if(t1_Index == -1) {//fraction
    		t1Num = termOne.substring(t1_Index+1, t1SlashIndex);
        	t1Den = termOne.substring((t1SlashIndex+1), end1Index);
        	t1Whole = "00";
    	}else {//mixed number
    		t1Whole = termOne.substring(0, t1_Index);
        	t1Num = termOne.substring(t1_Index+1, t1SlashIndex);
        	t1Den = termOne.substring((t1SlashIndex+1), end1Index);
        	if (t1Whole.indexOf("-") != -1) { //distributes negative to the fraction 
        		t1Num = "-"+t1Num;
        	}
    	}
    	//parse second term
    	String t2Whole = "";
    	String t2Num = "";
    	String t2Den = "";
    	int end2Index = termTwo.length();
    	int t2_Index = termTwo.indexOf("_");
    	int t2SlashIndex = termTwo.indexOf("/");
    	if(t2_Index == -1 && t2SlashIndex == -1) { //whole number
    		t2Whole = termTwo;
    		t2Num = "0";
    		t2Den = "1";
    	}else if(t2_Index == -1) {//a fraction
    		t2Num = termTwo.substring(t2_Index+1, t2SlashIndex);
        	t2Den = termTwo.substring((t2SlashIndex+1), end2Index);
        	t2Whole = "00";
    	}else {//mixed number
    		t2Whole = termTwo.substring(0, t2_Index);
        	t2Num = termTwo.substring(t2_Index+1, t2SlashIndex);
        	t2Den = termTwo.substring((t2SlashIndex+1), end2Index);
        	if (t2Whole.indexOf("-") != -1) { 
        		t2Num = "-"+t2Num;
        	}
    	}
    	//operators
    	String answer = "";
    	int nFinal;
    	int dFinal;
    	int numerator1 = Integer.valueOf(t1Num);
		int numerator2 = Integer.valueOf(t2Num);
		int denominator1 = Integer.valueOf(t1Den);
		int denominator2 = Integer.valueOf(t2Den);
		int whole1 = Integer.valueOf(t1Whole);
		int whole2 = Integer.valueOf(t2Whole);
    	if (operator.equals("+")) {//addition operator
    		if(numerator1 == 0 && numerator2 == 0) { //whole number added to whole number
    			int value = whole1 + whole2;
    			answer = ""+value;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) { //fraction added to fraction
    			nFinal = numerator1*denominator2 + numerator2*denominator1;
    			dFinal = denominator1*denominator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction added to mixed fraction
    				nFinal = numerator1*denominator2 + numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal + dFinal*whole2;
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction added to a whole number
    				nFinal = numerator1 + denominator1*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")){
    			if(numerator1 != 0) { //mixed fraction added to a fraction
    				nFinal = numerator1*denominator2 + numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal + dFinal*whole1;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number added to a fraction
    				nFinal = numerator2 + denominator2*whole1;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { 
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction added to whole number
    				int wholeSum = whole1 + whole2;
    				nFinal = denominator1*wholeSum + numerator1;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number added to mixed fraction
    				int wholeSum = whole1 + whole2;
    				nFinal = denominator2*wholeSum + numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction added to mixed fraction
    				int wholeSum = whole1 + whole2;
    				nFinal = (denominator1*denominator2)*wholeSum + numerator1*denominator2 + numerator2*denominator1;
    				dFinal = denominator1*denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}else if(operator.equals("-")) {//subtraction operator
    		if(numerator1 == 0 && numerator2 == 0) { //whole number minus whole number
    			int value = whole1 - whole2;
    			answer = ""+value;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) { //fraction minus fraction
    			nFinal = numerator1*denominator2 - numerator2*denominator1;
    			dFinal = denominator1*denominator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction minus mixed fraction
    				nFinal = numerator1*denominator2 - numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal - dFinal*whole2;
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction minus whole number
    				nFinal = numerator1 - denominator1*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")){
    			if(numerator1 != 0) { //mixed fraction minus fraction
    				nFinal = numerator1*denominator2 - numerator2*denominator1;
        			dFinal = denominator1*denominator2;
        			nFinal = nFinal + dFinal*whole1;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number minus fraction
    				nFinal = denominator2*whole1 - numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { 
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction minus whole number 
    				int wholeSum = whole1 - whole2;
    				nFinal = denominator1*wholeSum + numerator1;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number minus mixed fraction
    				int wholeSum = whole1 - whole2;
    				nFinal = denominator2*wholeSum - numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction minus mixed fraction
    				nFinal = (whole1*denominator1+numerator1)*denominator2-(whole2*denominator2+numerator2)*denominator1;
    				dFinal = denominator1*denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}else if(operator.equals("/")) {//division operator
    		if(numerator1 == 0 && numerator2 == 0) { //whole number / whole number
    			answer = whole1+"/"+whole2;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) { //fraction / fraction
    			nFinal = numerator1*denominator2;
    			dFinal = denominator1*numerator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction / mixed fraction 
    				nFinal = numerator1*denominator2;
        			dFinal = denominator1*(whole2*denominator2+numerator2);
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction / whole number
    				nFinal = numerator1;
    				dFinal = denominator1*whole2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")) {
    			if(numerator1 != 0) { //mixed fraction / fraction
    				nFinal = denominator2*(whole1*denominator1+numerator1);
        			dFinal = numerator2*denominator1;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number / fraction
    				nFinal = denominator2*whole1;
    				dFinal = numerator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { //mixed fraction / mixed fraction
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction / whole number 
    				nFinal = whole1*denominator1*denominator2;
    				dFinal = denominator1*numerator2;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number / mixed fraction
    				nFinal = whole1*denominator2;
    				dFinal = denominator2*whole2+numerator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction / mixed fraction
    				nFinal = (whole1*denominator1+numerator1)*(denominator2);
    				dFinal = (whole2*denominator2+numerator2)*(denominator1);
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}else if(operator.equals("*")) {//multiplication operator
    		if(numerator1 == 0 && numerator2 == 0) {
    			answer = ""+whole1*whole2;
    		}else if(t1Whole.equals("0") && t2Whole.equals("0")) {
    			nFinal = numerator1*numerator2;
    			dFinal = denominator1*denominator2;
    			answer = nFinal + "/" + dFinal;
    		}else if(t1Whole.equals("0") && !t2Whole.equals("0")){
    			if(numerator2 != 0){ //fraction * mixed fraction 
    				nFinal = numerator1*numerator2*denominator2;
        			dFinal = denominator1*denominator2;
        			answer = nFinal + "/" + dFinal;
    			}else { //fraction * whole number
    				nFinal = numerator1*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && t2Whole.equals("0")) {
    			if(numerator1 != 0) { //mixed fraction * fraction
    				nFinal = numerator1*whole2*numerator2;
        			dFinal = denominator1*denominator2;
        			answer = nFinal + "/" + dFinal;
    			}else { //whole number * fraction
    				nFinal = whole1*numerator2;
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}else if(!t1Whole.equals("0") && !t2Whole.equals("0")) { 
    			if(numerator1 != 0 && numerator2 == 0) { //mixed fraction * whole number 
    				nFinal = (whole1*denominator1+numerator1)*whole2;
    				dFinal = denominator1;
    				answer = nFinal + "/" + dFinal;
    			}else if (numerator1 == 0 && numerator2 != 0){ //whole number * mixed fraction
    				nFinal = whole1*(whole2*denominator2+numerator2);
    				dFinal = denominator2;
    				answer = nFinal + "/" + dFinal;
    			}else { //mixed fraction / mixed fraction
    				nFinal = (whole1*denominator1+numerator1)*(whole2*denominator2+numerator2);
    				dFinal = denominator1*denominator2;
    				answer = nFinal + "/" + dFinal;
    			}
    		}
    	}
    	return answer;
    }
}
    
