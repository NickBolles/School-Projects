package com.nb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public FileSystem fs = null;
    CommandParser(FileSystem fs){
        this.fs = fs;
    }
    public String getCommand(){
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return text;
    }

    /**
     * Parse A command String
     * @return
     */
    public ArrayList<String> parseCommand(String cmd){
        ArrayList<String> parsed =  new ArrayList<String>();
        int quoteIndex = cmd.indexOf("\"");
        int quoteIndexEnd = cmd.lastIndexOf("\"");
        if (quoteIndex != -1 && quoteIndexEnd != 1 && quoteIndex != quoteIndexEnd){
            String match = cmd.substring(quoteIndex, quoteIndexEnd+1);
            cmd = cmd.substring(0, quoteIndex) + cmd.substring(quoteIndexEnd+1);
            cmd = unescapeJavaString(cmd);
            parsed.add(match);
        }
//        Pattern wordPattern =  Pattern.compile("(\"[(\\n)(\\w\\n)\\w\\s\\n]+\")");
//        Matcher m = wordPattern.matcher(cmd);
//        while(m.find()){
//            String match = m.group();
//            //Remove this group from the cmd and add this to the arguments list
//            //cmd = cmd.replaceFirst(match,"");
//            int indexStart = cmd.indexOf(match);
//            cmd = cmd.substring(0, indexStart) + cmd.substring(indexStart+match.length());
//            match = unescapeJavaString(match);
//            parsed.add(match);
//        }
        parsed.addAll(0, Arrays.asList(cmd.split(" ")));
        return parsed;
    }
    /**
     * Execute A command string
     * @return
     */
    public boolean executeCommand(String cmd){
        ArrayList<String> parsedCmd = parseCommand(cmd);
        if (parsedCmd.size() == 1 && parsedCmd.get(0).equals("")){parsedCmd.remove(0);}
        if (parsedCmd.size() >0) {
            String action = parsedCmd.remove(0).toLowerCase();
            if (action.equals("mkdir")) {
                //Check to see if the optional parameter is included
                if (!parsedCmd.isEmpty()) {
                    String arg = parsedCmd.get(0);
                    boolean createParents = false;
                    if (arg.equals("-p")) {
                        createParents = true;
                        parsedCmd.remove(0);
                    }
                    if (parsedCmd.size() > 0 ){
                        for (int i = 0; i<parsedCmd.size();){
                            if (!parsedCmd.isEmpty()) {
                                //The -p option is included, create all of the parent items if they dont exists
                                System.out.print("mkdir:");
                                String dir = parsedCmd.remove(0);
                                if(fs.createFile(dir, true, createParents)){
                                    System.out.println("Directory " + dir + " Created");
                                }
                            }
                        }
                    }else{
                        //TODO: error path argument missing
                        System.out.println("Not Enough Arguments");
                    }
                }else{
                    //TODO: Print MkDir help
                    System.out.println("Not Enough Arguments");
                }
            }
            else if (action.equals("rm")) {
                //Check to see if the optional parameter is included
                if (!parsedCmd.isEmpty()) {
                    String arg = parsedCmd.get(0);
                    boolean r = false;
                    if (arg.equals("-r")) {
                        r = true;
                        parsedCmd.remove(0);
                    }
                    if (parsedCmd.size() > 0 ){
                        for (int i = 0; i<parsedCmd.size();){
                            if (!parsedCmd.isEmpty()) {
                                //The -r option is included, delete directories and everything in them
                                System.out.print("rm:");
                                String name = parsedCmd.remove(0);
                                if (fs.removeFile(name, r)){
                                    System.out.println("File " + name + " Removed");
                                }
                            }
                        }
                    }else{
                        //todo: Error path argument missing
                        System.out.println("Not Enough Arguments");
                    }
                }else{
                    //TODO: Print rm help
                    System.out.println("Not Enough Arguments");
                }
            }
            else if (action.equals("cd")) {
                //Check to see if the optional parameter is included
                if (!parsedCmd.isEmpty()) {
                    String name = parsedCmd.remove(0);
                    fs.setWorkingDir(name);
                }else{
                    //TODO: Print cd help
                    System.out.println("Not Enough Arguments");
                }
            }
            else if (action.equals("ls")) {
                //Check to see if the optional parameter is included
                boolean r = false;
                if (!parsedCmd.isEmpty()) {
                    String arg = parsedCmd.get(0);
                    if (arg.equals("-r")) {
                        r = true;
                        parsedCmd.remove(0);
                    }
                }
                if (parsedCmd.size() > 0 ){
                    for (int i = 0; i<parsedCmd.size();){
                        if (!parsedCmd.isEmpty()) {
                            String name = parsedCmd.remove(0);
                            System.out.println(fs.listFiles(name, r));
                        }
                    }
                }else{
                    System.out.println(fs.listFiles("", r));
                }
            }
            else if (action.equals("pwd")) {
                System.out.println(fs.getWorkingDirPath());
            }
            else if (action.equals("edit")) {
                if (parsedCmd.size() > 0 ){
                    String name = parsedCmd.remove(0);
                    if(parsedCmd.size() >0){
                        //There are more options, check to see if its the content
                        String arg = parsedCmd.get(0);
                        if (arg.startsWith("\"") && arg.endsWith("\"") && arg.length()>0){
                            //call edit file with the contents being arg, without the trailing and ending quotes
                            fs.editFile(name, arg.substring(1,arg.length()-1));
                        }else{
                            fs.editFile(name);
                        }
                    }else{
                        fs.editFile(name);
                    }
                }else{
                    System.out.println("Error Not enough Arguments");
                }
            }
            else if (action.equals("cat")) {
                if (parsedCmd.size() > 0 ){
                    for (int i = 0; i<parsedCmd.size();){
                        if (!parsedCmd.isEmpty()) {
                            String path = parsedCmd.remove(0);
                            File f = fs.getFile(path, false);
                            if (f instanceof TextFile){
                                System.out.println(((TextFile) f).getContent());
                            }else if (f == null){
                                System.out.println("File at " + path + " does not exist");
                            }else{
                                System.out.println("File at " + path + " is not a TextFile, Cannot print contents");
                            }
                        }
                    }
                }else{
                    System.out.println("Error Not enough Arguments");
                }
            }
            else if (action.equals("mv")) {
                if (parsedCmd.size() >1  ){
                    boolean c = false;
                    String src;
                    String dest;
                    String arg = parsedCmd.get(0);
                    if (arg.equals("-c")) {
                        c = true;
                        parsedCmd.remove(0);
                    }
                    src = parsedCmd.remove(0);
                    //because we just check to see if parsedCmd is greater then 1, if -c is included then it
                    // would be 3 arguments, which would not have been caught there
                    if (!parsedCmd.isEmpty()) {
                        dest = parsedCmd.remove(0);
                        fs.moveFile(src,dest,c);
                    }else{
                        System.out.println("Error Not enough Arguments");
                    }
                }else{
                    System.out.println("Error Not enough Arguments");
                }
            }
            else if (action.equals("locate")) {
                if (parsedCmd.size() >0  ){
                    String name = parsedCmd.get(0);
                    fs.searchFiles(name);
                }else{
                    System.out.println("Error Not enough Arguments");
                }
            }
            else if (action.equals("search")) {
                if (parsedCmd.size() > 0 ){
                    for (int i = 0; i<parsedCmd.size();){
                        if (!parsedCmd.isEmpty()) {
                            String word = parsedCmd.remove(0);
                            fs.searchForWord(word);
                        }
                    }
                }else{
                    System.out.println("Error Not enough Arguments");
                }
            }
            else if (action.equals("updatedb")) {
                fs.index();
            }
            else if (action.equals("save")) {
                if (parsedCmd.size() >0  ){
                    String name = parsedCmd.get(0);
                    fs.saveToFile(name);
                }else{
                    fs.saveToFile("NBFileSystemSave");
                }
            }
            else if (action.equals("load")) {
                if (parsedCmd.size() >0  ){
                    String name = parsedCmd.get(0);
                    fs.loadFromFile(name);
                }else{
                    fs.loadFromFile("NBFileSystemSave");
                }
            }
            else if (action.equals("exec")) {
                if (parsedCmd.size() > 0 ){
                    for (int i = 0; i<parsedCmd.size();){
                        if (!parsedCmd.isEmpty()) {
                            this.executeScriptFile(parsedCmd.remove(0));
                        }
                    }
                }else{
                    System.out.println("Error Not enough Arguments");
                }
            }
        }
        else{
            //Todo: ERROR Not enough Arguments print help
            System.out.println("Error Not enough Arguments");
        }
        return true;

    }
    public boolean executeScriptFile(String fileName){
        try {
            System.out.println("Executing script file " + fileName);
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line = in.readLine();
            while (line != null) {
                int indexStart = line.indexOf("//"); // allow for commenting
                if (indexStart != -1){
                    line = line.substring(0, indexStart);
                }
                if (line.length()>0){
                    executeCommand(line);
                }
                line = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Script File Not Found");
        } catch (IOException e) {
            System.out.println("Failed to execute script file. IOException Occurred");
        }
        return false;
    }
    public boolean getAndExecuteCommand(){
        return executeCommand(getCommand());
    }

    /**
     * Unescapes a string that contains standard Java escape sequences.
     * <ul>
     * <li><strong>\b \f \n \r \t \" \'</strong> :
     * BS, FF, NL, CR, TAB, double and single quote.</li>
     * <li><strong>\X \XX \XXX</strong> : Octal character
     * specification (0 - 377, 0x00 - 0xFF).</li>
     * <li><strong></strong> : Hexadecimal based Unicode character.</li>
     * </ul>
     *
     * @param st
     *            A string optionally containing standard java escape sequences.
     * @return The translated string.
     */
    public String unescapeJavaString(String st) {

        StringBuilder sb = new StringBuilder(st.length());

        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);
            if (ch == '\\') {
                char nextChar = (i == st.length() - 1) ? '\\' : st
                        .charAt(i + 1);
                // Octal escape?
                if (nextChar >= '0' && nextChar <= '7') {
                    String code = "" + nextChar;
                    i++;
                    if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
                            && st.charAt(i + 1) <= '7') {
                        code += st.charAt(i + 1);
                        i++;
                        if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
                                && st.charAt(i + 1) <= '7') {
                            code += st.charAt(i + 1);
                            i++;
                        }
                    }
                    sb.append((char) Integer.parseInt(code, 8));
                    continue;
                }
                switch (nextChar) {
                    case '\\':
                        ch = '\\';
                        break;
                    case 'b':
                        ch = '\b';
                        break;
                    case 'f':
                        ch = '\f';
                        break;
                    case 'n':
                        ch = '\n';
                        break;
                    case 'r':
                        ch = '\r';
                        break;
                    case 't':
                        ch = '\t';
                        break;
                    case '\"':
                        ch = '\"';
                        break;
                    case '\'':
                        ch = '\'';
                        break;
                    // Hex Unicode: u????
                    case 'u':
                        if (i >= st.length() - 5) {
                            ch = 'u';
                            break;
                        }
                        int code = Integer.parseInt(
                                "" + st.charAt(i + 2) + st.charAt(i + 3)
                                        + st.charAt(i + 4) + st.charAt(i + 5), 16);
                        sb.append(Character.toChars(code));
                        i += 5;
                        continue;
                }
                i++;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
