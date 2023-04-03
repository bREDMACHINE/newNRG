package get.a.big.head.newNRG.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataFileToMultipart implements MultipartFile {

    private final DataFile dataFile;

    public DataFileToMultipart(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public String getName() {
        return dataFile.getName();
    }

    @Override
    public String getOriginalFilename() {
        return dataFile.getName();
    }

    @Override
    public String getContentType() {
        return dataFile.getType();
    }

    @Override
    public boolean isEmpty() {
        return dataFile.getSize() == 0;
    }

    @Override
    public long getSize() {
        return dataFile.getSize();
    }

    @Override
    public byte[] getBytes() {
        return dataFile.getContent();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(dataFile.getName());
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
}
