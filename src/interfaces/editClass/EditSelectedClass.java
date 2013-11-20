/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.editClass;

import interfaces.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.io.File;
import java.io.IOException;

import objects.Assignment;
import objects.AssignmentCategory;
import objects.MyCourse;
import io.Exporter;
import io.parseXML;

/**
 *
 * @author Lilong
 */
public class EditSelectedClass extends javax.swing.JPanel implements ActionListener{

    private MainFrame parent;
    private int assignmentIndex, categoryIndex, courseIndex;
    private boolean isTableSet = false;
    
    /**
     * Creates new form EditCourse
     * @param frame
     * @param currentCourse
     */
    public EditSelectedClass(MainFrame frame, int currentCourseInd) {
        parent = frame;
        courseIndex = currentCourseInd;
        initComponents();
        if (courseIndex != -1)
        	setup();
    }
    
    private void setup() {
        model = (DefaultTableModel)assignmentTable.getModel();
        if (parent.courses.get(courseIndex) != null)
        	courseName.setText(parent.courses.get(courseIndex).getName() 
        			+ "-" + parent.courses.get(courseIndex).getSection());
        if (parent.courses.get(courseIndex).getNumberOfAssignmentCategories() > 0) {
            loadCourseData();
        }
    }
    
    private void loadCourseData() {
        for (int i = 0; i < parent.courses.get(courseIndex).getNumberOfAssignmentCategories(); i++) {
            //FIXME Should have add/remove buttons
            javax.swing.JMenu categoryMenu = new javax.swing.JMenu();
            categoryMenu.setText(parent.courses.get(courseIndex).getAssignmentCategory(i).getName());

            for (int j = 0; j < parent.courses.get(courseIndex).getAssignmentCategory(i).getNumberOfAssignments(); j++) {
        	 	final int indexOfCategory = i;
        	 	final int indexOfAssignment = j;
        	 	assignmentIndex = j;
             	categoryIndex = i;
             	final javax.swing.JMenuItem assignmentMenuItem = new javax.swing.JMenuItem();
             	assignmentMenuItem.setText(parent.courses.get(courseIndex).getAssignmentCategory(i).getAssignment(j).getName());
             	assignmentMenuItem.addActionListener(new java.awt.event.ActionListener() {
             		public void actionPerformed(java.awt.event.ActionEvent evt) {
             			categoryIndex = indexOfCategory;
             			assignmentIndex  = indexOfAssignment;
             			isTableSet = false;
             			populateTable();
             			courseName.setText(parent.courses.get(courseIndex).getName() 
                     		+ "-" + parent.courses.get(courseIndex).getSection() 
                     		+ " " + assignmentMenuItem.getText());
                     }
                });
             	categoryMenu.add(assignmentMenuItem);   		
            }
            menuBar.add(categoryMenu);
        }     
    }
    
    private void populateTable() {
    	for (int i = model.getRowCount()-1; i >= 0; i--) {
    		model.removeRow(i);
    	}
    	for (int i = 0; i < parent.courses.get(courseIndex).getNumberOfStudents(); i++) {
    		model.insertRow(i, new Object[]{ 
    				parent.courses.get(courseIndex).getStudent(i).getFullName(), 
    				parent.courses.get(courseIndex)
    				.getCategories().get(categoryIndex)
    				.getAssignment(assignmentIndex)
    				.getGrade(parent.courses.get(courseIndex).getStudent(i).getPseudoName())});
    	}
    	isTableSet = true;
    }
    
    public void setPanelMenu() {
    	parent.setJMenuBar(menuBar);
    }
    
    public void actionPerformed(ActionEvent evt) {
        //loadCourseData();
    }
    
    @SuppressWarnings("serial")
    public DefaultTableModel model = new DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Student", "Grade"
        }
    ) {
        public boolean isCellEditable(int row, int column) {
                if (column == 0)
                        return false;
                else
                        return true;
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        File_Save = new javax.swing.JMenuItem();
        File_ExportToHTML = new javax.swing.JMenuItem();
        createMenu = new javax.swing.JMenu();
        newMenu = new javax.swing.JMenuItem();
        studentMenu = new javax.swing.JMenu();
        addStudent = new javax.swing.JMenuItem();
        removeStudent = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        assignmentTable = new javax.swing.JTable();
        goBackButton = new javax.swing.JButton();
        courseName = new javax.swing.JLabel();

        fileMenu.setText("File");

        File_Save.setText("Save");
        fileMenu.add(File_Save);
        File_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parseXML.saveXML(parent.courses.get(courseIndex));
            }
        });

        File_ExportToHTML.setText("Export to HTML");
        File_ExportToHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                File_ExportToHTMLActionPerformed(evt);
            }
        });
        fileMenu.add(File_ExportToHTML);

        menuBar.add(fileMenu);

        createMenu.setText("Create");

        newMenu.setText("New Category");
        createMenu.add(newMenu);

        menuBar.add(createMenu);

        studentMenu.setText("Student");

        addStudent.setText("Add Student");
        studentMenu.add(addStudent);

        removeStudent.setText("Remove Student");
        studentMenu.add(removeStudent);

        menuBar.add(studentMenu);

        assignmentTable.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        assignmentTable.setModel(model);
        assignmentTable.getModel().addTableModelListener(changedData());
        jScrollPane1.setViewportView(assignmentTable);

        goBackButton.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        goBackButton.setText("Go Back");
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });

        courseName.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        courseName.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(goBackButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(courseName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(courseName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goBackButton)
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private TableModelListener changedData() {
    	TableModelListener cha = new TableModelListener() {
    		public void tableChanged(TableModelEvent e) {
  			   if (isTableSet) {
  				   int r = e.getLastRow();
  				   parent.courses.get(courseIndex).getCategories()
  	    			.get(categoryIndex)
  	    			.getAssignment(assignmentIndex)
  	    			.setGrade(parent.courses.get(courseIndex).getStudent(r).getPseudoName(), 
  	    					Integer.parseInt(model.getValueAt(r, 1).toString()));
  			   }
		   }
		};
		return cha;
    }
    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackButtonActionPerformed
        parseXML.saveXML(parent.courses.get(courseIndex));
        parent.setSimpleModeVisible();
    }//GEN-LAST:event_goBackButtonActionPerformed

    private void File_ExportToHTMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_File_ExportToHTMLActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(EditSelectedClass.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Exporter exp = new Exporter();
            File file = fc.getSelectedFile();
            try {
                exp.exportCourseToHTML(parent.courses.get(courseIndex), file.getCanonicalPath());
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error exporting HTML.");
            }
        }
    }//GEN-LAST:event_JMenuItem_exportToHTMLActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem File_Save;
    private javax.swing.JMenuItem File_ExportToHTML;
    private javax.swing.JMenuItem addStudent;
    private javax.swing.JTable assignmentTable;
    private javax.swing.JLabel courseName;
    private javax.swing.JMenu createMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton goBackButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newMenu;
    private javax.swing.JMenuItem removeStudent;
    private javax.swing.JMenu studentMenu;
    // End of variables declaration//GEN-END:variables
}
