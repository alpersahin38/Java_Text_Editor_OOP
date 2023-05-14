package ce204_hw3_test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Controller.Command;
import Controller.CommandHistory;
import Controller.CopyCommand;
import Controller.CutCommand;
import Controller.PasteCommand;

import org.junit.Assert;
import View.Editor;

public class AppTest {

    private CopyCommand copyCommand;
    private CommandHistory commandHistory;
    private Editor editor;
    private PasteCommand pasteCommand;
    private CutCommand cutCommand;

    @Before
    public void setUp() {
        commandHistory = new CommandHistory();
        editor = new Editor();
        copyCommand = new CopyCommand(editor);
        pasteCommand = new PasteCommand(editor);
    }


    @Test
    public void TestUndo_1() {
        editor.textField.setText("Merhaba");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(2);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();

        assertEquals("Merhaba", editor.textField.getText());
    }

    @Test
    public void TestUndo_2() {
        editor.textField.setText("Lorem ipsum dolor");
        editor.textField.setSelectionStart(3);
        editor.textField.setSelectionEnd(8);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();

        assertEquals("Lorem ipsum dolor", editor.textField.getText());
    }

    @Test
    public void TestUndo_3() {
        editor.textField.setText("Alper Şahin");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(3);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();

        assertEquals("Alper Şahin", editor.textField.getText());
    }

    @Test
    public void TestUndo_4() {
        editor.textField.setText("Deneme");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(3);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();

        assertEquals("Deneme", editor.textField.getText());
    }

    @Test
    public void TestUndo_5() {
        editor.textField.setText("Lorem ipsum dolor sit amet");
        editor.textField.setSelectionStart(8);
        editor.textField.setSelectionEnd(10);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();

        assertEquals("Lorem ipsum dolor sit amet", editor.textField.getText());
    }

    @Test
    public void TestUndo_6() {
        editor.textField.setText("Monster Notebook");
        editor.textField.setSelectionStart(1);
        editor.textField.setSelectionEnd(6);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();

        assertEquals("Monster Notebook", editor.textField.getText());
    }

    @Test
    public void TestRedo_1() {
        editor.textField.setText("Hello");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(2);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();
        editor.redo();
        assertEquals("Hello", editor.textField.getText());
    }

    @Test
    public void TestRedo_2() {
        editor.textField.setText("Lorem ipsum dolor sit amet");
        editor.textField.setSelectionStart(3);
        editor.textField.setSelectionEnd(8);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();
        editor.redo();
        assertEquals("Lorem ipsum dolor sit amet", editor.textField.getText());
    }

    @Test
    public void TestRedo_3() {
        editor.textField.setText("32152");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(3);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();
        editor.redo();
        assertEquals("32152", editor.textField.getText());
    }

    @Test
    public void TestRedo_4() {
        editor.textField.setText("Cna");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(3);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();
        editor.redo();

        assertEquals("Cna", editor.textField.getText());
    }

    @Test
    public void TestRedo_5() {
        editor.textField.setText("Lorem ipsum dolor sit amet");
        editor.textField.setSelectionStart(8);
        editor.textField.setSelectionEnd(10);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();
        editor.redo();
        assertEquals("Lorem ipsum dolor sit amet", editor.textField.getText());
    }

    @Test
    public void TestRedo_6() {
        editor.textField.setText("Hi Turkish");
        editor.textField.setSelectionStart(1);
        editor.textField.setSelectionEnd(6);
        editor.executeCommand(new CutCommand(editor));

        editor.undo();
        editor.redo();
        assertEquals("Hi Turkish", editor.textField.getText());
    }

    @Test
    public void Test_Copy_Without_Selection() {
        editor.textField.setText("Hello, World!");

        boolean result = copyCommand.execute();
        assertFalse(result);
        assertNull(editor.clipboard);
    }

    @Test
    public void Test_CopyEmptyText() {
        editor.textField.setText("");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(0);

        boolean result = copyCommand.execute();
        assertFalse(result);
        assertNull(editor.clipboard);
    }
  

    @Test
    public void TestCopy_1() {
        editor.textField.setText("Hello, World!");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(5);

    	editor.executeCommand(new CopyCommand(editor));
        assertEquals("Hello", editor.clipboard);
    }
    @Test
    public void TestCopy_2() {
        editor.textField.setText("Ne yazabilirim");
        editor.textField.setSelectionStart(2);
        editor.textField.setSelectionEnd(8);

    	editor.executeCommand(new CopyCommand(editor));
        assertEquals(" yazab", editor.clipboard);
    }
    @Test
    public void TestCopy_3() {
        editor.textField.setText("Hi Guys");
        editor.textField.setSelectionStart(3);
        editor.textField.setSelectionEnd(7);

    	editor.executeCommand(new CopyCommand(editor));
        assertEquals("Guys", editor.clipboard);
    }

    @Test
    public void TestCopy_4() {
        editor.textField.setText("Alper Hüseyin");
        editor.textField.setSelectionStart(8);
        editor.textField.setSelectionEnd(17);

    	editor.executeCommand(new CopyCommand(editor));
        assertEquals("seyin", editor.clipboard);
    }


    @Test
    public void TestPaste_1() {
        editor.clipboard = "World";
    	editor.textField.setText(" Hello");


        pasteCommand = new PasteCommand(editor);
    	editor.executeCommand(new PasteCommand(editor));
        assertEquals("World Hello", editor.textField.getText());
    }
    @Test
    public void TestPaste_2() {
        editor.clipboard = "Hi";
    	editor.textField.setText(" Guys");


        pasteCommand = new PasteCommand(editor);
    	editor.executeCommand(new PasteCommand(editor));
        assertEquals("Hi Guys", editor.textField.getText());
    }
    @Test
    public void TestPaste_3() {
        editor.clipboard = "Alper";
    	editor.textField.setText(" Alper");


        pasteCommand = new PasteCommand(editor);
    	editor.executeCommand(new PasteCommand(editor));
        assertEquals("Alper Alper", editor.textField.getText());
    }
    @Test
    public void TestPaste_NoText_1() {
        editor.textField.setText("");
        editor.clipboard = "World";

        pasteCommand = new PasteCommand(editor);
    	editor.executeCommand(new PasteCommand(editor));
        assertEquals("World", editor.textField.getText());
    }
    @Test
    public void TestPaste_NoText_2() {
        editor.textField.setText("");
        editor.clipboard = "Kayseri";

        pasteCommand = new PasteCommand(editor);
    	editor.executeCommand(new PasteCommand(editor));
        assertEquals("Kayseri", editor.textField.getText());
    }
    @Test
    public void TestPaste_NoText_3() {
        editor.textField.setText("");
        editor.clipboard = "Lorem";

        pasteCommand = new PasteCommand(editor);
    	editor.executeCommand(new PasteCommand(editor));
        assertEquals("Lorem", editor.textField.getText());
    }
    @Test
    public void TestPaste_EmptyClipboard() {
        editor.textField.setText("Hello Hello Hello");
        editor.clipboard = "";

        pasteCommand = new PasteCommand(editor);
    	editor.executeCommand(new PasteCommand(editor));
        assertEquals("Hello Hello Hello", editor.textField.getText());
    }

    @Test
    public void TestCut_1() {
        editor.textField.setText("Hello, World!");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(5);

        cutCommand = new CutCommand(editor);
    	editor.executeCommand(new CutCommand(editor));
        assertEquals(", World!", editor.textField.getText());
    }
    @Test
    public void testCut_2() {
        editor.textField.setText("Cano, Kaaaa");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(5);

        cutCommand = new CutCommand(editor);
    	editor.executeCommand(new CutCommand(editor));
        assertEquals(" Kaaaa", editor.textField.getText());
    }
    @Test
    public void testCut_3() {
        editor.textField.setText("Alper, Merhaba");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(5);

        cutCommand = new CutCommand(editor);
    	editor.executeCommand(new CutCommand(editor));
        assertEquals(", Merhaba", editor.textField.getText());
    }

    @Test
    public void TestCut_NoSelection() {
        editor.textField.setText("Alper Hüseyin");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(0);

        cutCommand = new CutCommand(editor);
        assertEquals("Alper Hüseyin", editor.textField.getText());
    }

    @Test
    public void TestCut_Undo() {
        editor.textField.setText("Hüseyin, Napıyorsun");
        editor.textField.setSelectionStart(0);
        editor.textField.setSelectionEnd(7);

        cutCommand = new CutCommand(editor);
    	editor.executeCommand(new CutCommand(editor));
        assertEquals(", Napıyorsun", editor.textField.getText());
        assertEquals("Hüseyin", editor.clipboard);

        editor.undo();
        assertEquals("Hüseyin, Napıyorsun", editor.textField.getText());
    }

}
