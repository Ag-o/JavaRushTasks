package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes imageTypes) {
        ImageReader imageReader = null;

        if (ImageTypes.JPG.equals(imageTypes)) {
            imageReader = new JpgReader();
        } else if (ImageTypes.BMP.equals(imageTypes)) {
            imageReader = new BmpReader();
        } else if (ImageTypes.PNG.equals(imageTypes)) {
            imageReader = new PngReader();
        } else throw new IllegalArgumentException("Неизвестный тип картинки");

        return imageReader;
    }
}
