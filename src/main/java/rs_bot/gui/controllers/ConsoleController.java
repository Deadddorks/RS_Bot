package rs_bot.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import rs_bot.exceptions.CommandNotFoundException;
import rs_bot.exceptions.MouseOffException;
import rs_bot.input.CommandRepository;
import rs_bot.input.ConsoleCommand;
import rs_bot.output.ConsoleOutput;
import scripts.*;

import java.util.ArrayList;
import java.util.List;

public class ConsoleController
{

	private int lineNum;
	private List<String> prevCommands;
	private CommandRepository repo;
	
	private ConsoleOutput out;
	
	@FXML
	private VBox consoleLog;
	@FXML
	private TextField consoleTextField;
	
	public void initialize()
	{
		prevCommands = new ArrayList<>();
		lineNum = -1;
		
		out = new ConsoleOutput(consoleLog);
		
		repo = new CommandRepository();
		repo.add(new Test(out));
		repo.add(new Exit());
		repo.add(new MapUi(out));
		repo.add(new Clear(out));
		repo.add(new Alch(out));
	}
	
	public void enter(final KeyEvent e)
	{
		switch (e.getCode())
		{
			case ENTER:
				if (!consoleTextField.getText().replaceAll(" ", "").equals(""))
				{
					handle(consoleTextField.getText());
				}
				consoleTextField.setText("");
				break;
			case UP:
				if (lineNum + 1 < prevCommands.size())
				{
					lineNum++;
					updateLine();
				}
				break;
			case DOWN:
				if (lineNum >= 0)
				{
					lineNum--;
					updateLine();
				}
				break;
		}
	}
	
	private void handle(final String text)
	{
		consoleTextField.setDisable(true);
		
		prevCommands.add(0, text);
		lineNum = -1;
		print("> " + consoleTextField.getText() + "");
		
		ConsoleCommand command = ConsoleCommand.parse(text);
		
		Thread thread = new Thread(() ->
		{
			try
			{
				repo.run(command);
			}
			catch (CommandNotFoundException e)
			{
				Platform.runLater(() -> print(e.getCommand().getCommand() + " is not a recognized command."));
			}
			catch (MouseOffException e)
			{
				Platform.runLater(() -> print("Script stopped from mouse move."));
			}
			
			consoleTextField.setDisable(false);
		});
		
		thread.start();
	}
	
	private void updateLine()
	{
		if (lineNum == -1)
		{
			consoleTextField.setText("");
		}
		else
		{
			consoleTextField.setText(prevCommands.get(lineNum));
		}
	}
	
	private void print(final String str)
	{
		out.print(str);
	}

}
