package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

public class Frame1 extends JFrame implements ActionListener{
    JPanel panel;
    //Labels
    JLabel jl_name , jl_roll , jl_gender ,jl_batch , jl_section , jl_qualification , jl_address , jl_country;
    //TextFields (for jl_name , jl_roll , jl_batch and jl_section)
    JTextField jtf_name, jtf_roll , jtf_batch , jtf_section;
    //RadioButtons (for jl_gender)
    JRadioButton gender_male , gender_female;
    //Checkboxes (for jl_qualification)
    JCheckBox jcb_matric , jcb_intermediate , jcb_graduate , jcb_post_graduate;
    //TextArea (for jl_address)
    JTextArea jta_address;
    //list of countries
    String[] countries = {"None","Australia","Algeria","Belgium","Brazil","Cuba","Denmark","Egypt","France","Germany","Hungary","Iran","Kenya","Mexico","Norway","Pakistan","Sri Lanka","Sweden","India","Zimbabwe"};
    //ComboBox (for jl_country)
    JComboBox country;
    //Buttons
    JButton save , print;
    
    public Frame1(){
    new JFrame();
    //Setting the icon of the frame.
    Image icon = Toolkit.getDefaultToolkit().getImage("registration.png");    
    setIconImage(icon); 
    //Setting Title
    setTitle("Student Registration");
    setBounds(50, 50, 570, 460);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new JPanel();
    panel.setLayout(new FlowLayout(1,15,50));
    panel.setBackground(Color.ORANGE);
    panel.setForeground(Color.RED);

    jl_name = new JLabel(" Name:-");
    jl_roll = new JLabel("  Roll No#:-");
    jl_gender = new JLabel("  Gender:-"); 
    jl_batch = new JLabel("  Batch:-");
    jl_section = new JLabel("Section:-");
    jl_qualification = new JLabel(" Qualification:-");
    jl_address = new JLabel("Address:-");
    jl_country = new JLabel("  Country:-");

    jtf_name = new JTextField(20);
    jtf_roll = new JTextField(20);
    jtf_batch = new JTextField(20);
    jtf_section = new JTextField(20);

    gender_male = new JRadioButton("Male");
    gender_male.setBackground(Color.ORANGE);
    gender_female = new JRadioButton("Female");
    gender_female.setBackground(Color.ORANGE);
    ButtonGroup g = new ButtonGroup();               //ButtonGroup class's object created.
    g.add(gender_male);                              //Grouping.
    g.add(gender_female);

    jcb_matric = new JCheckBox("Matric");
    jcb_matric.setBackground(Color.ORANGE);
    jcb_intermediate = new JCheckBox("Intermediate");
    jcb_intermediate.setBackground(Color.ORANGE);
    jcb_graduate = new JCheckBox("Graduate");
    jcb_graduate.setBackground(Color.ORANGE);
    jcb_post_graduate = new JCheckBox("Post Graduate");
    jcb_post_graduate.setBackground(Color.ORANGE);

    jta_address = new JTextArea(4, 20);
    jta_address.setLineWrap(true);

    country = new JComboBox<>(countries);
    
    save = new JButton("Save");
    print = new JButton("Print");
  
    panel.add(jl_name); panel.add(jtf_name);
    panel.add(jl_roll); panel.add(jtf_roll);
    panel.add(jl_section); panel.add(jtf_section);
    panel.add(jl_batch); panel.add(jtf_batch);
    panel.add(jl_qualification); panel.add(jcb_matric); panel.add(jcb_intermediate); panel.add(jcb_graduate); panel.add(jcb_post_graduate);
    panel.add(jl_gender); panel.add(gender_male); panel.add(gender_female);
    panel.add(jl_address); panel.add(jta_address);
    panel.add(jl_country); panel.add(country);
    panel.add(save); panel.add(print);
    add(panel);  
    
    save.addActionListener(this); //adding event for save button.
    print.addActionListener(this);// adding event for print button.
    setVisible(true);
    }// end of constructor.
     @Override
    public void actionPerformed(ActionEvent e){
        FileWriter file;
        JSONObject put_obj = new JSONObject();
        put_obj.put("Name", jtf_name.getText());      //putting(initializing) values in json file.
        put_obj.put("Roll.No#", jtf_roll.getText());
        put_obj.put("Batch", jtf_batch.getText());
        put_obj.put("Section", jtf_section.getText());
        if(gender_male.isSelected())
            put_obj.put("Gender", "Male");
        else if(gender_female.isSelected())
            put_obj.put("Gender", "Female");

        if(jcb_matric.isSelected())
        put_obj.put("Qualification","Matric");
        if(jcb_intermediate.isSelected())
        put_obj.put("Qualification","Matric , Intermediate");
        if(jcb_graduate.isSelected())
        put_obj.put("Qualification","Matric , Intermediate ,Graduate");
        if(jcb_post_graduate.isSelected())
        put_obj.put("Qualification","Matric Intermediate , Graduate , Post-Graduate");
        
        put_obj.put("Address", jta_address.getText());
        put_obj.put("Country", country.getSelectedItem());

        if(e.getSource() == save){  //if save button is clicked.
        try{
        file = new FileWriter("Data.json"); //writing to a json file.
        file.write(put_obj.toJSONString());
        file.close();
        JOptionPane.showMessageDialog(this, "Data Saved.");
        }catch(IOException e1){
            e1.printStackTrace();}
        }
        else if(e.getSource() == print){//if print button is clicked.
            try {JSONParser jsonParser = new JSONParser();
                JSONObject get_obj = (JSONObject) jsonParser.parse(new FileReader("Data.json")); 
                String name = (String) get_obj.get("Name");             //getting values from json file and storing them in a variable.
                String roll_Number = (String) get_obj.get("Roll.No#");
                String batch = (String) get_obj.get("Batch");
                String section = (String) get_obj.get("Section");
                String gender = (String) get_obj.get("Gender");
                String qualification = (String) get_obj.get("Qualification");
                String address = (String) get_obj.get("Address");
                String country = (String) get_obj.get("Country");
                new Frame2(name,roll_Number,gender,batch,section,qualification,address,country);}  //passing variables to Frame2 using constructor.
            catch(FileNotFoundException e1){
                JOptionPane.showMessageDialog(this, "Click save first!");}
            catch (IOException | ParseException e1){
                e1.printStackTrace();}
        }
    }// end of method actionPerformed.
} // end of class



 
