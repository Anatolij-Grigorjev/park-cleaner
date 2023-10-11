package com.tiem625.parkcleaner.testsupport;

import com.badlogic.gdx.graphics.Texture;
import com.tiem625.parkcleaner.domain.Size;
import org.mockito.Mockito;

public class GdxScaffolding {

    private GdxScaffolding() {
        throw new UnsupportedOperationException("static helper");
    }

    public static Texture mockTexture(Size ofSize) {
        var texture = Mockito.mock(Texture.class);
        Mockito.when(texture.getWidth()).thenReturn((int)ofSize.width());
        Mockito.when(texture.getHeight()).thenReturn((int)ofSize.height());

        return texture;
    }
}
