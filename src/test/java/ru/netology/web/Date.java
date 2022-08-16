package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Date {
    public String dateAfterDays(int afterdays) {
        //Возвращает дату с текущего момента через заданное в параметрах количество дней
        String date = LocalDate.now().plusDays(afterdays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return (date);
    }

    public String dayAfterDays(int afterdays) {
        Calendar instance = Calendar.getInstance();
        //Возвращает день с текущего момента через заданное в параметрах количество дней
        String date = LocalDate.now().plusDays(afterdays).format(DateTimeFormatter.ofPattern("d"));
        return (date);
    }

    public boolean isNextYear(int afterdays) {
        //Проверяет, что выбран следующий год
        String date = LocalDate.now().plusDays(afterdays).format(DateTimeFormatter.ofPattern("yyyy"));
        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        boolean flag = false;
        if (!date.equals(dateNow)) {
            flag = true;
        }
        return (flag);
    }

    public boolean isNextMounts(int afterdays) {
        //Проверяет, что выбран следующий месяц
        String date = LocalDate.now().plusDays(afterdays).format(DateTimeFormatter.ofPattern("MM"));
        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        boolean flag = false;
        if (!date.equals(dateNow)) {
            flag = true;
        }
        return (flag);
    }
}
