import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MainWindow extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTextArea txtAreaSoru;
	JButton btnA, btnB, btnC, btnD;
	JLabel lblHak, lblScore, lblSoruSayisiTxt, lblAsama;
	FileOperations file;

	String path, verilenCevap, dogruCevap, tiklananBtnMetin;
	int maxSoruSayisi, mevcutSoruIndex = 1, score = 0, hak = 3;
	boolean btnsClickable = true;

	public MainWindow() {
		path = "C:\\Users\\Fatih\\Desktop\\sorucevap.xlsx";
		file = new FileOperations(path);
		maxSoruSayisi = file.soruSayisi;

		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLocationByPlatform(true);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Soru Cevap Oyunu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel solPanel = new JPanel();
		solPanel.setBackground(new Color(47, 79, 79));
		solPanel.setBounds(0, 0, 100, 534);
		contentPane.add(solPanel);

		JPanel sagPanel = new JPanel();
		sagPanel.setBackground(new Color(47, 79, 79));
		sagPanel.setBounds(765, 0, 100, 534);
		contentPane.add(sagPanel);

		JPanel soruPanel = new JPanel();

		soruPanel.setBounds(110, 0, 645, 255);
		contentPane.add(soruPanel);

		JPanel sikPanel = new JPanel();
		sikPanel.setBounds(110, 266, 645, 175);
		contentPane.add(sikPanel);
		sikPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel altPanel = new JPanel();

		altPanel.setBounds(110, 452, 645, 72);
		contentPane.add(altPanel);
		altPanel.setLayout(null);

		lblHak = new JLabel("Kalan Hakkınız:" + hak);
		lblHak.setBounds(20, 36, 150, 25);
		lblHak.setHorizontalAlignment(SwingConstants.CENTER);
		lblHak.setFont(new Font("Tahoma", Font.PLAIN, 18));
		altPanel.add(lblHak);

		lblScore = new JLabel("Skor: " + score);
		lblScore.setBounds(20, 11, 150, 25);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		altPanel.add(lblScore);

		lblAsama = new JLabel("01/" + maxSoruSayisi);
		lblAsama.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsama.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAsama.setBounds(516, 36, 104, 25);
		altPanel.add(lblAsama);

		lblSoruSayisiTxt = new JLabel("Soru Sayısı");
		lblSoruSayisiTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoruSayisiTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoruSayisiTxt.setBounds(516, 11, 104, 25);
		altPanel.add(lblSoruSayisiTxt);
		soruPanel.setLayout(new BorderLayout(0, 0));

		txtAreaSoru = new JTextArea();
		txtAreaSoru.setWrapStyleWord(true);
		txtAreaSoru.setMargin(new Insets(36, 36, 36, 36));
		txtAreaSoru.setEditable(false);
		txtAreaSoru.setLineWrap(true);
		txtAreaSoru.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtAreaSoru.setSize(100, 50);
		soruPanel.add(txtAreaSoru);

		btnA = new JButton();
		btnA.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnA.setName("A");
		sikPanel.add(btnA);
		btnA.addActionListener(this);
		btnA.addMouseListener(this);

		btnB = new JButton();
		btnB.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnB.setName("B");
		sikPanel.add(btnB);
		btnB.addActionListener(this);
		btnB.addMouseListener(this);

		btnC = new JButton();
		btnC.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnC.setName("C");
		sikPanel.add(btnC);
		btnC.addActionListener(this);
		btnC.addMouseListener(this);

		btnD = new JButton();
		btnD.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnD.setName("D");
		sikPanel.add(btnD);
		btnD.addActionListener(this);
		btnD.addMouseListener(this);

		if (file.readFile(path)) {
			setVisible(true);
			addQuestion(mevcutSoruIndex);

		} else {
			JOptionPane.showMessageDialog(null, "Excel dosyası okunamadı");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tiklananBtnMetin = e.getActionCommand();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (btnsClickable) {
			if (hak >= 0) {
				if (e.getComponent().getName().equals(dogruCevap)) {
					verilenCevap = e.getComponent().getName() + "-" + tiklananBtnMetin;
					trueAnswer();
					if (mevcutSoruIndex < maxSoruSayisi) {
						addQuestion(mevcutSoruIndex);
					} else {
						overQuestion();
					}
				} else {
					if (hak == 0) {
						falseAnswer();
						endGame();
					} else {
						falseAnswer();
					}

				}
			} else {
				endGame();

			}
		} else {
			System.out.println("Butonlar kullanılamaz.");
		}
	}

	public void disableAllComponents() {
		btnA.setText("");
		btnA.setEnabled(false);
		btnB.setText("");
		btnB.setEnabled(false);
		btnC.setText("");
		btnC.setEnabled(false);
		btnD.setText("");
		btnD.setEnabled(false);
		lblHak.setText("");
		lblAsama.setText("");
		lblScore.setText("");
		lblSoruSayisiTxt.setText("");
		txtAreaSoru.setText("");
		txtAreaSoru.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnsClickable = false;
	}

	public void addDialog() {
		ScoreDiaolog scoreDialog = new ScoreDiaolog(score);
		scoreDialog.btnYenidenOyna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scoreDialog.dispose();
				dispose();
				new MainWindow();
			}
		});
		scoreDialog.setLocationRelativeTo(this);
		scoreDialog.setVisible(true);
	}

	public void trueAnswer() {
		mevcutSoruIndex++;
		score += 10;
		JOptionPane.showMessageDialog(this, "Cevabınız Doğru!\n" + verilenCevap);
		lblScore.setText("Skor:" + score);

	}

	public void falseAnswer() {
		score -= 10;
		JOptionPane.showMessageDialog(this, "Yanlış Cevap");
		lblScore.setText("Skor:" + score);
		hak--;
		if (hak >= 0)
			lblHak.setText("Kalan Hakkınız:" + hak);
	}

	public void endGame() {
		JOptionPane.showMessageDialog(this, "Elendiniz.\n Skorunuz: " + score);
		System.exit(0);
	}

	public void overQuestion() {
		JOptionPane.showMessageDialog(this, "Tebrikler soruları bitirdin.");
		score = score + (hak * 5);
		addDialog();
		disableAllComponents();
	}

	public void addQuestion(int index) {
		txtAreaSoru.setText(file.soru[index].soruCumlesi);
		txtAreaSoru.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		dogruCevap = file.soru[index].dogruCevap;
		btnA.setText(file.soru[index].cevapA);
		btnB.setText(file.soru[index].cevapB);
		btnC.setText(file.soru[index].cevapC);
		btnD.setText(file.soru[index].cevapD);
		setBtnFont(btnA);
		setBtnFont(btnB);
		setBtnFont(btnC);
		setBtnFont(btnD);
		lblAsama.setText(mevcutSoruIndex + "/" + (maxSoruSayisi - 1));
	}

	public void setBtnFont(JButton btn) {
		btn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
