
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

//Image and File imports
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * User Registration
 * @author: 216049245
 *
 */
public class VisitorRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField company;
    private JTextField mob;
    private JButton btnNewButton;
    
    private JButton webcamBtn;
    
    private JButton regUser;
    private JButton newUser;
    
    /**
     * Launch the application.
     * Main method
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VisitorRegistration frame = new VisitorRegistration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public JFrame welcomeWindow()
    {
        JFrame welcome = new JFrame("Gatekeeper");
        
        JLabel lblNewUserRegister = new JLabel("Gatekeeper Visitor Registration");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        lblNewUserRegister.setBounds(50, 100, 500, 50);
        
        JButton regUser = new JButton("I am a registered user");
        JButton newUser = new JButton("I am not a registered user");
        
        newUser.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                VisitorRegistration vr = new VisitorRegistration();
                
                welcome.setVisible(false);
                vr.setVisible(true);
            }
        });
        
        regUser.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                welcome.setVisible(false);
                //add code for registration window here...
            }
        });
        
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setSize(500, 600);
        welcome.setLocationRelativeTo(null);
        welcome.setVisible(true);
        welcome.getContentPane().setLayout(null);
        
        regUser.setFont(new Font("roman", Font.BOLD, 14));
        regUser.setBounds(130, 300, 210, 37);
        
        newUser.setFont(new Font("roman", Font.BOLD, 14));
        newUser.setBounds(125, 400, 220, 37);
        
        welcome.add(lblNewUserRegister);
        welcome.add(regUser);
        welcome.add(newUser);
        
        return welcome;
    }
    
    /**
     * Create the frame.
     */

    public VisitorRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 600, 480);
        setResizable(false);
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
        firstname.setBounds(150,150,400,30);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setFont(new Font("roman", Font.ITALIC, 12));
        lastname.setBounds(150,200,400,30);
        contentPane.add(lastname);
        lastname.setColumns(10);

        company = new JTextField();
        company.setFont(new Font("roman", Font.ITALIC, 12));
        company.setBounds(150,300,300,30);
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
                int id = 1;
                
                int len = mobileNumber.length();
               
                String msg = "" + firstName;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnNewButton, "Enter a valid mobile number");
                }

                try {
                    //Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\Brandon\\Documents\\Maven\\Gatekeeper\\src\\main\\java\\za\\ac\\cput\\gatekeeper\\registration\\Database\\visitors.db");
                    System.out.println("Connection established");
                    //String query = "INSERT INTO visitors values('" + firstName + "','" + lastName + "','" + mobileNumber + "','" + Company + "')";
                    String query = "INSERT INTO visitors values('" + id + "','" + firstName + "','" + lastName + "','" + mobileNumber + "','" + Company + "')";
                    

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This user already exists");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Welcome, " + msg + "Your account is sucessfully created");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("roman", Font.BOLD, 14));
        btnNewButton.setBounds(150, 350, 180, 37);
        contentPane.add(btnNewButton);
    }
}
