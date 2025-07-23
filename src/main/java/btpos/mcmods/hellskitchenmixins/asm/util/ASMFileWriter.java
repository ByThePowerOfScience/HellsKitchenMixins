package btpos.mcmods.hellskitchenmixins.asm.util;

import btpos.mcmods.hellskitchenmixins.asm.Transformers;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ASMFileWriter {
    public static final File ASM_OUT_DIR = new File(".asm.out");
    public static boolean shouldWrite = true;
    
    static {
        if (!ASM_OUT_DIR.exists()) {
            ASM_OUT_DIR.mkdir();
        } else if (!ASM_OUT_DIR.isDirectory()) {
            Transformers.LOGGER.error("Unable to output transformers to {}: is file!", ASM_OUT_DIR.getPath());
            shouldWrite = false;
        }
    }
    
    public static void clear(){
        if (shouldWrite) {
            try {
                FileUtils.deleteDirectory(ASM_OUT_DIR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static void printClass(ClassNode node) {
        var writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        printClass(node.name, writer.toByteArray());
    }
    public static void printClass(String name, byte[] bytes) {
        var outfile = ASM_OUT_DIR.toPath().resolve(name.replace(".", File.separator) + ".class");
        try {
        	FileUtils.createParentDirectories(outfile.toFile());
            try (var os = Files.newOutputStream(outfile)) {
                os.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
        	Transformers.LOGGER.warn("Unable to write transformed class \"{}\" to output file.", name);
        }
    }
}
