package markciurea.model.language;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Setter
@Getter
@NoArgsConstructor
public class Language {

    private static Language singleton = null;

    private List<Observer> observers = new ArrayList<>();
    private Locale locale = Locale.getDefault();
    private ArrayList<Locale> locales = new ArrayList<>() {
        {
            add(Locale.getDefault());
            add(Locale.forLanguageTag("ro-RO"));
            add(Locale.ITALY);
        }
    };
    private ResourceBundle rb = ResourceBundle.getBundle("viewLanguage", locale);

    public static Language getInstance() {
        if (singleton == null) {
            singleton = new Language(LANGUAGES.ENGLISH);
        }
        return singleton;
    }

    private Language(LANGUAGES language) {
        rb = ResourceBundle.getBundle("viewLanguage", locales.get(language.ordinal()));
        notifyObservers();
    }

    public LANGUAGES getLanguage() {
        if (locale.equals(Locale.getDefault()))
            return LANGUAGES.ENGLISH;
        if (locale.equals(Locale.ITALY))
            return LANGUAGES.ITALIAN;
        if (locale.equals(Locale.forLanguageTag("ro-RO")))
            return LANGUAGES.ROMANIAN;
        return LANGUAGES.ENGLISH;
    }

    public void setCurrentLanguage(LANGUAGES language) {
        rb = ResourceBundle.getBundle("viewLanguage", locales.get(language.ordinal()));
        locale = locales.get(language.ordinal());
        notifyObservers();
    }

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
