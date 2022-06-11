package pro.sky.map_employee_hw8.services;

import org.apache.commons.lang3.StringUtils;
import pro.sky.map_employee_hw8.exceptions.InvalidInputException;

public class CheckRightWritingOfName {
    public void isRightWriting(String name) {
        // --- проверяем на наличие только букв алфавита --------------
        boolean isAlpha = StringUtils.isAlpha(name);
        // =====   если в имени есть символы, кроме букв, вызываем ошибку 400 Bad Request =======
        if (!isAlpha) {
            throw new InvalidInputException();
        }
        // (1) ====== принудительный перевод всех символов в нижний регистр =======
        // (2) ====== и перевод первой буквы в Заглавный регистр =======
        // ===== осуществляется непосредственно в конструкторе объекта Employee
        // ========        ШИКАРНО !!! === (спасибо Кириллу Качалову :)) =======
    }
}
