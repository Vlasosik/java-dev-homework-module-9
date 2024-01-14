package org.example;

import org.example.utilException.ImageException;

import java.util.Scanner;

public class HttpImageStatusCli {
    void askStatus() {
        HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter HTTP status code: ");
        while (true) {
            if (scanner.hasNextInt()) {
                int code = scanner.nextInt();
                try {
                    httpStatusImageDownloader.downloadStatusImage(code);
                    System.out.println("Image download successful!");
                    break;
                } catch (ImageException ex) {
                    System.out.println("There is not image for HTTP status: " + code);
                    System.out.println("Please enter the code from 100 to 599 again to receive the image:");
                }
            } else {
                System.out.println("Please enter valid number: ");
                scanner.next();
            }
        }
        scanner.close();
    }
}
