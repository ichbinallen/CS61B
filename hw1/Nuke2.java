/* Nuke2.java */

import java.net.*;
import java.io.*;
//import java.URL.*;

/**  A class that provides a main function to print a string with the second
 *   character removed.
 */

class Nuke2 {

  /** Reads in a string from the user (without a prompt), deletes the second
   *  character in the string, and prints the remaining string.
   *  Will not work if string is less than 2 chars long.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    //System.out.print("Please enter the name of a company (without spaces): ");
    //System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    /* Replace this comment with your solution.  */
    String str = inputLine.substring(0,1) + inputLine.substring(2,inputLine.length());
    System.out.println(str);
  }
}
