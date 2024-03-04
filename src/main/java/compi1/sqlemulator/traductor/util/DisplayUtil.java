
package compi1.sqlemulator.traductor.util;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

/**
 *
 * @author yenni
 */
public class DisplayUtil {
    public void appendText(JTextPane display, String text) throws BadLocationException{
        StyledDocument doc = display.getStyledDocument();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        doc.insertString(doc.getLength(), text, attributeSet);
    }
}
