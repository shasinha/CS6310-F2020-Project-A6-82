import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class StreamingWarsUI {
    public static void main(String[] args) throws IOException {

        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        // Create panel to hold components
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));


        TerminalSize size = new TerminalSize(30, 10);
        ActionListBox actionListBox = new ActionListBox(size);
        
        actionListBox.addItem("Create Demo", new Runnable() {
          @Override
          public void run() {
            // Code to run when action activated
          }
        });

        actionListBox.addItem("Create Studio", new Runnable() {
          @Override
          public void run() {
            // Code to run when action activated
          }
        });

        actionListBox.addItem("Create Event", new Runnable() {
          @Override
          public void run() {
            // Code to run when action activated
          }
        });

        actionListBox.addItem("Create Stream", new Runnable() {
          @Override
          public void run() {
            // Code to run when action activated
          }
        });

        panel.addComponent(actionListBox);

        /*
        
        panel.addComponent(new Label("Surname"));
        panel.addComponent(new TextBox());

        panel.addComponent(new EmptySpace(new TerminalSize(0,0))); // Empty space underneath labels
        panel.addComponent(new Button("Submit"));
        */
        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setComponent(panel);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);

    }
}