package com.szut;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner input_scanner = new Scanner(System.in);
        String user_poly = input_scanner.next();            // test_poly = x^2-x+3x-x^1

        Pattern disallowed = Pattern.compile("[^a-z0-9\\^\\-+*]");
        Matcher disallowed_m = disallowed.matcher(user_poly);
        if( disallowed_m.find() ) {
            System.err.println("Illegal characters found!");
            System.exit(0);
        }

	    Pattern neg_exp = Pattern.compile("\\^-");
        Matcher neg_exp_m = neg_exp.matcher(user_poly);
        if( neg_exp_m.find() ) {
            System.err.println("Negative Exponent found! No Polynomial.");
            System.exit(0);
        }

        Pattern vars = Pattern.compile("[a-z]*");
        Matcher vars_m = vars.matcher(user_poly);
        if( !vars_m.find() ) {
            System.err.println("No indeterminates found! (Note: only indeterminates in lowercase are found.)");
            System.exit(0);
        }

        Pattern degree_p = Pattern.compile("(\\^[0-9])");
        Matcher degree_m = degree_p.matcher(user_poly);
        int highest_degree = -1;
        while( degree_m.find() ){
            String[] string_degree = degree_m.group(degree_m.groupCount()-1).split("[\\^]");
            int degree = Integer.parseInt(string_degree[1]);
            if( degree > highest_degree )
                highest_degree = degree;
        }

        Pattern term_splitter = Pattern.compile("([+-]?[^-+]+)");       // Matches every group once and separate from other groups
        Matcher term_splitter_m = term_splitter.matcher(user_poly);

        if( term_splitter_m.groupCount() <= 0 ) {
            System.err.println("No Polynomials found!");
            System.exit(0);
        }

        System.out.println("Congrats, you have a polynomial! Groups found:");
        while( term_splitter_m.find() ){
            System.out.println(term_splitter_m.group(term_splitter_m.groupCount()-1));
        }

        if( highest_degree == -1 )
            highest_degree = 1;         // If no degree specified, it is expected that only indeterminates without exponent are specified (which means they have a degree of 1)
        System.out.printf("The highest degree is: %d", highest_degree);

        System.exit(1);

    }
}
