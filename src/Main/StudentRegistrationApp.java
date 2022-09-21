package Main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.FlowLayout;

public class StudentRegistrationApp {
    
    public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("Student Registration");
    frame.setBounds(50, 50, 500, 200);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(1,1,20));

    JLabel name = new JLabel("Name");
    JLabel roll = new JLabel("Roll No#");
    JLabel gender = new JLabel("Gender");
    ButtonGroup g = new ButtonGroup();               //ButtonGroup class object created.
    JRadioButton genderMale = new JRadioButton("Male");
    JRadioButton genderFemale = new JRadioButton("Female");
    g.add(genderMale);                              //Grouping.
    g.add(genderFemale);

    JLabel batch = new JLabel("Batch");
    JLabel section = new JLabel("Section");

    JButton save = new JButton("Save");
    JButton print = new JButton("Print");

    JTextField nameField = new JTextField(20);
    JTextField rollField = new JTextField(20);
    JTextField batchField = new JTextField(20);
    JTextField sectionField = new JTextField(20);
    
    panel.add(name);
    panel.add(nameField);
    panel.add(roll);
    panel.add(rollField);
    panel.add(batch);
    panel.add(batchField);
    panel.add(section);
    panel.add(sectionField);
    panel.add(gender);
    panel.add(genderMale);
    panel.add(genderFemale);
    panel.add(save);
    panel.add(print);
    frame.setVisible(true);
    frame.add(panel);

    save.addActionListener( new ActionListener(){  //event for save button.
        @Override
        public void actionPerformed(ActionEvent e){
        try{
            JSONObject obj = new JSONObject();
            String batch = batchField.getText();
            String section = sectionField.getText();

            Float checkBatch = Float.parseFloat(batch);
            Float checkSection = Float.parseFloat(section);

            obj.put("Roll.No#",rollField.getText());
            obj.put("Batch",batchField.getText());
            obj.put("Section",sectionField.getText());
            obj.put("Name", nameField.getText());
            if(genderMale.isSelected()){
                obj.put("Gender",genderMale.getText());
            }
            else if(genderFemale.isSelected()){
                obj.put("Gender",genderFemale.getText());
            }
            
            FileWriter file = new FileWriter("Data.json");
            file.write(obj.toJSONString());
            file.close();
            JOptionPane.showMessageDialog(frame, "Data Saved.");
        } // end of try.
            catch (IOException e1) {
                e1.printStackTrace();}
            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(frame, "Fill all the Fields!");}
        }
    });

    // For printing data in another frame.
    JFrame Frame2 = new JFrame();
    Frame2.setTitle("Student Data");
    Frame2.setBounds(30, 30, 750,75);
    Frame2.setLocationRelativeTo(null);
    Frame2.setResizable(false);   

    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout(-1,15,1));
    
    print.addActionListener(new ActionListener(){  //for print button functionality.
        @Override
        public void actionPerformed(ActionEvent e){
            try{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("Data.json"));
            String printName = (String) jsonObject.get("Name");
            String printRollNumber = (String) jsonObject.get("Roll.No#");
            String printBatch = (String) jsonObject.get("Batch");
            String printSection = (String) jsonObject.get("Section");
            String printGender = (String) jsonObject.get("Gender");
            JLabel forShowingName = new JLabel(printName);
            JLabel forShowingRollNumber = new JLabel(printRollNumber);
            JLabel forShowingBatch = new JLabel(printBatch);
            JLabel forShowingSection = new JLabel(printSection);
            JLabel forShowingGender = new JLabel(printGender);
            panel2.add(name);
            panel2.add(forShowingName);
            panel2.add(roll);
            panel2.add(forShowingRollNumber);
            panel2.add(batch);
            panel2.add(forShowingBatch);
            panel2.add(section);
            panel2.add(forShowingSection);
            panel2.add(gender);
            panel2.add(forShowingGender);
            Frame2.add(panel2); 
            Frame2.setVisible(true);}
            catch(FileNotFoundException e1){
                JOptionPane.showMessageDialog(frame, "Click save first!");} 
            catch (IOException e2){
                e2.printStackTrace();} 
            catch (ParseException e3){
                e3.printStackTrace();} 
        }
      });
    }
}
