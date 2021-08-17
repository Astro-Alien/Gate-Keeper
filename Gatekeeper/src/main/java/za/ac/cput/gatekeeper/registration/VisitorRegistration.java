

package za.ac.cput.gatekeeper.registration;

//AWT imports
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//Swing imports
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//Webcam imports
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.event.WindowEvent;

//Image and File imports
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * User Registration
 * @author: 216049245
 *
 */
public class VisitorRegistration extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField company;
    private JTextField mob;
    private JButton btnNewButton;
    
    private JButton webcamBtn;
    
    private JButton btnReturn;
    
    
    /**
     * Launch the application.
     * Main method
     */
   
    public JFrame verifyIdentityWindow()
    {
        JFrame webcamWindow = new JFrame("Camera");
        
        JButton takePic = new JButton("Take picture");
        JButton exitCam = new JButton("Exit camera");
        
        webcamWindow.getContentPane();
        webcamWindow.setSize(450, 550);
        webcamWindow.setLocationRelativeTo(null);
        webcamWindow.setVisible(true);
        
        webcamWindow.getContentPane().setLayout(null);
        
        Webcam w = Webcam.getDefault();
        
        //setting the size of the webcam display, then add the display to the frame
        w.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel p = new WebcamPanel(w);
        p.setSize(450, 450);
        webcamWindow.add(p);
        
        //setting button size & position, then adding the buttons to the frame
        takePic.setBounds(70, 460, 150, 30);
        exitCam.setBounds(240, 460, 150, 30);
        webcamWindow.add(takePic);
        webcamWindow.add(exitCam);
        
        takePic.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    /*
                    writes the image that was recieved from the time the webcam was opened
                    and saves it
                    */
                    
                    ImageIO.write(w.getImage(), "jpg", new File("images\\image.jpg"));
                    webcamWindow.setVisible(false);
                }
                
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        
        exitCam.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                webcamWindow.setVisible(false);
                w.close();//switches off the webcam
            }
        });
        
        return webcamWindow;
    }
    
    /**
     * Create the frame.
     */

    public VisitorRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 600, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Gatekeeper Visitor Registration");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        lblNewUserRegister.setBounds(100, 52, 500, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First name");
        lblName.setFont(new Font("roman", Font.ITALIC, 12));
        lblName.setBounds(50,148,100,30);
        contentPane.add(lblName);

        JLabel lblLName = new JLabel("Last name");
        lblLName.setFont(new Font("roman", Font.ITALIC, 12));
        lblLName.setBounds(50,198,100,30);
        contentPane.add(lblLName);
        
        JLabel lblMobileNumber = new JLabel("Mobile No");
        lblMobileNumber.setFont(new Font("roman", Font.ITALIC, 12));
        lblMobileNumber.setBounds(50,248,100,30);
        contentPane.add(lblMobileNumber);
        
        JLabel lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("roman", Font.ITALIC, 12));
        lblCompany.setBounds(50,298,100,30);
        contentPane.add(lblCompany);
        
        firstname = new JTextField();
        firstname.setFont(new Font("roman", Font.ITALIC, 12));
        firstname.setBounds(150,150,200,30);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setFont(new Font("roman", Font.ITALIC, 12));
        lastname.setBounds(150,200,200,30);
        contentPane.add(lastname);
        lastname.setColumns(10);

        company = new JTextField();
        company.setFont(new Font("roman", Font.ITALIC, 12));
        company.setBounds(150,300,200,30);
        contentPane.add(company);
        company.setColumns(10);


        mob = new JTextField();
        mob.setFont(new Font("roman", Font.ITALIC, 12));
        mob.setBounds(150,250,200,30);
        contentPane.add(mob);
        mob.setColumns(10);
        
        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String Company = company.getText();
                String mobileNumber = mob.getText();
                
                
                int len = mobileNumber.length();
               
                String msg = "" + firstName;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnNewButton, "Enter a valid mobile number");
                }
                
                //Inserts registration form data into database.

                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:Database\\visitors.db");
                    System.out.println("Connection established");
                    String query = "INSERT INTO visitors values('" + firstName + "','" + lastName + "','" + mobileNumber + "','" + Company + "')";
                    
                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This user already exists");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Welcome, " + msg + "Your account is sucessfully created");
                    }
                    //Catch error if the mobile number is in use by another user.
                    connection.close();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(btnNewButton, "Mobile number in use by another user.");
                    exception.printStackTrace();
                    
                }
            }
        });
        
        btnReturn = new JButton("Return");
        btnReturn.setBounds(190,490,220,37);
        btnReturn.addActionListener(this);
        
        webcamBtn = new JButton("Verify your identity");
        webcamBtn.setFont(new Font("roman", Font.BOLD, 14));
        webcamBtn.setBounds(205, 367, 180, 37);
        webcamBtn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                VisitorRegistration v = new VisitorRegistration();
                v.verifyIdentityWindow();
            }
        });
        
        btnNewButton.setFont(new Font("roman", Font.BOLD, 14));
        btnNewButton.setBounds(190, 450, 220, 37);
        
        contentPane.add(webcamBtn);
        contentPane.add(btnNewButton);
        contentPane.add(btnReturn);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
                
               
                Main rg = new Main();
                rg.startProgram();
                dispose();
    }
}
