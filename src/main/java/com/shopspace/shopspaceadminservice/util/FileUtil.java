package com.shopspace.shopspaceadminservice.util;

import com.shopspace.shopspaceadminservice.exception.ConflictException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class FileUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${files.validations.availableFormats}")
    private Set<String> defaultAvailableFormats;

    public File base64ToFile(String base64) {
        return this.base64ToFile(base64, null);
    }

    public File base64ToFile(String base64, String name) {
        try {
            if (name == null) {
                LocalDateTime fechaActual = LocalDateTime.now();
                // Get timestamp in seconds
                long timestamp = fechaActual.toEpochSecond(ZoneOffset.UTC);
                name = "file_" + timestamp;
            }

            String symbol = Pattern.quote(",");
            String format = "." + this.getFormatFromBase64(base64);
            String path = this.getPublicPathOS() + name + (name.contains(format) ? "" : format);
            String[] formattedBase64 = base64.split(symbol);

            byte[] data = Base64.decodeBase64(formattedBase64[1]);
            try (OutputStream stream = new FileOutputStream(path)) {
                stream.write(data);
            }

            return new File(path);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public String getFormatFromBase64(String base64) {
        try {
            String firstSymbol = Pattern.quote(";");
            String secondSymbol = Pattern.quote(":");
            String thirdSymbol = Pattern.quote("/");

            String[] first = base64.split(firstSymbol);
            String[] second = first[0].split(secondSymbol);
            String[] third = second[1].split(thirdSymbol);

            return third[1];
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public String mediaTypeFromName(String name) {
        String symbol = Pattern.quote(".");
        String[] nameSplited = name.split(symbol);
        String format = nameSplited[nameSplited.length - 1];

        return this.mediaTypeFromFormat(format);
    }

    public String mediaTypeFromFormat(String format) {
        switch (format) {
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG_VALUE;
            case "png":
                return MediaType.IMAGE_PNG_VALUE;
            case "webp":
                return "image/webp";
            default:
                break;
        }

        return null;
    }

    /**
     * Get the name from a path
     *
     * @param path The path of the file from which you want to get the name
     * @return The file name as String including the format for example: image.png,
     *         sound.mp3, video.mp4
     */
    public String getNameFromPath(String path) {
        try {
            if (path.contains("/")) {
                String symbol = Pattern.quote("/");

                String[] pathSplited = path.split(symbol);

                return pathSplited[pathSplited.length - 1];
            }

            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getFolderFromPath(String path) {
        String name = this.getNameFromPath(path);

        return path.replace("/" + name, "");
    }

    public String getPublicPathOS() {
        try {
            String os = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

            if (os.equals("linux")) return "/tmp";

            ClassLoader classLoader = getClass().getClassLoader();
            String path = Objects.requireNonNull(classLoader.getResource(".")).getFile() + "tmp/";

            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdir();
                folder.setReadable(true);
                folder.setWritable(true);
            }

            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void safeCloseAndDelete(File f) {
        try {
            f.delete();
        } catch (Exception e) {
            logger.error("An error occurred while trying to save the file securely");
            e.printStackTrace();
        }
    }

    public int getFileSizeKb(File f) {
        try {
            return (int) (f.length() / 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean validateFile(File f, int minSize, int maxSize) {
        return this.validateFile(f, minSize, maxSize, null);
    }

    public boolean validateFile(File f, int minSize, int maxSize, Set<String> formatsAvailable) {
        String symbol = Pattern.quote(".");

        if (formatsAvailable == null) {
            formatsAvailable = this.defaultAvailableFormats;
        }

        int sizeKb = this.getFileSizeKb(f);
        if (sizeKb < minSize)
            throw new ConflictException("An error occurred while validating the minimum file size which should be: " + minSize + " kb");


        if (sizeKb > maxSize)
            throw new ConflictException("An error occurred while validating the maximum file size which should be: " + minSize + " kb");

        String[] fileDataName = f.getName().split(symbol);
        String format = fileDataName[1];
        boolean isValidateFormat = formatsAvailable.contains(format);

        if (!isValidateFormat)
            throw new ConflictException("An error occurred validating the file extension which should be: " + formatsAvailable);

        return true;
    }
}
