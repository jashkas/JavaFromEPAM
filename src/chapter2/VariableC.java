package chapter2;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class VariableC {

    private int[][] matrix;
    private double[][] matrixDouble;
    private int n;

    public VariableC(int n) {
        this.n = n;
        this.matrix = new int[n][n];
        this.matrixDouble = new double[n][n];
        fillMatrix();
    }

    private void fillMatrix() {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n; // значения от -n до n
                matrixDouble[i][j] = (double)matrix[i][j] + random.nextDouble();
            }
        }
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public void printMatrixDouble() {
        for (double[] row : matrixDouble) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    // 1. Упорядочить строки (столбцы) матрицы в порядке возрастания значений
    // элементов k-го столбца (строки).
    public void sortRowsByColumn(int k) {
        System.out.println("1. Строки упорядочены по столбцу " + (k+1));
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (matrix[j][k] > matrix[j + 1][k]) {
                    // Обмен строк
                    int[] temp = matrix[j];
                    matrix[j] = matrix[j + 1];
                    matrix[j + 1] = temp;
                }
            }
        }
    }

    // 2. Выполнить циклический сдвиг заданной матрицы на k позиций вправо
    // (влево, вверх, вниз).
    public void cyclicShift(int k, String direction) {
        if (k < 0 || k >= n) {
            throw new IllegalArgumentException("Индекс k выходит за пределы матрицы.");
        }

        int[][] newMatrix = new int[n][n];

        switch (direction.toLowerCase()) {
            case "вправо":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        newMatrix[i][(j + k) % n] = matrix[i][j];
                    }
                }
                break;
            case "влево":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        newMatrix[i][(j - k + n) % n] = matrix[i][j];
                    }
                }
                break;
            case "вверх":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        newMatrix[(i - k + n) % n][j] = matrix[i][j];
                    }
                }
                break;
            case "вниз":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        newMatrix[(i + k) % n][j] = matrix[i][j];
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Некорректное направление смещения. Ожидалось 'вправо', 'влево', 'вверх' или 'вниз'.");
        }

        matrix = newMatrix; // Обновляем текущую матрицу
    }

    // 3. Найти и вывести наибольшее число возрастающих\убывающих элементов
    // матрицы, идущих подряд.
    public int longestMonotonicSequence(boolean increasing) {
        System.out.print("3. ");
        int maxLength = 0;
        for (int[] row : matrix) {
            int currentLength = 1, previous = row[0];
            for (int j = 1; j < n; j++) {
                if ((increasing && row[j] > previous) || (!increasing && row[j] < previous)) {
                    currentLength++;
                } else {
                    maxLength = Math.max(maxLength, currentLength);
                    currentLength = 1;
                }
                previous = row[j];
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    // 4. Найти сумму элементов матрицы, расположенных между первым и вторым
    // положительными элементами каждой строки.
    public void sumBetweenPositives() {
        int row_ind = 0;
        for (int[] row : matrix) {
            row_ind += 1;
            int firstPos = -1, secondPos = -1;
            for (int j = 0; j < n; j++) {
                if (row[j] > 0) {
                    if (firstPos == -1) {
                        firstPos = j;
                    } else if (secondPos == -1) {
                        secondPos = j;
                        break;
                    }
                }
            }
            if (firstPos != -1 && secondPos != -1) {
                int sum = 0;
                for (int j = firstPos + 1; j < secondPos; j++) {
                    sum += row[j];
                }
                System.out.println("4. Сумма между положительными элементами в " + row_ind + " строке: " + sum);
            }
        }
    }

    // 5. Вывести числа от 1 до k в виде матрицы N x N слева направо и сверху вниз
    public void printNumbersInMatrix(int k) {
        System.out.println("5");
        int n = (int) Math.sqrt(k);
        int[][] new_matrix = new int[n][n];
        int a = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a++;
                new_matrix[i][j] = a;
            }
        }
        for (int[] row : new_matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.print("\n");
    }

    // 6. Округлить все элементы матрицы до целого числа.
    public void roundMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixDouble[i][j] = (int)Math.round(matrixDouble[i][j]);
            }
        }
    }

    // 7. Повернуть матрицу на 90, 180 или 270 градусов против часовой стрелки
    public void rotateMatrix(int degrees) {
        int count = (degrees / 90) % 4; // Количество поворотов на 90 градусов
        for (int r = 0; r < count; r++) {
            int[][] newMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newMatrix[j][n - 1 - i] = matrix[i][j]; // Поворот против часовой стрелки
                }
            }
            matrix = newMatrix;
        }
    }

    // 8. Вычислить определитель матрицы
    public double determinant(int[][] matrix) {
        int size = matrix.length;
        if (size == 1) return matrix[0][0];
        if (size == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        double result = 0;
        for (int f = 0; f < size; f++) {
            result += Math.pow(-1, f) * matrix[0][f] * determinant(minor(matrix, 0, f));
        }
        return result;
    }

    private int[][] minor(int[][] matrix, int row, int col) {
        int size = matrix.length;
        int[][] minorMatrix = new int[size - 1][size - 1];
        for (int i = 0, mi = 0; i < size; i++) {
            for (int j = 0, mj = 0; j < size; j++) {
                if (i != row && j != col) {
                    minorMatrix[mi][mj++] = matrix[i][j];
                    if (mj == size - 1) {
                        mj = 0;
                        mi++;
                    }
                }
            }
        }
        return minorMatrix;
    }

    // 9. Построить матрицу, вычитая из элементов каждой строки матрицы ее среднее арифметическое.
    public double subtractRowMeans() {
        double mean = 0.0;
        for (int i = 0; i < n; i++) {
            // сумма элементов строки
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
            // среднее арифметическое
            mean = sum / n;
            for (int j = 0; j < n; j++) {
                matrix[i][j] -= (int) Math.round(mean); // вычитание округленного среднего у элементов
            }
        }
        return mean;
    }

    // 10.  Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки
    // и столбцы, его содержащие.
    public void removeMaxElementRowsAndColumns() {
        System.out.println("10. Удаление строк и столбцов с максимальным элементом");
        // нахождение максимального элемента
        int max = -n - 2;  // диапазон -n до +n
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        System.out.println("Максимальный элемент = " + max);
        // определение местоположения в матрице максимальных элементов
        boolean[] removeRows = new boolean[n];
        boolean[] removeCols = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == max) {
                    removeRows[i] = true;
                    removeCols[j] = true;
                }
            }
        }
        // расчет новых размеров матрицы
        int newRowCount = 0;  // количество строк с максимальным значением
        for (boolean remove : removeRows) {
            if (!remove) {
                newRowCount++;
            }
        }

        int newColCount = 0;  // количество столбцов с максимальным значением
        for (boolean remove : removeCols) {
            if (!remove) {
                newColCount++;
            }
        }
        // запись новой матрицы
        int[][] newMatrix = new int[newRowCount][newColCount];
        for (int i = 0, newRow = 0; i < n; i++) {
            if (!removeRows[i]) {
                // пропуск удаляемой строки
                for (int j = 0, newCol = 0; j < n; j++) {
                    if (!removeCols[j]) {
                        // пропуск удаляемого столбца
                        newMatrix[newRow][newCol++] = matrix[i][j];
                    }
                }
                newRow++;
            }
        }
        // матрица остается неизменна, преобразованная матрица выводится
        for (int[] row : newMatrix) {
            System.out.println(Arrays.toString(row));
        }
        //matrix = newMatrix;
        //n = matrix.length;
    }

    // 11. Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями
    public void compactMatrix() {
        System.out.println("\n11. Удаление строк и столбцов, заполненных нулями");
        System.out.println("Исходная матрица:");
        printMatrix();
        boolean[] rowsToDelete = new boolean[n];  // какие строки нужно удалить
        boolean[] colsToDelete = new boolean[n];  // какие столбцы нужно удалить

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowsToDelete[i] = true;
                    colsToDelete[j] = true;
                    break;
                }
            }
        }

        // количество строк и столбцов, которые не нужно удалять
        int newRowCount = 0;
        for (int i = 0; i < n; i++) {
            if (!rowsToDelete[i]) newRowCount++;
        }

        int newColCount = 0;
        for (int j = 0; j < n; j++) {
            if (!colsToDelete[j]) newColCount++;
        }

        // новая матрица
        int[][] finalMatrix = new int[newRowCount][newColCount];
        int newRow = 0;

        // копирование ненулевых строк в новую матрицу
        for (int i = 0; i < n; i++) {
            if (!rowsToDelete[i]) {
                int newCol = 0;
                // копирование ненулевых столбцов
                for (int j = 0; j < n; j++) {
                    if (!colsToDelete[j]) {
                        finalMatrix[newRow][newCol] = matrix[i][j];
                        newCol++;
                    }
                }
                newRow++;
            }
        }
        System.out.println("Матрица без нулей");
        // матрица остается неизменна, преобразованная матрица выводится
        for (int[] row : finalMatrix) {
            System.out.println(Arrays.toString(row));
        }
        //this.matrix = finalMatrix;
    }

    // 12. В матрице найти минимальный элемент и переместить его на место заданного
    // элемента путем перестановки строк и столбцов.
    public void moveMinElementToPosition(int targetRow, int targetCol) {
        int min = n+n; // максимальный элемент не будет больше n*2 после преобразований
        int minRow = -1, minCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }
        System.out.println("Исходная матрица:");
        printMatrix();
        System.out.println("Минимальный элемент " + min + " на позиции (" + minRow + " , " + minCol + ")");
        while(minRow != targetRow || minCol != targetCol) {
            if (minRow > targetRow) {
                cyclicShift(1, "вверх");
                minRow--;
            } else if (minRow < targetRow) {
                cyclicShift(1, "вниз");
                minRow++;
            }
            if (minCol > targetCol) {
                cyclicShift(1, "влево");
                minCol--;
            } else if (minCol < targetCol) {
                cyclicShift(1, "вправо");
                minCol++;
            }
        }
    }

    // 13. Преобразовать строки матрицы таким образом, чтобы элементы, равные
    // нулю, располагались после всех остальных.
    public void moveZerosToEnd() {
        for (int i = 0; i < n; i++) {
            int count = 0; // счётчик ненулевых элементов
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][count++] = matrix[i][j]; // перемещаем ненулевые в начало
                }
            }
            for (int j = count; j < n; j++) {
                matrix[i][j] = 0; // заполняем оставшиеся нулями
            }
        }
    }

    // 14. Найти количество всех седловых точек матрицы (матрица А имеет седловую
    // точку Аi, j, если Аi, j является минимальным элементом в i-й строке
    // и максимальным в j-м столбце).
    public int countSaddlePoints() {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            // Находим минимальный элемент в текущей строке
            int minInRow = Integer.MAX_VALUE;
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] < minInRow) {
                    minInRow = matrix[i][j];
                }
            }

            // Проверка является ли минимальный элемент максимальным в своем столбце
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == minInRow) {
                    boolean isSaddlePoint = true;

                    // Проверка максимален ли этот элемент в столбце j
                    for (int k = 0; k < rows; k++) {
                        if (matrix[k][j] > minInRow) {
                            isSaddlePoint = false;
                            break; // Выход из внутреннего цикла, если встречается большее значение
                        }
                    }
                    if (isSaddlePoint) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // 15. Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов
    // в строках полученной матрицы возрастала.
    public void sortRowsBySum() {
        System.out.println("Изначальная матрица");
        // подсчет суммы в строках
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = 0;
            for (int j = 0; j < n; j++) {
                sum[i] += matrix[i][j];
            }
            System.out.println(Arrays.toString(matrix[i]) + " = " + sum[i]);
        }
        // шейкерная сортировка
        boolean swapped;
        int start = 0;
        int end = sum.length - 1;

        do {
            swapped = false;

            // Сортировка слева направо
            for (int i = start; i < end; i++) {
                if (sum[i] > sum[i + 1]) {
                    // Меняем местами элементы в sum
                    int temp = sum[i];
                    sum[i] = sum[i + 1];
                    sum[i + 1] = temp;
                    // Меняем местами строки матрицы
                    int[] temp2 = matrix[i];
                    matrix[i] = matrix[i + 1];
                    matrix[i + 1] = temp2;

                    swapped = true;
                }
            }
            // Уменьшаем конечный индекс, так как последний элемент уже отсортирован
            end--;

            // Если не было перестановок, массив уже отсортирован
            if (!swapped) {
                break;
            }

            swapped = false;

            // Сортировка справа налево
            for (int i = end; i > start; i--) {
                if (sum[i] < sum[i - 1]) {
                    // Меняем местами элементы в sum
                    int temp = sum[i];
                    sum[i] = sum[i - 1];
                    sum[i - 1] = temp;
                    // Меняем местами строки матрицы
                    int[] temp2 = matrix[i];
                    matrix[i] = matrix[i - 1];
                    matrix[i - 1] = temp2;

                    swapped = true;
                }
            }
            // Увеличиваем начальный индекс, так как первый элемент уже отсортирован
            start++;

        } while (swapped);
        // вывод
        System.out.print("\n");
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(matrix[i]) + " = " + sum[i]);
        }
        //Arrays.sort(matrix, (a, b) -> Integer.compare(Arrays.stream(a).sum(), Arrays.stream(b).sum()));
    }

    // 16. Найти число локальных минимумов. Соседями элемента матрицы назовем
    // элементы, имеющие с ним общую сторону или угол. Элемент матрицы называется
    // локальным минимумом, если он строго меньше всех своих соседей.
    public int countLocalMinima() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isLocalMin = true;
                for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, n - 1); x++) {
                    for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                        if (matrix[x][y] <= matrix[i][j] && (x != i || y != j)) {
                            isLocalMin = false;
                        }
                    }
                }
                if (isLocalMin) count++;
            }
        }
        return count;
    }

    // 17. Найти наименьший среди локальных максимумов. Элемент матрицы называется
    // локальным минимумом, если он строго меньше всех своих соседей.
    public Integer minLocalMaxima() {
        Integer minLocalMax = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isLocalMax = true;
                for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, n - 1); x++) {
                    for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                        if (matrix[x][y] >= matrix[i][j] && (x != i || y != j)) {
                            isLocalMax = false;
                        }
                    }
                }
                if (isLocalMax) {
                    if (minLocalMax == null || matrix[i][j] < minLocalMax) {
                        minLocalMax = matrix[i][j];
                    }
                }
            }
        }
        return minLocalMax;
    }

    // 18. Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы
    // значения их характеристик убывали. Характеристикой столбца прямоугольной
    // матрицы называется сумма модулей его элементов.
    public void sortColumnsBySumOfAbsoluteValues() {
        Integer[] colSums = new Integer[n];
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.abs(matrix[i][j]);
            }
            colSums[j] = sum;
        }

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> colSums[b].compareTo(colSums[a]));

        int[][] newMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrix[i][indices[j]];
            }
        }
        matrix = newMatrix;
    }

    // 19. Путем перестановки элементов квадратной вещественной матрицы добиться
    // того, чтобы ее максимальный элемент находился в левом верхнем углу,
    // следующий по величине — в позиции (2, 2), следующий по величине — в позиции
    // (3, 3) и т.д., заполнив таким образом всю главную диагональ.
    public void arrangeMaxOnDiagonal() {
        int size = n;
        int[] flatMatrix = new int[size * size];
        int index = 0;

        // Создаем одномерный массив и заполняем его элементами матрицы
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                flatMatrix[index++] = matrix[i][j];
            }
        }

        // Сортируем массив в обратном порядке
        Arrays.sort(flatMatrix);
        for (int i = 0; i < size * size / 2; i++) {
            int temp = flatMatrix[i];
            flatMatrix[i] = flatMatrix[size * size - 1 - i];
            flatMatrix[size * size - 1 - i] = temp;
        }

        // Заполняем матрицу, ставя максимальные элементы на главную диагональ
        index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrix[i][j] = flatMatrix[index++];
                } else {
                    matrix[i][j] = flatMatrix[index++];
                }
            }
        }
    }

    // Метод main для вызова всех операций
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Введите размерность матрицы n: ");
        int n = 5; //scanner.nextInt();

        VariableC variableC = new VariableC(n);
        System.out.println("Сгенерированная матрица");
        variableC.printMatrix();

        // вызов методов
        int numSortedColumn = 1;
        variableC.sortRowsByColumn(numSortedColumn); // Упорядочить строки по 2-му столбцу
        variableC.printMatrix();

        System.out.println("2. Циклический сдвиг вправо на 2 позиции");
        variableC.cyclicShift(2, "вправо"); // Циклический сдвиг вправо на 2 позиции
        variableC.printMatrix();
        System.out.println("2. Циклический сдвиг влево на 2 позиции");
        variableC.cyclicShift(2, "влево"); // Циклический сдвиг вправо на 2 позиции
        variableC.printMatrix();
        System.out.println("2. Циклический сдвиг вверх на 2 позиции");
        variableC.cyclicShift(2, "вверх"); // Циклический сдвиг вправо на 2 позиции
        variableC.printMatrix();
        System.out.println("2. Циклический сдвиг вниз на 2 позиции");
        variableC.cyclicShift(2, "вниз"); // Циклический сдвиг вправо на 2 позиции
        variableC.printMatrix();

        int longestInc = variableC.longestMonotonicSequence(true);
        System.out.println("Наибольшее количество возрастающих элементов: " + longestInc);

        int longestDec = variableC.longestMonotonicSequence(false);
        System.out.println("Наибольшее количество убывающих элементов: " + longestDec + "\n");

        variableC.sumBetweenPositives();
        System.out.print("\n");

        variableC.printNumbersInMatrix(5); // Пример для k = 5

        System.out.println("6. Матрица вещественных чисел");
        variableC.printMatrixDouble();
        variableC.roundMatrix();
        System.out.println("6. Все элементы округлены до целого числа");
        variableC.printMatrixDouble();

        System.out.println("7.\nИсходная матрица");
        variableC.printMatrix();
        System.out.println("Поворот матрицы на 90");
        variableC.rotateMatrix(90);
        variableC.printMatrix();
        System.out.println("Поворот матрицы на 180");
        variableC.rotateMatrix(180);
        variableC.printMatrix();
        System.out.println("Поворот матрицы на 270");
        variableC.rotateMatrix(270);
        variableC.printMatrix();

        double det = variableC.determinant(variableC.matrix);
        System.out.println("8.Определитель матрицы: " + det + "\n");

        variableC.subtractRowMeans();
        System.out.println("9. Из каждой строки вычтено её среднее арифметическое");
        variableC.printMatrix();

        variableC.removeMaxElementRowsAndColumns();

        variableC.compactMatrix();

        System.out.println("\n12");
        variableC.moveMinElementToPosition(0, 0); // Перемещение минимума в (0, 0)
        System.out.println("Минимальный элемент в место (0, 0)");
        variableC.printMatrix();

        System.out.println("13. Нули в конец строк");
        variableC.moveZerosToEnd();
        variableC.printMatrix();

        int saddlePointsCount = variableC.countSaddlePoints();
        System.out.println("14. Количество седловых точек: " + saddlePointsCount);

        System.out.println("\n15.Cумма элементов в строках возрастает");
        variableC.sortRowsBySum();

        int localMinimaCount = variableC.countLocalMinima();
        System.out.println("\n16. Количество локальных минимумов: " + localMinimaCount);

        Integer minLocalMax = variableC.minLocalMaxima();
        System.out.println("\n17. Наименьший среди локальных максимумов: " + minLocalMax);

        System.out.println("\n18. Перестроить столбцы по убыванию суммы модулей");
        variableC.sortColumnsBySumOfAbsoluteValues();
        variableC.printMatrix();

        System.out.println("19. Переставить элементы, чтобы максимальные были на главной диагонали");
        variableC.arrangeMaxOnDiagonal();
        variableC.printMatrix();

        scanner.close();
        // TODO заменить комментарии заданий на те которые в книге
    }
}