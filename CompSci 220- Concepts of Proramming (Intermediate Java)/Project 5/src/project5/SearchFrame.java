package project5;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchFrame extends JFrame {
		 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// Variables declaration - do not modify                     
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTextArea resultTextArea;
	    private javax.swing.JTextField titleTextBox;
	    private javax.swing.JTextField ratingTextBox;
	    private JButton searchTitleButton;
	    private JButton searchRatingButton;
	    private DatabaseManager dbManager;
	    // End of variables declaration           

		/**
	     * Creates new form NewJFrame
	     */
	    public SearchFrame(DatabaseManager DBManager) {
	    	dbManager = DBManager;
	        initComponents();
	    }

	    /**
	     * This method is called from within the constructor to initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is always
	     * regenerated by the Form Editor.
	     */                        
	    private void initComponents() {
	    	searchTitleButton = new JButton("Search Title");
	    	//searchTitleButton.setBounds(70,160,80,30);
	    	searchRatingButton = new JButton("Search Rating");
	    	//searchRatingButton.setBounds(70,160,80,30);	
				
	        jPanel1 = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        resultTextArea = new javax.swing.JTextArea();
	        titleTextBox = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        ratingTextBox = new javax.swing.JTextField();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Movie Results"));

	        resultTextArea.setColumns(20);
	        resultTextArea.setRows(5);
	        jScrollPane1.setViewportView(resultTextArea);

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jScrollPane1)
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
	        );

	        searchTitleButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	titleButtonActionPerformed(evt);
	            }
	        });
	        searchRatingButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	ratingButtonActionPerformed(evt);
	            }
	        });

	        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
	        jLabel1.setText("Search Movies");

	        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	        jLabel2.setText("Search Movie By Title");

	        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	        jLabel3.setText("Search Movie By Rating");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGap(35, 35, 35)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                            .addComponent(jLabel3)
	                            .addGap(18, 18, 18)
	                            .addComponent(ratingTextBox))
	                            .addComponent(searchRatingButton)
	                            .addGap(18,18,18)	                            
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                            .addComponent(jLabel2)
	                            .addGap(18, 18, 18)
	                            .addComponent(titleTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                            .addComponent(searchTitleButton)
	                            .addGap(18,18,18)	                            
                            .addContainerGap(50, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel2)
	                    .addComponent(titleTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(searchTitleButton)
	                    
	                    .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel3)
	                    .addComponent(ratingTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(searchRatingButton)
	                    .addGap(18, 18, 18)
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGap(51, 51, 51))
	        );

	        pack();
	    }
	    private void titleButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
	        // TODO add your handling code here:
	    	String searchQuery = titleTextBox.getText();
	    	ResultSet results = dbManager.search(0,searchQuery);
	    	printResults(results, " Title = " + searchQuery);
	    }  
	    private void ratingButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
	        // TODO add your handling code here:
	    	String searchQuery = ratingTextBox.getText();
	    	ResultSet results = dbManager.search(1,searchQuery);
	    	printResults(results, " Rating > " + searchQuery);
	    } 
	    private void printResults(ResultSet result, String search){
	    	ResultSetMetaData metadata;
	    	resultTextArea.append("============================================\r\n");
	    	resultTextArea.append("Results for:  " + search + " \r\n");
	    	
			try {
				metadata = result.getMetaData();
				int columns = metadata.getColumnCount();
				String str = "";
				str += metadata.getColumnName(1)+"\t\t\t";
				for (int i=2; i<= columns; i++){
					str += metadata.getColumnName(i)+"\t\t";
				}
				resultTextArea.append(str + "\r\n");
				resultTextArea.append("-----------------------------------------------"+
				"---------------------------------------------------------------------"+
				"---------------------------------------------------------------------"+
				"---------------------------------------------------------------------"+
				"-----------------------------------------------------------------\r\n");
				int resultCount = 0;
				while (result.next()) {
					str = "";
					for (int i=1; i<= columns; i++){
						str+=result.getObject(i)+"\t\t";
					}
					resultTextArea.append(str + "\r\n");
					resultCount++;
				}
				
				if (resultCount == 0){
					resultTextArea.append("There are No results for  " + search + " \r\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
	}


