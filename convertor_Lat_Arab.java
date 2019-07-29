    /*
       Класс RomanNumeral 
       - целый от 1 и до 3999.  I
    */
    
    public class RomanNumeral {
    
       private final int num;   
       
       private static int[]    numbers = { 1000,  900,  500,  400,  100,   90,  
                                             50,   40,   10,    9,    5,    4,    1 };
                                          
       private static String[] letters = { "M",  "CM",  "D",  "CD", "C",  "XC",
                                           "L",  "XL",  "X",  "IX", "V",  "IV", "I" };
          
    
       public RomanNumeral(int arabic) {
         
          if (arabic < 1)
             throw new NumberFormatException("Значение - RomanNumeral должно быть 3999 или меньше.");
          if (arabic > 3999)
             throw new NumberFormatException("Значение - RomanNumeral должно быть 3999 или меньше.");
          num = arabic;
       }
       
    
       public RomanNumeral(String roman) {
             // Конструктор.  Создание римских цифр в даннром предствалении.
             // Так, RomanNumeral("xvi") это 16.  В случае ошибки в набое римских цифр
             // NumberFormatException - сбрасывется.  Оба регистра
             // верхний и нижний можно набирать.
             
          if (roman.length() == 0)
             throw new NumberFormatException("Пустая строка - это не римские цифры.");
             
          roman = roman.toUpperCase();  // Преобразование верхнего регистра.
          
          int i = 0;       // Место в строке римских цифр;
          int arabic = 0;  // Арабский цифровой эквивалент,
                           //   который уже был преобразован.
          
          while (i < roman.length()) {
          
             char letter = roman.charAt(i);        // Следующая буква в строке.
             int number = letterToNumber(letter);  // Цифровой эвивалент буквы.
             
             if (number < 0)
                throw new NumberFormatException("Не верный символ \"" + letter + "\" в римском числе.");
                
             i++;  // Переместиться на следующую позицию в сторке
             
             if (i == roman.length()) {
                  
                arabic += number;
             }
             else {
                   // Просматривает следующую букву в строке.  В случае когда набранные буквы длиннее
                   // то они группируются на латинские цифры соответствующего разряда с помощью nextNumber - number).
                int nextNumber = letterToNumber(roman.charAt(i));
                if (nextNumber > number) {
                      // Объединение двух букв в одну цифру в строке.
                   arabic += (nextNumber - number);
                   i++;
                }
                else {
                      // Не совмещайте набор букв и цифр!.
                   arabic += number;
                }
             }
             
          }  // окончание while
          
          if (arabic > 3999)
             throw new NumberFormatException("Римские цифры должны быть не более чем 3999 или меньше.");
             
          num = arabic;
          
       } // окончание constructor
       
    
       private int letterToNumber(char letter) {
             // Поиск целого цисла в римских цифрах.  Установление значения
             // -1 если введенные буквы не римские цифры.  Буквы могут быть набраны в верхнем регистре
          switch (letter) {
             case 'I':  return 1;
             case 'V':  return 5;
             case 'X':  return 10;
             case 'L':  return 50;
             case 'C':  return 100;
             case 'D':  return 500;
             case 'M':  return 1000;
             default:   return -1;
          }
       }
       
    
       public String toString() {
             // Возврат к стандартному отображению римских цифр.
          String roman = "";  // Римские цифры.
          int N = num;        

          for (int i = 0; i < numbers.length; i++) {
             while (N >= numbers[i]) {
                roman += letters[i];
                N -= numbers[i];
             }
          }
          return roman;
       }
       
     
       public int toInt() {
            // возврат значений римских цифр в int.
          return num;
       }
     
       
    } // окончание class RomanNumeral

The main program class:

    /* Программа конвертирует римские в арабские цифры и наоборот.
    При вводе пустой строки  - работа завершается.
    */
    
    public class RomanConverter {
    
       public static void main(String[] args) {
          
          TextIO.putln("Введите римские цифры и они будут сконвертированы");
          TextIO.putln("Введите целые арабские цифры от 1 до 3999.");
          TextIO.putln("Они будут сконвертированы.  Нажмите ENTER");
          TextIO.putln("когда нужно выйти.");
          
          while (true) {
    
             TextIO.putln();
             TextIO.put("? ");
             
             /* Работа с пробелами. Прекращения вычисления в цикле при отсутствии данных */
             
             while (TextIO.peek() == ' ' || TextIO.peek() == '\t')
                TextIO.getAnyChar();
             if ( TextIO.peek() == '\n' )
                break;
                
             /* Чтение римских цифр их перевод в арбские */
                
             if ( Character.isDigit(TextIO.peek()) ) {
                int arabic = TextIO.getlnInt();
                try {
                    RomanNumeral N = new RomanNumeral(arabic);
                    TextIO.putln(N.toInt() + " = " + N.toString());
                }
                catch (NumberFormatException e) {
                    TextIO.putln("Ошибка ввода.");
                    TextIO.putln(e.getMessage());
                }
             }
             else {
                String roman = TextIO.getln();
                try {
                    RomanNumeral N = new RomanNumeral(roman);
                    TextIO.putln(N.toString() + " = " + N.toInt());
                }
                catch (NumberFormatException e) {
                    TextIO.putln("Ошибка ввода.");
                    TextIO.putln(e.getMessage());
                }
             }
    
          }  // конец while
          
          TextIO.putln("Конвертер закончил работу");
    
       }  // конец main()
       
    } // конец class RomanConverter
