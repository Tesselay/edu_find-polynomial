package com.szut;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner input_scanner = new Scanner(System.in);
        String user_input = input_scanner.next();

	    Pattern neg_exp = Pattern.compile("\\^-");
        Matcher neg_exp_m = neg_exp.matcher(user_input);
        if( neg_exp_m.find() ) {
            System.err.println("Negative Exponent found! No Polynomial.");
            System.exit(0);
        }

        Pattern vars = Pattern.compile("[a-z]*");
        Matcher vars_m = vars.matcher(user_input);
        if( !vars_m.find() ) {
            System.err.println("No indeterminates found! (Note: only indeterminates in lowercase are found.)");
            System.exit(0);
        }

        Pattern sqrt = Pattern.compile("(sqrt)");
        Matcher sqrt_m = sqrt.matcher(user_input);
        if( sqrt_m.find() ) {
            System.err.println("Polynomials don't allow squareroots!");
            System.exit(0);
        }

        Pattern term_splitter = Pattern.compile("([+-]?[^-+]+)");       // Matches every group once and separate from other groups
        Matcher term_splitter_m = term_splitter.matcher(user_input);

        if( term_splitter_m.groupCount() <= 0 ) {
            System.err.println("No Polynomials found!");
            System.exit(0);
        }

        System.out.println("Congrats, you have a polynomial! Groups found:");
        while( term_splitter_m.find() ){
            System.out.println(term_splitter_m.group(term_splitter_m.groupCount()-1));
        }

    }
}
