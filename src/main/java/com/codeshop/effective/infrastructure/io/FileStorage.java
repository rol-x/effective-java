package com.codeshop.effective.infrastructure.io;

import com.codeshop.effective.domain.model.Cat;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

@Service
public class FileStorage {
    private static final String RESOURCES_PATH = "src/main/resources/";

    public void save(Map<UUID, Cat> cats, String fileName) {
        var filePath = RESOURCES_PATH + fileName;
        try (FileOutputStream fileOut = new FileOutputStream(filePath);     // 9. Try-with-resources zamiast try-finally
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            var dataToSave = new HashMap<>(cats);
            out.writeObject(dataToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<UUID, Cat> load(String fileName) {
        var filePath = RESOURCES_PATH + fileName;
        try (FileInputStream fileIn = new FileInputStream(filePath);        // 8. Bez finalizatorów i oczyszczaczy
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Map<UUID, Cat>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new WeakHashMap<>();
        }
    }
}
