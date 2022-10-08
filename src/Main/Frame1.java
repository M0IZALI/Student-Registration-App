package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import org.json.simple.JSONObject;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Frame1 extends JFrame implements ActionListener{
    
    private String name , roll_Number , batch , section , gender , qualification , address , country;
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
    JComboBox JCcountry;
    //Buttons
    JButton save , print , store_In_Db, fetch;
    
    public Frame1(){
    new JFrame();
    //Setting the icon of the frame.
    Image icon = Toolkit.getDefaultToolkit().getImage("registration.png");    
    setIconImage(icon); 
    //Setting Title
    setTitle("Student Registration");
    setSize(600,600);
    setLayout(null);
    setResizable(false);
    //setting background color.
    getContentPane().setBackground(Color.ORANGE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jl_name = new JLabel(" Name:-");
    jl_roll = new JLabel("  Roll No#:-");
    jl_gender = new JLabel("  Gender:-"); 
    jl_batch = new JLabel("  Batch:-");
    jl_section = new JLabel("Section:-");
    jl_qualification = new JLabel(" Qualification:-");
    jl_address = new JLabel("Address:-");
    jl_country = new JLabel("  Country:-");

    jl_name.setBounds(50,50,75,25);
    jl_roll.setBounds(47, 100 , 75 ,25);
    jl_gender.setBounds(47, 150 , 75 , 25);
    jl_batch.setBounds(47,200,75,25);
    jl_section.setBounds(52,250,75,25);
    jl_qualification.setBounds(48,300,90,25);
    jl_address.setBounds(51,360,75,25);
    jl_country.setBounds(46,420,75,25);

    jtf_name = new JTextField(20);
    jtf_roll = new JTextField(20);
    jtf_batch = new JTextField(20);
    jtf_section = new JTextField(20);

    jtf_name.setBounds(125,50,200,20);
    jtf_roll.setBounds(125,100,200,20);
    jtf_batch.setBounds(125,200,200,20);
    jtf_section.setBounds(125,250,200,20);

    gender_male = new JRadioButton("Male");
    gender_male.setBounds(125,150,80,25);
    gender_male.setBackground(Color.ORANGE);
    gender_female = new JRadioButton("Female");
    gender_female.setBounds(200,150,90,25);
    gender_female.setBackground(Color.ORANGE);
    ButtonGroup g = new ButtonGroup();               //ButtonGroup class's object created.
    g.add(gender_male);                              //Grouping.
    g.add(gender_female);
    
    jcb_matric = new JCheckBox("Matric");
    jcb_matric.setBackground(Color.ORANGE);
    jcb_matric.setBounds(140,300,75,25);
    jcb_intermediate = new JCheckBox("Intermediate");
    jcb_intermediate.setBackground(Color.ORANGE);
    jcb_intermediate.setBounds(210,300,100,25);
    jcb_graduate = new JCheckBox("Graduate");
    jcb_graduate.setBackground(Color.ORANGE);
    jcb_graduate.setBounds(325,300,85,25);
    jcb_post_graduate = new JCheckBox("Post Graduate");
    jcb_post_graduate.setBackground(Color.ORANGE);
    jcb_post_graduate.setBounds(425,300,125,25);

    jta_address = new JTextArea();
    jta_address.setLineWrap(true);
    jta_address.setBounds(125,350,200,50);

    JCcountry = new JComboBox<>(countries);
    JCcountry.setBounds(125,425,100,20);
    
    save = new JButton("Save");
    print = new JButton("Print");
    store_In_Db = new JButton("Database");
    fetch = new JButton("Fetch");
    save.setBounds(100, 500, 75, 25);
    print.setBounds(200,500,75,25);
    store_In_Db.setBounds(300,500,88,25);
    fetch.setBounds(400,500,75,25);
  
    add(jl_name); add(jtf_name);
    add(jl_roll); add(jtf_roll);
    add(jl_section); add(jtf_section);
    add(jl_batch);  add(jtf_batch);
    add(jl_qualification); add(jcb_matric); add(jcb_intermediate); add(jcb_graduate); add(jcb_post_graduate);
    add(jl_gender); add(gender_male); add(gender_female);
    add(jl_address); add(jta_address);
    add(jl_country); add(JCcountry);
    add(save); add(print); add(store_In_Db); add(fetch);
    
    save.addActionListener(this); //adding event for save button.
    print.addActionListener(this);// adding event for print button.
    store_In_Db.addActionListener(this);// adding event for database button.
    fetch.addActionListener(this); //adding event for fetch button.
    setVisible(true);
    }// end of constructor.

    //Method for generating id for student.
    public int generateId(){
        Random random_number = new Random();
        return random_number.nextInt(100);
    }

    //Getters
    public String getName(){
        return name;
    }

    public String getRollNumber(){
        return roll_Number;
    }

    public String getBatch(){
        return batch;
    }

    public String getSection(){
        return section;
    }

    public String getGender(){
        return gender;
    }

    public String getQualification(){
        return qualification;
    }

    public String getAddress(){
        return address;
    }

    public String getCountry(){
        return country;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == save){  //if save button is clicked.
        FileWriter file;
        JSONObject put_obj = new JSONObject();
    //Initializing variables.
        name = jtf_name.getText();
        roll_Number = jtf_roll.getText();
        batch = jtf_batch.getText();
        section = jtf_section.getText();

        if(gender_male.isSelected())
        gender = "Male";
        else if(gender_female.isSelected())
        gender = "Female";

        if(jcb_matric.isSelected())
        qualification = "Matric";
        if(jcb_intermediate.isSelected())
        qualification = qualification+", Intermediate";
        if(jcb_graduate.isSelected())
        qualification = qualification+", Graduate";
        if(jcb_post_graduate.isSelected())
        qualification = qualification+", Post-Graduate";

        address = jta_address.getText();
        country = (String) JCcountry.getSelectedItem();
    //putting values in json file.
        put_obj.put("Name", name);      
        put_obj.put("Roll.No#", roll_Number);
        put_obj.put("Batch", batch);
        put_obj.put("Section", section);
        put_obj.put("Gender", gender);
        put_obj.put("Qualification",qualification);
        put_obj.put("Address", address);
        put_obj.put("Country", country);

        try{
        file = new FileWriter("Data.json"); //writing to a json file.
        file.write(put_obj.toJSONString());
        file.close();
        JOptionPane.showMessageDialog(this, "Data Saved.");
        }catch(IOException e1){
            e1.printStackTrace();}
        }

        else if(e.getSource() == print){//if print button is clicked.
         new Frame2(name,roll_Number,gender,batch,section,qualification,address,country);//}  //passing variables to Frame2 using constructor(Frame2 is for showing student information)
        }
         
        else if(e.getSource() == store_In_Db){//if database button is clicked.
        Connection con = null;
        String sqlQuery = "insert into students values(?,?,?,?,?,?,?,?,?)";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
            if(con != null)
            System.out.println("Connection Established");
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, generateId());
            pst.setString(2,getName());
            pst.setString(3,getRollNumber());
            pst.setString(4,getBatch());
            pst.setString(5,getSection());
            pst.setString(6,getGender());
            pst.setString(7,getQualification());
            pst.setString(8,getAddress());
            pst.setString(9,getCountry());
            //myres = pst.executeQuery();
            if(pst.executeUpdate()==1)
            JOptionPane.showMessageDialog(this, "Record Saved");
        } catch (SQLException e2) {
            e2.printStackTrace();}
            finally{
                if(con != null)
                    try {
                        con.close();
                        name = null;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
            } // end of finally block.
       }// end of else if block.
       
       else if(e.getSource() == fetch){ //if fetch button is clicked.
        String query = "Select * from students";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);

            System.out.println("ID\tName\t\tRoll.No\t\tBatch\t\tSection\t\tGender\t\tQualification\t\t\t\tAddress\t\t\t\tCountry");
            while(result.next()){
                String id = result.getString(1);
                name = result.getString(2);
                roll_Number = result.getString(3);
                batch = result.getString(4);
                section = result.getString(5);
                gender = result.getString(6);
                qualification = result.getString(7);
                address = result.getString(8);
                country = result.getString(9);

                System.out.println(id+"\t"+name+"\t\t"+roll_Number+"\t\t"+batch+"\t\t"+section+"\t\t"+gender+"\t\t"+qualification+"\t\t\t"+address+"\t\t\t"+country);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
       } // end of else if block.
    }// end of method actionPerformed.
} // end of class


 
