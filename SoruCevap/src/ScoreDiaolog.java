import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScoreDiaolog extends JDialog {
	JButton btnYenidenOyna;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	int score = 0;

	public ScoreDiaolog(int score) {
		this.score = score;
		setModal(true);
		setResizable(false);
		setBackground(new Color(148, 0, 211));
		setBounds(100, 100, 340, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblSkorunuz = new JLabel("Önceki Skor :");
			lblSkorunuz.setForeground(new Color(0, 0, 0));
			lblSkorunuz.setFont(new Font("Tahoma", Font.ITALIC, 18));
			lblSkorunuz.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblSkorunuz, BorderLayout.NORTH);
		}
		{
			JLabel lblNewLabel = new JLabel("YENİ SKOR:" + score);
			lblNewLabel.setBackground(new Color(119, 136, 153));
			lblNewLabel.setForeground(new Color(0, 128, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				btnYenidenOyna = new JButton("YENİDEN OYNA");
				btnYenidenOyna.setBackground(new Color(60, 179, 113));
				btnYenidenOyna.setFont(new Font("Tahoma", Font.PLAIN, 18));
				buttonPane.add(btnYenidenOyna);
			}
			{
				JButton btnCik = new JButton("ÇIK");
				btnCik.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						System.exit(0);
					}
				});
				btnCik.setBackground(new Color(204, 51, 51));
				btnCik.setFont(new Font("Tahoma", Font.PLAIN, 18));
				buttonPane.add(btnCik, BorderLayout.EAST);
			}
		}
	}

}
