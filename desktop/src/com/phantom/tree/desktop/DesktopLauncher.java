package com.phantom.tree.desktop;

import com.phantom.tree.parser.Calc;
import com.phantom.tree.parser.ParseException;

import java.io.ByteArrayInputStream;

public class DesktopLauncher {
    public static void main(String[] args){
        System.out.println(String.format("%d", 1));


        String test = "x+2*(1+x)\n";
        new Calc(new ByteArrayInputStream(test.getBytes()));

        while (true) {
            System.out.print(">> ");
            System.out.flush();

            try {
                if (Calc.one_line() == -1) System.exit(0);
            } catch (ParseException x) {
                System.out.println("Finalizando con: " + x.getLocalizedMessage());

            }
        }
    }
}
