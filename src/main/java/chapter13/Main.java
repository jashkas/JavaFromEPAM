package chapter13;

import chapter13.entity.Parameter;
import chapter13.entity.Product;
import chapter13.entity.ProductParameter;
import chapter13.repository.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Вывести перечень параметров для заданной группы продукции.
        int productGroupId = 2;  // группа продукции с id 2
        String nameProductGroup = ProductGroupRepository.getById(productGroupId).getName();  // название группы
        System.out.println("1. Перечень параметров для заданной группы продукции: " + nameProductGroup);
        List<Parameter> parameters1 = ParameterRepository.getParametersByProductGroupId(productGroupId);
        for (Parameter parameter : parameters1) {
            System.out.println(parameter);
        }

        // Вывести перечень продукции, не содержащий заданного параметра.
        System.out.println("2. Перечень продукции, не содержащий заданного параметра");
        Parameter choosenParameter = ParameterRepository.getById(2);  // выбран параметр с id 2
        List<Product> productWithout = ProductRepository.getWithoutParameter(choosenParameter.getId());
        for (Product product : productWithout) {
            System.out.println(product);
        }


        // Вывести информацию о продукции для заданной группы.
        System.out.println("3. Информация о продукции для заданной группы");

        // Вывести информацию о продукции и всех ее параметрах со значениями.
        System.out.println("4. информация о продукции и всех ее параметрах со значениями.");

        // Удалить из базы продукцию, содержащую заданные параметры.
        System.out.println("5. Удаление из базы продукции, содержащей заданные параметры.");

        // Переместить группу параметров из одной группы товаров в другую.
        System.out.println("6. Переместить группу параметров из одной группы товаров в другую.");

    }
}
