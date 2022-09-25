package Main;
import javax.swing.*;
import java.awt.Color;
public class Frame2 extends JFrame{
    //Labels
    JLabel jl1 , jl2 , jl3 , jl4 , jl5 , jl6 , jl7 , jl8;
    //Labels for showing student data.
    JLabel show_Name , show_Roll_No , show_Gender , show_Batch , show_Section , show_Qualification , show_Address , show_Country;
    //Variables used to set text of the Labels.
    private String name , roll_no , gender , batch , section , qualification , address , country;
    
    public Frame2(String name , String roll_no , String gender , String batch , String section , String qualification , String address , String country){
        //initializing the variables by assigning them the values passed from Frame1.
    this.name = name; this.roll_no = roll_no; this.gender = gender; this.batch = batch; this.section = section; this.qualification = qualification; this.address = address; this.country = country;
    new JFrame();
    setTitle("Student Information");
    setSize(420, 300);
    setLocationRelativeTo(null);
    this.getContentPane().setBackground(Color.ORANGE);
    setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
    setResizable(false);

    jl1 = new JLabel("Name:");
    jl2 = new JLabel("Roll.No#:");
    jl3 = new JLabel("Gender:");
    jl4 = new JLabel("Section:");
    jl5 = new JLabel("Batch:");
    jl6 = new JLabel("Qualification:");
    jl7  = new JLabel("Address:");
    jl8 = new JLabel("Country:");
    
    //Printing the student data.
    show_Name = new JLabel(name);
    show_Name.setForeground(Color.RED);
    show_Roll_No = new JLabel(roll_no);
    show_Roll_No.setForeground(Color.RED);
    show_Gender = new JLabel(gender);
    show_Gender.setForeground(Color.RED);
    show_Batch = new JLabel(batch);
    show_Batch.setForeground(Color.RED);
    show_Section = new JLabel(section);
    show_Section.setForeground(Color.RED);
    show_Qualification = new JLabel(qualification);
    show_Qualification.setForeground(Color.RED);
    show_Address = new JLabel(address);
    show_Address.setForeground(Color.RED);
    show_Country = new JLabel(country);
    show_Country.setForeground(Color.RED);

    add(jl1); add(show_Name);
    add(jl2); add(show_Roll_No);
    add(jl3); add(show_Gender);
    add(jl4); add(show_Section);
    add(jl5); add(show_Batch);
    add(jl6); add(show_Qualification);
    add(jl7); add(show_Address);
    add(jl8); add(show_Country);
    setVisible(true);
    }// end of constructor.
}// end of class.
