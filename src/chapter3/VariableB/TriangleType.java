package chapter3.VariableB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriangleType {
    // Список треугольников для обработки
    List<Triangle> triangleList = null;

    // Подсчет треугольников разных типов
    Map<String, List<Triangle>> triangleTypeMap = null;

    // Карта для хранения минимальных и максимальных значений площади и периметра
    Map<String, Map<String, Triangle>> minMaxTrianglesMap = null;

    public TriangleType(TriangleArray triangleArray) {
        triangleList = triangleArray.getTriangleList();
        triangleTypeMap = new HashMap<>();
        sortByType();
        minMaxTrianglesMap = new HashMap<>();
        defineMaxMinPerimeterAndArea();
    }

    public Map<String, Map<String, Triangle>> getMinMaxTrianglesMap() {
        return minMaxTrianglesMap;
    }

    private void sortByType() {
        if (triangleList != null) {
            for (Triangle triangle : triangleList) {
                String type = triangle.triangleType();
                triangleTypeMap.computeIfAbsent(type, k -> new ArrayList<>()).add(triangle);
            }
        }
    }

    // определение наибольшего и наименьшего по площади и периметру
    private void defineMaxMinPerimeterAndArea() {
        for (Map.Entry<String, List<Triangle>> entry : triangleTypeMap.entrySet()) {
            String type = entry.getKey();
            List<Triangle> typeTriangles = entry.getValue();

            Triangle minAreaTriangle = typeTriangles.getFirst();
            Triangle maxAreaTriangle = typeTriangles.getFirst();
            Triangle minPerimeterTriangle = typeTriangles.getFirst();
            Triangle maxPerimeterTriangle = typeTriangles.getFirst();

            for (Triangle triangle : typeTriangles) {
                if (triangle.area() < minAreaTriangle.area()) {
                    minAreaTriangle = triangle;
                }
                if (triangle.area() > maxAreaTriangle.area()) {
                    maxAreaTriangle = triangle;
                }
                if (triangle.perimeter() < minPerimeterTriangle.perimeter()) {
                    minPerimeterTriangle = triangle;
                }
                if (triangle.perimeter() > maxPerimeterTriangle.perimeter()) {
                    maxPerimeterTriangle = triangle;
                }
            }
            Map<String, Triangle> minMaxMap = new HashMap<>();
            minMaxMap.put("Минимальная площадь", minAreaTriangle);
            minMaxMap.put("Максимальная площадь", maxAreaTriangle);
            minMaxMap.put("Минимальный периметр", minPerimeterTriangle);
            minMaxMap.put("Максимальный периметр", maxPerimeterTriangle);

            minMaxTrianglesMap.put(type, minMaxMap);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (minMaxTrianglesMap != null) {
            for (Map.Entry<String, Map<String, Triangle>> entry : minMaxTrianglesMap.entrySet()) {
                String type = entry.getKey();
                str.append("Тип: ").append(type).append("\n");

                // Получаем количество треугольников данного типа
                int count = triangleTypeMap.containsKey(type) ? triangleTypeMap.get(type).size() : 0;
                str.append("Общее количество треугольников данного типа:").append(count).append("\n");

                Map<String, Triangle> minMaxMap = entry.getValue();
                for (Map.Entry<String, Triangle> minMaxEntry : minMaxMap.entrySet()) {
                    str.append(minMaxEntry.getKey()).append(": ").append(minMaxEntry.getValue().toString()).append("\n");
                }
                str.append("\n");
            }
        }
        return str.toString();
    }
}
