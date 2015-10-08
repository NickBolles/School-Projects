package com.nb;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class FileSystem {
    Directory root = new Directory("");
    Directory working_dir = root;
    String prompt_prefix = "$";
    boolean prompt_workin_dir = false;
    FileSearchTree<FileIndexEntry> fileNames = new FileSearchTree<FileIndexEntry>();
    FileSearchTree<WordEntry> txtFileIndex = new FileSearchTree<WordEntry>();
    FileSystem() {

    }

    /**
     * Get the Prefix for the user prompt
     * @return
     */
    public String getPrompt_prefix() {
        return prompt_prefix;
    }

    /**
     * Set the prefix for the user Prompt
     * @param prompt_prefix     The New Prompt Prefix
     */
    public void setPrompt_prefix(String prompt_prefix) {
        this.prompt_prefix = prompt_prefix;
    }

    /**
     * Print the user prompt for the user
     */
    public void printPrompt() {
        if (prompt_workin_dir){
            System.out.print(getWorkingDirPath());
        }
        System.out.print(" " + prompt_prefix);
    }

    /**
     * Get the current working_dir path
     * @return          String of the Working_dir path
     */
    public String getWorkingDirPath() {
        return working_dir.getPath();
    }
    /**
     * Get the current working_dir path
     * @return          String of the Working_dir path
     */
    public Directory getWorkingDir() {
        return working_dir;
    }
    /**
     * Set the File Systems working_dir
     * @param path      The Path of the directory for the new working_dir
     * @return          If the operation was successful, True/False
     */
    public boolean setWorkingDir(String path) {
        if (path.equals("") || path.equals(".")) {
            return true;
        }
        Directory nw = null;
        if (path.equals("..") || path.equals("../")) {
            nw = working_dir.getParent();
        } else {
            nw = (Directory) getFile(root, parseDirectory(path), true, false);
        }
        if (nw != null) {
            working_dir = nw;
            return true;
        } else {
            System.out.println("Directory Not Found");
            return false;
        }
    }

    /**
     * Function for getting a file at the specified path
     * @param path          Path for the file
     * @param directory     Whether to only look for directories or all files
     * @return              The File that was found, or Null if no file was found
     */
    public File getFile(String path, boolean directory) {
        return getFile(root, parseDirectory(path), directory, false);
    }

    /**
     * Recursively finds and gets a file at the specified path
     * @param in                Directory in which to look
     * @param parsedDirectory   Arraylist of directories to traverse
     * @param directory         If True only return a directory. False return any file found
     * @param createDirs        Create the directories down to the file
     * @return                  File, The file that was found
     */
    private File getFile(Directory in, ArrayList<String> parsedDirectory, boolean directory, boolean createDirs) {
        if (parsedDirectory.size() == 0) {
            return in;
        } else {
            String dirName = parsedDirectory.remove(0);
            ArrayList<File> children = in.getChildren();
            for (File child : children) {
                if (child.getName().replaceAll("/", "").equals(dirName)) {
                    if (child instanceof Directory) {
                        return getFile((Directory) child, parsedDirectory, directory, createDirs);
                    } else if (!directory) {
                        //This is not a directory, if we are asking for any file, not just directories then return the file
                        return child;
                    }
                }
            }
            if (createDirs) {
                Directory nd = new Directory(dirName);
                in.addChild(nd);
                if (parsedDirectory.size() > 0) {
                    return getFile(nd, parsedDirectory, directory, createDirs);
                }
                return nd;
            }
            return null;
        }
    }

    /**
     * Utility function to determine whether the file exists or not
     * @param path          Path to the file
     * @param directory     Whether to look for only a directory or not
     * @return              True if file exists, False if it does not
     */
    public boolean fileExists(String path, boolean directory) {
        if (getFile(root, parseDirectory(path), directory, false) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Create A File in the File System
     *
     * @return      True if the creation was successful
     */
    public boolean createFile(String path, boolean directory, boolean createParents) {
        if (!fileExists(path, false)) {
            //We need to get the directory above the file and then add a child to that
            ArrayList<String> parsed = parseDirectory(path);
            //Check to see if there are more then one directory, if not it is the root directory
            if (parsed.size() > 1) {
                String toCreate = parsed.remove(parsed.size() - 1);
                Directory dir = (Directory) getFile(root, parsed, true, createParents);
                if (dir != null) {
                    if (directory) {
                        dir.addChild(new Directory(toCreate));
                    } else {
                        dir.addChild(new TextFile(toCreate));
                    }
                    return true;
                } else {
                    System.out.println("Cannot Create File " + path + " Directory Does Not Exist, Include -p To Create The Parent Directories");
                    return false;
                }
            } else {
                if (directory) {
                    root.addChild(new Directory(parsed.get(0)));
                } else {
                    root.addChild(new TextFile(parsed.get(0)));
                }
                return true;
            }
        }
        System.out.println("Cannot Create File " + path + " File Already Exists");
        return false;
    }

    /**
     * Create A File in the File System
     *
     * @return      True if the deletion was successful
     */
    public boolean removeFile(String path, boolean r) {
        if (fileExists(path, false)) {
            //We need to get the directory above the file and then add a child to that
            ArrayList<String> parsed = parseDirectory(path);
            if (parsed.size() > 1) {
                String toRemove = "";
                if (!r) {
                    //We want a file, so get the directory above this file
                    toRemove = parsed.remove(parsed.size() - 1);
                    File toRemoveFile = getFile(root, parsed, false, false);
                    if (toRemoveFile instanceof Directory) {
                        //Error we cant remove this its a directory
                        System.out.println("Cannot remove " + toRemove + " it Is a Directory. Include the -r option to remove directories, and all sub directories");
                        return false;
                    }
                }
                Directory dir = (Directory) getFile(root, parsed, true, false);
                if (dir != null) {
                    if (!r) {
                        //We are deleting a file, so call the removeChild instead of the delete function
                        return dir.removeChild(toRemove);
                    } else {
                        dir.delete();
                        return true;
                    }
                } else {
                    System.out.println(" Directory " + path + " not found");
                    return false;
                }

            } else {
                if (!r) {
                    return root.removeChild(parsed.remove(0));
                } else {
                    root = new Directory("");
                    working_dir = root;
                    System.out.println("Deleting root!");
                }
            }
        } else {
            System.out.println("File " + path + " does not exist");
        }
        return false;
    }

    /**
     * Create or Edits A text File in the File System
     *
     * @return      True if the edit was successful
     */
    public boolean editFile(String path) {
        System.out.println("Editing File " + path);
        Scanner scanner = new Scanner(System.in);
        boolean endOfFile = false;
        StringBuilder newContents = new StringBuilder();
        //Loop the input until the user enters <<EOF>>
        while (!endOfFile) {
            String line = scanner.nextLine() + "\n";
            if (line.contains("<<EOF>>")) {
                endOfFile = true;
            }else{
                newContents.append(line);
            }
        }
        return editFile(path,newContents.toString());
    }

    /**
     * Create or Edits A text File in the File System
     *
     * @return      True if the edit was successful
     */
    public boolean editFile(String path, String contents) {
        if (!fileExists(path, false)) {
            createFile(path, false, false);
        }
        ArrayList<String> parsed = parseDirectory(path);
        File file = getFile(root, parsed, false, false);
        if (file instanceof TextFile) {
            TextFile tFile = (TextFile) file;
            tFile.setContent(contents);
        } else {
            System.out.println("File " + path + " is not a Text File");
        }
        return false;
    }


    /**
     * Move or copies a file
     *
     * @param src       Relative or absolute path of the source file
     * @param dest      Relative or absolute path of the destination file
     * @param copy      Whether this is a copy, or a move operation
     * @return          Boolean, whether the operation was successful
     */
    public boolean moveFile(String src, String dest, boolean copy) {
        //first make sure the src exists
        if (fileExists(src, false)) {
            //If the source exists we can get the file
            File sourceFile = getFile(root, parseDirectory(src), false, false);
            File sourceFileCopy = sourceFile.duplicate();
            boolean sourceIsDir = sourceFile instanceof Directory;
            //Get the destination directory, or the directory containing the dest file
            ArrayList<String> destDirParsed = parseDirectory(dest);
            String destFileName = destDirParsed.remove(destDirParsed.size() - 1).replaceAll("/", "");
            Directory destDir = (Directory) getFile(root, destDirParsed, true, false);
            if (destDir != null) {
                File existFile = destDir.findChild(destFileName);
                if (existFile != null) {
                    //Destination already contains a child with this name
                    if ((existFile instanceof Directory && sourceIsDir) || (existFile instanceof TextFile && !sourceIsDir)) {
                        //There is already a directory with this name in the destination, and the source is a directory, so we can overwrite it
                        //OR
                        //There is already a text file with this name in the destination, but the source is a text file, so we can overwrite it
                        existFile.delete();
                        if (!copy) {
                            //"delete" the source file, or at least the reference from the old directory, if its not a copy action
                            sourceFile.delete();
                        }
                        sourceFileCopy.setName(destFileName);
                        destDir.addChild(sourceFileCopy);
                        return true;
                    } else if (existFile instanceof Directory && !sourceIsDir) {
                        //If the existing file is a directory, and the source is a text file, then add the source to the destination directory
                        if (!copy) {
                            //"delete" the source file, or at least the reference from the old directory, if its not a copy action
                            sourceFile.delete();
                        }
                        sourceFileCopy.setName(destFileName);
                        ((Directory) existFile).addChild(sourceFileCopy);
                        return true;
                    } else {
                        //The file types of source and Dest do not match, print error
                        System.out.println("Src and Dest File Types do not match, cannot overwrite");
                        return false;
                    }
                } else {
                    //There is not a file with the current name, we can safely move this file there with no further checks
                    if (!copy) {
                        //"delete" the source file, or at least the reference from the old directory, if its not a copy action
                        sourceFile.delete();
                    }
                    sourceFileCopy.setName(destFileName);
                    destDir.addChild(sourceFileCopy);
                    return true;
                }
            } else {
                System.out.println("Destination directory does not exist");
                return false;
            }
        } else {
            System.out.println("Source file does not exist");
            return false;
        }
    }

    /**
     * List Files in the specified path
     * @param path      path in which to list files, "" will be current Working dir
     * @param r         If true will list the files in each subdirectory recursively
     */
    public String listFiles(String path, boolean r) {
        Directory d = (Directory) getFile(root, parseDirectory(path), true, false);
        if (d != null) {
            return d.listFiles(r, 3);
        }else{
            return "Directory not found";
        }
    }

    /**
     * Create the fileNames and txtFileIndex index's for the filesystem
     */
    public void index(){
        //Clear the search trees
        fileNames = new FileSearchTree<FileIndexEntry>();
        txtFileIndex = new FileSearchTree<WordEntry>();
        index(root);
//        System.out.println(fileNames.toStringInOrderNoDepth());
//        System.out.println(txtFileIndex.toStringInOrderNoDepth());

    }

    /**
     * Create the file Names index for the directory, and create txt file index with any TextFile children
     * @param f             The Directory to index
     */
    private void index(Directory f){
        ArrayList<File> children = f.getChildren();
        for (int i = 0; i < children.size(); i++) {
            File child = children.get(i);
            FileIndexEntry entry = new FileIndexEntry(child);
            FileIndexEntry existing = fileNames.find(entry);
            if (existing != null){
                existing.addFile(child);
            }else{
                fileNames.add(entry);
            }
            if (child instanceof Directory) {
               index((Directory) child);
            } else if(child instanceof TextFile) {
                index((TextFile) child);
            }
        }
    }

    /**
     * Populate the txtFileIndex for this text file
     * @param f             The File to create the index from
     */
    private void index(TextFile f){
        Pattern wordPattern =  Pattern.compile("[\\p{L}\\p{N}']+");
        try{
            BufferedReader in = new BufferedReader(new StringReader(f.getContent()));
            String line = in.readLine();
            int lineCount =0;
            while(line != null){
                lineCount++;
                Matcher m = wordPattern.matcher(line);
                while(m.find()){
                    //instantiate a new entry
                    WordEntry entry = new WordEntry(m.group());
                    //search the bst to see if there is already an entry for this word;
                    WordEntry e = txtFileIndex.find(entry);
                    if (e == null){
                        entry.addOccurrence(f, lineCount);
                        //if there is not an entry for this word add it
                        txtFileIndex.add(entry);
                    }else{
                        //the word is already in the bst, so add this line to it
                        e.addOccurrence(f, lineCount);
                    }
                }
                line = in.readLine();
            }
            in.close();
        }catch(IOException e){
            System.out.println("Failed to load text file--IOException Occurred");
        }
    }

    public void searchForWord(String word){
        index();
        System.out.println(findWord(word));
    }
    public String findWord(String word){
        WordEntry entry = txtFileIndex.find(new WordEntry(word));
        if (entry != null){
            return entry.toString();
        }
        return "No Results";
    };
    public void searchFiles(String name){
        index();
        ArrayList<File> results =  find(name);
        System.out.println(results.size() + " results found for " + name + ": ");
        StringBuilder sb = new StringBuilder();
        for (File result: results){
            sb.append("    ");
            sb.append(result.getPath() + "\n");
        }
        System.out.println(sb.toString());
    }
    private ArrayList<File> find( String name) {
        FileIndexEntry entry = fileNames.find(new FileIndexEntry(new TextFile(name)));
        if (entry != null){
            ArrayList<File> results = entry.getFiles();
            return results;
        }
        return new ArrayList<File>();
    }
    /**
     * Utility function to parse a directory into an arrayList
     *
     * @return ArrayList of directories, the last one being either the last directory or the filename
     */
    public ArrayList<String> parseDirectory(String path) {
        if (path.startsWith("/")) {
            //This is an absolute path
            path = path.substring(1);
            if (path.equals("")) {
                return new ArrayList<String>(0);
            }
            ArrayList<String> a = new ArrayList<String>(Arrays.asList(path.split("/")));
            return a;
        } else {
            //This is a relative path, we need to add the working_dir to it
            String wDirPath = working_dir.getPath().substring(1);
            if (!wDirPath.equals("")) {
                wDirPath += "/";
            }
            String test = wDirPath + path;
            if (test.equals("")) {
                return new ArrayList<String>(0);
            }
            ArrayList<String> a = new ArrayList<String>(Arrays.asList(test.split("/")));
            a.remove("");
            return a;
        }

    }




    /**
     * Load the File System from a file
     *
     * @return True if the load was successful
     */
    public boolean loadFromFile(String fileName) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            JSONObject obj = (JSONObject) JSONValue.parse(in);
            this.root = Directory.fromJSON(obj);
            System.out.println("File System Loaded");
        } catch (FileNotFoundException e) {
            System.out.println("Failed to Restore. Save File Not Found");
        } catch (IOException e) {
            System.out.println("Failed to Restore. IOException Occurred");
        }
        return false;
    }

    /**
     * Save the FileSystem to file
     *
     * @return True if the save was successful
     */
    public  boolean saveToFile(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            out.println(root.toJSON().toJSONString());
            out.close();
        } catch (IOException e) {
            System.out.println("Failed To Save Sets. IOException Occured");
        }
        return false;
    }



    public class FileIndexEntry implements Comparable<FileIndexEntry>{
        ArrayList<File> files = new ArrayList<File>();
        String name = "";
        FileIndexEntry(File file){
            this.files.add(file);
            this.name = file.getName();
        }

        public ArrayList<File> getFiles() {
            return files;
        }

        public void addFile(File file) {
            this.files.add(file);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(FileIndexEntry o) {
            return this.name.compareTo(o.getName());
        }

        @Override
        public String toString() {
            return "FileIndexEntry{" +
                    "file=" + files +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public class WordEntry implements Comparable<WordEntry>{
        private class FileLineEntry {
            File file;
            ArrayList<Integer> lines = new ArrayList<Integer>();
            FileLineEntry(TextFile file, ArrayList<Integer> lines){
                this.file = file;
                this.lines = lines;
            }
            FileLineEntry(TextFile file){
                this.file = file;
            }
            public File getFile(){
                return this.file;
            }
            public String getFileName(){
                return this.file.getName();
            }
            public void addLines(ArrayList<Integer> lines){
                this.lines.addAll(lines);
            }
            public void addLine(Integer line){
                this.lines.add(line);
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(file.getPath());
                sb.append(": ");
                for (int i =0; i<lines.size();i++){
                    sb.append(lines.get(i));
                    if (i!=lines.size()-1){
                        sb.append(",");
                    }
                }
                return  sb.toString();
            }
        }

        //A List of all of the spots this word exists
        ArrayList<FileLineEntry> fileEntries = new ArrayList<FileLineEntry>();
        String word;
        WordEntry(String word){
            this.word = word;
        }
        public String getWord(){
            return word;
        }
        public void addOccurrences(TextFile f,ArrayList<Integer> lines){
            findFileEntry(f).addLines(lines);
        }
        public void addOccurrence(TextFile f,Integer line){
            findFileEntry(f).addLine(line);
        }
        private FileLineEntry findFileEntry(TextFile file){
            for (FileLineEntry fileEntry: fileEntries){
                if (fileEntry.getFile().getPath().equals(file.getPath())){
                    return fileEntry;
                }
            }
            FileLineEntry newFLE = new FileLineEntry(file);
            fileEntries.add(newFLE);
            return newFLE;
        }

        @Override
        public int compareTo(WordEntry s) {
            return this.word.compareTo(s.getWord());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(word);
            int wordLength = word.length();
            sb.append(": ");
            for (int i =0; i<fileEntries.size();i++){
                sb.append(fileEntries.get(i));
                if (i!=fileEntries.size()-1){
                    sb.append("\n");
                    for (int s=0;s<wordLength+2;s++){
                        sb.append(" ");
                    }
                }
            }
            return  sb.toString();
        }
    }
    public class FileSearchTree<E extends Comparable> extends BinarySearchTree<E>{
        FileSearchTree(){

        }
        public String toStringInOrderNoDepth() {
            StringBuilder sb = new StringBuilder();
            this.inOrderTraverseNoDepth(this.getRoot(), sb);
            return sb.toString();
        }

        /**
         * Perform an inorder traversal.
         * @param node The local root
         * @param sb The string buffer to save the output
         */
        private void inOrderTraverseNoDepth(Node<E> node, StringBuilder sb) {
            if (node != null){
                inOrderTraverseNoDepth(node.left, sb);
                sb.append(node.toString());
                sb.append("\n");
                inOrderTraverseNoDepth(node.right, sb);
            }
        }

    }

}