# File I/O

# Understanding Java File I/O

File Input/Output (I/O) in Java provides mechanisms to read from and write to files. Think of it like a conversation with your computer's storage system - you're either listening (reading) or speaking (writing) to files.

## Basic Concepts

Java's I/O system is built around streams - imagine them as channels through which data flows. Just like a water stream, data flows in one direction: either into your program (input) or out of it (output).

### 1. File Handling Basics

```java
import java.io.File;

public class FileBasics {
    public static void main(String[] args) {
        // Creating a file object
        File file = new File("example.txt");
        
        // Basic file operations
        System.out.println("File exists: " + file.exists());
        System.out.println("Can read: " + file.canRead());
        System.out.println("Can write: " + file.canWrite());
        System.out.println("File size: " + file.length() + " bytes");
    }
}
```

### 2. Writing to Files

Java offers multiple ways to write to files. Here's a modern approach using try-with-resources:

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteExample {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write("Hello, File I/O!");
            writer.newLine();
            writer.write("This is a new line.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
```

### 3. Reading from Files

Reading files is just as important as writing them. Here's how to read a file line by line:

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadExample {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
```

## Best Practices

- **Always close resources:** Use try-with-resources to automatically close files
- **Use buffered streams:** BufferedReader and BufferedWriter improve performance
- **Handle exceptions properly:** File operations can fail for many reasons
- **Use proper character encoding:** Specify character encoding when dealing with text files

## Error Handling

Here's a comprehensive example of proper error handling in File I/O:

```java
import java.io.*;
import java.nio.charset.StandardCharsets;

public class RobustFileHandler {
    public static void writeToFile(String fileName, String content) {
        File file = new File(fileName);
        
        // Create directory if it doesn't exist
        file.getParentFile().mkdirs();
        
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(file), StandardCharsets.UTF_8))) {
            
            writer.write(content);
            
        } catch (IOException e) {
            // Log the exception
            System.err.println("Error writing to file: " + e.getMessage());
            // Optionally rethrow or handle differently
            throw new RuntimeException("Failed to write to file", e);
        }
    }
}
```

## Modern File I/O with Java NIO

Java NIO (New I/O) provides more modern and flexible ways to handle files:

```java
import java.nio.file.*;
import java.io.IOException;

public class ModernFileIO {
    public static void main(String[] args) {
        Path path = Paths.get("example.txt");
        
        // Writing
        try {
            Files.write(path, 
                       "Hello, Modern File I/O!".getBytes(), 
                       StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        // Reading
        try {
            String content = Files.readString(path);
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

## Working with Binary Files

When working with binary files, use FileInputStream and FileOutputStream:

```java
import java.io.*;

public class BinaryFileExample {
    public static void copyFile(String source, String destination) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[1024];
            int length;
            
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
}
```

Remember that File I/O operations are relatively slow compared to memory operations. Always consider buffering for better performance, and handle resources properly to avoid memory leaks and file system issues.

<aside>
💡 Pro Tip: For modern applications, consider using the Java NIO.2 API (java.nio.file package) as it provides more features and better performance compared to the traditional java.io package.

</aside>