import java.io.*;
public class CapsaSusan {
    public static String cards = "";
    public static int[] point = new int[13];
    public static String[] card = new String[13];

    public static String[] set1 = new String[3];
    public static String[] set2 = new String[50];
    public static String[] set3 = new String[5];

    static String[] RoyalFlush = new String[5];
    static String[] StraightFlush = new String[5];
    static String[] FourOfaKind = new String[4];
    static String[] FullHouse = new String[5];
    static String[] Flush = new String[50];
    static String[] ThreeOfaKind = new String[3];
    static String[] TwoPair = new String[4];
    static String[] OnePair = new String[2];


    public static void main(String[] args) {

//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
//            writer.write("Writing to a file.");
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
//            อ่านค่าจากไฟล์ cards.txt แล้วเก็บใว้ในตัวแปล line
            BufferedReader reader = new BufferedReader(new FileReader("cards.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
//                แยก String ออกจากกันและเก็บใว้ในตัวแปร card
                card = line.split(",");
                cards += line;
            }


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cardPoint(card);
        printArr(point);
        cardSort(point, card);
        printArr(point);
        printStrArr(card);

//        System.out.println(isRoyalFlush());
//        System.out.println(isStraightFlush());
//        System.out.println(isFourOfaKind());
//        System.out.println(isFullHouse());
//        System.out.println(isFlush());
//        System.out.println(isThreeOfaKind());
//        System.out.println(isTwoPair());
        System.out.println(isOnePair());

        printStrArr(OnePair);
    }

    public static boolean isRoyalFlush() {
        int n = point.length - 1;
        int idxSet = set3.length - 1;
        int counter = 14;

//       Check is ten, jack, queen, king, ace, are sequential order and all the same suit.
//       ตรวจสอบว่าไพ่เรียงกันตามลำดับ ten, jack, queen, king, ace, และมีดอกเดียวกันหรือไม่
        for (int i = n; i > 0; i--) {
            if (point[i] == counter) {
                if (lcs(card[i], card[n], card[i].length(), card[n].length(), 0) == 2 || lcs(card[i], card[n], card[i].length(), card[n].length(), 0) == 1) {
                    RoyalFlush[idxSet] = card[i];
                    counter--;
                    idxSet--;
                }
            }
        }

//        เช็คว่า Array มี Null มั้ย ถ้ามีแสดงว่าไม่ตรงตามเงื่อนไขของ RoyalFlush ให้ return false
        if (isArrayEmpty(RoyalFlush)) {
            clearArr(RoyalFlush);
            return false;
        }
        return true;
    }

    public static boolean isStraightFlush() { // ไพ่เรียงต่อกัน 5 ใบ และ มีดอกเดียวกัน
        int n = point.length;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (point[i] < point[j] && point[i] == point[j] - 1) {
                    if (lcs(card[i], card[j], card[i].length(), card[j].length(), 0) == 1) {
                        if (counter != 5) {
                            StraightFlush[counter] = card[i];
                            counter++;
                            break;
                        }

                    }
                }
            }
        }

        if (isArrayEmpty(StraightFlush)) {
            clearArr(StraightFlush);
            return false;
        }
        return true;
    }

    public static boolean isFourOfaKind() {
        int n = point.length;
        int counter = 0;

        for (int i = 0; i < n; i++) {
            if (countOccurrences(point, point.length, i) == 4) {

                for (int j = i; j < n; j++) {
                    if (counter != 4) {
                        FourOfaKind[counter] = card[j - 3];
                        counter++;
                    }

                }
            }
        }
        if (isArrayEmpty(FourOfaKind)) {
            clearArr(FourOfaKind);
            return false;
        }
        return true;
    }

    public static boolean isFullHouse() {
        int n = point.length;
        int counter = 0;

        return true;
    }

    public static boolean isFlush() {
        int n = point.length;
        int counter = 0;
        int count = 0;

        String st1 = cards.replaceAll("[^A-Za-z]", "");

        // Converting string into an array for computation
        char arr[] = st1.toCharArray();

        // Nested loops for comparison of characters
        // in above character array

        char temp;

        int i = 0;
        while (i < arr.length) {
            int j = i + 1;
            while (j < arr.length) {
                if (arr[j] < arr[i]) {

                    // Comparing the characters one by one
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                j += 1;
            }
            i += 1;
        }
        System.out.print(arr);

        for(int x = 0; i<n;x++){
            for(int j = x+1; j<n;j++){
                if(arr[x] == arr[j]){
                    count++;
                } else {
                    count = 0;
                }
            }
        }

        System.out.println(count);

        return true;
    }

    public static void isStraight() {
    }

    //    สามารถเก็บลง set1 ได้
    public static boolean isThreeOfaKind() {
        int n = point.length;
        int counter = 0;

        for (int i = 0; i < n; i++) {
            if (countOccurrences(point, point.length, i) == 4) {

                for (int j = i; j < n; j++) {
                    if (counter != 3) {
                        ThreeOfaKind[counter] = card[j - 3];
                        counter++;
                    }

                }
            }
        }
        if (isArrayEmpty(ThreeOfaKind)) {
            clearArr(ThreeOfaKind);
            return false;
        }
        return true;
    }

    public static boolean isTwoPair() {
        int n = point.length;
        int counter = 0;
        for(int i = 0; i <n; i++){
            for(int j = i+1; j<n; j++){
                if(point[i] == point[j] && counter != 4){
                    TwoPair[counter] = card[i];
                    TwoPair[counter+1] = card[j];
                    counter+=2;

                }
            }
        }

        if (isArrayEmpty(TwoPair)) {
            clearArr(TwoPair);
            return false;
        }

        return true;
    }

    //    สามารถเก็บลง set1 ได้
    public static boolean isOnePair() {
        int n = point.length;
        int counter = 0;
        for(int i = 0; i <n; i++){
            for(int j = i+1; j<n; j++){
                if(point[i] == point[j] && counter != 2){
                    OnePair[counter] = card[i];
                    OnePair[counter+1] = card[j];
                    counter+=2;

                }
            }
        }

        if (isArrayEmpty(OnePair)) {
            clearArr(OnePair);
            return false;
        }

        return true;
    }

    //    สามารถเก็บลง set1 ได้
    public static void isHighCard() {
    }

    public static int[] cardPoint(String[] card) {
        String[] diamonds = new String[]{"2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d", "10d", "Jd", "Qd", "Kd", "Ad"};
        String[] clubs = new String[]{"2c", "3c", "4c", "5c", "6c", "7c", "8c", "9c", "10c", "Jc", "Qc", "Kc", "Ac"};
        String[] hearts = new String[]{"2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "10h", "Jh", "Qh", "Kh", "Ah"};
        String[] spades = new String[]{"2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "10s", "Js", "Qs", "Ks", "As"};

//        int[] point = new int[13];
        // assign points into point array
        for (int i = 0; i < card.length; i++) {
            // Check diamonds point
            for (int d = 0; d < diamonds.length; d++) {
                if (card[i].equals(diamonds[d])) {
                    point[i] = d + 2;
                }
            }
            // Check club point
            for (int d = 0; d < clubs.length; d++) {
                if (card[i].equals(clubs[d])) {
                    point[i] = d + 2;
                }
            }
            // Check heart point
            for (int d = 0; d < hearts.length; d++) {
                if (card[i].equals(hearts[d])) {
                    point[i] = d + 2;
                }
            }
            // Check spade point
            for (int d = 0; d < spades.length; d++) {
                if (card[i].equals(spades[d])) {
                    point[i] = d + 2;
                }
            }

        }
        return point;
    } // เช็คไพ่แต่ละใบว่ามีค่าเท่าไหร่ เพื่อจะนำไปจัดลำดับ

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void cardSort(int[] arr1, String[] arr2) {
        int n = arr1.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr1[i] > arr1[j]) {
                    int temp1 = arr1[i];
                    arr1[i] = arr1[j];
                    arr1[j] = temp1;

                    String temp2 = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = temp2;
                }
            }
        }
    } // จัดเรียงลำดับไพ่จากน้อยไปมาก

    public static void printArr(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void printStrArr(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static int lcs(String str1, String str2, int i, int j, int count) {

        if (i == 0 || j == 0) {
            return count;
        }

        if (str1.charAt(i - 1)
                == str2.charAt(j - 1)) {
            count = lcs(str1, str2, i - 1, j - 1, count + 1);
        }
        count = Math.max(count,
                Math.max(lcs(str1, str2, i, j - 1, 0),
                        lcs(str1, str2, i - 1, j, 0)));
        return count;
    }

    public static void clearArr(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = null;
        }
    } // ล้างค่า Array ให้เป็น Null ทั้งหมด

    public static boolean isArrayEmpty(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == null) {
                return true;
            }
        }
        return false;
    } // เช็กว่ามีค่าใน Array มั้ย

    public static int countOccurrences(int arr[], int n, int x) {
        int res = 0;
        for (int i = 0; i < n; i++)
            if (x == arr[i])
                res++;
        return res;
    } // นับจำนวนค่าที่ซ้ำกันใน Array
}
