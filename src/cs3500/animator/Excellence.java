package cs3500.animator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.ViewFactory;

// Represents the main point of entry for the Animator program.
public final class Excellence {

  /**
   * Main method for Animator program. Takes in user input and creates/runs Animator as such.
   * @param args command line args.
   */
  public static void main(String[] args) {

    Readable rd;
    AnimatorModel model;
    ViewFactory factory = new ViewFactory();
    AnimatorView view;

    String readFile = "";
    String outFile = "";
    int tps = 1;
    String viewType = "text";

    try {
      for (int i = 0; i < args.length - 1; i++) {

        if (args[i].equals("-in")) {
          readFile = args[i + 1];
        }

        if (args[i].equals("-out")) {
          outFile = args[i + 1];
        }

        if (args[i].equals("-view")) {
          viewType = args[i + 1];
        }

        if (args[i].equals("-speed")) {
          tps = Integer.parseInt(args[i + 1]);
        }
      }
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(new JFrame(), "Invalid arguments", "Arguments Error",
              JOptionPane.WARNING_MESSAGE);
      System.exit(0);
    }

    try {
      rd = new BufferedReader(new FileReader(readFile));
      model = AnimationReader.parseFile(rd, new AnimatorModelImpl.Builder());

      view = factory.getView(viewType, tps, model);
      view.makeVisible();

      view.writeToFile(outFile);

    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(new JFrame(),"Input file not found", "File Not Found",
              JOptionPane.WARNING_MESSAGE);
      System.exit(0);
    }
  }
}