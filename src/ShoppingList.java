import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.activation.*;

public class ShoppingList {
	private ArrayList<Recipe> recipes;
	private Hashtable<String, Double> list = new Hashtable<>();
	private Button sendMail = new Button("שלח לי למייל");

	public ShoppingList(ArrayList<Recipe> currentRecipes) {
		for (int i = 0; i < currentRecipes.size(); i++) {
			Set<String> keys = currentRecipes.get(i).getIngredients().keySet();
			for (String ingre : keys) {
				if (list.containsKey(ingre)) {
					list.put(ingre, list.get(ingre) + currentRecipes.get(i).getIngredients().get(ingre));

				} else {
					list.put(ingre, currentRecipes.get(i).getIngredients().get(ingre));
				}
			}
		}
		System.out.println(list.toString() + "\n");
	}

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Pane toPane() {
		TextArea txt = new TextArea();
		String[] shopList = list.toString().replace("{", "").replace("}", "").split(",");
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < shopList.length; i++) {
			str.append(shopList[i] + "\n");
		}
		txt.setText(str.toString());
		txt.setWrapText(true);
		txt.setEditable(false);
		txt.setFont(new Font("Segoe Script", 22));
		txt.setMaxWidth(300);
//		txt.setAl
		VBox box = new VBox();
		box.getChildren().add(sendMail);
		sendMail.setOnAction(e -> {
		    JFrame frame = new JFrame("InputDialog Example #1");
			String Email = JOptionPane.showInputDialog(frame, "What's your email?");
			sentEMail(str.toString(),Email);
		});
//		Label l = new Label(str.toString());
		box.getChildren().add(txt);
		return box;
	}

	private void sentEMail(String ShoppingList, String Email) {
		final String username = "itayzemah@gmail.com";
		final String password = "Zemah1994";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("itayzemah@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(Email));
			message.setSubject("Your Shopping List");
			message.setText("Dear User\nHere is you shopping list:\n," +ShoppingList+ "\n\n Please do not spam my email!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

//	public Hashtable<String, Number> getList() {
//		return list;
//	}

//	public void setList(Hashtable<String, Number> list) {
//		this.list = list;
//	}
}
