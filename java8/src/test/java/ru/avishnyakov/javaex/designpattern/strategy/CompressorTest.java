package ru.avishnyakov.javaex.designpattern.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

public class CompressorTest {
    @TempDir
    File tempDir;
    Path inFile;

    private Path createFile(String fileName) throws IOException {
        final Path pathTempDir = tempDir.getAbsoluteFile().toPath();
        final Path inFile = pathTempDir.resolve(fileName);
        return Files.createFile(inFile);
    }

    @BeforeEach
    public void beforeMethod() throws IOException {
        inFile = createFile("InputFile.txt");
        Files.write(inFile, "test".getBytes());

    }

    @Test
    public void testGzipCompressionStrategy() throws IOException {
        final Path outFile = createFile("OutputFile.gz");
        final Compressor compressor = new Compressor(new GzipCompressionStrategy());
        compressor.compress(inFile, outFile.toFile());
    }

    @Test
    public void testZipCompressionStrategy() throws IOException {
        final Path outFile = createFile("OutputFile.zip");
        final Compressor compressor = new Compressor(new ZipCompressionStrategy());
        compressor.compress(inFile, outFile.toFile());
    }

    @Test
    public void testGzipOutputStream() throws IOException {
        final Path outFile = createFile("OutputFile.gz");
        final Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
        gzipCompressor.compress(inFile, outFile.toFile());
    }

    @Test
    public void testZipOutputStream() throws IOException {
        final Path outFile = createFile("OutputFile.zip");
        final Compressor zipCompressor = new Compressor(ZipOutputStream::new);
        zipCompressor.compress(inFile, outFile.toFile());
    }
}
