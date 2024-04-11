package cz.cvut.fit.niadp.mvcgame.adapter;

import cz.cvut.fit.niadp.mvcgame.model.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LevelLoader {
    public List<Position> LoadLevel(String filePath){
        return readPositionsFromFile(filePath);
    }

    private static List<Position> readPositionsFromFile(String filePath) {
        List<Position> positions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.split("\\s+");
                if (coordinates.length == 2) {
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    positions.add(new Position(x, y));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return positions;
    }
}
