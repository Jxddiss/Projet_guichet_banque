module org.example.projet_guichet_banque {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projet_guichet_banque to javafx.fxml;
    exports org.example.projet_guichet_banque;
}