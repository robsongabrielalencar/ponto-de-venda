
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MemoryGame extends JFrame {

	private JPanel cardPanel;
	private List<JButton> buttons;
	private List<Integer> cardValues;
	private int firstCardIndex = -1;
	private int secondCardIndex = -1;

	public MemoryGame() {
		setTitle("Memory Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);

		cardPanel = new JPanel(new GridLayout(4, 4));
		buttons = new ArrayList<>();
		cardValues = new ArrayList<>();

		for (int i = 0; i < 16; i++) {
			JButton button = new JButton();
			button.addActionListener(new CardClickListener(i));
			buttons.add(button);
			cardPanel.add(button);
		}

		getContentPane().add(cardPanel);

		initializeCards();
	}

	private void initializeCards() {
		List<Integer> values = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			values.add(i);
			values.add(i);
		}
		Collections.shuffle(values);

		for (int i = 0; i < buttons.size(); i++) {
			cardValues.add(values.get(i));
		}
	}

	private class CardClickListener implements ActionListener {
		private final int index;

		public CardClickListener(int index) {
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (firstCardIndex == -1) {
				firstCardIndex = index;
				buttons.get(index).setText(cardValues.get(index).toString());
			} else if (secondCardIndex == -1 && index != firstCardIndex) {
				secondCardIndex = index;
				buttons.get(index).setText(cardValues.get(index).toString());

				if (cardValues.get(firstCardIndex).equals(cardValues.get(secondCardIndex))) {
					buttons.get(firstCardIndex).setEnabled(false);
					buttons.get(secondCardIndex).setEnabled(false);
					firstCardIndex = -1;
					secondCardIndex = -1;
				} else {
					SwingUtilities.invokeLater(() -> {
						buttons.get(firstCardIndex).setText("");
						buttons.get(secondCardIndex).setText("");
						firstCardIndex = -1;
						secondCardIndex = -1;
					});
				}
			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MemoryGame game = new MemoryGame();
			game.setVisible(true);
		});
	}

}
