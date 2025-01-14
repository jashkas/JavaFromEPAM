package chapter13;

import chapter13.entity.*;
import chapter13.repository.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) throws SQLException {
        //
        // Вывести перечень параметров для заданной группы продукции.
        //
        int productGroupId = 2;  // группа продукции с id 2
        String nameProductGroup = ProductGroupRepository.getById(productGroupId).getName();  // название группы
        System.out.println("1. Перечень параметров для заданной группы продукции: " + nameProductGroup);
        List<Parameter> parameters1 = ParameterRepository.getParametersByProductGroupId(productGroupId);
        for (Parameter parameter : parameters1) {
            System.out.println(parameter);
        }
        //
        // Вывести перечень продукции, не содержащий заданного параметра.
        //
        Parameter choosenParameter = ParameterRepository.getById(2);  // выбран параметр с id 2
        System.out.println("\n2. Перечень продукции, не содержащий заданного параметра: " + choosenParameter.getName());
        List<Product> productWithout = ProductRepository.getWithoutParameter(choosenParameter.getId());
        ProductRepository.show(productWithout);
        //
        // Вывести информацию о продукции для заданной группы.
        //
        System.out.println("\n3. Информация о продукции для заданной группы");
        List<Product> productsByGroup = ProductRepository.getByProductGroup(1);
        ProductRepository.show(productsByGroup);
        //
        // Вывести информацию о продукции и всех ее параметрах со значениями.
        //
        System.out.println("\n4. информация о продукции и всех ее параметрах со значениями.");
        List<Product> allProducts = ProductRepository.getAll();
        ProductRepository.show(allProducts);
        //
        // Удалить из базы продукцию, содержащую заданные параметры.
        //
        System.out.println("\n5. Список продукции ДО удаления");
        List<Parameter> parameters = List.of(
                new Parameter[]{
                        ParameterRepository.getById(71),  // Громкость дБ
                        ParameterRepository.getById(73)  // Плотность пикселей unit:ppi
                });  // список заданных параметров

        createProductsForDelete(parameters);  // "Создание" продукции для удаления (в качестве демонстрации)
        ProductRepository.show(ProductRepository.getAll());  // Вывод продукции ДО
        System.out.println("\n5. Удаление из базы продукции, содержащей заданные параметры: " + parameters.get(0).getName() + ", " + parameters.get(1).getName());
        ProductRepository.deleteByParameters(parameters);
        ProductRepository.show(ProductRepository.getAll());  // Вывод продукции ПОСЛЕ
        //
        // Переместить группу параметров из одной группы товаров в другую.
        //
        System.out.println("\n6. Группа параметров ДО");
        List<ParameterGroup> parametersGroup = List.of(
                new ParameterGroup[]{
                        ParameterGroupRepository.getById(9),
                        ParameterGroupRepository.getById(10)
                });  // список группы параметров
        ParameterGroupRepository.show(parametersGroup);  // до
        int changedProductGroupId = 8;  // Выбранная группа товаров
        System.out.println("\n6. Переместить группу параметров из одной группы товаров в другую (" + changedProductGroupId + ")");
        ParameterGroupRepository.updateProductGroup(parametersGroup, changedProductGroupId);
        ParameterGroupRepository.show(List.of(new ParameterGroup[]{ParameterGroupRepository.getById(9), ParameterGroupRepository.getById(10)}));  // после
    }

    public static void createProductsForDelete(List<Parameter> parameters) throws SQLException {
        // "Создание" продукции
        Instant instant = Instant.parse("2024-12-25T14:27:32.728500600Z");
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        Product[] products = new Product[]{
                new Product("Колонки Aceline ASP100",
                        "Вполне достаточно для пользователей, которые не любят слишком громкий звук и привыкли использовать акустику в качестве фона во время работы.",
                        localDate,
                        2),
                new Product("Колонки DEXP R800",
                        "Простота, надежность и миниатюрные размеры – главные преимущества этого устройства.",
                        localDate,
                        2),
                new Product("Проводные наушники JBL QUANTUM 200",
                        "Благодаря эргономичному оголовью и амбушюрам из экокожи с эффектом памяти обеспечивается высокий уровень комфорта даже при длительных игровых сеансах.",
                        localDate,
                        2),
                new Product("Монитор DEXP DF22N2S",
                        "В ее конструкции предусмотрена регулировка положения за счет изменения угла наклона.",
                        localDate,
                        2),
                new Product("Монитор LG 24MS500-B",
                        "Трехсторонний безрамочный дизайн увеличивает полезную площадь экрана и делает удобной работу с несколькими установленными в ряд мониторами.",
                        localDate,
                        2)
        };
        for (Product product : products) {
            ProductRepository.create(product);
        }
        // Связь продукции с параметром
        ProductParameter[] productParameters = new ProductParameter[]{
                new ProductParameter(products[0].getId(), parameters.get(0).getId(), "30"),
                new ProductParameter(products[1].getId(), parameters.get(0).getId(), "60"),
                new ProductParameter(products[2].getId(), parameters.get(0).getId(), "100"),

                new ProductParameter(products[3].getId(), parameters.get(1).getId(), "103"),
                new ProductParameter(products[4].getId(), parameters.get(1).getId(), "93")
        };
        for (ProductParameter productParameter : productParameters) {
            ProductParameterRepository.create(productParameter);
        }
    }

}
