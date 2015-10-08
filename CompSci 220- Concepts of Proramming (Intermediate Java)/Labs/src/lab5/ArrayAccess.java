package

lab5;

import

java.awt.FlowLayout;

import

java.awt.GridLayout;

import

java.awt.event.ActionEvent;

import

java.awt.event.ActionListener;

import

javax.swing.JFrame;

import

javax.swing.JLabel;

import

javax.swing.JOptionPane;

import

javax.swing.JPanel;

import

javax.swing.JTextField;

public

class ArrayAccess extends JFrame {

private JTextField inputField;

private JTextField retrieveField1;

private JTextField retrieveField2;

private JTextField outputField;

private JPanel inputArea;

private JPanel retrieveArea;

private JPanel outputArea;

private int num;

private int index =0;

private int array[] = new int [10];

private String result;

// Constructor of ArrayAccess class

public ArrayAccess() {

setLayout(

new FlowLayout());

inputArea = new JPanel();

inputArea.add(new JLabel("Enter array" +

" element here:"));

inputField = new JTextField(10);

inputArea.add(inputField);

inputField.addActionListener(

new ActionListener() {

public void actionPerformed(ActionEvent e) {

  

try {

num = Integer.parseInt(inputField.getText());

array[index] = num;

index++;

}

catch (NumberFormatException exception) {

outputField.setText("Error in entering data to the array");

}

catch (ArrayIndexOutOfBoundsException exception) {

outputField.setText("Array may contain only 10 elements");

}

inputField.setText("");

}

});

// Setting up the retrieve panel

retrieveArea = new JPanel(new GridLayout(2,2));

retrieveArea.add(new JLabel("Enter a number " +

"to retrieve"));

retrieveField1 = new JTextField(10);

retrieveArea.add(retrieveField1);

retrieveField1.addActionListener(

new ActionListener() {

public void actionPerformed(ActionEvent event) {

try {

num = Integer.parseInt(

retrieveField1.getText());

int i=0;

while ( i< index && array[i] != num)

i++;

       

if (i== index)

       

throw new NumberNotFoundException();

       

else

       

outputField.setText(" The value is at index: "+i);

       

retrieveField1.setText("");

}

catch (NumberFormatException exception) {

outputField.setText("Error in entering " +

"the value to retrieve");

}

catch (NumberNotFoundException exception) {

outputField.setText(exception.getMessage());

}

   

   

   

   

   

   

}

});

retrieveArea.add(new JLabel("Enter index to " +

"retrieve"));

retrieveField2 = new JTextField(10);

retrieveArea.add(retrieveField2);

retrieveField2.addActionListener(

new ActionListener() {

 

public void actionPerformed(ActionEvent event) {

// Enter the error handling code here 

  }

});

outputArea = new JPanel();

outputArea.add(new JLabel("Result: "));

outputField = new JTextField(30);

outputField.setEditable(false);

outputArea.add(outputField);

add(

inputArea);

add(

retrieveArea);

add(

outputArea);

}

// end of ArrayAccess constructor

}

// end of ArrayAccess class

