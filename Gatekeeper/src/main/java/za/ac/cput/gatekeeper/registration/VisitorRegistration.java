package za.ac.cput.gatekeeper.registration;

//AWT imports
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

//Swing imports
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Webcam imports
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.ByteArrayOutputStream;

//Image and File imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * User Registration
 *
 * @author: 216049245
 *
 */
public class VisitorRegistration extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel border;
    private JPanel secondBorder;
    private JPanel thirdBorder;
    private JLabel lblIcon;
    private JLabel label;
    private JLabel lblImage;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField company;
    private JTextField mob;
    private JButton btnNewButton;

    private JFrame window;

    private JButton webcamBtn;

    private JButton btnReturn;

    private JButton takePic;
    private JButton exitCam;

    private Webcam w;
    private JFrame webcamWindow;

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * Launch the application. Main method
     */
    public VisitorRegistration() {

        webcamWindow = new JFrame("Camera");
        window = new JFrame("GateKeeper");
        border = new JPanel();
        secondBorder = new JPanel();
        thirdBorder = new JPanel();

        lblIcon = new JLabel();
        label = new JLabel();
        lblImage = new JLabel();

        takePic = new JButton("Take picture");
        exitCam = new JButton("Exit camera");
        webcamBtn = new JButton("Verify your identity");
    }

    public void VisitorRegistrationGUI() {
        conn = DbConnection.ConnectDb();
        //----------------------------------------------------------------------Start Webcam window code
        //author : Zondi
        webcamWindow.getContentPane();
        webcamWindow.setSize(450, 550);
        webcamWindow.setLocationRelativeTo(null);
        webcamWindow.setVisible(false);

        webcamWindow.getContentPane().setLayout(null);

        w = Webcam.getDefault();

        //setting the size of the webcam display, then add the display to the frame
        w.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel p = new WebcamPanel(w);
        p.setSize(450, 450);
        webcamWindow.add(p);

        //setting button size & position, then adding the buttons to the frame
        takePic.setBounds(70, 460, 150, 30);
        //takePic.addActionListener(this);
        exitCam.setBounds(240, 460, 150, 30);
        //exitCam.addActionListener(this);
        webcamWindow.add(takePic);
        webcamWindow.add(exitCam);
        //----------------------------------------------------------------------end of Webcam window code
        window.setSize(876, 497);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        border.setSize(876, 497);
        border.setLayout(null);
        window.add(border);

        secondBorder.setBounds(50, 37, 450, 385);
        secondBorder.setLayout(null);
        border.add(secondBorder);
        secondBorder.add(lblIcon);
        iconImg();

        thirdBorder.setBounds(525, 37, 290, 385);
        thirdBorder.setLayout(null);

        lblImage.setVisible(false);

        border.add(thirdBorder);

        thirdBorder.add(label);
        thirdBorder.add(lblImage);

        JLabel lblNewUserRegister = new JLabel("Gatekeeper Visitor Registration");
        lblNewUserRegister.setFont(new Font("SourceSansPro", Font.BOLD, 25));
        lblNewUserRegister.setForeground(Color.BLACK);
        lblNewUserRegister.setBounds(60, 5, 500, 50);
        secondBorder.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First Name");
        lblName.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblName.setForeground(Color.BLACK);
        lblName.setBounds(25, 200, 100, 30);
        secondBorder.add(lblName);

        firstname = new JTextField();
        firstname.setFont(new Font("SourceSansPro", Font.BOLD, 12));
        firstname.setBounds(133, 200, 200, 26);
        secondBorder.add(firstname);
        firstname.setColumns(10);

        JLabel lblLName = new JLabel("Last name");
        lblLName.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblLName.setForeground(Color.BLACK);
        lblLName.setBounds(25, 240, 100, 30);
        secondBorder.add(lblLName);

        lastname = new JTextField();
        lastname.setFont(new Font("SourceSansPro", Font.BOLD, 12));
        lastname.setBounds(133, 240, 200, 26);
        secondBorder.add(lastname);
        lastname.setColumns(10);

        JLabel lblMobileNumber = new JLabel("Mobile No");
        lblMobileNumber.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblMobileNumber.setForeground(Color.BLACK);
        lblMobileNumber.setBounds(25, 280, 100, 30);
        secondBorder.add(lblMobileNumber);

        mob = new JTextField();
        mob.setFont(new Font("SourceSansPro", Font.BOLD, 12));
        mob.setBounds(133, 280, 200, 26);
        secondBorder.add(mob);
        mob.setColumns(10);

        JLabel lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblCompany.setForeground(Color.BLACK);
        lblCompany.setBounds(25, 320, 100, 30);
        secondBorder.add(lblCompany);

        company = new JTextField();
        company.setFont(new Font("SourceSansPro", Font.BOLD, 12));
        company.setBounds(133, 320, 200, 26);
        secondBorder.add(company);
        company.setColumns(10);

        //----------------------------------------------------------------------third panel design
        //----------------------------------------------------------------------image
        //image to be replaced with the current image being taken by the user will be modified once the images are saved to the database
        ImageIcon userimage = new ImageIcon("images\\default.jpg");
        label.setBounds(58, 30, 180, 160);
        label.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(180, 160, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        label.setIcon(ScaledIcon);
        thirdBorder.add(label);

        webcamBtn.setFont(new Font("SourceSansPro", Font.BOLD, 14));
        webcamBtn.setBounds(58, 210, 180, 37);
        webcamBtn.addActionListener(this);
        thirdBorder.add(webcamBtn);

        btnNewButton = new JButton("Register");
        btnNewButton.setFont(new Font("SourceSansPro", Font.BOLD, 14));
        btnNewButton.setBounds(90, 260, 120, 37);
        btnNewButton.addActionListener(this);
        thirdBorder.add(btnNewButton);

        btnReturn = new JButton("Return");
        btnReturn.setBounds(90, 310, 120, 37);
        btnReturn.addActionListener(this);
        thirdBorder.add(btnReturn);
        //----------------------------------------------------------------------Color button
        webcamBtn.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnReturn.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnNewButton.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        webcamBtn.setBackground(new Color(0x424242));
        btnReturn.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(0x424242));
        webcamBtn.setForeground(Color.WHITE);
        btnReturn.setBackground(new Color(0x424242));
        btnNewButton.setForeground(Color.WHITE);

        webcamBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //Hover colour change when the cursor hovers over the Login Jbutton
        webcamBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                webcamBtn.setBackground(new Color(0x005ba3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                webcamBtn.setBackground(new Color(0x424242));
            }
        });
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnReturn.setBackground(new Color(0x005ba3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReturn.setBackground(new Color(0x424242));
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setBackground(new Color(0x005ba3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setBackground(new Color(0x424242));
            }
        });

        takePic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    /*
                writes the image that was recieved from the time the webcam was opened
                and saves it
                     */

                    ImageIO.write(w.getImage(), "jpg", new File("images\\image.jpg"));

                } catch (IOException ex) {
                    Logger.getLogger(VisitorRegistration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        exitCam.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                w.close();//switches off the webcam
                webcamWindow.setVisible(false);
                setImage();
            }

        });

        //----------------------------------------------------------------------Design
        border.setBackground(new Color(0x005ba3));
        secondBorder.setBackground(new Color(0x03a9f4));
        secondBorder.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        thirdBorder.setBackground(new Color(0x03a9f4));
        thirdBorder.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

        //-----------------------------------------------textboxes
        firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        firstname.setBackground(new Color(0x424242));
        firstname.setForeground(Color.WHITE);
        firstname.setCaretColor(Color.WHITE);
        firstname.setCaretColor(Color.WHITE);

        lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        lastname.setBackground(new Color(0x424242));
        lastname.setForeground(Color.WHITE);
        lastname.setCaretColor(Color.WHITE);
        lastname.setCaretColor(Color.WHITE);

        mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        mob.setBackground(new Color(0x424242));
        mob.setForeground(Color.WHITE);
        mob.setCaretColor(Color.WHITE);
        mob.setCaretColor(Color.WHITE);

        company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        company.setBackground(new Color(0x424242));
        company.setForeground(Color.WHITE);
        company.setCaretColor(Color.WHITE);
        company.setCaretColor(Color.WHITE);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void iconImg() {

        ImageIcon userimage = new ImageIcon("images\\icon.png");
        lblIcon.setBounds(130, 30, 180, 170);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(200, 190, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(ScaledIcon);
        secondBorder.add(lblIcon);

    }

    public void setImage() {
        label.setVisible(false);
        lblImage.setVisible(true);
        ImageIcon userimageTwo = new ImageIcon("images\\image.jpg");
        lblImage.setBounds(58, 30, 180, 160);
        lblImage.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        Image imgTwo = userimageTwo.getImage();
        Image imgScaleTwo = imgTwo.getScaledInstance(180, 160, Image.SCALE_SMOOTH);
        ImageIcon ScaledIconTwo = new ImageIcon(imgScaleTwo);
        lblImage.setIcon(ScaledIconTwo);
        thirdBorder.add(lblImage);

    }
    public void userResgistrationValidation() {
        Date recentDate = new Date();
        SimpleDateFormat dateStamp = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeStamp = new SimpleDateFormat("h:mm:ss a");
        String dateuser = dateStamp.format(recentDate);
        String timeuser = timeStamp.format(recentDate);

        String firstName = firstname.getText();
        String lastName = lastname.getText();
        String companyName = company.getText();
        String mobileNumber = mob.getText();
        String date = dateuser;
        String time_In = timeuser;
        String reason = "default";
        int len = mobileNumber.length();
        String sql = "SELECT 1 FROM visitors WHERE mobileNumber = ?";
        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, mob.getText());
            rs = ps.executeQuery();

            if (rs.next()) {

                mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                JOptionPane.showMessageDialog(null, "This mobile number is in use by another user");
                mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                mob.setText("");

            } else {
                if (firstname.getText().isEmpty() && lastname.getText().isEmpty() && mob.getText().isEmpty() && company.getText().isEmpty()) {

                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name, Last Name, Mobile Number and Company Name'");
                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (firstname.getText().isEmpty() && mob.getText().isEmpty() && company.getText().isEmpty()) {

                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name, Mobile Number and Company Name'");

                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (lastname.getText().isEmpty() && mob.getText().isEmpty() && company.getText().isEmpty()) {

                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'Last Name, Mobile Number and Company Name'");

                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (firstname.getText().isEmpty() && lastname.getText().isEmpty() && company.getText().isEmpty()) {

                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name, Last Name and Company Name'");

                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (firstname.getText().isEmpty() && lastname.getText().isEmpty() && mob.getText().isEmpty()) {

                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name, Last Name and Mobile Number'");

                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (lastname.getText().isEmpty() && company.getText().isEmpty()) {

                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'Last Name and Company Name'");

                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (firstname.getText().isEmpty() && mob.getText().isEmpty()) {

                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name and Mobile Number'");

                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (firstname.getText().isEmpty() && lastname.getText().isEmpty()) {

                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name and Last Name'");

                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (mob.getText().isEmpty() && company.getText().isEmpty()) {

                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'Mobile Number and Company Name'");

                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (mob.getText().isEmpty() && lastname.getText().isEmpty()) {

                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'Mobile Number and Last Name'");

                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (firstname.getText().isEmpty() && company.getText().isEmpty()) {

                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name and Company Name'");

                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                } else if (firstname.getText().isEmpty()) {

                    firstname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'First Name'");

                    firstname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

                } else if (lastname.getText().isEmpty()) {

                    lastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'Last Name'");

                    lastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

                } else if (mob.getText().isEmpty()) {

                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'Mobile Number'");

                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

                } else if (company.getText().isEmpty()) {

                    company.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter the missing values 'Company Name'");

                    company.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

                }
                else if (len <= 9) {

                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter a valid Mobile Number");

                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

                }
                else if (len > 10) {

                    mob.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                    JOptionPane.showMessageDialog(null, "Please Enter a valid Mobile Number");

                    mob.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

                }
                
                
                else {
                    String query = "INSERT INTO visitors(mobileNumber,firstName,lastName,company,time_in,date,reason,image) VALUES(?,?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(query);
                    try {
                        FileInputStream fis;
                        int numberOfRows = 0;
                        File img = new File("images\\image.jpg");
                        fis = new FileInputStream(img);

                        //creating byte array 
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        for (int readNumber; (readNumber = fis.read(buffer)) != -1;) {

                            baos.write(buffer, 0, readNumber);
                        }
                        fis.close();

                        ps.setString(1, mobileNumber);
                        ps.setString(2, firstName);
                        ps.setString(3, lastName);
                        ps.setString(4, companyName);
                        ps.setString(5, time_In);
                        ps.setString(6, date);
                        ps.setString(7, reason);
                        ps.setBytes(8, baos.toByteArray());

                        int x = ps.executeUpdate();
                        String name = firstname.getText();
                        String surname = lastname.getText();
                        if (x > 0) {
                            JOptionPane.showMessageDialog(null, "Welcome " + name + " "+ surname + " Your account is sucessfully created");
                            firstname.setText("");
                            lastname.setText("");
                            mob.setText("");
                            company.setText("");
                            lblImage.setVisible(false);
                            label.setVisible(true);
                        }
                    } catch (Exception e) {

                        System.out.println("The Error in the registration is: " + e);

                    }
                }

            }

        } catch (Exception e) {
            System.out.println("The Error is: " + e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            window.setVisible(false);
            Main rg = new Main();
            rg.startProgram();
            dispose();
        }
        if (e.getSource() == btnNewButton) {

            userResgistrationValidation();

        }
        if (e.getSource() == webcamBtn) {

            webcamWindow.setVisible(true);

        }
        /*if(e.getSource() == takePic){
            try {
                
                writes the image that was recieved from the time the webcam was opened
                and saves it
                
                
                ImageIO.write(w.getImage(), "jpg", new File("images\\image.jpg"));
                
                
            } catch (IOException ex) {
                Logger.getLogger(VisitorRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }*/
 /*if (e.getSource() == exitCam) {

            w.close();//switches off the webcam
            webcamWindow.setVisible(false);

        }*/
    }

}
