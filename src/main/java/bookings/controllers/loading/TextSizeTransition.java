package bookings.controllers.loading;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.lang.reflect.Field;

public class TextSizeTransition extends Transition {

    private final Text text;
    private final int startFontSize;
    private final int finalFontSize;
    private final String customFontURLString;

    public TextSizeTransition(Text text, int startFontSize, int finalFontSize, String customFontURLString , Duration duration) {
        this.text = text;
        this.startFontSize = startFontSize;
        this.finalFontSize = finalFontSize - startFontSize;
        this.customFontURLString = customFontURLString;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double frac) {
        int size = (int) ((finalFontSize * frac) + startFontSize);
        System.out.println(size);
        if(size<= finalFontSize) {
            Font font = Font.loadFont(customFontURLString,size);

            text.setFont(font);
        }
    }
}