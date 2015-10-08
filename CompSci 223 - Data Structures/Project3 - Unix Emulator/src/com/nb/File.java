package com.nb;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by Nicholas on 4/18/2015.
 */
abstract class File {
    private String name;
    private Directory parent;

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    /**
     * Get the absolute path of the file
     * @return              The Absolute Path of the file
     */
    String getPath() {
        if (this.parent != null) {
            return this.parent.getPath() + this.getName(); //A directory will always have a trailing slash on it, to
            // signify that it is a directory not a file, so there is no need ot add one here
        } else {
            //this doesn't have a parent so return the name
            return this.getName();
        }
    }

    /**
     * Deletes the file from the parent directory
     */
    void delete() {
        if (this.getParent() != null) {
            this.getParent().removeChild(this.getName());
        }
    }
    /**
     * Returns true if this is a directory
     */
    public boolean isDirectory(){
        return false;
    }
    /**
     * Set the toString method for a file to return its name
     * @return
     */
    public String toString() {
        return this.name;
    }
    public String toJSONString(){
        return this.toJSON().toJSONString();
    }
    abstract JSONObject toJSON();
    abstract File duplicate();
}


class TextFile extends File {
    private String content;

    TextFile(String name, String content) {
        super(name);
        this.content = content;
    }

    TextFile(String name) {
        super(name);
    }

    /**
     * Get the content of the Text File
     * @return              String of the text file contents
     */
    String getContent() {
        return content;
    }

    /**
     * Set the content of the Text File
     * @param content       The new content of the Text File
     */
    void setContent(String content) {
        this.content = content;
    }

    /**
     * Implement the duplicate method for the Text File
     * @return
     */
    File duplicate() {
        TextFile f = new TextFile(this.getName().replaceAll("/", ""), this.content);
        f.setParent(this.getParent());
        return f;
    }

    public static TextFile fromJSON(JSONObject json){
        TextFile file = new TextFile((String) json.get("name"), (String) json.get("content"));
        return  file;
    }
    @Override
    JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("name", this.getName());
        obj.put("content", this.getContent());
        return obj;
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "name='" + this.getName() + '\'' +
                "content='" + content + '\'' +
                '}';
    }
}

class Directory extends File {
    private ArrayList<File> children = new ArrayList<File>();

    Directory(String name) {
        super(name);
    }

    /**
     * Get the arraylist of the children files
     * @return          Arraylist of child files
     */
    ArrayList<File> getChildren() {
        return children;
    }

    /**
     * Set the children arraylist to a new arraylist
     * @param f         Arraylist of children to replace the old one with
     */
    void setChildren(ArrayList<File> f) {
        this.children = new ArrayList<File>();
        for (File child : f) {
            File newChild = child.duplicate();
            newChild.setParent(this);
            children.add(newChild);
        }
    }

    /**
     * Add a File to the directory
    * @param f          The File to add to the directory
     */
    void addChild(File f) {
        f.setParent(this);
        children.add(f);
    }

    /**
     * Remove a child file from the directory
     * @param name      The Name of the file to remove
     * @return          Whether the operation was successful
     */
    boolean removeChild(String name) {
        int child = findChildIndex(name);
        if (child != -1) {
            children.remove(child);
        } else {
            System.out.println("Could Not Remove Child File '" + name + "'Because It Does Not Exist");
            return false;
        }
        return false;
    }

    /**
     * Find a child in this directory
     * @param name      The name of the child to find
     * @return          The Child file that was found, or null if no file exists
     */
    File findChild(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().replaceAll("/", "").equals(name)) {
                return children.get(i);
            }
        }
        return null;
    }

    /**
     * Find the index of a child
     * @param name      The name of the child to find
     * @return          The index of the child in the children arraylist
     */
    int findChildIndex(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * List the files in the directory in a tree view
     * @param r         Whether or not to list the files recursively
     * @param indent    The Indent of the child files
     * @return          A String, the treeview of the directory
     */
    String listFiles(boolean r, int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName() + "\n");
        for (int i = 0; i < children.size(); i++) {
            for (int k = 0; k < indent; k++) {
                sb.append(" ");
            }
            File child = children.get(i);
            if (r && child instanceof Directory) {
                sb.append(((Directory) child).listFiles(r, indent + 3));
            } else {
                sb.append(child.getPath() + "\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean isDirectory(){
        return true;
    }

    @Override
    /**
     * Override the getName in order to always have a trailing slash.
     * Because this is a directory it should always have a trailing slash to signify
     */
    public String getName() {
        return super.getName() + "/";
    }

    /**
     * Implement the duplicate method for the directory
     */
    File duplicate() {
        Directory f = new Directory(this.getName().replaceAll("/", ""));
        f.setChildren(this.children);
        f.setParent(this.getParent());
        return f;
    }

    public String toStringIndented(int indent){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName() + "\n");
        for (int i = 0; i < children.size(); i++) {
            for (int k = 0; k < indent; k++) {
                sb.append(" ");
            }
            File child = children.get(i);
            if (child instanceof Directory) {
                sb.append(((Directory) child).toStringIndented(indent + 3));
            } else {
                sb.append(child.getPath() + "\n");
            }
        }
        return sb.toString();
    }
    public static Directory fromJSON(JSONObject json){
        Directory dir = new Directory((String) json.get("name"));
        JSONArray children = (JSONArray) json.get("children");
        while (!children.isEmpty()){
            JSONObject child = (JSONObject) children.remove(0);
            if (child.containsKey("children")){
                //This is a directory, so call From JSON for it
                Directory childDir = Directory.fromJSON(child);
                childDir.setParent(dir);
                dir.addChild(childDir);
            }else if (child.containsKey("content")){
                //This is a text file
                TextFile childTxt = TextFile.fromJSON(child);
                childTxt.setParent(dir);
                dir.addChild(childTxt);
            }
        }
        return  dir;
    }
    public JSONObject toJSON(){
        JSONObject dirObj = new JSONObject();
        dirObj.put("name", this.getName().replace("/",""));
        JSONArray childArray = new JSONArray();
        for(File child: children){
            childArray.add(child.toJSON());
        }
        dirObj.put("children", childArray);
        return  dirObj;
    }
    @Override
    public String toString() {

        return "Directory{" +
                "name='" + this.getName() +"'"+
                "children=" + children +
                "}\n";
    }
}
