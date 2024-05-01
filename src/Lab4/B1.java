package Lab4;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class B1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter directory path: ");
        String directoryPath = scanner.nextLine();

        Path path = Paths.get(directoryPath);

        if (!Files.exists(path) || !Files.isDirectory(path)) {
            System.err.println("Invalid directory path.");
            return;
        }

        try (PrintWriter writer = new PrintWriter("directory_structure.xml")) {
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            writer.println("<directory>");

            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                int depth = 0;

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    writer.println(indent(depth) + "<" + dir.getFileName() + ">");
                    depth++;
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    writer.println(indent(depth) + "<file>" + file.getFileName() + "</file>");
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    depth--;
                    writer.println(indent(depth) + "</" + dir.getFileName() + ">");
                    return FileVisitResult.CONTINUE;
                }

                private String indent(int depth) {
                    return "  ".repeat(depth);
                }
            });

            writer.println("</directory>");
            System.out.println("XML file created successfully.");
        } catch (IOException e) {
            System.err.println("Failed to create XML file.");
            e.printStackTrace();
        }
    }
}
