package com.john.udemy.reactivejava.sec01;

import com.john.udemy.reactivejava.sec01.assignment.FileService;
import com.john.udemy.reactivejava.utils.Utilities;
import jdk.jshell.execution.Util;

public class Lec09AssignmentDemo {

    public static void main(String[] args) {
        FileService.read("file03.txt")
                .subscribe(Utilities.onNext(), Utilities.onError(), Utilities.onComplete());

        FileService.write("file03.txt", "this is file03")
                .subscribe(Utilities.onNext(), Utilities.onError(), Utilities.onComplete());

        FileService.delete("file03.txt")
                .subscribe(Utilities.onNext(), Utilities.onError(), Utilities.onComplete());
    }
}
