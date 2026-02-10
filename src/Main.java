// =============================================
// Класс Sentence — основной класс для задания
// =============================================
class Sentence {

    private final String originalText;
    private final String[] words;           // массив чистых слов

    // Конструктор
    public Sentence(String text) {
        this.originalText = text != null ? text : "";
        this.words = extractWordsStrict(this.originalText);
    }

    /**
     * Строгий метод выделения слов:
     * слово = последовательность букв и цифр
     * всё остальное (знаки препинания, пробелы и т.д.) — разделители
     */
    private String[] extractWordsStrict(String text) {
        java.util.List<String> result = new java.util.ArrayList<>();

        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Если символ — буква или цифра → добавляем к текущему слову
            if (Character.isLetter(c) || Character.isDigit(c)) {
                currentWord.append(c);
            }
            // Иначе — конец слова
            else {
                if (!currentWord.isEmpty()) {
                    result.add(currentWord.toString());
                    currentWord.setLength(0);   // очищаем
                }
            }
        }

        // Добавляем последнее слово, если оно есть
        if (!currentWord.isEmpty()) {
            result.add(currentWord.toString());
        }

        return result.toArray(new String[0]);
    }

    /**
     * Основной метод задания:
     * Возвращает длину указанного слова или -1, если слово не найдено
     */
    public int getWordLength(String targetWord) {
        if (targetWord == null || targetWord.trim().isEmpty()) {
            return -1;
        }

        String search = targetWord.trim().toLowerCase();

        for (String word : words) {
            if (word.toLowerCase().equals(search)) {
                return word.length();
            }
        }

        return -1; // слово не найдено
    }

    // Вспомогательный метод для отладки
    public void printAllWords() {
        System.out.println("Всего слов в предложении: " + words.length);
        for (int i = 0; i < words.length; i++) {
            System.out.printf("%2d. \"%s\"  (длина = %d)%n",
                    i + 1, words[i], words[i].length());
        }
    }

    // Геттеры
    public String getOriginalText() {
        return originalText;
    }

    public String[] getWords() {
        return words.clone(); // защитная копия
    }
}

// =============================================
// Главный класс с методом main()
// =============================================
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Лабораторная работа: Длина слова в предложении ===\n");

        // ==================== Тест 1 ====================
        String text1 = "Мама, мыла раму. Рама мыла маму!";
        String word1 = "раму";

        Sentence sentence1 = new Sentence(text1);
        int len1 = sentence1.getWordLength(word1);

        System.out.println("Предложение: " + text1);
        System.out.println("Ищем слово: \"" + word1 + "\"");
        if (len1 != -1) {
            System.out.println("→ Длина слова = " + len1 + "\n");
        } else {
            System.out.println("→ Слово не найдено\n");
        }

        // ==================== Тест 2 ====================
        String text2 = "Привет, как дела? Всё хорошо, спасибо! А у тебя?";
        String word2 = "хорошо";

        Sentence sentence2 = new Sentence(text2);
        int len2 = sentence2.getWordLength(word2);

        System.out.println("Предложение: " + text2);
        System.out.println("Ищем слово: \"" + word2 + "\"");
        System.out.println("→ Длина слова = " + len2 + "\n");

        // ==================== Тест 3 (слово не найдено) ====================
        int len3 = sentence2.getWordLength("котик");
        System.out.println("Ищем слово \"котик\" → " +
                (len3 == -1 ? "не найдено" : "длина = " + len3));

        // Дополнительно: вывод всех слов
        System.out.println("\n--- Все слова из второго предложения ---");
        sentence2.printAllWords();
    }
}