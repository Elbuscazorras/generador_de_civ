package window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel_Principal extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label_periodo = null;
	private JLabel label_poblacion = null;
	private JLabel label_comida = null;

	private JTextField value_periodo = null;
	private JTextField value_poblacion = null;
	private JTextField value_comida = null;

	private JLabel label_item = null;
	private JTextField value_item = null;
	private Vector<String> items = null;
	private JButton submit_item = null;
	private JButton delete_item = null;

	private JTextArea generated_text = null;

	private JButton button_siguiente_periodo = null;

	// REGLA 1: CADA PERIODO COMIDA -= POBLACION / 3

	// REGLA 2: SI COMIDA > POBLACION * 2, LA POBLACION AUMENTA UN 10% POR PERIODO

	// REGLA 3: SI COMIDA < POBLACION, MUERE (POBLACION-COMIDA)% DE POBLACION (SITUACION DE SUPERVIVENCIA)

	public Panel_Principal(Frame_Principal frame) {
		this.setSize(frame.getWidth(), frame.getHeight());
		this.setLayout(null);
		this.setBackground(new Color(150, 180, 150));

		this.items = new Vector<String>();

		this.label_periodo = new JLabel("Periodo");
		this.label_periodo.setBounds(10, 10, 100, 20);
		this.add(this.label_periodo);

		this.label_poblacion = new JLabel("Población");
		this.label_poblacion.setBounds(10, 50, 100, 20);
		this.add(this.label_poblacion);

		this.label_comida = new JLabel("Comida");
		this.label_comida.setBounds(10, 90, 100, 20);
		this.add(this.label_comida);

		this.label_item = new JLabel("Item");
		this.label_item.setBounds(10, 130, 100, 20);
		this.add(this.label_item);

		this.value_periodo = new JTextField("0");
		this.value_periodo.setBounds(130, 10, 100, 20);
		this.value_periodo.setEditable(false);
		this.add(this.value_periodo);

		this.value_poblacion = new JTextField("10");
		this.value_poblacion.setBounds(130, 50, 100, 20);
		this.add(this.value_poblacion);

		this.value_comida = new JTextField("50");
		this.value_comida.setBounds(130, 90, 100, 20);
		this.add(this.value_comida);

		this.value_item = new JTextField();
		this.value_item.setBounds(130, 130, 100, 20);
		this.add(this.value_item);

		this.submit_item = new JButton("+");
		this.submit_item.setBounds(130 + 110, 130, 50, 20);
		this.submit_item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (value_item.getText().length() < 1) {
					JOptionPane.showMessageDialog(null, "Ingresa alguna wea po");
					return;
				}
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).equals(value_item.getText())) {
						JOptionPane.showMessageDialog(null, "Ya existe ese item");
						return;
					}
				}
				items.add(value_item.getText());
				generated_text.setText(generarTextoLoquillo());
			}
		});
		this.add(this.submit_item);

		this.delete_item = new JButton("-");
		this.delete_item.setBounds(130 + 110 + 60, 130, 50, 20);
		this.delete_item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (value_item.getText().length() < 1) {
					JOptionPane.showMessageDialog(null, "Ingresa alguna wea po");
					return;
				}
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).equals(value_item.getText())) {
						items.remove(i);
						generated_text.setText(generarTextoLoquillo());
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "No existe ese item");
			}
		});
		this.add(this.delete_item);

		this.generated_text = new JTextArea();
		this.generated_text.setBounds(10, this.getHeight() - 315, this.getWidth() - 30, 200);
		this.add(this.generated_text);

		this.button_siguiente_periodo = new JButton("siguiente periodo");
		this.button_siguiente_periodo.setBounds(10, this.getHeight() - 100, this.getWidth() - 30, 50);
		this.button_siguiente_periodo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				long period = Integer.parseInt(value_periodo.getText());
				long food = Integer.parseInt(value_comida.getText());
				long population = Integer.parseInt(value_poblacion.getText());
				
				//regla1
				food -= population/3;
				if(food < 0){value_comida.setText("0");}
				else{value_comida.setText(""+food);}
				
				//regla2
				if(food > population*2){population *= 1.1;}
				
				//regla3
				if(food < population){
					float d = food * 100;
					d = d / population;
					d = d/100;
					population *= d;
				}
				
				value_poblacion.setText(""+population);
				
				if(population < 2){
					JOptionPane.showMessageDialog(null, "Murieron todos, hijos de la maraca");
				}
				
				value_periodo.setText("" + (period + 1));
				generated_text.setText(generarTextoLoquillo());
			}
		});
		this.add(this.button_siguiente_periodo);

		this.setVisible(true);
	}

	private String generarTextoLoquillo() {
		String output = "";

		output += "Periodo: " + this.value_periodo.getText() + "\n";
		output += "Población: " + this.value_poblacion.getText() + "\n";
		output += "Comida: " + this.value_comida.getText() + "\n";
		output += "Items: {";

		if (items.size() == 0) {
			output += "}";
		}

		for (int i = 0; i < items.size(); i++) {
			if (i == items.size() - 1) {
				output += items.get(i) + "}";
				break;
			}
			output += items.get(i) + ", ";
		}

		return output;
	}
}
